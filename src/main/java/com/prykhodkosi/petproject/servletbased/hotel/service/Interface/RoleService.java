package com.prykhodkosi.petproject.servletbased.hotel.service.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.model.Role;

import java.util.List;

public interface RoleService {
    Role getRole(Integer id);
    Role getRoleByName(String role);
    List<Role> getRoles();
    Role postRole(Role role);
    Role putRole(Role role);
    Role deleteRole(Integer id);
}
