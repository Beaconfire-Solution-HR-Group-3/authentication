package com.example.authentication.dao;

import com.example.authentication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,String> {
    List<User> findAll();
}
