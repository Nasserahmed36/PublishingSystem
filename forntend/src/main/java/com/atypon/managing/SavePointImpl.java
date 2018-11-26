package com.atypon.managing;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SavePointImpl implements SavePoint {


    private final DataSource dataSource;
    private final String name;

    public SavePointImpl(DataSource dataSource, String name) {
        this.dataSource = dataSource;
        this.name = name;
    }

    @Override
    public void write(String value) {
        String sql = "REPLACE INTO save_point values(?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, value);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read() {
        String sql = "SELECT value FROM save_point WHERE name=?";
        String value = null;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            value = resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public boolean exists() {
        String sql = "SELECT value FROM save_point WHERE name=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void remove() {
        String sql = "DELETE FROM save_point WHERE name=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

