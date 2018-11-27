package com.atypon.domain.dao;

import com.atypon.commons.FileUtils;
import com.atypon.domain.ArticleSubmission;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleSubmissionDaoImpl implements ArticleSubmissionDao {

    private final DataSource dataSource;
    private final String outputDirPath;

    public ArticleSubmissionDaoImpl(DataSource dataSource, String outputDirPath) {
        this.dataSource = dataSource;
        this.outputDirPath = outputDirPath;
    }

    public int save(ArticleSubmission submission, InputStream inputStream) {

        String sql = "INSERT  INTO submission(series_issn, file_name, date," +
                "status, path) values (?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            String outputFile = submission.getSeriesIssn() +
                    File.separator + submission.getFileName();
            connection.setAutoCommit(false);
            statement.setString(1, submission.getSeriesIssn());
            statement.setString(2, submission.getFileName());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.setString(4, "Uploaded");
            statement.setString(5, outputFile);
            statement.execute();
            String absolutePath = outputDirPath + File.separator + outputFile;
            createNecessaryParentDirs(absolutePath);
            FileUtils.download(inputStream, absolutePath);
            connection.commit();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE submission SET status=? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            statement.setString(index++, status);
            statement.setInt(index, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public ArticleSubmission get(int id) {
        ArticleSubmission journal = null;
        String sql = "SELECT id, file_name, series_issn, date, status, path " +
                "from submission where id =?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                journal = extractSubmission(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journal;
    }

    @Override
    public List<ArticleSubmission> getAll() {
        List<ArticleSubmission> submissions = new ArrayList<>();
        String sql = "SELECT * FROM submission order by id desc";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ArticleSubmission submission = extractSubmission(resultSet);
                submissions.add(submission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submissions;
    }

    private ArticleSubmission extractSubmission(ResultSet resultSet) throws SQLException {
        ArticleSubmission submission = new ArticleSubmission();
        submission.setId(resultSet.getInt("id"));
        submission.setSeriesIssn(resultSet.getString("series_issn"));
        submission.setFileName(resultSet.getString("file_name"));
        submission.setTimestamp(resultSet.getTimestamp("date").getTime());
        String absolutePath = outputDirPath + File.separator + resultSet.getString("path");
        submission.setPath(absolutePath);
        submission.setStatus(resultSet.getString("status"));
        return submission;
    }

    private void createNecessaryParentDirs(String filePath) throws IOException {
        File parentDirs = new File(filePath).getParentFile();
        if (!parentDirs.exists()) {
            if (!parentDirs.mkdirs()) {
                throw new IOException("File dirs cannot be created: " + parentDirs);
            }
        }
    }

}
