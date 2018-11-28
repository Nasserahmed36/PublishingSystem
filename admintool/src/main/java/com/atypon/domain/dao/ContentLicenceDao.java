package com.atypon.domain.dao;

import com.atypon.domain.ContentLicence;

import java.util.List;

public interface ContentLicenceDao {
    void  create(ContentLicence contentLicence);
    List<ContentLicence> get(String contentId);
}
