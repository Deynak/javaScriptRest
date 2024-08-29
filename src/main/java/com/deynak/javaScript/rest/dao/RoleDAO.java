package com.deynak.javaScript.rest.dao;


import com.deynak.javaScript.rest.model.Role;

import java.util.List;

public interface RoleDAO {

    List<Role> allRoles();

    Role getRoleById(int id);

    Role getRoleByName(String name);

    Role getDefaultRole();
}
