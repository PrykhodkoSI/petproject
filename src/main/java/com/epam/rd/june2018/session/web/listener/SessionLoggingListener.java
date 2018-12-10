package com.epam.rd.june2018.session.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionLoggingListener implements HttpSessionListener {

    private static final Logger logger = LoggerFactory.getLogger(SessionLoggingListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.trace("Create session: " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.trace("Destroy session: " + se.getSession().getId());
    }
}
