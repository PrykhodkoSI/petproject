package com.epam.rd.june2018.session.validator;

public interface StringValidator {

    void validateString(String value,String invalidMessage);

    void validateWithRegex(String value, String regex, String invalidMessage);

    void validateString(String value,String invalidMessage, String localeKey);

    void validateWithRegex(String value, String regex, String invalidMessage, String localeKey);

    void validateString(String value,String invalidMessage, String localeKey, String extraLocaleInfo);

    void validateWithRegex(String value, String regex, String invalidMessage, String localeKey, String extraLocaleInfo);
}
