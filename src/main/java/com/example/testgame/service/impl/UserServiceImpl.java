package com.example.testgame.service.impl;

import com.example.testgame.dao.UserRepository;
import com.example.testgame.dto.UserRegistrationDto;
import com.example.testgame.entity.Role;
import com.example.testgame.entity.User;
import com.example.testgame.exceptions.NotFoundException;
import com.example.testgame.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFullName(),
                registrationDto.getUsername(),
                passwordEncoder.encode(registrationDto.getPassword()), false,
                Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }


    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("Пользователь не найдено!"));
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Override
    public Page<User> getUsersPagination(int currentPage, int size) {
        Pageable pageable = PageRequest.of(currentPage, size);
        return userRepository.getAllByIsDeletedFalse(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Неправильный логин или пароль!");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
