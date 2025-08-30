package com.example.demo.dto.v1;

import lombok.Data;

@Data
public class UserDTOV1 {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
}
