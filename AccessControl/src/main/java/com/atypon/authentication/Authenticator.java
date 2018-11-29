package com.atypon.authentication;

import com.atypon.domain.ContentLicence;
import com.atypon.domain.Request;

public interface Authenticator {
    boolean hasAccess(String user, Request request, ContentLicence contentLicence);
}
