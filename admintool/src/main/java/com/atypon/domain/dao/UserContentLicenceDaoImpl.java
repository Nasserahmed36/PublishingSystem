package com.atypon.domain.dao;

import com.atypon.domain.UserContentLicence;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserContentLicenceDaoImpl implements UserContentLicenceDao {

    private final DataSource dataSource;

    public UserContentLicenceDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean create(UserContentLicence userContentLicence) {
        String sql = "INSERT  INTO user_content_licence(username, content_licence_Id, start_date," +
                " body) values (?,?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 0;
            statement.setString(++index, userContentLicence.getUsername());
            statement.setInt(++index, userContentLicence.getContentLicenceId());
            statement.setTimestamp(++index, new Timestamp(System.currentTimeMillis()));
            statement.setString(++index, userContentLicence.getBody());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<UserContentLicence> get(String username, String contentLicenceId) {
        List<UserContentLicence> userLicences = new ArrayList<>();
        String sql = "SELECT  username, content_licence_Id, start_date, body " +
                "from user_content_licence where content_licence_Id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserContentLicence userLicence = extractUserLicence(resultSet);
                userLicences.add(userLicence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userLicences;
    }

    private UserContentLicence extractUserLicence(ResultSet resultSet) throws SQLException {
        UserContentLicence userLicence = new UserContentLicence();
        int index = 0;
        userLicence.setUsername(resultSet.getString(++index));
        userLicence.setContentLicenceId(resultSet.getInt(++index));
        userLicence.setStartDate(resultSet.getTimestamp(++index).getTime());
        userLicence.setBody(resultSet.getString(++index));
        return userLicence;
    }
}
