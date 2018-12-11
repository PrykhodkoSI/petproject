package com.prykhodkosi.petproject.servletbased.hotel.service.Impl;

import com.prykhodkosi.petproject.servletbased.hotel.exception.ApplicationException;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.RoleRepository;
import com.prykhodkosi.petproject.servletbased.hotel.repository.RepositoryFabric;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.RoleSpecificationAll;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.RoleSpecificationById;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.RoleSpecificationByName;
import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.RoleService;
import com.prykhodkosi.petproject.servletbased.hotel.model.Role;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository = RepositoryFabric.getRoleRepository();

    @Override
    public Role getRole(Integer id) {
        Role role = new Role(id);
        List<Role> roles = roleRepository.read(new RoleSpecificationById(role));
        if(roles == null || roles.isEmpty()){
            throw new ApplicationException("No such Role: " + id,
                    "exception.role.noRole",
                    id.toString());
        }
        return roles.iterator().next();
    }

    @Override
    public Role getRoleByName(String role) {
        Role criteriaRole = new Role(role);
        List<Role> roles = roleRepository.read(new RoleSpecificationByName(criteriaRole));
        if(roles == null || roles.isEmpty()){
            throw new ApplicationException("No such Role: " + role,
                    "exception.role.noRole",
                    role);
        }
        return roles.iterator().next();
    }

    @Override
    public List<Role> getRoles() {
        List<Role> roles = roleRepository.read(new RoleSpecificationAll());
        if(roles == null || roles.isEmpty()){
            throw new ApplicationException("Empty Role table",
                    "exception.role.noTable");
        }
        return roles;
    }

    @Override
    public Role postRole(Role role) {
        List<Role> roles = roleRepository.read(new RoleSpecificationByName(role));
        if(roles != null && !roles.isEmpty()){
            throw new ApplicationException("Role already exists: " + role.getName(),
                    "exception.role.existsRole",
                    role.getName());
        }
        if(!roleRepository.create(role)){
            throw new ApplicationException("Cant create Role: " + role.getName(),
                    "exception.role.cantCreateRole",
                    role.getName());
        }
        return role;
    }

    @Override
    public Role putRole(Role role) {
        getRole(role.getId());//check if exists
        if(!roleRepository.update(role)){
            throw new ApplicationException("Cant update role: " + role.toString(),
                    "exception.role.cantUpdateRole",
                    role.toString());
        }
        return role;
    }

    @Override
    public Role deleteRole(Integer id) {
        Role role = getRole(id);
        if(!roleRepository.delete(role)){
            throw new ApplicationException("Cant delete role: " + id,
                    "exception.role.cantDeleteRole",
                    id.toString());
        }
        return role;
    }
}
