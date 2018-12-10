package com.epam.rd.june2018.session;

import com.epam.rd.june2018.session.model.Role;
import com.epam.rd.june2018.session.model.User;
import com.epam.rd.june2018.session.repository.Implementation.*;
import com.epam.rd.june2018.session.repository.Interface.RoleRepository;
import com.epam.rd.june2018.session.repository.Interface.UserRepository;
import com.epam.rd.june2018.session.repository.LiquibaseManager;
import com.epam.rd.june2018.session.repository.specification.impl.RoleSpecificationById;
import com.epam.rd.june2018.session.repository.specification.impl.RoleSpecificationByUser;
import com.epam.rd.june2018.session.repository.specification.impl.UserSpecificationAll;
import com.epam.rd.june2018.session.repository.specification.impl.UserSpecificationById;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.*;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.Matchers.*;


public class JdbcUserRepositoryTest {

    private UserRepository repository = new JdbcUserRepository();
    private LiquibaseManager config = new LiquibaseManager();

    @Before
    public void before() {
        config.reinitDB();
    }

    @Test
    public void readAllTest(){
        //GIVEN
        UserSpecificationAll userSpecificationAll = new UserSpecificationAll();
        Collection<User> expected = Arrays.asList(
                new User(1, "Vasia", "adminin", "admin", "admin1@gmail.com", 18),
                new User(2, "Petia", "adminin", "admin", "admin2@gmail.com", 18),
                new User(3, "Leha", "adminin", "admin", "admin3@gmail.com", 18),
                new User(40,	"Станислав","Приходько","56311024829147210889186","prykhodko.s.i@gmail.com",20));

        //WHEN
        Collection<User> actual = repository.read(userSpecificationAll);

        //THEN
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readByIdTest(){
        //GIVEN
        UserSpecificationAll userSpecificationAll = new UserSpecificationAll();
        User user = new User(1, "Vasia", "adminin", "admin", "admin1@gmail.com", 18);
        Collection<User> expected = Arrays.asList(user);

        //WHEN
        Collection<User> actual = repository.read(new UserSpecificationById(user));

        //THEN
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createTest(){
        //GIVEN
        User expectedUser = new User(41,"Zhenia", "adminin", "admin", "admin4@gmail.com", 18);

        //WHEN
        Boolean actual = repository.create(expectedUser);
        User actualUser = repository.read(new UserSpecificationById(expectedUser)).iterator().next();

        //THEN
        Assert.assertTrue(actual);
        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void updateTest(){
        //GIVEN
        User expectedUser = new User(2,"Dima", "adminin", "admin", "admin2@gmail.com", 18);

        //WHEN
        Boolean actual = repository.update(expectedUser);
        User actualUser = repository.read(new UserSpecificationById(expectedUser)).iterator().next();

        //THEN
        Assert.assertTrue(actual);
        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void deleteTest(){
        //GIVEN
        User user = new User(2,"Petia", "adminin", "admin", "admin2@gmail.com", 18);
        Collection<User> expected = Collections.emptyList();

        //WHEN
        Boolean actualFlag = repository.delete(user);
        Collection<User> actual = repository.read(new UserSpecificationById(user));

        //THEN
        Assert.assertTrue(actualFlag);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void grantRoleTest() throws IOException {
        //GIVEN
        RoleRepository roleRepository = new JdbcRoleRepository();
        User u = new User(2);
        Role r = new Role(1);
        User user = repository.read(new UserSpecificationById(u)).iterator().next();
        Role role = roleRepository.read(new RoleSpecificationById(r)).iterator().next();

        //WHEN
        Boolean actual = repository.grantRole(user, role);
        Collection<Role> actualSet = roleRepository.read(new RoleSpecificationByUser(user));

        //THEN
        Assert.assertTrue(actual);
        Assert.assertThat(actualSet, hasItem(role));
    }

    @Test
    public void revokeRoleTest() throws IOException {
//GIVEN
        RoleRepository roleRepository = new JdbcRoleRepository();
        User u = new User(1);
        Role r = new Role(1);
        User user = repository.read(new UserSpecificationById(u)).iterator().next();
        Role role = roleRepository.read(new RoleSpecificationById(r)).iterator().next();

        //WHEN
        Boolean actual = repository.revokeRole(user, role);
        Collection<Role> actualSet = roleRepository.read(new RoleSpecificationByUser(user));

        //THEN
        Assert.assertTrue(actual);
        Assert.assertThat(actualSet, not(contains(role)));
    }

}
