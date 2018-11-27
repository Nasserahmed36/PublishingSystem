package com.atypon.domain;

public interface Licence {

    /**
     * @param identity can be null
     * @param request  can be null
     */
    boolean hasAccess(Identity identity, Request request, String licenceBody);

}
