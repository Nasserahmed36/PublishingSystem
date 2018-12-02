package com.atypon.domain.dao;

import com.atypon.domain.ArticleSubmission;

import java.io.InputStream;
import java.util.List;

public interface ArticleSubmissionDao {
    int save(ArticleSubmission submission, InputStream fileInputStream);

    boolean updateStatus(int id, String status);

    ArticleSubmission get(int id);

    List<ArticleSubmission> getAll();

}
