package com.epam.rd.june2018.session.service.Interface;

import com.epam.rd.june2018.session.repository.LiquibaseManager;
import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.model.ApartmentClass;
import com.epam.rd.june2018.session.service.Impl.ApartmentClassServiceImpl;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.junit.Assert.*;

public class ApartmentClassServiceTest {

    private static LiquibaseManager config = new LiquibaseManager();
    private ApartmentClassService service = new ApartmentClassServiceImpl();


    @Before
    public void before() {
        config.reinitDB();
    }

    @AfterClass
    public static void afterClass(){
        config.reinitDB();
    }

    @Test
    public void getApartmentClass() {
        //GIVEN
        Integer id = 2;
        ApartmentClass expected = new ApartmentClass(id, "standard");

        //WHEN
        ApartmentClass actual = service.getApartmentClass(id);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void getApartmentClassByName() {
        //GIVEN
        String name = "standard";
        ApartmentClass expected = new ApartmentClass(-1, name);

        //WHEN
        ApartmentClass actual = service.getApartmentClassByName(name);

        //THEN
        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void getApartmentClasses() {
        //GIVEN
        List<ApartmentClass> expected = Arrays.asList(
                new ApartmentClass(1,	"economy"),
                new ApartmentClass(2,	"standard"),
                new ApartmentClass(3,	"semilux"),
                new ApartmentClass(4,	"luxury"),
                new ApartmentClass(5,	"business"),
                new ApartmentClass(6,	"elites"));

        //WHEN
        List<ApartmentClass> actual = service.getApartmentClasses();

        //THEN
        assertNotNull(actual);
        Assert.assertThat(actual.toArray(), is(arrayContainingInAnyOrder(actual.toArray())));
    }

    @Test
    public void postApartmentClass() {
        //GIVEN
        String name = "extraClass";
        Integer id = 7;
        ApartmentClass expected = new ApartmentClass(id, name);
        ApartmentClass insertionEntity = new ApartmentClass(name);

        //WHEN
        service.postApartmentClass(insertionEntity);
        ApartmentClass actual = service.getApartmentClass(id);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void putApartmentClass() {
        //GIVEN
        String name = "testClass";
        ApartmentClass expected = new ApartmentClass(6, name);

        //WHEN
        service.putApartmentClass(expected);
        ApartmentClass actual = service.getApartmentClass(expected.getId());

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void deleteApartmentClass() {
        //GIVEN
        Integer id = 1;
        expectedException.expect(ApplicationException.class);
        expectedException.expectMessage("No such ApartmentClass: " + id);
        ApartmentClass expected = new ApartmentClass(1, "economy");

        //WHEN
        ApartmentClass actual = service.deleteApartmentClass(id);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);

        //WHEN
        service.getApartmentClass(id);

        //THEN
        //ApplicationException
    }
}