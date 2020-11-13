package com.mort.middle.ware.id.service;

import com.mort.middle.ware.id.IDService;
import com.mort.middle.ware.id.dto.ResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component("redisImplementService")
public class RedisImplementService implements IDService {


    @Override
    public List<Long> getIds(String biz, String token, int delta) throws Exception {
        return null;
    }

    @Override
    public ResponseDTO getIdInfo(String biz, String token) throws Exception {
        return null;
    }
}
