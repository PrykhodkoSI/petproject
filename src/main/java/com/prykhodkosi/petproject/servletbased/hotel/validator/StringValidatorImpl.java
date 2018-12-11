package com.prykhodkosi.petproject.servletbased.hotel.validator;

import com.prykhodkosi.petproject.servletbased.hotel.exception.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidatorImpl implements StringValidator{

    public void validateString(String value, String invalidMessage) {
        this.validateString(value, invalidMessage, null, null);
    }
    public void validateWithRegex(String value, String regex, String invalidMessage) {
        this.validateWithRegex(value,regex, invalidMessage, null,null);
    }

    @Override
    public void validateString(String value, String invalidMessage, String localeKey) {
        this.validateString(value, invalidMessage, localeKey, null);
    }

    @Override
    public void validateWithRegex(String value, String regex, String invalidMessage, String localeKey) {
        this.validateWithRegex(value,regex, invalidMessage, localeKey,null);
    }

    @Override
    public void validateString(String value, String invalidMessage, String localeKey, String extraLocaleInfo) {
        if (value == null || value.isEmpty()) {
            throw new ValidationException(invalidMessage, localeKey, extraLocaleInfo);
        }
    }

    @Override
    public void validateWithRegex(String value, String regex, String invalidMessage, String localeKey, String extraLocaleInfo) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if(!matcher.matches()){
            throw new ValidationException(invalidMessage, localeKey, extraLocaleInfo);
        }
    }
}
