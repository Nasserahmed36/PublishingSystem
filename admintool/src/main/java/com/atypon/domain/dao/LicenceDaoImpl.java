//package com.atypon.domain.dao;
//
//import com.atypon.domain.Licence;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.List;
//
//
//public class LicenceDaoImpl implements LicenceDao {
//
//    private final DataSource dataSource;
//
//    public LicenceDaoImpl(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//
//    @Override
//    public boolean create(Licence licence) {
//        String sql = "INSERT  INTO licence(name, content) VALUES (?,?)";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            int index = 1;
//            statement.setString(index++, licence.getName());
//            statement.setString(index, licence.getContent());
//            statement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//}
