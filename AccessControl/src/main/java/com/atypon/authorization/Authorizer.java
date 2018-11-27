package com.atypon.authorization;

import com.atypon.domain.Request;

public interface Authorizer {
    boolean hasAccess(String user, Request request, String contentId);
}
