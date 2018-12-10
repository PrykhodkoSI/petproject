package com.epam.rd.june2018.session.web.filter;

import com.epam.rd.june2018.session.web.dto.ViewExceptionDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "exceptionFilter", urlPatterns = "/*")
public class ExceptionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        if(servletRequest.getMethod().equals("GET")){
            ViewExceptionDto.writeExceptionToRequest(servletRequest);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
