package com.epam.rd.june2018.session.exception;


public class ApplicationException extends LocalizedException {
    private static final long serialVersionUID = 4281187329795668968L;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(String message, String localeKey) {
        super(message, localeKey);
    }
    public ApplicationException(String message, String localeKey, Throwable cause) {
        super(message, localeKey, cause);
    }

    public ApplicationException(String message, String localeKey, String localizedMessageExtraInfo) {
        super(message, localeKey, localizedMessageExtraInfo);
    }

    public ApplicationException(String message, String localeKey, Throwable cause, String localizedMessageExtraInfo) {
        super(message, localeKey, cause, localizedMessageExtraInfo);
    }
}
