package com.ra.model.dao;

import com.ra.model.entity.Student;
import com.ra.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> findAll() {
        Connection connection = null;
        List<Student> studentList = new ArrayList<Student>();
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL SHOW_USER_LIST}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setAddress(resultSet.getString("address"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    @Override
    public List<Student> findByName(String name) {
        Connection connection = null;
        List<Student> students = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            String sql = "SELECT * FROM student WHERE LOWER(name) LIKE ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "%" + name.toLowerCase().trim() + "%");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setBirthday(Date.valueOf(resultSet.getString("birthday")));
                student.setAddress(resultSet.getString("address"));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return students;
    }

    @Override
    public Student findById(Integer integer) {
        List<Student> students = findAll();
        for (Student student : students) {
            if (student.getId() == integer) {
                return student;
            }
        }
        return null;
    }

    @Override
    public boolean saveOrUpdate(Student student, Integer id) {
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            if (id == null) {
                CallableStatement callableStatement = connection.prepareCall("{CALL ADD_USER(?,?,?,?)}");
                callableStatement.setString(1, student.getName());
                callableStatement.setString(2, student.getEmail());
                callableStatement.setDate(3, student.getBirthday());
                callableStatement.setString(4, student.getAddress());
                int check = callableStatement.executeUpdate();
                if (check > 0) {
                    return true;
                }
            } else {
                CallableStatement callableStatement = connection.prepareCall("{CALL EDIT_USER(?,?,?,?,?)}");
                callableStatement.setInt(1, id);
                callableStatement.setString(2, student.getName());
                callableStatement.setString(3, student.getEmail());
                callableStatement.setDate(4, student.getBirthday());
                callableStatement.setString(5, student.getAddress());
                int check = callableStatement.executeUpdate();
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
    public void delete(Integer integer) {
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL DELETE_USER(?)}");
            callableStatement.setInt(1, integer);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
    }
}
