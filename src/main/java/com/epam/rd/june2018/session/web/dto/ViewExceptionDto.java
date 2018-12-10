package com.epam.rd.june2018.session.web.dto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;

public class ViewExceptionDto implements Serializable {
    private static final long serialVersionUID = 5250485484191641518L;
    public static final String EXCEPTION_ATTRIBUTE = "exception";
    private Exception exception;

    public static ViewExceptionDto getException(Exception e){
        return new ViewExceptionDto(e);
    }

    public static ViewExceptionDto getExceptionFromSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session != null){
            ViewExceptionDto exception = (ViewExceptionDto) session.getAttribute(EXCEPTION_ATTRIBUTE);
            session.removeAttribute(EXCEPTION_ATTRIBUTE);
            return exception;
        }
        return null;
    }
    public static void writeExceptionToRequest(HttpServletRequest request){
        ViewExceptionDto exception = getExceptionFromSession(request);
        if(exception != null){
            request.setAttribute(EXCEPTION_ATTRIBUTE, exception);
        }
    }

    public ViewExceptionDto(Exception e) {
        this.exception = e;
    }



    public String getMessage() {
        return exception.getMessage();
    }


    public String getLocalizedMessage() {
        return exception.getLocalizedMessage();
    }


    public Throwable getCause() {
        return exception.getCause();
    }


    public Throwable initCause(Throwable cause) {
        return exception.initCause(cause);
    }


    public String toString() {
        return exception.toString();
    }


    public void printStackTrace() {
        exception.printStackTrace();
    }


    public void printStackTrace(PrintStream s) {
        exception.printStackTrace(s);
    }


    public void printStackTrace(PrintWriter s) {
        exception.printStackTrace(s);
    }


    public Throwable fillInStackTrace() {
        return exception.fillInStackTrace();
    }


    public StackTraceElement[] getStackTrace() {
        return exception.getStackTrace();
    }


    public void setStackTrace(StackTraceElement[] stackTrace) {
        exception.setStackTrace(stackTrace);
    }
}
