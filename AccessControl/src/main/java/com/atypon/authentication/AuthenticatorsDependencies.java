package com.atypon.authentication;


import com.atypon.domain.dao.UserContentLicenceDao;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticatorsDependencies {
    @Autowired
    private static UserContentLicenceDao userContentLicenceDao;

    public static UserContentLicenceDao getUserContentLicenceDao() {
        return userContentLicenceDao;
    }
}