package com.atypon.authentication;

import com.atypon.domain.Request;

public class FreeLicenceAuthenticator implements Authenticator {
    @Override
    public boolean hasAccess(String user, Request request, String contentId) {
        return true;
    }
}
