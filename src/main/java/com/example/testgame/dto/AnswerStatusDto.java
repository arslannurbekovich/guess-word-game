package com.example.testgame.dto;

import com.example.testgame.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerStatusDto {

    private Status status;
    private String value;
}
