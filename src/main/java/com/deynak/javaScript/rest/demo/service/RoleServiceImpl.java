package com.deynak.javaScript.rest.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deynak.javaScript.rest.demo.dao.RoleRepository;
import com.deynak.javaScript.rest.demo.model.Role;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElse(null);
    }

    @Override
    public Role getRoleById(long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElse(null);
    }

    @Override
    public Role getDefaultRole() {
        return roleRepository.findByName("ROLE_USER").orElse(null); // Assuming "ROLE_USER" is the default role name.
    }
}
