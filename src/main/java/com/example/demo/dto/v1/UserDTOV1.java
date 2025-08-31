package com.example.demo.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDTOV1 {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
