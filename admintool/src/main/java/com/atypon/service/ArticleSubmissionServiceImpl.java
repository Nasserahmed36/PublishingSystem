package com.atypon.service;

import com.atypon.domain.ArticleSubmission;
import com.atypon.domain.dao.ArticleSubmissionDao;

import java.io.InputStream;
import java.util.List;

public class ArticleSubmissionServiceImpl implements ArticleSubmissionService {

    private ArticleSubmissionDao dao;

    public ArticleSubmissionServiceImpl(ArticleSubmissionDao dao) {
        this.dao = dao;
    }

    @Override
    public int save(ArticleSubmission articleSubmission, InputStream fileInputStream) {
        return dao.save(articleSubmission, fileInputStream);
    }

    @Override
    public boolean updateStatus(int id, String status) {
        return dao.updateStatus(id, status);
    }

    @Override
    public ArticleSubmission get(int id) {
        return dao.get(id);
    }


    @Override
    public List<ArticleSubmission> getAll() {
        return dao.getAll();
    }

}
