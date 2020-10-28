package com.mort.middle.ware.id.service;

import com.mort.middle.ware.id.IDService;
import com.mort.middle.ware.id.dto.ResponseDTO;
import com.mort.middle.ware.id.mapper.IdInfoMapper;
import com.mort.middle.ware.id.model.IdInfo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DBImplementService implements IDService {

    // biz ,token , 认真根据系统参数配置认证 配置数据从配置中心取
    // 每次从DB 增加时候认证
    // 增加预热功能  根据APPkey 下面的biz 做预热
    private Map<String, ResponseDTO> mIDInfo = new ConcurrentHashMap<String, ResponseDTO>();

    @Resource
    private IdInfoMapper infoMapper;

    public long getID(String biz) {
        return mIDInfo.get(biz).getStart().getAndIncrement();
    }

    public long getLastID(String biz, int delta) {
        return mIDInfo.get(biz).getStart().addAndGet(delta);
    }

    //固定步长的预热配置 增强性能
    public List<Long> getIDs(String biz, int delta) {
        List<Long> ret = new ArrayList<Long>();
        long end = mIDInfo.get(biz).getStart().addAndGet(delta);
        ret.add(end - delta);
        for (long i = end - delta; i < end; i++) {
            ret.add(i);
        }
        return ret;
    }

    public ResponseDTO getIdInfo(String biz, String token) {
        if (null == mIDInfo.get(biz)) {
            // DB 获取号段；
            mIDInfo.put(biz, increaseIdFormDb(biz, token, 10));
        }
        return mIDInfo.get(biz);
    }

    private ResponseDTO increaseIdFormDb(String biz, String token, int count, int retry) {

        if (count > retry) {
            return null;
        }

        ResponseDTO ret = new ResponseDTO();
        IdInfo idInfo = infoMapper.selectByBiz(biz);
        if (!idInfo.getCertificate().equals(token)) {
            // error return
            return null;
        }

        long oldFrom = idInfo.getForm();
        long end = oldFrom + idInfo.getStep();

        idInfo.setUpdateTime(new Date());
        idInfo.setForm(end);
        int i = infoMapper.updateByBizAndForm(idInfo, biz, oldFrom);
        if (i > 0) {
            ret.setStart(new AtomicLong(oldFrom));
            ret.setEnd(new AtomicLong(end));
            return ret;
        } else {
            increaseIdFormDb(biz, token, count + 1, retry);
        }

        return null;
    }

    public ResponseDTO increaseIdFormDb(String biz, String token, int retry) {
        return increaseIdFormDb(biz, token, 0, retry);
    }


    private boolean checkToken() {
        return true;
    }

}
