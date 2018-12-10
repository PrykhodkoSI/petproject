package com.epam.rd.june2018.session.web.servlet;

import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.exception.ValidationException;
import com.epam.rd.june2018.session.service.Interface.BookingRequestService;
import com.epam.rd.june2018.session.service.ServiceFabric;
import com.epam.rd.june2018.session.validator.UserValidator;
import com.epam.rd.june2018.session.validator.UserValidatorImpl;
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
import java.util.Locale;

@WebServlet(name = "acceptRequestServlet", urlPatterns = "/user/profile/request/accept")
public class AcceptRequestServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AcceptRequestServlet.class);
    private UserValidator userValidator = new UserValidatorImpl();
    private BookingRequestService bookingRequestService = ServiceFabric.getBookingRequestService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String idString = req.getParameter("id");
            Integer id = Integer.parseInt(idString);
            ProfileUserDto userFromSession = ProfileUserDto.getUserFromSession(req);
            userValidator.validateProfileUserDto(userFromSession);
            bookingRequestService.accept(userFromSession.getId(), id);

            resp.sendRedirect(req.getContextPath() + "/user/profile");
        }
        catch (ApplicationException|ValidationException e){
            logger.warn(e.getMessage());
            HttpSession session = req.getSession(true);
            Locale locale = (Locale) session.getAttribute("locale");
            e.setLocale(locale);
            session.setAttribute(ViewExceptionDto.EXCEPTION_ATTRIBUTE, ViewExceptionDto.getException(e));
            resp.sendRedirect(req.getContextPath() + "/user/profile");
        }
        catch (Exception e) {

            logger.error(e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/user/profile");
        }
    }
}
