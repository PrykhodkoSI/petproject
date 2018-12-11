package com.prykhodkosi.petproject.servletbased.hotel.exception;


public class ValidationException extends LocalizedException{
    private static final long serialVersionUID = -2203079825444938417L;


    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message, String localeKey) {
        super(message, localeKey);
    }

    public ValidationException(String message, String localeKey, Throwable cause) {
        super(message, localeKey, cause);
    }

    public ValidationException(String message, String localeKey, String localizedMessageExtraInfo) {
        super(message, localeKey, localizedMessageExtraInfo);
    }

    public ValidationException(String message, String localeKey, Throwable cause, String localizedMessageExtraInfo) {
        super(message, localeKey, cause, localizedMessageExtraInfo);
    }
}
