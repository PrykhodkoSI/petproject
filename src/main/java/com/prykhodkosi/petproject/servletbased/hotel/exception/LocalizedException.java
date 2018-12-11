package com.prykhodkosi.petproject.servletbased.hotel.exception;

import com.prykhodkosi.petproject.servletbased.hotel.locale.LocaleResourceBundle;

import java.util.Locale;

public class LocalizedException extends RuntimeException {

    private String localeKey;
    private String extraInfo;
    private Locale locale;

    public LocalizedException(String message) {
        super(message);
    }

    public LocalizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocalizedException(String message, String localeKey) {
        super(message);
        this.localeKey = localeKey;
    }
    public LocalizedException(String message, String localeKey, Throwable cause) {
        super(message, cause);
        this.localeKey = localeKey;
    }

    public LocalizedException(String message, String localeKey, String localizedMessageExtraInfo) {
        super(message);
        this.localeKey = localeKey;
        this.extraInfo = localizedMessageExtraInfo;
    }
    public LocalizedException(String message, String localeKey, Throwable cause, String localizedMessageExtraInfo) {
        super(message, cause);
        this.localeKey = localeKey;
        this.extraInfo = localizedMessageExtraInfo;
    }

    public void setLocale(Locale locale){

        this.locale = locale;
    }

    public void setLocale(String locale){

        this.locale = new Locale(locale);
    }

    @Override
    public String getLocalizedMessage() {
        if(localeKey == null || localeKey.isEmpty() || locale == null) {
            return super.getLocalizedMessage();
        }else{
            return extraInfo == null ?
                    LocaleResourceBundle.getMessage(locale, localeKey) :
                    LocaleResourceBundle.getMessage(locale, localeKey) + " " + extraInfo;
        }
    }

}
