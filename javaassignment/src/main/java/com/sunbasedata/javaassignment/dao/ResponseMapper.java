package com.sunbasedata.javaassignment.dao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseMapper {
    private int statusCode;
    private String status;
}
