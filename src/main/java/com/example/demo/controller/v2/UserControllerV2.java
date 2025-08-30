package com.example.demo.controller.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.v2.UserDTOV2;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v2/users")
public class UserControllerV2 {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTOV2> createUser(@RequestBody UserDTOV2 userDTO) {
        UserDTOV2 createdUser = userService.createUserV2(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTOV2> getUser(@PathVariable Long id) {
        UserDTOV2 user = userService.getUserV2(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserDTOV2>> getAllUsers() {
        List<UserDTOV2> users = userService.getAllUsersV2();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTOV2> updateUser(@PathVariable Long id, @RequestBody UserDTOV2 userDTO) {
        UserDTOV2 updatedUser = userService.updateUserV2(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
