package com.deynak.javaScript.rest.demo.service;

import com.deynak.javaScript.rest.demo.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> allRoles();
    Role getRoleByName(String name);
    Role getRoleById(int id);
    Role getDefaultRole();
}
