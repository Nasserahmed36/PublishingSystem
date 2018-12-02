package com.atypon.controller;


import com.atypon.consensus.QuerySignatureComputer;
import com.atypon.consensus.SignatureComputer;
import com.atypon.consensus.Signer;
import com.atypon.domain.AuthorizedInquirer;
import com.atypon.domain.HasAccessQuery;
import com.atypon.domain.UserRequest;
import com.atypon.service.AuthenticationService;
import com.atypon.service.AuthorizedInquirerService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class AccessControlController {

    @Autowired
    private AuthorizedInquirerService authorizedInquirerService;

    @Autowired
    private AuthenticationService authenticationService;

    private final SignatureComputer<HasAccessQuery> signatureComputer =
            new QuerySignatureComputer();


    @ModelAttribute("hasAccessQuery")
    public HasAccessQuery foo(@RequestBody String body) {
        HasAccessQuery hasAccessQuery = new Gson().fromJson(body, HasAccessQuery.class);
        AuthorizedInquirer inquirer = authorizedInquirerService.get(hasAccessQuery.getInquirerName());
        return hasAccessQuery;
//        try {
//            if (signatureComputer.verify(hasAccessQuery, hasAccessQuery.getInquirerSignature(), inquirer.getPublicKey())) {
//                return hasAccessQuery;
//            }
//        } catch (Exception ignore) {
//        }
//        return null;
    }


    @RequestMapping(value = "/hasAccess", method = {RequestMethod.POST})
    public boolean hasAccess(@ModelAttribute HasAccessQuery hasAccessQuery,
                             HttpServletRequest request) {
        if (hasAccessQuery == null) {
            return false;
        }
        return authenticationService.hasAccess(extractUserRequest(request),
                hasAccessQuery.getUsername(),
                hasAccessQuery.getContentId());
    }

    private UserRequest extractUserRequest(HttpServletRequest request) {
        UserRequest userRequest = new UserRequest();
        userRequest.setUrl(request.getRemoteAddr());
        userRequest.setUrl(request.getRequestURL().toString());
        userRequest.setMethod(request.getMethod());
        return userRequest;
    }


}
