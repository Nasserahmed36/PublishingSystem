package com.atypon.domain.dao;

import com.atypon.domain.Article;
import com.atypon.domain.Article.Author;
import com.atypon.domain.Issue;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ArticleDaoImpl implements ArticleDao {

    private final DataSource dataSource;

    public ArticleDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean create(Article article) {
        StringBuilder sql = new StringBuilder("insert into article (doi, title, " +
                "issue_doi, subject, first_page, last_page,month,year,journal_print_issn)" +
                " VALUES (?,?,?,?,?,?,?,?,?); ");
        if (!article.getAuthors().isEmpty()) {
            sql.append("insert into article_author (article_dao, given_name, sur_name," +
                    " degrees) values ");
        }
        for (int i = 0; i < article.getAuthors().size(); i++) {
            sql.append(" (?,?,?,?),");
        }
        sql.deleteCharAt(sql.length() - 1);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            connection.setAutoCommit(false);
            int index = 1;
            statement.setString(index++, article.getDoi());
            statement.setString(index++, article.getTitle());
            statement.setString(index++, article.getIssueDoi());
            statement.setString(index++, article.getSubject());
            statement.setString(index++, article.getFirstPage());
            statement.setString(index++, article.getLastPage());
            statement.setInt(index++, article.getMonth());
            statement.setInt(index++, article.getYear());
            statement.setString(index++, article.getJournalPrintIssn());
            for (Author author : article.getAuthors()) {
                statement.setString(index++, article.getDoi());
                statement.setString(index++, author.getGivenName());
                statement.setString(index++, author.getSurName());
                statement.setString(index++, author.getDegrees());
            }
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Article get(String doi) {
        Article article = null;
        String sql = "select doi, title, issue_doi, subject, first_page, last_page" +
                ",month, year,journal_print_issn from article where doi = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, doi);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                article = extractArticleWithAuthors(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public Article getFromIssue(Issue issue) {
        Article article = null;
        String sql = "select article.doi, title, issue_doi, subject, first_page, last_page, article.month," +
                " article.year ,journal_print_issn from article where issue_doi = ? and journal_print_issn = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            statement.setString(index++, issue.getDoi());
            statement.setString(index, issue.getJournalPrintIssn());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                article = extractArticleWithAuthors(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public Map<String, List<Article>> getSubjectToArticlesMap(String journalPrintIssn, String issueDoi) {
        Map<String, List<Article>> map = new LinkedHashMap<>();
        String sql = "select doi, title, issue_doi, subject, first_page, last_page" +
                ",month, year,journal_print_issn from article where journal_print_issn = ? and issue_doi = ? order by first_page";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, journalPrintIssn);
            statement.setString(2, issueDoi);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Article article = extractArticleWithAuthors(resultSet);
                if (!map.containsKey(article.getSubject())) {
                    map.put(article.getSubject(), new ArrayList<>());
                }
                map.get(article.getSubject()).add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public List<Article> getAll() {
        List<Article> articles = new ArrayList<>();
        String sql = "select doi, title, issue_doi, subject, first_page, last_page" +
                ",month, year,journal_print_issn from article order by first_page";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Article article = extractArticle(resultSet);
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    private Article extractArticleWithAuthors(ResultSet resultSet) throws SQLException {
        Article article = new Article();
        int index = 1;
        article.setDoi(resultSet.getString(index++));
        article.setTitle(resultSet.getString(index++));
        article.setIssueDoi(resultSet.getString(index++));
        article.setSubject(resultSet.getString(index++));
        article.setFirstPage(resultSet.getString(index++));
        article.setLastPage(resultSet.getString(index++));
        article.setMonth(resultSet.getInt(index++));
        article.setYear(resultSet.getInt(index++));
        article.setJournalPrintIssn(resultSet.getString(index));
        article.setAuthors(getAuthors(article.getDoi()));
        return article;
    }

    private Article extractArticle(ResultSet resultSet) throws SQLException {
        Article article = new Article();
        int index = 1;
        article.setDoi(resultSet.getString(index++));
        article.setTitle(resultSet.getString(index++));
        article.setIssueDoi(resultSet.getString(index++));
        article.setSubject(resultSet.getString(index++));
        article.setFirstPage(resultSet.getString(index++));
        article.setLastPage(resultSet.getString(index++));
        article.setMonth(resultSet.getInt(index++));
        article.setYear(resultSet.getInt(index++));
        article.setJournalPrintIssn(resultSet.getString(index));
        return article;
    }

    public List<Author> getAuthors(String doi) {
        List<Author> authors = new ArrayList<>();
        String sql = "select given_name,sur_name,degrees from article_author " +
                "where article_dao = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, doi);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Author author = extractAuthor(resultSet);
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    private Author extractAuthor(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        int index = 1;
        author.setGivenName(resultSet.getString(index++));
        author.setSurName(resultSet.getString(index++));
        author.setDegrees(resultSet.getString(index));
        return author;
    }
}
