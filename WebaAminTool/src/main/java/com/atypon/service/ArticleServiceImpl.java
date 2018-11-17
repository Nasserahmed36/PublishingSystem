package com.atypon.service;

import com.atypon.domain.Article;
import com.atypon.domain.dao.ArticleDao;

public class ArticleServiceImpl implements ArticleService {
    private final ArticleDao dao;

    public ArticleServiceImpl(ArticleDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean save(Article article) {
        return dao.save(article);
    }
}
