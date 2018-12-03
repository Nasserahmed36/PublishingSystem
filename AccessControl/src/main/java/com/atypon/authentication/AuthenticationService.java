package com.atypon.authentication;

import com.atypon.domain.UserRequest;

public interface AuthenticationService {
    boolean hasAccess(UserRequest userRequest, String user, String contentId);
}
