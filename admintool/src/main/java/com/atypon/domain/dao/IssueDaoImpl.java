package com.atypon.domain.dao;

import com.atypon.domain.Issue;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Issue> getBy(String journalPrintIssn) {
        List<Issue> issues = new ArrayList<>();
        String sql = "SELECT doi, journal_print_issn, number, volume, year, month FROM issue " +
                "where journal_print_issn = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, journalPrintIssn);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Issue issue = extractIssue(resultSet);
                issues.add(issue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issues;
    }

    @Override
    public List<Issue> getBy(String journalPrintIssn, int volume) {
        List<Issue> issues = new ArrayList<>();
        String sql = "SELECT doi, journal_print_issn, number, volume, year, month FROM issue " +
                "where journal_print_issn = ? AND volume = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, journalPrintIssn);
            statement.setInt(2, volume);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Issue issue = extractIssue(resultSet);
                issues.add(issue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issues;
    }

    @Override
    public int getMaxVolume(String journalPrintIssn) {
        int maxVolume = -1;
        String sql = "select max(volume) from issue where journal_print_issn = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, journalPrintIssn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxVolume;
    }

    private Issue extractIssue(ResultSet resultSet) throws SQLException {
        Issue issue = new Issue();
        issue.setDoi(resultSet.getString(1));
        issue.setJournalPrintIssn(resultSet.getString(2));
        issue.setNumber(resultSet.getInt(3));
        issue.setVolume(resultSet.getInt(4));
        issue.setYear(resultSet.getInt(5));
        issue.setMonth(resultSet.getInt(6));
        return issue;
    }


}
