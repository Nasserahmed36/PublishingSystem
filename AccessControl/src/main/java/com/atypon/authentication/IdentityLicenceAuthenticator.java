package com.atypon.authentication;

import com.atypon.context.ApplicationContext;
import com.atypon.domain.Request;

public class IdentityLicenceAuthenticator implements Authenticator{
    @Override
    public boolean hasAccess(String user, Request request, int contentLicenceId) {
        ApplicationContext.getAttribute("")
    }
}
