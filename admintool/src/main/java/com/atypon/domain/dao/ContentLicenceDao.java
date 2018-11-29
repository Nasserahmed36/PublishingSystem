package com.atypon.domain.dao;

import com.atypon.domain.ContentLicence;

import java.util.List;

public interface ContentLicenceDao {
    boolean  create(ContentLicence contentLicence);
    List<ContentLicence> get(String contentId);
    List<ContentLicence> getAll();
}
