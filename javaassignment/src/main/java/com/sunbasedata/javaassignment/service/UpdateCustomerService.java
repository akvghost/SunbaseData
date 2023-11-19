package com.sunbasedata.javaassignment.service;

import com.sunbasedata.javaassignment.dao.LoginResponseMapper;
import com.sunbasedata.javaassignment.model.CustomerProfile;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UpdateCustomerService {

    private final Logger log = LoggerFactory.getLogger(UpdateCustomerService.class);
    @Autowired
    private LoginResponseMapper loginResponseMapper;
    @Autowired
    private RestTemplate restTemplate;

    private static final String UPDATE_URL = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";
    public ResponseEntity<String> updateCustomer(String cmd,String uuid, CustomerProfile customerProfile){
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization","Bearer "+loginResponseMapper.getAccessToken());
            ResponseEntity<String> response = restTemplate.exchange(UPDATE_URL+"?cmd="+cmd+"&uuid="+uuid, HttpMethod.POST,new HttpEntity<>(customerProfile,headers), String.class);
            log.debug("this is request body:{}",customerProfile);
            log.info("this is response : {}",response);

            return ResponseEntity.status(200).build();
        }
        catch (Exception e){
            log.error("this is the problem {}",e.getMessage());
            if(e.getMessage().contains("401")){
                return ResponseEntity.status(401).body("Invalid Authorisation");
            }
            else if (e.getMessage().contains("500")){
                return ResponseEntity.status(500).body("UUID Not Found");
            }
            else
                return  ResponseEntity.status(400).body("Body is Empty");
        }

    }
}
