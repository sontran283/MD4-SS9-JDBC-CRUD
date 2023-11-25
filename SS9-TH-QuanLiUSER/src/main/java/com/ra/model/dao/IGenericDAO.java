package com.ra.model.dao;

import java.util.List;

public interface IGenericDAO <T,ID>{
    List<T> findAll();

    boolean saveOrUpdate(T t, ID id);

    T findById(ID id);

    void delete(ID id);

    int getNewId();

    List<T> finByName(String name);
}
