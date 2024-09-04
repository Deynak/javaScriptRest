package com.deynak.javaScript.rest.demo.dao;

import org.springframework.stereotype.Repository;
import com.deynak.javaScript.rest.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class RoleDAOImpl implements RoleDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("select r from Role r where r.name=:name", Role.class).setParameter("name", name).getSingleResult();
    }

    @Override
    public Role getDefaultRole() {
        return getRoleByName("ROLE_USER");
    }

}
