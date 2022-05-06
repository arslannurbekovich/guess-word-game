package com.example.testgame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

    @NotEmpty(message = "Поле является обязательным для заполнения.")
    private String fullName;

    @NotEmpty(message = "Поле является обязательным для заполнения.")
    private String username;

    @NotEmpty(message = "Поле является обязательным для заполнения.")
    private String password;

    }
