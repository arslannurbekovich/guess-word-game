package com.example.testgame.dao;

import com.example.testgame.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByUserId(Long userId);

    Integer countAllByUserId(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM answers WHERE user_id= :userId ORDER BY id ASC LIMIT(5) OFFSET(:page)")
    List<Answer> getAnswersByUserWithTotalPage(@Param("userId") Long userId,@Param("page") Integer page);

}
