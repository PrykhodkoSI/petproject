package com.prykhodkosi.petproject.servletbased.hotel.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "authenticationFilter", urlPatterns = {"/user/*", "/managers/*"})
public class AuthenticationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

            HttpSession session = httpRequest.getSession();
            if (session == null || session.getAttribute("user") == null) {
                logger.warn("Unauthorized tried to break through: " + request.getRemoteAddr());
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
                return;
            }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
