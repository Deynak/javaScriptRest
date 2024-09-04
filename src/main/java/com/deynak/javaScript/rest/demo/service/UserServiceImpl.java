package com.deynak.javaScript.rest.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.deynak.javaScript.rest.demo.dao.UserDAO;
import com.deynak.javaScript.rest.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

//    @Override
//    public String getPage(Principal principal, ModelMap modelMap) {
//        User user = userDAO.getUserByEmail(principal.getName());
//        modelMap.addAttribute("currentUser", user);
//        if (user.hasRole("ADMIN")) {
//            return "allUsersPage";
//        } else if (user.hasRole("USER")) {
//            return "user";
//        }
//        return "index";
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDAO.getUserByEmail(email);
    }
}
