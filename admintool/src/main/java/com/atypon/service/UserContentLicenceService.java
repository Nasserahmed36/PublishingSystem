package com.atypon.service;

import com.atypon.domain.UserContentLicence;

import java.util.List;

public interface UserContentLicenceService {
    boolean create(UserContentLicence userContentLicence);

    boolean delete(int id);

    List<UserContentLicence> get(String username, String contentLicenceId);

    List<UserContentLicence> getAll();
}
