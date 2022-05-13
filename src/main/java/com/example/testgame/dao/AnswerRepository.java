package com.example.testgame.dao;

import com.example.testgame.entity.Answer;
import com.example.testgame.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByUserId(Long userId);

    Integer countAllByUserId(Long userId);

    Integer countAllByUserIdAndAndStatusEquals(Long userId, Status status);

    @Query(nativeQuery = true, value = "SELECT * FROM answers WHERE user_id= :userId ORDER BY id ASC LIMIT(5) OFFSET(:page)")
    List<Answer> getAnswersByUserWithTotalPage(@Param("userId") Long userId,@Param("page") Integer page);

    @Query(nativeQuery = true, value = "SELECT * FROM answers WHERE user_id= :userId AND game_status= :status ORDER BY id ASC LIMIT(5) OFFSET(:page)")
    List<Answer> getAnswersByUserWithTotalPageWithFilter(@Param("userId") Long userId, @Param("status") String status, @Param("page") Integer page);

}
