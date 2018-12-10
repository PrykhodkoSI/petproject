package com.epam.rd.june2018.session.config;

import com.epam.rd.june2018.session.exception.ApplicationException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesManager {

    private static final String PROPERTY_FILE_NAME = "db.properties";

    private Properties properties;

    public Properties getApplicationProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {
                properties.load(stream);
                Class.forName(properties.getProperty("driver"));
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

}
