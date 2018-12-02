package com.atypon.authentication;

import com.atypon.domain.ContentLicence;
import com.atypon.domain.UserRequest;

public class FreeLicenceAuthenticator implements Authenticator {

    @Override
    public boolean hasAccess(String user, UserRequest userRequest, ContentLicence contentLicence) {
        return true;
    }
}
