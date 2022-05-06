package com.example.testgame.entity;

import com.example.testgame.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends BaseEntity{

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "question_id")
    @ManyToOne
    private Question question;

    @Column(name = "attempts")
    private String attempts;

    @Column(name = "game_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

}
