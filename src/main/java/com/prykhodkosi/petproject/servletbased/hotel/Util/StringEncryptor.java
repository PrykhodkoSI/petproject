package com.prykhodkosi.petproject.servletbased.hotel.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringEncryptor {
    public static String encryptString(String data){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            sb.append(getSalt(data));
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static String getSalt(String data){
        char code = 'k';
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            builder.append(data.charAt(i)^code);
        }
        return builder.toString();
    }
}
