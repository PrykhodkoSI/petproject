package com.epam.rd.june2018.session.web.servlet;

import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.exception.LocalizedException;
import com.epam.rd.june2018.session.exception.ValidationException;
import com.epam.rd.june2018.session.service.Interface.BillService;
import com.epam.rd.june2018.session.service.ServiceFabric;
import com.epam.rd.june2018.session.web.dto.ProfileBillDto;
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

@WebServlet(name = "payBillServlet", urlPatterns = {"/user/profile/bill/pay"})
public class PayBillServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(PayBillServlet.class);
    private BillService billService = ServiceFabric.getBillService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String idString = req.getParameter("id");
            Integer id = Integer.parseInt(idString);
            ProfileBillDto billDto = billService.payBill(id);
            resp.sendRedirect(req.getContextPath() + "/user/profile");
        }
        catch (NumberFormatException e){
            exceptionHandler(req, resp, new ValidationException(e.getMessage(), e));
        }
        catch (ValidationException|ApplicationException e){
            exceptionHandler(req, resp, e);
        }
        catch (Exception e){

            logger.warn(e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/user/profile");
        }
    }

    private void exceptionHandler(HttpServletRequest req, HttpServletResponse resp, LocalizedException e) throws IOException {

        logger.warn(e.getMessage());
        HttpSession session = req.getSession(true);
        Locale locale = (Locale) session.getAttribute("locale");
        e.setLocale(locale);
        session.setAttribute(ViewExceptionDto.EXCEPTION_ATTRIBUTE, ViewExceptionDto.getException(e));
        resp.sendRedirect(req.getContextPath() + "/user/profile");
    }
}
