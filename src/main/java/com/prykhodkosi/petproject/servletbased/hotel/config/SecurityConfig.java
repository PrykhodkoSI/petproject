package com.prykhodkosi.petproject.servletbased.hotel.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SecurityConfig {
    private Set<String> protectedURLs = new HashSet<>();
    private Set<String> clientProtectedURLs = new HashSet<>();

    public SecurityConfig() {
        protectedURLs.add("/rooms");
        protectedURLs.add("/profile");

        clientProtectedURLs.add("/profile");
    }

    public Set<String> getProtectedURLs() {
        return Collections.unmodifiableSet(protectedURLs);
    }

    public Set<String> getClientProtectedURLs() {
        return Collections.unmodifiableSet(clientProtectedURLs);
    }
}
