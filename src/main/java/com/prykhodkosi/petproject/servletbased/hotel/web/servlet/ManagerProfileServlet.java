package com.prykhodkosi.petproject.servletbased.hotel.web.servlet;

import com.prykhodkosi.petproject.servletbased.hotel.exception.ApplicationException;
import com.prykhodkosi.petproject.servletbased.hotel.exception.ValidationException;
import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.BillService;
import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.BookingRequestService;
import com.prykhodkosi.petproject.servletbased.hotel.service.ServiceFabric;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileBillDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileBookingRequestDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ViewExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "managerProfileServlet", urlPatterns = "/managers/profile")
public class ManagerProfileServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ManagerProfileServlet.class);
    private BookingRequestService requestService = ServiceFabric.getBookingRequestService();
    private BillService billService = ServiceFabric.getBillService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {//TODO
        try {
            List<ProfileBookingRequestDto> notAcceptedRequests = requestService.getBookingRequestNotAccepted();
            List<ProfileBillDto> notPaidBills = billService.getBillsNotPaid();
            req.setAttribute("requests", notAcceptedRequests);
            req.setAttribute("bills", notPaidBills);
            req.getRequestDispatcher("/WEB-INF/managerProfile.jsp").forward(req,resp);
        }
        catch (ValidationException | ApplicationException e){

            logger.warn(e.getMessage());
            HttpSession session = req.getSession(true);
            Locale locale = (Locale) session.getAttribute("locale");
            e.setLocale(locale);
            session.setAttribute(ViewExceptionDto.EXCEPTION_ATTRIBUTE, ViewExceptionDto.getException(e));
            resp.sendRedirect(req.getContextPath() + "/rooms");
        }
        catch (Exception e){

            logger.error(e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/rooms");
        }
    }

}
