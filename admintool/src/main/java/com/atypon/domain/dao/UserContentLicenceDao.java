package com.atypon.domain.dao;

import com.atypon.domain.UserContentLicence;

import java.util.List;

public interface UserContentLicenceDao {
    boolean create(UserContentLicence userContentLicence);

    List<UserContentLicence> get(String contentLicenceId);
}
