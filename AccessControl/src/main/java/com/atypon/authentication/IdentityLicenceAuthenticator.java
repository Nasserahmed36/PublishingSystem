package com.atypon.authentication;

import com.atypon.domain.ContentLicence;
import com.atypon.domain.UserRequest;
import com.atypon.domain.UserContentLicence;
import com.atypon.service.UserContentLicenceService;

import java.util.List;

public class IdentityLicenceAuthenticator implements Authenticator {

    private static final UserContentLicenceService userContentLicenceService=
            AuthenticatorsDependencies.getUserContentLicenceDao();

    @Override
    public boolean hasAccess(String user, UserRequest userRequest, ContentLicence contentLicence) {
        List<UserContentLicence> userLicences = userContentLicenceService.get(user, contentLicence.getId());
        return !userLicences.isEmpty();
    }
}
