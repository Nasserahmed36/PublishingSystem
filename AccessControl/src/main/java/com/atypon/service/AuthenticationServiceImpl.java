package com.atypon.service;

import com.atypon.authentication.Authenticator;
import com.atypon.authentication.Authenticators;
import com.atypon.domain.ContentLicence;
import com.atypon.domain.Request;
import com.atypon.domain.dao.ContentLicenceDao;

import java.util.List;

public class AuthenticationServiceImpl implements AuthenticationService {

    private final ContentLicenceDao contentLicenceDao;

    public AuthenticationServiceImpl(ContentLicenceDao contentLicenceDao) {
        this.contentLicenceDao = contentLicenceDao;
    }

    @Override
    public boolean hasAccess(String user, Request request, String contentId) {
        List<ContentLicence> contentLicences = contentLicenceDao.get(contentId);
        for (ContentLicence contentLicence : contentLicences) {
            String licenceName = contentLicence.getLicenceName();
            Authenticator authenticator = Authenticators.getAuthenticator(licenceName);
            if (authenticator == null) {
                continue;
            }
            if (authenticator.hasAccess(user, request, contentLicence)) {
                return true;
            }
        }
        return false;
    }
}
