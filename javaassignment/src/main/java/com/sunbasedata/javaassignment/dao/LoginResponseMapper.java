package com.sunbasedata.javaassignment.dao;


import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class LoginResponseMapper {

     private String accessToken;


     public String getAccessToken(){
         return this.accessToken;
     }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
