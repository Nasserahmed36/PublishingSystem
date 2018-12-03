package com.atypon.authentication;


import com.atypon.domain.dao.UserContentLicenceDao;
import com.atypon.service.UserContentLicenceService;


public class AuthenticatorsDependencies {
    private static UserContentLicenceService userContentLicenceService;


    public static UserContentLicenceService getUserContentLicenceDao() {
        return userContentLicenceService;
    }

    public static void userContentLicenceService(UserContentLicenceService userContentLicenceService) {
        AuthenticatorsDependencies.userContentLicenceService = userContentLicenceService;
    }

}