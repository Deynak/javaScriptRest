package com.deynak.javaScript.rest.demo.dao;

import com.deynak.javaScript.rest.demo.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUser(int id);
    public User getUserByEmail(String email);

    public void deleteUser(int id);
}
