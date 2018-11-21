package com.atypon.domain.dao;

import com.atypon.domain.Article;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
    boolean create(Article article);
    Map<String, List<Article>> getSubjectToArticlesMap(String issueDoi);
    List<Article.Author> getAuthors(String doi);
}
