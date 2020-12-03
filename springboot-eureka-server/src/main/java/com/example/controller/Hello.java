package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mort
 * @Description
 * @date 2020/12/3
 **/
@RestController
public class Hello {

    @RequestMapping("/greeting")
    public Object greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "hello" + name;
    }

}
