package com.sunbasedata.javaassignment.service;

import com.sunbasedata.javaassignment.dao.LoginMapper;
import com.sunbasedata.javaassignment.dao.LoginResponseMapper;
import com.sunbasedata.javaassignment.model.CustomerProfile;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@Slf4j
@CrossOrigin
public class CreateCustomerService {
    private final Logger log = LoggerFactory.getLogger(CreateCustomerService.class);
    @Autowired
    private LoginResponseMapper loginResponseMapper;
    @Autowired
    private RestTemplate restTemplate;
    private static final String CREATE_URL = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

    public ResponseEntity<String> createCustomer(String cmd,CustomerProfile customerProfile){
        if(customerProfile.getFirst_name().isBlank()||customerProfile.getLast_name().isBlank())
            return ResponseEntity.status(400).body(", First Name or Last Name is missing");
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization","Bearer "+loginResponseMapper.getAccessToken());
            ResponseEntity<String> response = restTemplate.exchange(CREATE_URL+"?cmd="+cmd, HttpMethod.POST,new HttpEntity<>(customerProfile,headers), String.class);
            log.debug("this is request body:{}",customerProfile);
            log.info("this is response : {}",response);
            return response;
        }
        catch (Exception e){
            log.error("this is the problem {}",e.getMessage());
            return ResponseEntity.status(500).body("Internal Server Error");
        }

    }
}
