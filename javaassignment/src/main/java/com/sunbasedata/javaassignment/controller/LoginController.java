package com.sunbasedata.javaassignment.controller;

import com.sunbasedata.javaassignment.dao.LoginMapper;
import com.sunbasedata.javaassignment.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/user")
public class LoginController {

    private final LoginService loginService;

    @Autowired

    public LoginController( LoginService loginService) {
        this.loginService = loginService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginMapper loginMapper){
        return  loginService.login(loginMapper);
    }
}
