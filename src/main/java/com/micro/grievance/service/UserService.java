package com.micro.grievance.service;

import com.micro.grievance.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User getUserById(Integer id);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Integer id);
}
