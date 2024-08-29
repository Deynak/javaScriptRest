package com.deynak.javaScript.rest.service;


import com.deynak.javaScript.rest.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> allRoles();

    Role getRoleByName(String name);

    Role getRoleById(int id);

    Role getDefaultRole();
}
