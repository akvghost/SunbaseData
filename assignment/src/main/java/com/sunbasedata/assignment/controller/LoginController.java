package com.sunbasedata.assignment.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/checklogs")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    public void testLog(){
        logger.info("this is logger info");
    }
}
