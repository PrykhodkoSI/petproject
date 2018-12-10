package com.epam.rd.june2018.session.web.servlet;

import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.exception.ValidationException;
import com.epam.rd.june2018.session.service.Interface.BillService;
import com.epam.rd.june2018.session.service.Interface.BookingRequestService;
import com.epam.rd.june2018.session.service.ServiceFabric;
import com.epam.rd.june2018.session.validator.UserValidator;
import com.epam.rd.june2018.session.validator.UserValidatorImpl;
import com.epam.rd.june2018.session.web.dto.ProfileBillDto;
import com.epam.rd.june2018.session.web.dto.ProfileBookingRequestDto;
import com.epam.rd.june2018.session.web.dto.ProfileUserDto;
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

@WebServlet(name = "userProfileServlet", urlPatterns = {"/user/profile"})
public class UserProfileServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(UserProfileServlet.class);
    private BookingRequestService bookingRequestService = ServiceFabric.getBookingRequestService();
    private BillService billService = ServiceFabric.getBillService();
    private UserValidator userValidator = new UserValidatorImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            ProfileUserDto userFromSession = ProfileUserDto.getUserFromSession(req);
            userValidator.validateProfileUserDto(userFromSession);
            List<ProfileBookingRequestDto> requests = bookingRequestService.getBookingRequestsByUserNotAccepted(userFromSession);
            List<ProfileBillDto> bills = billService.getBillsByUserNotPaid(userFromSession);
            req.setAttribute("requests", requests);
            req.setAttribute("bills", bills);
            req.getRequestDispatcher("/WEB-INF/userProfile.jsp").forward(req, resp);
        }
        catch (ValidationException|ApplicationException e){

            logger.warn(e.getMessage());
            HttpSession session = req.getSession(true);
            Locale locale = (Locale) session.getAttribute("locale");
            e.setLocale(locale);
            session.setAttribute(ViewExceptionDto.EXCEPTION_ATTRIBUTE, ViewExceptionDto.getException(e));
            resp.sendRedirect(req.getContextPath() + "/rooms");
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
