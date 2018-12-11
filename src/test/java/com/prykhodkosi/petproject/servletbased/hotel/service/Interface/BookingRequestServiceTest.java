package com.prykhodkosi.petproject.servletbased.hotel.service.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.repository.LiquibaseManager;
import com.prykhodkosi.petproject.servletbased.hotel.converter.UserConverter;
import com.prykhodkosi.petproject.servletbased.hotel.exception.ApplicationException;
import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentClass;
import com.prykhodkosi.petproject.servletbased.hotel.model.User;
import com.prykhodkosi.petproject.servletbased.hotel.service.Impl.BookingRequestServiceImpl;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.CreateBookingRequestDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileApartmentDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileBookingRequestDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileUserDto;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BookingRequestServiceTest {

    private static LiquibaseManager config = new LiquibaseManager();
    private BookingRequestService service = new BookingRequestServiceImpl();

    @Before
    public void before() {
        config.reinitDB();
    }

    @AfterClass
    public static void afterClass(){
        config.reinitDB();
    }

    @Test
    public void getBookingRequest() {
        //GIVEN
        Integer id = 1;
        ProfileUserDto expectedUser = new ProfileUserDto(1);
        expectedUser.setName("Vasia");
        expectedUser.setSurname("adminin");
        expectedUser.setEmail("admin1@gmail.com");
        expectedUser.setAge(18);
        ProfileApartmentDto expectedApartment = new ProfileApartmentDto(1,"Left facade Prime room", 20000, 3,3,null,1,null);
        ProfileBookingRequestDto expected = new ProfileBookingRequestDto(1,
                2,
                3,
                LocalDate.parse("2018-09-10", DateTimeFormatter.ISO_DATE),
                LocalDate.parse("2018-09-15", DateTimeFormatter.ISO_DATE),
                1,
                1,
                true);
        expected.setApartmentClassName("semilux");
        expected.setUser(expectedUser);
        expected.setApartment(expectedApartment);


        //WHEN
        ProfileBookingRequestDto actual = service.getBookingRequest(id);


        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void getBookingRequests() {
        //GIVEN
        List<ProfileBookingRequestDto> expected = Arrays.asList(
                new ProfileBookingRequestDto(1,	2,	3,	LocalDate.parse("2018-09-10", DateTimeFormatter.ISO_DATE),	LocalDate.parse("2018-09-15", DateTimeFormatter.ISO_DATE),	1,	1,	true),
                new ProfileBookingRequestDto(2,	3,	2,	LocalDate.parse("2018-09-10", DateTimeFormatter.ISO_DATE),	LocalDate.parse("2018-09-15", DateTimeFormatter.ISO_DATE),	2,	2,	true),
                new ProfileBookingRequestDto(3,	2,	3,	LocalDate.parse("2018-09-10", DateTimeFormatter.ISO_DATE),	LocalDate.parse("2018-09-15", DateTimeFormatter.ISO_DATE),	1,	null,false),
                new ProfileBookingRequestDto(4,	3,	2,	LocalDate.parse("2018-09-05", DateTimeFormatter.ISO_DATE),	LocalDate.parse("2018-09-10", DateTimeFormatter.ISO_DATE),	2,	2,	true));
        //WHEN
        List<ProfileBookingRequestDto> actual = service.getBookingRequests();

        //THEN
        assertNotNull(actual);
        Assert.assertThat(actual.toArray(), is(arrayContainingInAnyOrder(actual.toArray())));
    }

    @Test
    public void getBookingRequestsByUser() {
        //GIVEN
        UserConverter userConverter = new UserConverter();
        ProfileUserDto user = userConverter.asUserDto(new User(1, "Vasia", "adminin", "admin", "admin1@gmail.com", 18));
        List<ProfileBookingRequestDto> expected = Arrays.asList(
                new ProfileBookingRequestDto(1,	2,	3,	LocalDate.parse("2018-09-10", DateTimeFormatter.ISO_DATE),	LocalDate.parse("2018-09-15", DateTimeFormatter.ISO_DATE),	1,	1,	true),
                new ProfileBookingRequestDto(3,	2,	3,	LocalDate.parse("2018-09-10", DateTimeFormatter.ISO_DATE),	LocalDate.parse("2018-09-15", DateTimeFormatter.ISO_DATE),	1,	null,false));
        //WHEN
        List<ProfileBookingRequestDto> actual = service.getBookingRequestsByUser(user);

        //THEN
        assertNotNull(actual);
        Assert.assertThat(actual.toArray(), is(arrayContainingInAnyOrder(actual.toArray())));
    }

    @Test
    public void postBookingRequest() {
        //GIVEN
        ProfileUserDto userDto = new ProfileUserDto();
        userDto.setId(1);
        userDto.setName("Vasia");
        userDto.setSurname("Pupkin");
        userDto.setEmail("prykhodko.s.i@gmail.com");
        userDto.setAge(5);
        ApartmentClass apartmentClass = new ApartmentClass(1,"economy");
        CreateBookingRequestDto createBookingRequestDto = new CreateBookingRequestDto(LocalDate.now(),LocalDate.now().plusDays(2), userDto,apartmentClass.getId(), apartmentClass.getName(), 1);

        //WHEN
        ProfileBookingRequestDto actual = service.postBookingRequest(createBookingRequestDto);

        //THEN
        assertNotNull(actual);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void deleteBookingRequest() {
        //GIVEN
        Integer id = 1;
        expectedException.expect(ApplicationException.class);
        expectedException.expectMessage("No such BookingRequest: " + id);
        ProfileUserDto expectedUser = new ProfileUserDto(1);
        expectedUser.setName("Vasia");
        expectedUser.setSurname("adminin");
        expectedUser.setEmail("admin1@gmail.com");
        expectedUser.setAge(18);
        ProfileApartmentDto expectedApartment = new ProfileApartmentDto(1,"Left facade Prime room", 20000, 3,3,null,1,null);
        ProfileBookingRequestDto expected = new ProfileBookingRequestDto(1,	2,	3,	LocalDate.parse("2018-09-10", DateTimeFormatter.ISO_DATE),	LocalDate.parse("2018-09-15", DateTimeFormatter.ISO_DATE),	1,	1,	true);
        expected.setApartmentClassName("semilux");
        expected.setUser(expectedUser);
        expected.setApartment(expectedApartment);

        //WHEN
        ProfileBookingRequestDto actual = service.deleteBookingRequest(id);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);

        //WHEN
        service.getBookingRequest(id);

        //THEN
        //ApplicationException
    }
}