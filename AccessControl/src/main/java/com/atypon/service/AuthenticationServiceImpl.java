package com.atypon.service;

import com.atypon.domain.Request;

public class AuthenticationServiceImpl implements AuthenticationService{


    @Override
    public boolean hasAccess(String user, Request request, String contentId) {
        return false;
    }
}
