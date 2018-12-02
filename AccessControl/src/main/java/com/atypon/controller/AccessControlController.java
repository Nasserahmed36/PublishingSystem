package com.atypon.controller;


import com.atypon.domain.Request;
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
        Request request = extractRequest(httpServletRequest);
        return authenticationService.hasAccess(request, username, contentId);
    }

    @RequestMapping(value = "/hasAccess", method = {RequestMethod.POST})
    public Object hasAccess(@RequestParam String username, @RequestParam String contentId,
                            HttpServletRequest httpServletRequest, @RequestBody String body) {
        System.out.println(body);
        Request request = extractRequest(httpServletRequest);
        return authenticationService.hasAccess(request, username, contentId);
    }

    private Request extractRequest(HttpServletRequest httpServletRequest) {
        Request request = new Request();
        request.setIp(httpServletRequest.getRemoteAddr());
        request.setMethod("GET");
        request.setUrl(httpServletRequest.getRequestURL().toString());
        return request;
    }

//    private static class RequestBody {
//        String username;
//
//    }


}
