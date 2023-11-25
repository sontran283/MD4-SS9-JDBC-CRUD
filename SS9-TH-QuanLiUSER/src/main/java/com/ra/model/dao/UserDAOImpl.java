package com.ra.model.dao;

import com.ra.model.entity.User;
import com.ra.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> findAll() {
        Connection connection = null;
        List<User> users = new ArrayList<>();
        try {
            // mở kết nối
            connection = ConnectionDB.openConnection();
            // PreparedStatement đựng các câu lệnh truy vấn
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM user");
            // thực thi PreparedStatement
            // executeQuery- thực thi với câu lệnh select
            // executeUpdate- dùng với trường hợp thay đổi dữ liệu
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setCountry(resultSet.getString("country"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return users;
    }

    @Override
    public boolean saveOrUpdate(User user, Integer id) {
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            if (id == null) {
                String sql = "INSERT INTO user(name,email,country) VALUES (?,?,?)";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setString(1, user.getName());
                pstm.setString(2, user.getName());
                pstm.setString(3, user.getCountry());
                int check = pstm.executeUpdate();
                if (check > 0) {
                    return true;
                }
            } else {
                String sql = "UPDATE user SET name =?, email =?, country=? WHERE (id = ?)";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setString(1, user.getName());
                pstm.setString(2, user.getName());
                pstm.setString(3, user.getCountry());
                pstm.setInt(4, id);
                int check = pstm.executeUpdate();
                if (check > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public User findById(Integer integer) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getId() == integer) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer integer) {
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            String sql = "DELETE FROM user WHERE (id = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, integer);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
    }

    @Override
    public int getNewId() {
        return 0;
    }

    @Override
    public List<User> finByName(String name) {
        return null;
    }
}