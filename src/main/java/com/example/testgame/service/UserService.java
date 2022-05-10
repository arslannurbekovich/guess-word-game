package com.example.testgame.service;

import com.example.testgame.dto.UserRegistrationDto;
import com.example.testgame.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

    User getUser(String username);

    void deleteUserById(Long id);

    Page<User> getUsersPagination(int currentPage, int size);

}