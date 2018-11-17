package com.atypon.domain.dao;

import com.atypon.domain.Article;
import com.atypon.domain.Article.Author;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArticleDaoImpl implements ArticleDao {

    private final DataSource dataSource;

    public ArticleDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean save(Article article) {
        StringBuilder sql = new StringBuilder("insert into article (doi, title, " +
                "issue_doi, subject, first_page, last_page)" +
                " VALUES (?,?,?,?,?,?); ");
        sql.append("insert into article_author (article_dao, given_name, sur_name," +
                " degrees) values ");
        for (int i = 0; i < article.getAuthors().size(); i++) {
            sql.append(" (?,?),");
        }
        sql.deleteCharAt(sql.length() - 1);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            int index = 1;
            statement.setString(index++, article.getDoi());
            statement.setString(index++, article.getTitle());
            statement.setString(index++, article.getIssueDoi());
            statement.setString(index++, article.getSubject());
            statement.setString(index++, article.getFirstPage());
            statement.setString(index++, article.getLastPage());

            for (Author author : article.getAuthors()) {
                statement.setString(index++, article.getDoi());
                statement.setString(index++, author.getGivenName());
                statement.setString(index++, author.getSurName());
                statement.setString(index++, author.getSurName());
            }

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
