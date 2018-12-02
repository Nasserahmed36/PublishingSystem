package com.atypon.controller;


import com.atypon.domain.UserRequest;
import com.atypon.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class AccessControlController {

    @Autowired
    private AuthenticationService authenticationService;


    @RequestMapping(value = "/*", method = {RequestMethod.GET})
    public Object hasAccess1(@RequestParam String username, @RequestParam String contentId,
                            HttpServletRequest httpServletRequest) {
        UserRequest userRequest = extractRequest(httpServletRequest);
        return authenticationService.hasAccess(userRequest, username, contentId);
    }

    @RequestMapping(value = "/hasAccess", method = {RequestMethod.POST})
    public Object hasAccess(@RequestParam String username, @RequestParam String contentId,
                            HttpServletRequest httpServletRequest, @RequestBody String body) {
        System.out.println(body);
        UserRequest userRequest = extractRequest(httpServletRequest);
        return authenticationService.hasAccess(userRequest, username, contentId);
    }

    private UserRequest extractRequest(HttpServletRequest httpServletRequest) {
        UserRequest userRequest = new UserRequest();
        userRequest.setIp(httpServletRequest.getRemoteAddr());
        userRequest.setMethod("GET");
        userRequest.setUrl(httpServletRequest.getRequestURL().toString());
        return userRequest;
    }

//    private static class RequestBody {
//        String username;
//
//    }


}
