package com.sunbasedata.javaassignment.service;

import com.sunbasedata.javaassignment.dao.CustomerProfileMapper;
import com.sunbasedata.javaassignment.dao.LoginResponseMapper;
import com.sunbasedata.javaassignment.model.CustomerProfile;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class DeleteService {

    private static final String DELETE_URL = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

    @Autowired
    private LoginResponseMapper loginResponseMapper;

    private final Logger log = LoggerFactory.getLogger(DeleteService.class);
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> deleteCustomer(String cmd,String uid){

        try {

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization","Bearer "+loginResponseMapper.getAccessToken());

            ResponseEntity<String> response = restTemplate.exchange(DELETE_URL+"?cmd="+cmd+"&uuid="+uid, HttpMethod.POST,new HttpEntity<>(headers), String.class);

            return response;
        }
        catch (Exception e){
            log.error("this is the problem {}",e.getMessage());
            if(e.getMessage().contains("401")){
                return ResponseEntity.status(401).body("Invalid Authorisation");
            }
            else if (e.getMessage().contains("400")){
            return ResponseEntity.status(400).body("UUID Not Found");
            }
            else
            return  ResponseEntity.status(500).body("Error Not deleted");

        }
    }
}
