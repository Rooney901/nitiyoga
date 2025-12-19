package com.rooney.nitiyoga.service;

import java.util.List;

import com.rooney.nitiyoga.entity.User;

public interface UserService {

    public User findUserById(Long id);

    public User createUser(User user);

    public User updateUser(Long id, User user);

    public void deleteUser(Long id);

    public User findUserByEmail(String email);

    public List<User> getAllUsers();

    public User findUserByUserName(String userName);
}
