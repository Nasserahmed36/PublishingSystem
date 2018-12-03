package com.atypon.service;

import com.atypon.domain.UserContentLicence;
import com.atypon.domain.dao.UserContentLicenceDao;

import java.util.List;

public class UserContentLicenceServiceImpl implements UserContentLicenceService {

    private final UserContentLicenceDao dao;

    public UserContentLicenceServiceImpl(UserContentLicenceDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean create(UserContentLicence userContentLicence) {
        return dao.create(userContentLicence);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id);
    }

    @Override
    public List<UserContentLicence> get(String username, int contentLicenceId) {
        return dao.get(username, contentLicenceId);
    }

    @Override
    public List<UserContentLicence> getAll() {
        return dao.getAll();
    }
}
