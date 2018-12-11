package com.prykhodkosi.petproject.servletbased.hotel.repository.Interface;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.UserSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.model.Role;
import com.prykhodkosi.petproject.servletbased.hotel.model.User;


public interface UserRepository extends CrudRepository<User, UserSpecification>{

    Boolean grantRole(User user, Role role);

    Boolean revokeRole(User user, Role role);

}