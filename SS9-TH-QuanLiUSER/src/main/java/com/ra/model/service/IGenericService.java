package com.ra.model.service;

import java.util.List;

public interface IGenericService<T, ID> {
    List<T> findAll();

    boolean saveOrUpdate(T t, ID id);

    T findById(ID id);

    void delete(ID id);

    int getNewId();

    List<T> finByName(String name);

    List<T> sortByName(String name);
}
