package com.epam.rd.june2018.session;

import com.epam.rd.june2018.session.model.Role;
import com.epam.rd.june2018.session.model.User;
import com.epam.rd.june2018.session.repository.Implementation.*;
import com.epam.rd.june2018.session.repository.Interface.RoleRepository;
import com.epam.rd.june2018.session.repository.LiquibaseManager;
import com.epam.rd.june2018.session.repository.specification.Interface.RoleSpecification;
import com.epam.rd.june2018.session.repository.Interface.UserRepository;
import com.epam.rd.june2018.session.repository.specification.impl.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.Matchers.containsInAnyOrder;

@SuppressWarnings("Duplicates")
public class JdbcRoleRepositoryTest {

    private RoleRepository repository = new JdbcRoleRepository();
    private LiquibaseManager config = new LiquibaseManager();

    @Before
    public void before() {
        config.reinitDB();
    }

    @Test
    public void readAllTest(){
        //GIVEN
        RoleSpecificationAll specificationAll = new RoleSpecificationAll();
        Collection<Role> expected = Arrays.asList(
                new Role(1, "admin"),
                new Role(2, "client"),
                new Role(3, "manager"));

        //WHEN
        Collection<Role> actual = repository.read(specificationAll);

        //THEN
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readByUserTest() throws IOException {
        //GIVEN
        UserRepository userRepository = new JdbcUserRepository();
        User u = new User(2);
        User user = userRepository.read(new UserSpecificationById(u)).iterator().next();
        RoleSpecification specification = new RoleSpecificationByUser(user);
        Role role = new Role(2, "client");
        Collection<Role> expected = Arrays.asList(role);

        //WHEN
        Collection<Role> actual = repository.read(specification);

        //THEN
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createTest(){
        //GIVEN
        Role expectedRole = new Role(-1,"testRole");

        //WHEN
        Boolean actual = repository.create(expectedRole);
        Role actualRole = repository.read(new RoleSpecificationByName(expectedRole)).iterator().next();

        //THEN
        Assert.assertTrue(actual);
        Assert.assertEquals(expectedRole.getName(), actualRole.getName());
    }

    @Test
    public void updateTest(){
        //GIVEN
        Role expectedRole = new Role(1,"god");

        //WHEN
        Boolean actual = repository.update(expectedRole);
        Role actualRole = repository.read(new RoleSpecificationById(expectedRole)).iterator().next();

        //THEN
        Assert.assertTrue(actual);
        Assert.assertEquals(expectedRole, actualRole);
    }

    @Test
    public void deleteTest(){
        //GIVEN
        Role role = new Role(2,"client");
        Collection<Role> expected = Collections.emptyList();


        //WHEN
        Boolean actualFlag = repository.delete(role);
        Collection<Role> actual = repository.read(new RoleSpecificationById(role));

        //THEN
        Assert.assertTrue(actualFlag);
        Assert.assertEquals(expected, actual);
    }





}
