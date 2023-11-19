package com.sunbasedata.javaassignment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunbasedata.javaassignment.dao.LoginMapper;
import com.sunbasedata.javaassignment.dao.LoginResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.Map;

@EnableWebMvc
@Slf4j
@Component
public class LoginService {

    private final Logger log = LoggerFactory.getLogger(LoginService.class);
    private String accessToken;
    @Autowired
    private final LoginResponseMapper loginResponseMapper;
    @Autowired
    private  RestTemplate restTemplate;
    private static final String LOGIN_URL = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

    public LoginService(LoginResponseMapper loginResponseMapper) {
        this.loginResponseMapper = loginResponseMapper;
    }

    public ResponseEntity<String> login(LoginMapper loginMapper){
        try {
            HttpHeaders headers = new HttpHeaders();
            ResponseEntity<String> response = (ResponseEntity<String>) restTemplate.exchange(LOGIN_URL, HttpMethod.POST,new HttpEntity<>(loginMapper,headers), String.class);

            accessToken = response.getBody();


            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> jsonData = mapper.readValue(accessToken, Map.class);


            accessToken=jsonData.get("access_token");
            loginResponseMapper.setAccessToken(accessToken);
            return ResponseEntity.status(200).build();
        }
        catch (Exception e){
            log.error("this is the problem {}",e.getMessage()/*.replace("<EOL>",".")*/);
            return ResponseEntity.status(401).body("Invalid Authorization");
        }
    }
}
