package com.epam.rd.june2018.session.service.Interface;

import com.epam.rd.june2018.session.model.Role;

import java.util.List;

public interface RoleService {
    Role getRole(Integer id);
    Role getRoleByName(String role);
    List<Role> getRoles();
    Role postRole(Role role);
    Role putRole(Role role);
    Role deleteRole(Integer id);
}
