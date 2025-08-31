package com.example.demo.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.v1.UserDTOV1;
import com.example.demo.dto.auth.AuthenticationRequest;
import com.example.demo.dto.auth.AuthenticationResponse;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserControllerV1 {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateToken((User) authentication.getPrincipal());
        
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTOV1> register(@RequestBody UserDTOV1 userDTO) {
        // Encode password before creating user
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserDTOV1 createdUser = userService.createUserV1(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<UserDTOV1> createUser(@RequestBody UserDTOV1 userDTO) {
        UserDTOV1 createdUser = userService.createUserV1(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTOV1> getUser(@PathVariable Long id) {
        UserDTOV1 user = userService.getUserV1(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserDTOV1>> getAllUsers() {
        List<UserDTOV1> users = userService.getAllUsersV1();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTOV1> updateUser(@PathVariable Long id, @RequestBody UserDTOV1 userDTO) {
        UserDTOV1 updatedUser = userService.updateUserV1(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
