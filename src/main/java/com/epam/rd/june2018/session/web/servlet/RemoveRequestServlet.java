package com.epam.rd.june2018.session.web.servlet;

import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.exception.LocalizedException;
import com.epam.rd.june2018.session.exception.ValidationException;
import com.epam.rd.june2018.session.service.Interface.BookingRequestService;
import com.epam.rd.june2018.session.service.ServiceFabric;
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

@WebServlet(name = "removeRequestServlet", urlPatterns = {"/user/profile/request/remove", "/manager/profile/request/remove"})
public class RemoveRequestServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RemoveRequestServlet.class);
    private BookingRequestService requestService = ServiceFabric.getBookingRequestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean isManagerPath = req.getServletPath().startsWith("/managers");
        try {
            String idString = req.getParameter("id");
            Integer id = Integer.parseInt(idString);
            ProfileUserDto user = ProfileUserDto.getUserFromSession(req);
            if(user.isManager()){
                requestService.deleteBookingRequest(id);
            }else {
                requestService.deleteUsersBookingRequest(user.getId(), id);
            }
            resp.sendRedirect(req.getContextPath() + (isManagerPath?"/managers/profile":"/user/profile"));
        }
        catch (NumberFormatException e){
            exceptionHandler(req, resp, new ValidationException(e.getMessage(), e));
        }
        catch (ValidationException|ApplicationException e){
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
