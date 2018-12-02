package com.atypon.authentication;

import com.atypon.domain.ContentLicence;
import com.atypon.domain.UserRequest;

public interface Authenticator {
    boolean hasAccess(String user, UserRequest userRequest, ContentLicence contentLicence);
}
