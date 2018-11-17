package com.atypon.domain.dao;

import com.atypon.domain.Issue;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueDaoImpl implements IssueDao {
    private final DataSource dataSource;

    public IssueDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean create(Issue issue) {
        StringBuilder sql = new StringBuilder("INSERT INTO issue  " +
                "(doi, journal_print_issn, number, volume, month, year) " +
                "values (?,?,?,?,?,?); insert into issue_subject(issue_doi, subject_title) VALUES");

        for (int i = 0; i < issue.getSubjects().size(); i++) {
            sql.append(" (?,?),");
        }
        sql.deleteCharAt(sql.length() - 1);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            int index = 1;
            statement.setString(index++, issue.getDoi());
            statement.setString(index++, issue.getJournalPrintIssn());
            statement.setInt(index++, issue.getNumber());
            statement.setInt(index++, issue.getVolume());
            statement.setInt(index++, issue.getMonth());
            statement.setInt(index++, issue.getYear());
            for (String subject : issue.getSubjects()) {
                statement.setString(index++, issue.getDoi());
                statement.setString(index++, subject);
            }
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isExisted(String issueDao) {
        String sql = "SELECT doi from issue where doi = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, issueDao);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
