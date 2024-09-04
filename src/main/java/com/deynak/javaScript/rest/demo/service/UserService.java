package com.deynak.javaScript.rest.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.deynak.javaScript.rest.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUser(int id);

    public void deleteUser(int id);
//    public String getPage(Principal principal, ModelMap modelMap);

}
