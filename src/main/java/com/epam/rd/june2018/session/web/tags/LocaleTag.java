package com.epam.rd.june2018.session.web.tags;

import com.epam.rd.june2018.session.locale.LocaleResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;

public class LocaleTag  extends TagSupport {
    private static final long serialVersionUID = 7892256529687104983L;
    private static Locale defaultLocale = Locale.getDefault();
    private String key;
    private Locale locale;

    public static Locale getDefaultLocale() {
        return defaultLocale;
    }

    public static void setDefaultLocale(Locale defaultLocale) {
        LocaleTag.defaultLocale = defaultLocale;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String message = LocaleResourceBundle.getMessage(locale == null ? defaultLocale : locale, key);
            pageContext.getOut().write(message);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public void release() {
        super.release();
        this.key = null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLocaleString() {
        return this.locale.getLanguage();
    }

    public void setLocaleString(String localeString) {
        this.locale = Locale.forLanguageTag(localeString);
    }
}
