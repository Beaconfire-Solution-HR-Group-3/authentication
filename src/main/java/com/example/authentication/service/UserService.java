package com.example.authentication.service;


import com.example.authentication.dao.UserDao;
import com.example.authentication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
public class UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Transactional
    public List<User> getAllUsers(){
        return userDao.findAll();
    }
}
