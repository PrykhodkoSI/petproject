package com.prykhodkosi.petproject.servletbased.hotel.service.Impl;

import com.prykhodkosi.petproject.servletbased.hotel.converter.UserConverter;
import com.prykhodkosi.petproject.servletbased.hotel.exception.ApplicationException;
import com.prykhodkosi.petproject.servletbased.hotel.exception.ValidationException;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.RoleRepository;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.UserRepository;
import com.prykhodkosi.petproject.servletbased.hotel.repository.RepositoryFabric;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.RoleSpecificationByUser;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.UserSpecificationAll;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.UserSpecificationByEmail;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.UserSpecificationById;
import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.SecurityService;
import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.UserService;
import com.prykhodkosi.petproject.servletbased.hotel.validator.UserValidator;
import com.prykhodkosi.petproject.servletbased.hotel.validator.UserValidatorImpl;
import com.prykhodkosi.petproject.servletbased.hotel.Util.StringEncryptor;
import com.prykhodkosi.petproject.servletbased.hotel.model.Role;
import com.prykhodkosi.petproject.servletbased.hotel.model.User;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.CreateUserDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileUserDto;


import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository = RepositoryFabric.getUserRepository();
    private RoleRepository roleRepository = RepositoryFabric.getRoleRepository();
    private UserConverter userConverter = new UserConverter();
    private SecurityService securityService = new SecurityServiceImpl();
    private UserValidator userValidator = new UserValidatorImpl();


    @Override
    public ProfileUserDto getUser(Integer id) {
        User userToRead = new User(id);
        List<User> users = userRepository.read(new UserSpecificationById(userToRead));
        if(users == null || users.isEmpty()){
            throw new ApplicationException("No such User: " + id,
                    "exception.user.noUser",
                    id.toString());
        }
        User user = users.stream().findFirst().get();
        return getUserDto(user);
    }

    @Override
    public ProfileUserDto getUserByEmail(String email) {
        List<User> users = userRepository.read(new UserSpecificationByEmail(email));
        if(users == null || users.isEmpty()){
            throw new ApplicationException("There is no User with email: " + email,
                    "exception.user.noUser",
                    email);
        }
        User user = users.iterator().next();
        return getUserDto(user);
    }

    private ProfileUserDto getUserDto(User user){
        ProfileUserDto profileUserDto = userConverter.asUserDto(user);
        List<Role> roles = getRoles(user);
        if(roles != null){
            profileUserDto.setRoles(roles);
        }
        return profileUserDto;
    }

    private List<Role> getRoles(User user) {
        List<Role> roles = roleRepository.read(new RoleSpecificationByUser(user));
        roles.sort(Comparator.comparingInt(Role::getId));
        return roles;
    }

    @Override
    public List<ProfileUserDto> getUsers() {
        Collection<User> users = userRepository.read(new UserSpecificationAll());
        if(users == null || users.isEmpty()){
            throw new ApplicationException("Empty user table",
                    "exception.user.noTable");
        }
        List<ProfileUserDto> profileUserDtos = users.stream()
                .map(this::getUserDto)
                .collect(Collectors.toList());
        return profileUserDtos;
    }

    @Override
    public ProfileUserDto registerUser(CreateUserDto user) {
        userValidator.validateNewUser(user);
        List<User> users = userRepository.read(
                new UserSpecificationByEmail(user.getEmail()));
        if(users != null && !users.isEmpty()){
            throw new ApplicationException("User with such email already exists: " + user.getEmail(),
                    "exception.user.existsUser",
                    user.getEmail());
        }
        user.setPassword(StringEncryptor.encryptString(user.getPassword()));
        if(!userRepository.create(userConverter.asAppUser(user))){
            throw new ApplicationException("Cant create user: " + user.getName(),
                    "exception.user.cantCreateUser",
                    user.getName());
        }
        List<User> insertedUsers = userRepository.read(
                new UserSpecificationByEmail(user.getEmail()));
        if(insertedUsers == null || insertedUsers.isEmpty()){
            throw new ApplicationException("Cant find User with such email: " + user.getEmail(),
                    "exception.user.noUser",
                    user.getEmail());
        }
        User insertedUser = insertedUsers.iterator().next();
        Role role = new Role(2, "client");
        if(!userRepository.grantRole(insertedUser, role)){
            throw new ApplicationException("Cant grant client role, contact administrator",
                    "exception.user.cantGrantRole");
        }
        return getUserDto(insertedUser);

    }

    @Override
    public ProfileUserDto loginUser(String email, String password) {
        userValidator.validateUserCredentials(email, password);
        List<User> users = userRepository.read(new UserSpecificationByEmail(email));
        if(users == null || users.isEmpty()){
            throw new ApplicationException("There is no User with email: " + email,
                    "exception.user.noUser",
                    email);
        }
        User user = users.iterator().next();
        if(!securityService.isCorrectPassword(user, password)){
            throw new ValidationException("Wrong password",
                    "exception.user.wrongPassword");
        }
        return getUserDto(user);
    }

    @Override
    public ProfileUserDto putUser(ProfileUserDto user) {
        getUser(user.getId());//check if exists
        if(!userRepository.update(userConverter.asAppUser(user))){
            throw new ApplicationException("Cant update user: " + user.toString(),
                    "exception.user.cantUpdateUser",
                    user.toString());
        }
        return user;
    }


    @Override
    public ProfileUserDto deleteUser(Integer id) {
        ProfileUserDto userDto = getUser(id);
        if(!userRepository.delete(userConverter.asAppUser(userDto))){
            throw new ApplicationException("Cant delete user: " + id,
                    "exception.user.cantDeleteUser",
                    id.toString());
        }
        return userDto;
    }
}
