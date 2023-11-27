package com.ra.model.service;

import com.ra.model.dao.UserDAO;
import com.ra.model.dao.UserDAOImpl;
import com.ra.model.entity.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public boolean saveOrUpdate(User user, Integer integer) {
        return userDAO.saveOrUpdate(user, integer);
    }

    @Override
    public User findById(Integer integer) {
        return userDAO.findById(integer);
    }

    @Override
    public void delete(Integer integer) {
        userDAO.delete(integer);
    }

    @Override
    public int getNewId() {
        return userDAO.getNewId();
    }

    @Override
    public List<User> finByName(String name) {
        return userDAO.finByName(name);
    }

    @Override
    public List<User> sortByName(String name) {
        return userDAO.sortByName(name);
    }

    @Override
    public List<User> findAllSortedByName() {
        List<User> userList = userDAO.findAll();
        Collections.sort(userList, Comparator.comparing(User::getName));
        return userList;
    }
}
