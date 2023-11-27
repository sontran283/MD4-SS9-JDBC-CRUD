package com.ra.model.service;

import com.ra.model.entity.User;

import java.util.List;

public interface UserService extends IGenericService<User,Integer>{
    List<User> findAllSortedByName();
}
