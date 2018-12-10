package com.epam.rd.june2018.session.Util;

import java.io.UnsupportedEncodingException;

public class StringDecoder {
    public static String decodeISO88591(String parameter) {
        try {
            return new String(parameter.getBytes("ISO-8859-1"),"UTF8");
        } catch (UnsupportedEncodingException e) {
            return parameter;
        }
    }
}
