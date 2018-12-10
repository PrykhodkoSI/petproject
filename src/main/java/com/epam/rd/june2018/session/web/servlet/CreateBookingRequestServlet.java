package com.epam.rd.june2018.session.web.servlet;

import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.exception.LocalizedException;
import com.epam.rd.june2018.session.exception.ValidationException;
import com.epam.rd.june2018.session.model.ApartmentClass;
import com.epam.rd.june2018.session.service.Interface.ApartmentClassService;
import com.epam.rd.june2018.session.service.Interface.BookingRequestService;
import com.epam.rd.june2018.session.service.ServiceFabric;
import com.epam.rd.june2018.session.web.dto.CreateBookingRequestDto;
import com.epam.rd.june2018.session.web.dto.ViewExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@WebServlet(name = "createRequestServlet", urlPatterns = {"/user/profile/request/create"})
public class CreateBookingRequestServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CreateBookingRequestServlet.class);
    private ApartmentClassService apartmentClassService = ServiceFabric.getApartmentClassService();
    private BookingRequestService requestService = ServiceFabric.getBookingRequestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<ApartmentClass> apartmentClasses = apartmentClassService.getApartmentClasses();
            req.setAttribute("classes", apartmentClasses.stream()
                    .sorted(Comparator.comparingInt(ApartmentClass::getId))
                    .collect(Collectors.toList()));
            req.getRequestDispatcher("/WEB-INF/createRequest.jsp").forward(req, resp);
        }
        catch (ApplicationException|ValidationException e){
            exceptionHandler(req, resp, e);
        }
        catch (Exception e) {

            logger.error(e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/user/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            CreateBookingRequestDto bookingRequest = CreateBookingRequestDto.getBookingRequest(req);
            requestService.postBookingRequest(bookingRequest);
            resp.sendRedirect(req.getContextPath() + "/user/profile");
        }
        catch (ApplicationException|ValidationException e){
            exceptionHandler(req, resp, e);
        }
        catch (Exception e){

            logger.error(e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/user/profile");
        }
    }

    private void exceptionHandler(HttpServletRequest req, HttpServletResponse resp, LocalizedException e) throws IOException {

        logger.warn(e.getMessage());
        HttpSession session = req.getSession(true);
        Locale locale = (Locale) session.getAttribute("locale");
        e.setLocale(locale);
        session.setAttribute(ViewExceptionDto.EXCEPTION_ATTRIBUTE, ViewExceptionDto.getException(e));
        resp.sendRedirect(req.getContextPath() + "/user/profile/request/create");
    }
}
