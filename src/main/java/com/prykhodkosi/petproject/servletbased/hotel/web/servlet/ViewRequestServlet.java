package com.prykhodkosi.petproject.servletbased.hotel.web.servlet;

import com.prykhodkosi.petproject.servletbased.hotel.exception.ApplicationException;
import com.prykhodkosi.petproject.servletbased.hotel.exception.LocalizedException;
import com.prykhodkosi.petproject.servletbased.hotel.exception.ValidationException;
import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.ApartmentClassService;
import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.ApartmentStatusService;
import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.BookingRequestService;
import com.prykhodkosi.petproject.servletbased.hotel.service.ServiceFabric;
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
import java.util.Locale;

@WebServlet(name = "viewRequestServlet", urlPatterns = {"/user/profile/request/view", "/managers/profile/request/view"})
public class ViewRequestServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ViewRequestServlet.class);

    private BookingRequestService requestService = ServiceFabric.getBookingRequestService();
    private ApartmentClassService apartmentClassService = ServiceFabric.getApartmentClassService();
    private ApartmentStatusService apartmentStatusService = ServiceFabric.getApartmentStatusService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean isManagerPath = req.getServletPath().startsWith("/managers");
        try {
            String idString = req.getParameter("id");
            Integer id = Integer.parseInt(idString);
            ProfileBookingRequestDto bookingRequest = requestService.getBookingRequestWithApartment(id);
            req.setAttribute("rolePath", isManagerPath ? "managers" : "user");
            req.setAttribute("request", bookingRequest);
            req.getRequestDispatcher("/WEB-INF/requestView.jsp").forward(req, resp);
        }
        catch (NumberFormatException e){
            exceptionHandler(req, resp, new ValidationException(e.getMessage(), e));
        }
        catch (ValidationException| ApplicationException e){
            exceptionHandler(req,resp,e);
        }
        catch (Exception e){
            logger.error(e.getMessage());
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
