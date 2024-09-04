package com.deynak.javaScript.rest.demo.dao;

import com.deynak.javaScript.rest.demo.model.Role;

import java.util.List;
public interface RoleDAO {
    List<Role> allRoles();
    Role getRoleById(int id);
    Role getRoleByName(String name);
    Role getDefaultRole();
}
