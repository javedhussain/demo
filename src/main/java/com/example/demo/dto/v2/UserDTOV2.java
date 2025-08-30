package com.example.demo.dto.v2;

import lombok.Data;

@Data
public class UserDTOV2 {
    private Long id;
    private String fullName;    // Combined firstName and lastName
    private String email;
    private String username;
}
