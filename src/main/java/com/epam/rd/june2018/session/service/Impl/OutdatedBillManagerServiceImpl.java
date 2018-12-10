package com.epam.rd.june2018.session.service.Impl;

import com.epam.rd.june2018.session.service.Interface.BillService;
import com.epam.rd.june2018.session.service.Interface.BookingRequestService;
import com.epam.rd.june2018.session.service.Interface.OutdatedBillManagerService;
import com.epam.rd.june2018.session.service.ServiceFabric;
import com.epam.rd.june2018.session.web.dto.ProfileBillDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class OutdatedBillManagerServiceImpl implements OutdatedBillManagerService {

    private static OutdatedBillManagerService instance;// = new OutdatedBillManagerServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(OutdatedBillManagerServiceImpl.class);
    private static BillService billService = ServiceFabric.getBillService();
    private static BookingRequestService requestService = ServiceFabric.getBookingRequestService();
    private Timer clearTimer = new Timer(true);

    public synchronized static OutdatedBillManagerService getInstance(){
        if(instance == null){
            instance = new OutdatedBillManagerServiceImpl();
        }
        return instance;
    }

    private OutdatedBillManagerServiceImpl() {
        try {
            ZonedDateTime midDay = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).plusHours(12);
            LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
            if (now.isAfter(midDay.toLocalDateTime())) {
                clearOutdated();
            }
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    clearOutdated();
                }
            };
            Date startTime = Date.from(midDay.toInstant());
            clearTimer.schedule(task, startTime, 1000 * 60 * 60 * 24);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
    }


    @Override
    public void clearOutdated() {
        List<ProfileBillDto> billsByOutdated = billService.getBillsByOutdated();
        System.out.println("outdated = " + billsByOutdated.toString());
        billsByOutdated.forEach(profileBillDto -> {
            try {
                requestService.deleteBookingRequest(profileBillDto.getBookingRequest().getId());
            }
            catch (Exception e){
                logger.error(e.getMessage());
            }
        });
    }

}
