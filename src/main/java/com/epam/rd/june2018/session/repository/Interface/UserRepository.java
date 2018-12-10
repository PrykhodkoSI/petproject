package com.epam.rd.june2018.session.repository.Interface;
import com.epam.rd.june2018.session.model.Role;
import com.epam.rd.june2018.session.model.User;
import com.epam.rd.june2018.session.repository.specification.Interface.UserSpecification;


public interface UserRepository extends CrudRepository<User, UserSpecification>{

    Boolean grantRole(User user, Role role);

    Boolean revokeRole(User user, Role role);

}