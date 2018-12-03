package com.atypon.service;

import com.atypon.domain.ArticleSubmission;

import java.io.InputStream;
import java.util.List;

public interface ArticleSubmissionService {
    int save(ArticleSubmission articleSubmission, InputStream fileInputStream);

    boolean updateStatus(int id, String status);

    ArticleSubmission get(int id);

    List<ArticleSubmission> getAll();
}
