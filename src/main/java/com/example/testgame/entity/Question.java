package com.example.testgame.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question extends BaseEntity {

    @NotEmpty(message = "Поле является обязательным для заполнения.")
    @Size(min = 2 ,max = 50, message = "Поле должен быть от 2 до 50 символов")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Поле является обязательным для заполнения.")
    @Size(min = 2 ,max = 50, message = "Поле должен быть от 2 до 50 символов")
    @Column(name = "answer")
    private String answer;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
