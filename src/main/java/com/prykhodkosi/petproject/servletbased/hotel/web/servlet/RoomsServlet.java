package com.prykhodkosi.petproject.servletbased.hotel.web.servlet;

import com.prykhodkosi.petproject.servletbased.hotel.exception.ApplicationException;
import com.prykhodkosi.petproject.servletbased.hotel.exception.LocalizedException;
import com.prykhodkosi.petproject.servletbased.hotel.exception.ValidationException;
import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.ApartmentService;
import com.prykhodkosi.petproject.servletbased.hotel.service.ServiceFabric;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileApartmentDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ViewExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@WebServlet(name = "roomsServlet", urlPatterns = "/rooms")
public class RoomsServlet extends HttpServlet {
    private enum SortOrder{
        ASC,
        DESC
    }
    private enum SortType{
        Price,
        Capacity,
        Class,
        Status
    }
    private static List<String> SORT_OPTIONS = Arrays.stream(SortType.values()).map(Enum::name).collect(Collectors.toList());
    private static List<String> SORT_ORDERS = Arrays.stream(SortOrder.values()).map(Enum::name).collect(Collectors.toList());
    private static final Logger logger = LoggerFactory.getLogger(RoomsServlet.class);
    private ApartmentService apartmentService = ServiceFabric.getApartmentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String sort = req.getParameter("sort");
            String order = req.getParameter("order");
            List<ProfileApartmentDto> freeApartments = apartmentService.getApartments();
            if(SORT_OPTIONS.contains(sort) && SORT_ORDERS.contains(order)) {
                SortOrder sortOrder = SortOrder.valueOf(order);
                SortType sortType = SortType.valueOf(sort);
                ApartmentComparator comparator = new ApartmentComparator(sortOrder, sortType);
                freeApartments = freeApartments
                        .stream()
                        .sorted(comparator)
                        .collect(Collectors.toList());
            }
            req.setAttribute("rooms", freeApartments);
            req.setAttribute("sortOptions", SORT_OPTIONS);
            req.setAttribute("sortOrders", SORT_ORDERS);
            req.getRequestDispatcher("/WEB-INF/rooms.jsp").forward(req, resp);
        }
        catch (IllegalArgumentException e){
            exceptionHandler(req, resp, new ValidationException(e.getMessage(), e));
        }
        catch (ApplicationException |ValidationException e){
            exceptionHandler(req, resp, e);
        }
        catch (Exception e) {

            logger.warn(e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void exceptionHandler(HttpServletRequest req, HttpServletResponse resp, LocalizedException e) throws IOException {
        logger.warn(e.getMessage());
        HttpSession session = req.getSession(true);
        Locale locale = (Locale) session.getAttribute("locale");
        e.setLocale(locale);
        session.setAttribute(ViewExceptionDto.EXCEPTION_ATTRIBUTE, ViewExceptionDto.getException(e));
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    private class ApartmentComparator implements Comparator<ProfileApartmentDto>{
        SortOrder order;
        SortType sortType;

        ApartmentComparator(SortOrder order, SortType sortType) {
            this.order = order;
            this.sortType = sortType;
        }
        @Override
        public int compare(ProfileApartmentDto o1, ProfileApartmentDto o2) {
            int res = 0;
            switch (sortType){
                case Price:
                    res = o1.getPrice() - o2.getPrice();
                    break;
                case Capacity:
                    res = o1.getCapacity() - o2.getCapacity();
                    break;
                case Class:
                    res = o1.getApartmentClassName().compareTo(o2.getApartmentClassName());
                    break;
                case Status:
                    res = o1.getApartmentStatusName().compareTo(o2.getApartmentStatusName());
                    break;
            }
            return res * (order == SortOrder.ASC ? 1 : -1);
        }
    }
}
