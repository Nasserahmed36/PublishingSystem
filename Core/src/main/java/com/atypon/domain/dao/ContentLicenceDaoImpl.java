package com.atypon.domain.dao;

import com.atypon.domain.ContentLicence;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContentLicenceDaoImpl implements ContentLicenceDao {
    private final DataSource dataSource;

    public ContentLicenceDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean create(ContentLicence contentLicence) {
        String sql = "INSERT INTO content_licence(content_id, licence_name, body) " +
                "values (?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 0;
            statement.setString(++index, contentLicence.getContentId());
            statement.setString(++index, contentLicence.getLicenceName());
            statement.setString(++index, contentLicence.getBody());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM content_licence WHERE id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ContentLicence> get(String contentId) {
        List<ContentLicence> contentLicences = new ArrayList<>();
        String sql = "SELECT id, content_id, licence_name, body from content_licence" +
                " where content_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 0;
            statement.setString(++index, contentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contentLicences.add(extractContentLicence(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contentLicences;
    }

    @Override
    public List<ContentLicence> getAll() {
        List<ContentLicence> contentLicences = new ArrayList<>();
        String sql = "SELECT id, content_id, licence_name, body from content_licence";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contentLicences.add(extractContentLicence(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contentLicences;
    }

    private ContentLicence extractContentLicence(ResultSet resultSet) throws SQLException {
        ContentLicence contentLicence = new ContentLicence();
        int index = 1;
        contentLicence.setId(resultSet.getInt(index++));
        contentLicence.setContentId(resultSet.getString(index++));
        contentLicence.setLicenceName(resultSet.getString(index++));
        contentLicence.setBody(resultSet.getString(index));
        return contentLicence;
    }
}
