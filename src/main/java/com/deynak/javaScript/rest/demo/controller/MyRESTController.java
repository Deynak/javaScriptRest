package com.deynak.javaScript.rest.demo.controller;

import com.deynak.javaScript.rest.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.deynak.javaScript.rest.demo.model.Role;
import com.deynak.javaScript.rest.demo.model.User;
import com.deynak.javaScript.rest.demo.service.RoleService;


import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {
    private final UserService userService;

    private final RoleService roleService;
    public MyRESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        System.out.println(userService.getAllUsers());
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(userService.getUser(user.getId()));
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(userService.getUser(user.getId()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> allRoles() {
        return ResponseEntity.ok(roleService.allRoles());
    }

    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser(Principal principal, ModelMap modelMap) {
        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        modelMap.addAttribute("currentUser", currentUser);
        return ResponseEntity.ok(currentUser);
    }
}
