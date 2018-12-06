package com.atypon.service;

import com.atypon.domain.Article;
import com.atypon.domain.Issue;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    boolean create(Article article);

    Article get(String doi);

    Article getFromIssue(Issue issue);


    Map<String, List<Article>> getSubjectToArticlesMap(String journalPrintIssn, String issueDoi);

    List<Article> getAll();

    List<Article.Author> getAuthors(String doi);
}
