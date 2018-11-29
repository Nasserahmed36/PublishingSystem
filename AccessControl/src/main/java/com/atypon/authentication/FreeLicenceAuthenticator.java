package com.atypon.authentication;

import com.atypon.domain.ContentLicence;
import com.atypon.domain.Request;

public class FreeLicenceAuthenticator implements Authenticator {

    @Override
    public boolean hasAccess(String user, Request request, ContentLicence contentLicence) {
        return true;
    }
}
