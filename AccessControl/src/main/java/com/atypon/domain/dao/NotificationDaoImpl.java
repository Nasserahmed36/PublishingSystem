package com.atypon.domain.dao;

import com.atypon.domain.Notification;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.atypon.domain.Notification.Operation;

public class NotificationDaoImpl implements NotificationDao {
    private final DataSource dataSource;

    public NotificationDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean create(Notification notification) {
        String sql = "INSERT  INTO notification(content, type,operation)" +
                " values (?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            statement.setString(index++, notification.getContent());
            statement.setString(index++, notification.getType());
            statement.setString(index, notification.getOperation().name());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


    @Override
    public List<Notification> getAllAfter(String type, int serialNumber) {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT id, content, " +
                "type,operation from notification where type = ? and id > ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            statement.setString(index++, type);
            statement.setInt(index, serialNumber);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Notification notification = extractNotification(resultSet);
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    private Notification extractNotification(ResultSet resultSet) throws SQLException {
        Notification notification = new Notification();
        int index = 1;
        notification.setId(resultSet.getInt(index++));
        notification.setContent(resultSet.getString(index++));
        notification.setType(resultSet.getString(index++));
        Operation operation = Operation.valueOf(resultSet.getString(index));
        notification.setOperation(operation);
        return notification;
    }
}
