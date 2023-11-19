package com.sunbasedata.javaassignment.dao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Data
public class LoginMapper {
    @JsonProperty

    private String login_id;
    @JsonProperty

    private String password;
}
