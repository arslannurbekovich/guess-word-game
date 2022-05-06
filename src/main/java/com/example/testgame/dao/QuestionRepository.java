package com.example.testgame.dao;

import com.example.testgame.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM questions WHERE UPPER(answer)= :answer")
    Question getQuestionByAnswer(@Param("answer") String answer);

}
