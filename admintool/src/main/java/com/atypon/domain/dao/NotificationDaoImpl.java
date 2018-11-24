package com.atypon.domain.dao;

import com.atypon.domain.Notification;

import static com.atypon.domain.Notification.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDaoImpl implements NotificationDao {
    private final DataSource dataSource;

    public NotificationDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean create(Notification notification) {
        String sql = "INSERT  INTO notification(journal_print_issn, issue_doi, article_doi,operation)" +
                " values (?,?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            statement.setString(index++, notification.getJournalPrintIssn());
            statement.setString(index++, notification.getIssueDoi());
            statement.setString(index++, notification.getArticleDoi());
            statement.setString(index, notification.getOperation().name());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public List<Notification> getAllAfter(int serialNumber) {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT serial_number,journal_print_issn, issue_doi," +
                "article_doi,operation from notification where serial_number > ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
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
        notification.setSerailNumber(resultSet.getInt(index++));
        notification.setJournalPrintIssn(resultSet.getString(index++));
        notification.setIssueDoi(resultSet.getString(index++));
        notification.setArticleDoi(resultSet.getString(index++));
        Operation operation = Operation.valueOf(resultSet.getString(index));
        notification.setOperation(operation);
        return notification;
    }

}
