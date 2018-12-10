package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.repository.specification.Interface.ApartmentSpecification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ApartmentSpecificationByDates implements ApartmentSpecification {
    private LocalDate arrival;
    private LocalDate departure;

    public ApartmentSpecificationByDates(LocalDate arrival, LocalDate departure) {
        this.arrival = arrival;
        this.departure = departure;
    }

    @Override
    public String toSqlClauses() {
        if(arrival != null && departure != null){
            String arrivalString = arrival.format(DateTimeFormatter.ISO_DATE);
            String departureString = departure.format(DateTimeFormatter.ISO_DATE);
            return String.format(" left join booking_requests on apartments.id = booking_requests.apartment_id  where (booking_requests.arrival_date>'%s' AND booking_requests.arrival_date>='%s' AND booking_requests.is_accepted) OR (booking_requests.departure_date<='%s' AND booking_requests.departure_date<'%s' AND booking_requests.is_accepted) OR (booking_requests.arrival_date is null AND  booking_requests.departure_date is null) GROUP BY apartments.id",
                    arrivalString, departureString,
                    arrivalString, departureString);
        }
        throw new DatabaseException("Bad specification");
    }
}