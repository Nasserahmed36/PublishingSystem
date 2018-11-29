package com.atypon.authentication;

import com.atypon.domain.ContentLicence;
import com.atypon.domain.Request;
import com.atypon.domain.UserContentLicence;
import com.atypon.domain.dao.UserContentLicenceDao;

import java.util.List;

public class IdentityLicenceAuthenticator implements Authenticator {

    private static final UserContentLicenceDao userContentLicenceDao =
            AuthenticatorsDependencies.getUserContentLicenceDao();

    @Override
    public boolean hasAccess(String user, Request request, ContentLicence contentLicence) {
        List<UserContentLicence> userLicences = userContentLicenceDao.get(user, contentLicence.getContentId());
        return !userLicences.isEmpty();
    }
}
