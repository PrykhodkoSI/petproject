package com.epam.rd.june2018.session.service.Interface;

import com.epam.rd.june2018.session.repository.LiquibaseManager;
import com.epam.rd.june2018.session.converter.UserConverter;
import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.model.User;
import com.epam.rd.june2018.session.service.Impl.BillServiceImpl;
import com.epam.rd.june2018.session.web.dto.CreateBillDto;
import com.epam.rd.june2018.session.web.dto.ProfileBillDto;
import com.epam.rd.june2018.session.web.dto.ProfileUserDto;
import com.epam.rd.june2018.session.web.dto.UpdateBillDto;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BillServiceTest {

    private static LiquibaseManager config = new LiquibaseManager();
    private BillService service = new BillServiceImpl();

    @Before
    public void before() {
        config.reinitDB();
    }

    @AfterClass
    public static void afterClass(){
        config.reinitDB();
    }

    @Test
    public void getBill() {
        //GIVEN
        Integer id = 2;
        ProfileBillDto expected = new ProfileBillDto(2,	55000,	true,  LocalDate.parse("2017-09-09", DateTimeFormatter.ISO_DATE), 	LocalDate.parse("2017-09-10", DateTimeFormatter.ISO_DATE),	2,	2);


        //WHEN
        ProfileBillDto actual = service.getBill(id);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void getBills() {
        //GIVEN
        List<ProfileBillDto> expected = Arrays.asList(
                new ProfileBillDto(1,	50000,	false, LocalDate.parse("2017-08-09", DateTimeFormatter.ISO_DATE)	, null,	1,	1),
                new ProfileBillDto(2,	55000,	true,  LocalDate.parse("2017-09-09", DateTimeFormatter.ISO_DATE), 	LocalDate.parse("2017-09-10", DateTimeFormatter.ISO_DATE),	2,	2),
                new ProfileBillDto(3,	40000,	false, LocalDate.parse("2018-08-31", DateTimeFormatter.ISO_DATE)	,	null,3,	3));
        //WHEN
        List<ProfileBillDto> actual = service.getBills();

        //THEN
        assertNotNull(actual);
        Assert.assertThat(actual.toArray(), is(arrayContainingInAnyOrder(actual.toArray())));
    }

    @Test
    public void getBillsByUser() {
        //GIVEN
        UserConverter userConverter = new UserConverter();
        ProfileUserDto user = userConverter.asUserDto(new User(1, "Vasia", "adminin", "admin", "admin1@gmail.com", 18));
        List<ProfileBillDto> expected = Arrays.asList(
                new ProfileBillDto(1,
                        50000,
                        false,
                        LocalDate.parse("2017-08-09", DateTimeFormatter.ISO_DATE),
                        null,
                        1,
                        1));
        //WHEN
        List<ProfileBillDto> actual = service.getBillsByUser(user);

        //THEN
        assertNotNull(actual);
        Assert.assertThat(actual.toArray(), is(arrayContainingInAnyOrder(actual.toArray())));
    }

    @Test
    public void postBill() {
        //GIVEN
        CreateBillDto createBillDto = new CreateBillDto(50000, LocalDate.now(), 1, 1);

        //WHEN
        ProfileBillDto actual = service.postBill(createBillDto);

        //THEN
        assertNotNull(actual);
    }

    @Test
    public void putBill() {
        //GIVEN
        UpdateBillDto updateBillDto = new UpdateBillDto (2,	50000,	false,  LocalDate.parse("2017-09-10", DateTimeFormatter.ISO_DATE), 	LocalDate.parse("2017-09-11", DateTimeFormatter.ISO_DATE),	1,	1);
        ProfileBillDto expected = new ProfileBillDto(2,	50000,	false,  LocalDate.parse("2017-09-10", DateTimeFormatter.ISO_DATE), 	LocalDate.parse("2017-09-11", DateTimeFormatter.ISO_DATE),	1,	1);

        //WHEN
        ProfileBillDto actual = service.putBill(updateBillDto);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void deleteBill() {
        //GIVEN
        Integer id = 2;
        expectedException.expect(ApplicationException.class);
        expectedException.expectMessage("No such Bill: " + id);
        ProfileBillDto expected = new ProfileBillDto(2,	55000,	true,  LocalDate.parse("2017-09-09", DateTimeFormatter.ISO_DATE), 	LocalDate.parse("2017-09-10", DateTimeFormatter.ISO_DATE),	2,	2);

        //WHEN
        ProfileBillDto actual = service.deleteBill(id);

        //THEN
        assertNotNull(actual);
        assertEquals(expected, actual);

        //WHEN
        service.getBill(id);

        //THEN
        //ApplicationException
    }
}