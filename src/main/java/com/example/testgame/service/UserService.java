package com.example.testgame.service;

import com.example.testgame.dto.UserRegistrationDto;
import com.example.testgame.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    List<User> getAllUsers();

    User getUser(String username);

    void deleteUserById(Long id);

    Page<User> getUsersPagination(int currentPage, int size);

}