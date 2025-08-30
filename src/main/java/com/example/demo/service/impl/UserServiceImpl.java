package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.v1.UserDTOV1;
import com.example.demo.dto.v2.UserDTOV2;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.exception.ResourceNotFoundException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // V1 implementations
    @Override
    public UserDTOV1 createUserV1(UserDTOV1 userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        
        User savedUser = userRepository.save(user);
        return convertToV1DTO(savedUser);
    }

    @Override
    public UserDTOV1 getUserV1(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return convertToV1DTO(user);
    }

    @Override
    public List<UserDTOV1> getAllUsersV1() {
        return userRepository.findAll().stream()
            .map(this::convertToV1DTO)
            .collect(Collectors.toList());
    }

    @Override
    public UserDTOV1 updateUserV1(Long id, UserDTOV1 userDTO) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        
        User updatedUser = userRepository.save(user);
        return convertToV1DTO(updatedUser);
    }

    // V2 implementations
    @Override
    public UserDTOV2 createUserV2(UserDTOV2 userDTO) {
        User user = new User();
        String[] names = userDTO.getFullName().split(" ", 2);
        user.setFirstName(names[0]);
        user.setLastName(names.length > 1 ? names[1] : "");
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        
        User savedUser = userRepository.save(user);
        return convertToV2DTO(savedUser);
    }

    @Override
    public UserDTOV2 getUserV2(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return convertToV2DTO(user);
    }

    @Override
    public List<UserDTOV2> getAllUsersV2() {
        return userRepository.findAll().stream()
            .map(this::convertToV2DTO)
            .collect(Collectors.toList());
    }

    @Override
    public UserDTOV2 updateUserV2(Long id, UserDTOV2 userDTO) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        String[] names = userDTO.getFullName().split(" ", 2);
        user.setFirstName(names[0]);
        user.setLastName(names.length > 1 ? names[1] : "");
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        
        User updatedUser = userRepository.save(user);
        return convertToV2DTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    // Helper methods for DTO conversion
    private UserDTOV1 convertToV1DTO(User user) {
        UserDTOV1 dto = new UserDTOV1();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        return dto;
    }

    private UserDTOV2 convertToV2DTO(User user) {
        UserDTOV2 dto = new UserDTOV2();
        dto.setId(user.getId());
        dto.setFullName(user.getFirstName() + " " + user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        return dto;
    }
}
