package com.epam.rd.june2018.session;

import com.epam.rd.june2018.session.Util.StringEncryptor;
import org.junit.Test;
import org.junit.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tests {

    @Test
    public void regexTest(){
        String email = "prykhodko.s.i@gmail.com";
        Pattern compile = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        Matcher matcher = compile.matcher(email);
        matcher.find();
        String group = matcher.group();

        Assert.assertEquals(email, group);
    }

    @Test
    public void encode(){
        //GIVEN
        String value = "admin";
        String expected = StringEncryptor.encryptString(value);

        //WHEN
        String actual = StringEncryptor.encryptString(value);

        //THEN
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
        Assert.assertNotEquals(value, actual);
    }

}
