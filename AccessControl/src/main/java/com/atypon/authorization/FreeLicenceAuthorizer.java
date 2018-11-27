package com.atypon.authorization;

import com.atypon.domain.Request;

public class FreeLicenceAuthorizer implements Authorizer {
    @Override
    public boolean hasAccess(String user, Request request, String contentId) {
        return false;
    }
}
