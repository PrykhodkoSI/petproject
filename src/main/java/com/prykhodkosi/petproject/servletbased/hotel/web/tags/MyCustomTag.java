package com.prykhodkosi.petproject.servletbased.hotel.web.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class MyCustomTag extends TagSupport {
    private static final long serialVersionUID = -5343153492538839003L;
    private String message;

    @Override
    public int doStartTag() throws JspException {
        try {
                pageContext.getOut().write("<h1>" + message + "</h1>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public void release() {
        super.release();
        this.message = null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
