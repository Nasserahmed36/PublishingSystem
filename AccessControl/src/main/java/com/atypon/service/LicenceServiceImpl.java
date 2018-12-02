package com.atypon.service;

import com.atypon.domain.Licence;
import com.atypon.domain.dao.LicenceDao;

import java.util.List;

public class LicenceServiceImpl implements LicenceService {
    private final LicenceDao dao;

    public LicenceServiceImpl(LicenceDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Licence> getAll() {
        return dao.getAll();
    }
}
