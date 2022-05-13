package com.example.testgame.dao;

import com.example.testgame.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Page<User> getAllByIsDeletedFalse(Pageable pageable);

    Page<User> getAllByIsDeletedFalseAndFullNameIsContainingIgnoreCaseOrUsernameIsContainingIgnoreCase(Pageable pageable, String keyword,String keyword2);
}
