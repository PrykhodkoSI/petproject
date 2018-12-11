package com.prykhodkosi.petproject.servletbased.hotel.config;

import com.prykhodkosi.petproject.servletbased.hotel.exception.ApplicationException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertiesManager {

    private static final String PROPERTY_FILE_NAME = "db.properties";

    private Properties properties;

    public Properties getApplicationProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {
                properties.load(stream);
                Class.forName(properties.getProperty("driver"));
                String passwordKey = "password";
                properties.replace(passwordKey, resolveValueWithEnvVars(properties.getProperty(passwordKey)));
            } catch (IOException | ClassNotFoundException e) {
                throw new ApplicationException("Failed to load property file", e);
            }
        }
        return properties;
    }

    public static InputStream getStreamOfResource(String resource) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    }
    public static Path getPathToResource(String resource) throws URISyntaxException {
        return Paths.get(Thread.currentThread().getContextClassLoader().getResource(resource).toURI());
    }
    public static String getResource(String resource) {
        return Thread.currentThread().getContextClassLoader().getResource(resource).toString();
    }


    // Returns Env variable or value if var does not exists
    private static String resolveValueWithEnvVars(String value) {
        if (null == value) {
            return null;
        }
        Pattern p = Pattern.compile("\\$\\{(\\w+)\\}|\\$(\\w+)");
        Matcher m = p.matcher(value);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String envVarName = null == m.group(1) ? m.group(2) : m.group(1);
            String envVarValue = System.getenv(envVarName);
            if(envVarValue == null){
                return value;
            }
            m.appendReplacement(sb,
                    null == envVarValue ? "" : Matcher.quoteReplacement(envVarValue));
        }
        m.appendTail(sb);
        return sb.toString();
    }

}
