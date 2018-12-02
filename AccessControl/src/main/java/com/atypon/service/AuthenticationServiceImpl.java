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
    public boolean hasAccess(Request request, String user, String contentId) {
        List<ContentLicence> contentLicences = contentLicenceDao.get(contentId);
        for (ContentLicence contentLicence : contentLicences) {
            String licenceName = contentLicence.getLicenceName();
            Authenticator licenceAuthenticator = Authenticators.getAuthenticator(licenceName);
            if (licenceAuthenticator == null) {
                continue;
            }
            if (licenceAuthenticator.hasAccess(user, request, contentLicence)) {
                return true;
            }
        }
        return false;
    }
}
