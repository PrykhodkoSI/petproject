package com.epam.rd.june2018.session.locale;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleResourceBundle {
    public static final String LOCALE_ATTRIBUTE = "locale";
    private static String resourceFile = "locale.messages";//"classes.locale.messages";
    private static ResourceBundle.Control utf8Control = new Utf8Control();

    public static String getMessage(HttpServletRequest request, String messageKey){
        Locale locale = request.getLocale();
        ResourceBundle messages = ResourceBundle.getBundle(resourceFile, locale, utf8Control);
        return messages.getString(messageKey);
    }

    public static String getMessage(Locale locale, String messageKey){
        ResourceBundle messages = ResourceBundle.getBundle(resourceFile, locale, utf8Control);
        return messages.getString(messageKey);
    }
}
