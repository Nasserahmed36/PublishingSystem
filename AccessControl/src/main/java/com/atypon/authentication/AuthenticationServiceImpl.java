package com.atypon.authentication;

import com.atypon.domain.ContentLicence;
import com.atypon.domain.UserRequest;
import com.atypon.domain.dao.ContentLicenceDao;

import java.util.List;

public class AuthenticationServiceImpl implements AuthenticationService {

    private final ContentLicenceDao contentLicenceDao;

    public AuthenticationServiceImpl(ContentLicenceDao contentLicenceDao) {
        this.contentLicenceDao = contentLicenceDao;
    }

    @Override
    public boolean hasAccess(UserRequest userRequest, String user, String contentId) {
        List<ContentLicence> contentLicences = contentLicenceDao.get(contentId);
        for (ContentLicence contentLicence : contentLicences) {
            String licenceName = contentLicence.getLicenceName();
            Authenticator licenceAuthenticator = Authenticators.getAuthenticator(licenceName);
            if (licenceAuthenticator == null) {
                continue;
            }
            if (licenceAuthenticator.hasAccess(user, userRequest, contentLicence)) {
                return true;
            }
        }
        return false;
    }
}
