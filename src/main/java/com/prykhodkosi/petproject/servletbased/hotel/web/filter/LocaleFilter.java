package com.prykhodkosi.petproject.servletbased.hotel.web.filter;

import com.prykhodkosi.petproject.servletbased.hotel.locale.LocaleResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "localeFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LocaleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            HttpSession session = servletRequest.getSession(true);
            Locale locale = (Locale) session.getAttribute(LocaleResourceBundle.LOCALE_ATTRIBUTE);
            if(locale == null) {
                locale = request.getLocale();
                session.setAttribute(LocaleResourceBundle.LOCALE_ATTRIBUTE, locale);
            }
            response.setLocale(locale);
        }
        catch (Exception e){
            logger.warn(e.getMessage());
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
