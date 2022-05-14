package com.example.testgame.dao;

import com.example.testgame.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM questions WHERE UPPER(answer)= :answer")
    Question getQuestionByAnswer(@Param("answer") String answer);

    Page<Question> getAllByIsDeletedFalseAndAnswerIsContainingIgnoreCaseOrIsDeletedFalseAndNameContainingIgnoreCase(Pageable pageable,String keyword1,String keyword2);

    Page<Question> getAllByIsDeletedFalse(Pageable pageable);

    List<Question> getAllByIsDeletedFalse();
}
