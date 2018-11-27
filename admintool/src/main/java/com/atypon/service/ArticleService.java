package com.atypon.service;

import com.atypon.domain.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    boolean create(Article article);

    Map<String, List<Article>> getSubjectToArticlesMap(String issueDoi);

    List<Article> getAll();

    List<Article.Author> getAuthors(String doi);
}
