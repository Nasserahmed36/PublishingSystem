package com.atypon.service;

import com.atypon.domain.ContentLicence;

import java.util.List;

public interface ContentLicenceService {
    boolean  create(ContentLicence contentLicence);
    boolean delete(int id);
    List<ContentLicence> get(String contentId);
    List<ContentLicence> getAll();

}
