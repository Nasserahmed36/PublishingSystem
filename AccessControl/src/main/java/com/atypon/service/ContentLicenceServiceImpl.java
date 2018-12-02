package com.atypon.service;

import com.atypon.domain.ContentLicence;
import com.atypon.domain.dao.ContentLicenceDao;

import java.util.List;

public class ContentLicenceServiceImpl implements ContentLicenceService {
    private final ContentLicenceDao dao;

    public ContentLicenceServiceImpl(ContentLicenceDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean create(ContentLicence contentLicence) {
        return dao.create(contentLicence);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id);
    }

    @Override
    public List<ContentLicence> get(String contentId) {
        return dao.get(contentId);
    }

    @Override
    public List<ContentLicence> getAll() {
        return dao.getAll();
    }
}
