package com.atypon.service;

import com.atypon.domain.AuthorizedInquirer;
import com.atypon.domain.dao.AuthorizedInquirerDao;


public class AuthorizedInquirerServiceImpl implements AuthorizedInquirerService {
    private final AuthorizedInquirerDao dao;

    public AuthorizedInquirerServiceImpl(AuthorizedInquirerDao authorizedInquirerDao) {
        this.dao = authorizedInquirerDao;
    }

    @Override
    public AuthorizedInquirer get(String name) {
        return dao.get(name);
    }
}
