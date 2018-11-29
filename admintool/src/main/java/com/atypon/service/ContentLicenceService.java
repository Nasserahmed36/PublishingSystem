package com.atypon.service;

import com.atypon.domain.ContentLicence;

import java.util.List;

public interface ContentLicenceService {
    boolean  create(ContentLicence contentLicence);
    List<ContentLicence> get(String contentId);
    List<ContentLicence> getAll();

}
