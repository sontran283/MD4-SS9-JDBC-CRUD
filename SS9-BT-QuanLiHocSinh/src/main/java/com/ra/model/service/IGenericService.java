package com.ra.model.service;

import java.util.List;

public interface IGenericService<T, ID> {
    List<T> findAll();

    List<T> findByName(String name);

    T findById(ID id);

    boolean saveOrUpdate(T t, ID id);

    void delete(ID id);
}
