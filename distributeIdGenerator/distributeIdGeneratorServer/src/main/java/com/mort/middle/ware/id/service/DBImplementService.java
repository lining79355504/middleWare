package com.mort.middle.ware.id.service;

import com.mort.middle.ware.id.IDService;
import com.mort.middle.ware.id.dto.ResponseDTO;
import com.mort.middle.ware.id.mapper.IdInfoMapper;
import com.mort.middle.ware.id.model.IdInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component("dbImplementService")
public class DBImplementService implements IDService, InitializingBean {

    // biz ,token , 认真根据系统参数配置认证 配置数据从配置中心取
    // 每次从DB 增加时候认证
    // 增加预热功能  根据APPkey 下面的biz 做预热
    private Map<String, ResponseDTO> mIDInfo = new ConcurrentHashMap<String, ResponseDTO>();

    @Resource
    private IdInfoMapper infoMapper;

    public long getID(String biz, String token) {
        return mIDInfo.get(biz).getStart().getAndIncrement();
    }

    public long getLastID(String biz, int delta) {
        return mIDInfo.get(biz).getStart().addAndGet(delta);
    }

    //固定步长的预热配置 增强性能
    public List<Long> getIds(String biz, String token, int delta) throws Exception {
        List<Long> ret = new ArrayList<>();
        ResponseDTO cache = mIDInfo.get(biz);
        if (null == cache) {
            mIDInfo.put(biz, increaseIdFormDb(biz, token, 10));
        }

        synchronized (this) {
            if (cache.getStart().longValue() + delta > cache.getEnd().longValue()) {
                //update
                ResponseDTO dbIdInfo = increaseIdFormDb(biz, token, 10);
                // increase step
                cache.getEnd().addAndGet(dbIdInfo.getStep());
                mIDInfo.put(biz, cache);
            }
        }

        long end = cache.getStart().addAndGet(delta);
        ret.add(end - delta);
        for (long i = end - delta; i < end; i++) {
            ret.add(i);
        }
        return ret;
    }

    public ResponseDTO getIdInfo(String biz, String token) throws Exception {
        if (null == mIDInfo.get(biz)) {
            // DB 获取号段；
            mIDInfo.put(biz, increaseIdFormDb(biz, token, 10));
        }
        return mIDInfo.get(biz);
    }

    private ResponseDTO increaseIdFormDb(String biz, String token, int count, int retry) throws Exception {

        if (count > retry) {
            throw new Exception("retry to many ");
        }

        ResponseDTO ret = new ResponseDTO();
        IdInfo idInfo = infoMapper.selectByBiz(biz);
        if (!idInfo.getCertificate().equals(token)) {
            // error return
            throw new Exception("Token error");
        }

        long oldFrom = idInfo.getForm();
        long end = oldFrom + idInfo.getStep();

        idInfo.setUpdateTime(new Date());
        idInfo.setForm(end);
        int i = infoMapper.updateByBizAndForm(idInfo, biz, oldFrom);
        if (i > 0) {
            ret.setStart(new AtomicLong(oldFrom));
            ret.setEnd(new AtomicLong(end));
            ret.setStep(idInfo.getStep());
            return ret;
        } else {
            increaseIdFormDb(biz, token, count + 1, retry);
        }

        return ret;
    }

    public ResponseDTO increaseIdFormDb(String biz, String token, int retry) throws Exception {
        return increaseIdFormDb(biz, token, 0, retry);
    }


    private boolean checkToken() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
