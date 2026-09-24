package com.savej.service;

import com.savej.expections.UserException;
import com.savej.model.User;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface userService {

    public User saveUser(User user);

    public User findById(Integer id)throws UserException;

    public List<User> getAllUser(PageRequest pageRequest);

    public User deleteUserById(Integer userid)throws UserException;

    public User updateUserById(User user)throws UserException;

    public User updateUser(Integer userid , String name)throws UserException;

    public User findByName(String name)throws UserException;

    public User findByemail(String email)throws UserException;

   // public User findByNameContainingIgnoreCase(String name)throws UserException;


}
