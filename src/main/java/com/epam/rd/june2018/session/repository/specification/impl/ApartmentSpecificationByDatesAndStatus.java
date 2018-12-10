package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.ApartmentStatus;
import com.epam.rd.june2018.session.repository.specification.Interface.ApartmentSpecification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ApartmentSpecificationByDatesAndStatus implements ApartmentSpecification {
    private LocalDate arrival;
    private LocalDate departure;
    private ApartmentStatus status;

    public ApartmentSpecificationByDatesAndStatus(LocalDate arrival, LocalDate departure, ApartmentStatus status) {
        this.arrival = arrival;
        this.departure = departure;
        this.status = status;
    }

    @Override
    public String toSqlClauses() {
        if(arrival != null && departure != null && status != null && status.getName() != null){
            String arrivalString = arrival.format(DateTimeFormatter.ISO_DATE);
            String departureString = departure.format(DateTimeFormatter.ISO_DATE);
            return String.format("  left join apartment_statuses on apartments.apartment_status_id = apartment_statuses.id " +
                            "where apartments.id not in " +
                            "(" +
                            "SELECT booking_requests.apartment_id from booking_requests " +
                            "where booking_requests.apartment_id is not null AND not((booking_requests.arrival_date>'%s' AND booking_requests.arrival_date>='%s') " +
                            "OR (booking_requests.departure_date<='%s' AND booking_requests.departure_date<'%s')) " +
                            "GROUP BY booking_requests.apartment_id" +
                            ") " +
                            "and apartment_statuses.name='%s'",
                    arrivalString, departureString,
                    arrivalString, departureString,
                    status.getName());
        }
        throw new DatabaseException("Bad specification");
    }
}
