package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.v1.UserDTOV1;
import com.example.demo.dto.v2.UserDTOV2;

public interface UserService {
    // V1 operations
    UserDTOV1 createUserV1(UserDTOV1 userDTO);
    UserDTOV1 getUserV1(Long id);
    List<UserDTOV1> getAllUsersV1();
    UserDTOV1 updateUserV1(Long id, UserDTOV1 userDTO);
    void deleteUser(Long id);
    
    // V2 operations
    UserDTOV2 createUserV2(UserDTOV2 userDTO);
    UserDTOV2 getUserV2(Long id);
    List<UserDTOV2> getAllUsersV2();
    UserDTOV2 updateUserV2(Long id, UserDTOV2 userDTO);
}
