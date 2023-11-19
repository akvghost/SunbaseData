package com.sunbasedata.javaassignment.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunbasedata.javaassignment.model.CustomerProfile;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CustomerProfileMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<CustomerProfile> mapCustomerProfiles(String jsonString) throws IOException {
        return objectMapper.readValue(jsonString, new TypeReference<List<CustomerProfile>>() {});
    }
}

