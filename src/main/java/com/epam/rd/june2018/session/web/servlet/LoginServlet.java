package com.epam.rd.june2018.session.web.servlet;

import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.exception.ValidationException;
import com.epam.rd.june2018.session.service.Interface.UserService;
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

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private UserService userService = ServiceFabric.getUserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
        catch (Exception e) {

            logger.error(e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            ProfileUserDto user = userService.loginUser(email, password);
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);

            resp.sendRedirect(req.getContextPath() + "/rooms");
        }
        catch (ValidationException | ApplicationException e){

            logger.warn(e.getMessage());
            HttpSession session = req.getSession(true);
            Locale locale = (Locale) session.getAttribute("locale");
            e.setLocale(locale);
            session.setAttribute(ViewExceptionDto.EXCEPTION_ATTRIBUTE, ViewExceptionDto.getException(e));
            resp.sendRedirect(req.getContextPath() + "/login");
        }
        catch (Exception e){

            logger.error(e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
