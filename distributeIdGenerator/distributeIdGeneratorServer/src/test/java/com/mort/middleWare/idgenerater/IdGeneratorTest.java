package com.mort.middleWare.idgenerater;


import com.mort.middle.ware.id.IDService;
import com.mort.middle.ware.id.dto.ResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-context.xml"})
public class IdGeneratorTest {

     private static final Logger logger = LoggerFactory.getLogger(IdGeneratorTest.class);

    @Resource(name = "dbImplementService")
    private IDService idService;

    @Test
    public void idTest(){
        ResponseDTO idInfo = null;
        try {
            idInfo = idService.getIdInfo("test_biz", "0ca175b9c0f726a831d895e269332461");
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("{}",idInfo);
    }


}
