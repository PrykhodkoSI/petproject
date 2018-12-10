package com.epam.rd.june2018.session.web.filter;

import com.epam.rd.june2018.session.config.SecurityConfig;
import com.epam.rd.june2018.session.service.Impl.RoleServiceImpl;
import com.epam.rd.june2018.session.service.Interface.RoleService;
import com.epam.rd.june2018.session.web.dto.ProfileUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebFilter(filterName = "roleFilter", urlPatterns = "/managers/*")
public class RoleFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RoleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

            ProfileUserDto user = ProfileUserDto.getUserFromSession(httpRequest);
            if(user.getId() != -1) {
                if (!user.isManager()) {
                    logger.warn("User tried to break through: " + user.getId());
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
                    return;
                }
            }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
