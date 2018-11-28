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
    public List<ContentLicence> get(String contentId) {
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
        contentLicence.setContentId(resultSet.getString(index++));
        contentLicence.setLicenceName(resultSet.getString(index++));
        contentLicence.setBody(resultSet.getString(index));
        return contentLicence;
    }
}
