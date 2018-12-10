package com.epam.rd.june2018.session.web.servlet;

import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.exception.ValidationException;
import com.epam.rd.june2018.session.service.Interface.BillService;
import com.epam.rd.june2018.session.service.Interface.BookingRequestService;
import com.epam.rd.june2018.session.service.ServiceFabric;
import com.epam.rd.june2018.session.web.dto.ProfileBillDto;
import com.epam.rd.june2018.session.web.dto.ProfileBookingRequestDto;
import com.epam.rd.june2018.session.web.dto.ViewExceptionDto;
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
