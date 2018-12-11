package com.prykhodkosi.petproject.servletbased.hotel.service.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.repository.LiquibaseManager;
import com.prykhodkosi.petproject.servletbased.hotel.exception.ApplicationException;
import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentStatus;
import com.prykhodkosi.petproject.servletbased.hotel.service.Impl.ApartmentServiceImpl;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.CreateApartmentDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileApartmentDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.UpdateApartmentDto;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ApartmentServiceTest {

    private static LiquibaseManager config = new LiquibaseManager();
    private ApartmentService service = new ApartmentServiceImpl();
    //private BookingRequestService requestService = new BookingRequestServiceImpl();

    @Before
    public void before() {
        config.reinitDB();
    }

//    @After
//    public void after(){
//        config.reinitDB();
//    }

    @AfterClass
    public static void afterClass(){
        config.reinitDB();
    }

    @Test
    public void getApartment() {
        //GIVEN
        Integer id = 4;
        UpdateApartmentDto updateApartmentDto = new UpdateApartmentDto (id, "Right facade Prime room", 22000, 2,  1,1);
        ProfileApartmentDto expected = new ProfileApartmentDto(id, "Right facade Prime room", 22000, 2, 1, "economy", 1,"free");

        //WHEN
        service.putApartment(updateApartmentDto);
        ProfileApartmentDto actual = service.getApartment(id);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void getApartments() {
        //GIVEN
        List<ProfileApartmentDto> expected = Arrays.asList(
                new ProfileApartmentDto(1, "Left facade Prime room", 20000, 3,   -1, "semilux",  -1, "free"),
                new ProfileApartmentDto(2, "Right facade Prime room", 15000, 2,  -1, "standard", -1, "booked"),
                new ProfileApartmentDto(3, "Middle facade Prime room", 25000, 4, -1, "luxury",   -1, "occupied"));
        //WHEN
        List<ProfileApartmentDto> actual = service.getApartments();

        //THEN
        assertNotNull(actual);
        Assert.assertThat(actual.toArray(), is(arrayContainingInAnyOrder(actual.toArray())));
    }

    @Test
    public void postApartment() {
        //GIVEN
        CreateApartmentDto createApartmentDto = new CreateApartmentDto("Left facade Prime room", 20000, 3,  1,1);

        //WHEN
        ProfileApartmentDto actual = service.postApartment(createApartmentDto);

        //THEN
        assertNotNull(actual);
    }

    @Test
    public void putApartment() {
        //GIVEN
        Integer id = 4;
        UpdateApartmentDto updateApartmentDto = new UpdateApartmentDto (id, "Right facade Prime room", 22000, 2,  1,1);
        ProfileApartmentDto expected = new ProfileApartmentDto(id, "Right facade Prime room", 22000, 2, 1, "economy", 1,"free");

        //WHEN
        service.putApartment(updateApartmentDto);
        ProfileApartmentDto actual = service.getApartment(updateApartmentDto.getId());

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void deleteApartment() {
        //GIVEN
        Integer id = 1;
        expectedException.expect(ApplicationException.class);
        expectedException.expectMessage("No such Apartment: " + id);
        ProfileApartmentDto expected = new ProfileApartmentDto(1, "Left facade Prime room", 20000, 3, 3, "semilux", 1, "free");

        //WHEN
        ProfileApartmentDto actual = service.deleteApartment(id);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);

        //WHEN
        service.getApartment(id);

        //THEN
        //ApplicationException
    }

    @Test
    public void getApartmentsByStatus() {
        //GIVEN
        ApartmentStatus status = new ApartmentStatus(1, "free");
        List<ProfileApartmentDto> expected = service.getFreeApartments().stream()
                .filter(profileApartmentDto -> profileApartmentDto.getApartmentStatusName().equals(status.getName()))
                .collect(Collectors.toList());

        //WHEN
        List<ProfileApartmentDto> actual = service.getApartmentsByStatus(status);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void getFreeApartments() {
        //GIVEN
        List<ProfileApartmentDto> expected = service.getFreeApartments().stream()
                .filter(profileApartmentDto -> profileApartmentDto.getApartmentStatusName().equals("free"))
                .collect(Collectors.toList());

        //WHEN
        List<ProfileApartmentDto> actual = service.getFreeApartments();

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }


    @Test
    public void getFreeApartmentsByDates() {
        //GIVEN
        LocalDate arrival = LocalDate.parse("2022-09-15", DateTimeFormatter.ISO_DATE);
        LocalDate departure = LocalDate.parse("2022-09-20", DateTimeFormatter.ISO_DATE);
        List<ProfileApartmentDto> expected = Arrays.asList(
                new ProfileApartmentDto(1,"Left facade Prime room",20000,3, 3, "semilux", 1,"free"));

        //WHEN
        service.deleteApartment(4);
        service.deleteApartment(5);
        service.deleteApartment(6);
        List<ProfileApartmentDto> actual = service.getFreeApartments(arrival, departure);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}
/*
booking_requests.apartment_id is not null AND not((booking_requests.arrival_date>'%s' AND booking_requests.arrival_date>='%s') " +
                            "OR (booking_requests.departure_date<='%s' AND booking_requests.departure_date<'%s'
* */