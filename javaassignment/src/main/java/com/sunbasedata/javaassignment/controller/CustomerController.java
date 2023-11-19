package com.sunbasedata.javaassignment.controller;

import com.sunbasedata.javaassignment.dao.CustomerProfileMapper;
import com.sunbasedata.javaassignment.dao.LoginResponseMapper;
import com.sunbasedata.javaassignment.model.CustomerProfile;
import com.sunbasedata.javaassignment.service.CreateCustomerService;
import com.sunbasedata.javaassignment.service.DeleteService;
import com.sunbasedata.javaassignment.service.GetCustomerService;
import com.sunbasedata.javaassignment.service.UpdateCustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@NoArgsConstructor
@RequestMapping(value = "/c")
@AllArgsConstructor
public class CustomerController {
    @Autowired
    private  GetCustomerService getCustomerService;

    @Autowired
    private DeleteService deleteService;
    @Autowired
    private CreateCustomerService createCustomerService;

    @Autowired
    private UpdateCustomerService updateCustomerService;

    private final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private boolean customerExists = false;

    private  LoginResponseMapper loginResponseMapper;

    private CustomerProfileMapper customerProfileMapper;


    @PostMapping(value = "/new")
    public ResponseEntity<String> create(@RequestParam("cmd")String cmd, @RequestBody CustomerProfile customerProfile){
        if (cmd.equalsIgnoreCase("create"))
            return createCustomerService.createCustomer(cmd,customerProfile);
        return ResponseEntity.status(500).body("Invalid Command");
    }

    @PostMapping(value = "/update")
    public ResponseEntity<String> update(@RequestParam("cmd")String cmd,@RequestParam("uuid") String uuid,@RequestBody @NonNull CustomerProfile customerProfile){

        if (cmd.equalsIgnoreCase("update") )
            return updateCustomerService.updateCustomer(cmd,uuid,customerProfile);
        return ResponseEntity.status(500).body("Invalid Command");
    }

    @GetMapping(value = "/get")
    public ResponseEntity<String> getCustomer(  @RequestParam("cmd") String cmd){

        if(cmd.equalsIgnoreCase("get_customer_list")) {
            return getCustomerService.getCustomer(cmd);
        }
        return ResponseEntity.status(500).body("Invalid Command");
    }


    @PostMapping(value = "/delete")
    public ResponseEntity<String> delete(@RequestParam("cmd") String cmd, @RequestParam("uuid") String uid){
    try {

        if (cmd.equalsIgnoreCase("delete")){
            return deleteService.deleteCustomer(cmd,uid);}

        return ResponseEntity.status(500).body("Invalid Command");

    }
    catch (Exception e){
    log.error("this is problem : {}",e.getMessage());
        return ResponseEntity.noContent().build();
    }

    }

}
