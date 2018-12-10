package com.epam.rd.june2018.session.web.servlet;

import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.exception.LocalizedException;
import com.epam.rd.june2018.session.exception.ValidationException;
import com.epam.rd.june2018.session.service.Interface.ApartmentService;
import com.epam.rd.june2018.session.service.Interface.BookingRequestService;
import com.epam.rd.june2018.session.service.ServiceFabric;
import com.epam.rd.june2018.session.web.dto.ProfileApartmentDto;
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


@WebServlet(name = "attachApartmentServlet", urlPatterns = {"/user/profile/request/attach", "/managers/profile/request/attach"})
public class AttachApartmentServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(AttachApartmentServlet.class);
    private BookingRequestService requestService = ServiceFabric.getBookingRequestService();
    private ApartmentService apartmentService = ServiceFabric.getApartmentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean isManagerPath = req.getServletPath().startsWith("/managers");
        try {
            String requestIdString = req.getParameter("id");
            Integer requestId = Integer.parseInt(requestIdString);
            ProfileUserDto user = ProfileUserDto.getUserFromSession(req);
            ProfileBookingRequestDto request;
            if(user.isManager()){
                request = requestService.getBookingRequest(requestId);

            }else {
                request = requestService.getUsersBookingRequest(user. getId(), requestId);
            }
            List<ProfileApartmentDto> freeApartments = apartmentService.getFreeApartments(request.getArrivalDate(), request.getDepartureDate());

            req.setAttribute("rolePath", isManagerPath ? "managers" : "user");
            req.setAttribute("request", request);
            req.setAttribute("apartments", freeApartments);

            req.getRequestDispatcher("/WEB-INF/attachApartment.jsp").forward(req, resp);
        }
        catch (NumberFormatException e){
            exceptionHandler(req, resp, new ValidationException(e.getMessage(), e));
        }
        catch (ApplicationException|ValidationException e){
            exceptionHandler(req, resp, e);
        }
        catch (Exception e){
            logger.warn(e.getMessage());
            resp.sendRedirect(req.getContextPath() + (isManagerPath?"/managers/profile":"/user/profile"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean isManagerPath = req.getServletPath().startsWith("/managers");
        try {
            Integer requestId = Integer.parseInt(req.getParameter("requestId"));
            Integer apartmentId = Integer.parseInt(req.getParameter("apartmentId"));
            ProfileBookingRequestDto requestDto = requestService.setApartment(requestId, apartmentId);

            resp.sendRedirect(req.getContextPath() + (isManagerPath?"/managers/profile":"/user/profile"));
        }
        catch (ApplicationException|ValidationException e){
            exceptionHandler(req, resp, e);
        }
        catch (Exception e) {

            logger.warn(e.getMessage());
            resp.sendRedirect(req.getContextPath() + (isManagerPath?"/managers/profile":"/user/profile"));
        }
    }

    private void exceptionHandler(HttpServletRequest req, HttpServletResponse resp, LocalizedException e) throws IOException {
        logger.warn(e.getMessage());
        HttpSession session = req.getSession(true);
        Locale locale = (Locale) session.getAttribute("locale");
        e.setLocale(locale);
        session.setAttribute(ViewExceptionDto.EXCEPTION_ATTRIBUTE, ViewExceptionDto.getException(e));
        boolean isManagerPath = req.getServletPath().startsWith("/managers");
        resp.sendRedirect(req.getContextPath() + (isManagerPath?"/managers/profile":"/user/profile"));
    }
}
