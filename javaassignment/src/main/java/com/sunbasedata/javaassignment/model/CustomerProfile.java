package com.sunbasedata.javaassignment.model;
import lombok.*;

import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class CustomerProfile {
     String uuid;
     String first_name;
     String last_name;
     String street;
     String address;
     String city;
     String state;
     String email;
     String phone;

     public String getUuid() {
          return this.uuid;
     }

     public void setUuid(String Uuid){
          this.uuid = uuid;
     }

     public String getFirst_name() {
          return this.first_name;
     }

     public String getLast_name() {
          return this.last_name;
     }
}
