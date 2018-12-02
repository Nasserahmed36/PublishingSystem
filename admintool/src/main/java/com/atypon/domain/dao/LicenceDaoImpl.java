package com.atypon.domain.dao;

import com.atypon.domain.Licence;
import com.atypon.domain.UserContentLicence;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LicenceDaoImpl implements LicenceDao {
    private final DataSource dataSource;

    public LicenceDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Licence> getAll() {
        List<Licence> licences = new ArrayList<>();
        String sql = "SELECT  name, content_licence_body_description," +
                " user_licence_body_description" +
                " from licence";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Licence licence = extractLicence(resultSet);
                licences.add(licence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return licences;
    }

    private Licence extractLicence(ResultSet resultSet) throws SQLException {
        Licence licence = new Licence();
        int index = 0;
        licence.setName(resultSet.getString(++index));
        licence.setContentLicenceDescription(resultSet.getString(++index));
        licence.setUserLicenceDescription(resultSet.getString(++index));
        return licence;
    }
}
