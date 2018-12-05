package com.atypon.acs;

import com.atypon.domain.UserRequest;

public interface AuthenticationService {
    boolean hasAccess(UserRequest userRequest, String username, String contentId);
}
