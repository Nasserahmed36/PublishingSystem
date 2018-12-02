package com.atypon.service;

import com.atypon.domain.Request;

public interface AuthenticationService {
    boolean hasAccess(Request request, String user, String contentId);
}
