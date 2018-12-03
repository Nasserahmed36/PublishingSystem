package com.atypon.authentication;

import com.atypon.service.UserContentLicenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StaticContextInitializer {

    @Autowired
    private UserContentLicenceService userContentLicenceService;

    @PostConstruct
    public void init() {
        AuthenticatorsDependencies.userContentLicenceService(userContentLicenceService);
    }
}