package com.deynak.javaScript.rest.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deynak.javaScript.rest.demo.dao.RoleDAO;
import com.deynak.javaScript.rest.demo.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDAO roleDAO;
    @Override
    public List<Role> allRoles() {
        return roleDAO.allRoles();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }

    @Override
    public Role getRoleById(int id) {
        return roleDAO.getRoleById(id);
    }

    @Override
    public Role getDefaultRole() {
        return roleDAO.getDefaultRole();
    }

}
