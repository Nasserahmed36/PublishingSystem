package com.atypon.service;

import com.atypon.domain.Article;
import com.atypon.domain.dao.ArticleDao;

import java.util.List;
import java.util.Map;

public class ArticleServiceImpl implements ArticleService {
    private final ArticleDao dao;

    public ArticleServiceImpl(ArticleDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean create(Article article) {
        return dao.create(article);
    }

    @Override
    public Map<String, List<Article>> getSubjectToArticlesMap(String issueDoi) {
        return dao.getSubjectToArticlesMap(issueDoi);
    }

    @Override
    public List<Article.Author> getAuthors(String doi) {
        return dao.getAuthors(doi);
    }
}
