package com.atypon.service;

import com.atypon.domain.Request;

public interface AuthenticationService {
    boolean hasAccess(String user, Request request, String contentId);
}
