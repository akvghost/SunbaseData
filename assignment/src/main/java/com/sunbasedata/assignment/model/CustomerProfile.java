package com.sunbasedata.assignment.model;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerProfile {
    private Long id;
    private String firstName;
    private String lastName;
    private String street;
    private String address;
    private String city;
    private String state;
    private String email;
    private String phone;
}
