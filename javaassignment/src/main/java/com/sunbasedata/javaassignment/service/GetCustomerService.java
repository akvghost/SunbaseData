package com.sunbasedata.javaassignment.service;

import com.sunbasedata.javaassignment.dao.CustomerProfileMapper;
import com.sunbasedata.javaassignment.dao.LoginMapper;
import com.sunbasedata.javaassignment.dao.LoginResponseMapper;
import com.sunbasedata.javaassignment.model.CustomerProfile;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class GetCustomerService {

    private final Logger log = LoggerFactory.getLogger(GetCustomerService.class);
    @Autowired
    private LoginResponseMapper loginResponseMapper;
    @Autowired
    private CustomerProfileMapper customerProfileMapper;
    @Autowired
    private RestTemplate restTemplate;
    private  final String URL = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

    public ResponseEntity<String> getCustomer(@RequestParam("cmd") String cmd){
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization","Bearer "+loginResponseMapper.getAccessToken());
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            ResponseEntity<String> response = restTemplate.exchange(URL+"?cmd="+cmd, HttpMethod.GET,new HttpEntity<>(headers), String.class);

            String jsonString = response.getBody();
            List<CustomerProfile> customerProfiles = CustomerProfileMapper.mapCustomerProfiles(jsonString);

            return response;
        }
        catch (Exception e){
            log.error("this is the problem {}",e.getMessage());
            return ResponseEntity.status(500).body("Internal Server Error");
        }


    }
}
