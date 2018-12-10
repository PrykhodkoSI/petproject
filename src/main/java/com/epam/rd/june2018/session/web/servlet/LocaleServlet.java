package com.epam.rd.june2018.session.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "localeServlet", urlPatterns = {"/locale"})
public class LocaleServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LocaleServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = req.getHeader("referer");
        try {
            String locale = req.getParameter("locale");
            req.getSession(true).setAttribute("locale", new Locale(locale));
            resp.sendRedirect(url);
        }
        catch (Exception e){

            logger.error(e.getMessage());
            resp.sendRedirect(url);
        }
    }
}
