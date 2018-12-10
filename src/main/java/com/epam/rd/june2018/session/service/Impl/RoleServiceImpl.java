package com.epam.rd.june2018.session.service.Impl;

import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.model.Role;
import com.epam.rd.june2018.session.repository.Interface.RoleRepository;
import com.epam.rd.june2018.session.repository.RepositoryFabric;
import com.epam.rd.june2018.session.repository.specification.impl.RoleSpecificationAll;
import com.epam.rd.june2018.session.repository.specification.impl.RoleSpecificationById;
import com.epam.rd.june2018.session.repository.specification.impl.RoleSpecificationByName;
import com.epam.rd.june2018.session.repository.specification.impl.RoleSpecificationByUser;
import com.epam.rd.june2018.session.service.Interface.RoleService;

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
