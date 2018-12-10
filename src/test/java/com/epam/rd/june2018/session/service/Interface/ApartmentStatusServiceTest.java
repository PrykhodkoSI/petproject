package com.epam.rd.june2018.session.service.Interface;

import com.epam.rd.june2018.session.repository.LiquibaseManager;
import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.model.ApartmentStatus;
import com.epam.rd.june2018.session.service.Impl.ApartmentStatusServiceImpl;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;
import java.util.NavigableMap;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ApartmentStatusServiceTest {

    private static LiquibaseManager config = new LiquibaseManager();
    private ApartmentStatusService service = new ApartmentStatusServiceImpl();

    @Before
    public void before() {
        config.reinitDB();
    }

    @AfterClass
    public static void afterClass(){
        config.reinitDB();
    }
    
    @Test
    public void getApartmentStatus() {
        //GIVEN
        Integer id = 1;
        ApartmentStatus expected = new ApartmentStatus(id, "free");

        //WHEN
        ApartmentStatus actual = service.getApartmentStatus(id);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void getApartmentStatusByName() {
        //GIVEN
        String name = "free";
        ApartmentStatus expected = new ApartmentStatus(1, name);

        //WHEN
        ApartmentStatus actual = service.getApartmentStatusByName(name);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void getApartmentStatuses() {
        //GIVEN
        List<ApartmentStatus> expected = Arrays.asList(
                new ApartmentStatus(1,	"free"),
                new ApartmentStatus(2,	"booked"),
                new ApartmentStatus(3,	"occupied"),
                new ApartmentStatus(4,	"unavailable"));

        //WHEN
        List<ApartmentStatus> actual = service.getApartmentStatuses();

        //THEN
        assertNotNull(actual);
        Assert.assertThat(actual.toArray(), is(arrayContainingInAnyOrder(actual.toArray())));
    }

    @Test
    public void postApartmentStatus() {
        //GIVEN
        String name = "extraStatus";
        ApartmentStatus expected = new ApartmentStatus(5, name);
        ApartmentStatus insertionEntity = new ApartmentStatus(-1, name);

        //WHEN
        service.postApartmentStatus(insertionEntity);
        ApartmentStatus actual = service.getApartmentStatusByName(name);


                //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void putApartmentStatus() {
        //GIVEN
        String name = "extraStatus";
        ApartmentStatus expected = new ApartmentStatus(1, name);

        //WHEN
        ApartmentStatus actual = service.putApartmentStatus(expected);
        ApartmentStatus actualApartmentStatus = service.getApartmentStatus(actual.getId());

        //THEN
        assertNotNull(actual);
        assertNotNull(actualApartmentStatus);
        assertEquals(expected, actual);
        assertEquals(expected, actualApartmentStatus);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void deleteApartmentStatus() {
        //GIVEN
        Integer id = 1;
        expectedException.expect(ApplicationException.class);
        expectedException.expectMessage("No such ApartmentStatus: " + id);
        ApartmentStatus expected = new ApartmentStatus(1, "free");


        //WHEN
        ApartmentStatus actual = service.deleteApartmentStatus(id);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);

        //WHEN
        service.getApartmentStatus(id);

        //THEN
        //ApplicationException
    }
}