package com.mort.middle.ware.id;

import com.mort.middle.ware.id.dto.ResponseDTO;

import java.util.List;

public interface IDService {

    List<Long> getIds(String biz, String token, int delta) throws Exception;

    ResponseDTO getIdInfo(String biz, String token) throws Exception;

}
