package com.mort.middleWare.configCenter.server.service;

import com.mort.middleWare.configCenter.server.entity.MemoryCacheEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryCache {

    List<MemoryCacheEntity> cache = new ArrayList<>();


    // app , class , field , value
    Map<String, Map<String, Map<String, Object>>> cacheMap = new HashMap<>();



}
