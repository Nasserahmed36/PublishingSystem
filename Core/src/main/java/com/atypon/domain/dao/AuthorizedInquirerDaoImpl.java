package com.atypon.domain.dao;

import com.atypon.domain.AuthorizedInquirer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizedInquirerDaoImpl implements AuthorizedInquirerDao {
    private final DataSource dataSource;

    public AuthorizedInquirerDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public AuthorizedInquirer get(String name) {
        AuthorizedInquirer authorizedInquirer = null;
        String sql = "SELECT name, public_key, private_key FROM authorized_inquirer WHERE name=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                authorizedInquirer = extractAuthorizedInquirer(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorizedInquirer;
    }

    private AuthorizedInquirer extractAuthorizedInquirer(ResultSet resultSet) throws SQLException {
        AuthorizedInquirer authorizedInquirer = new AuthorizedInquirer();
        int index = 0;
        authorizedInquirer.setName(resultSet.getString(++index));
        authorizedInquirer.setPublicKey(resultSet.getString(++index));
        authorizedInquirer.setPrivateKey(resultSet.getString(++index));
        return authorizedInquirer;
    }
}
