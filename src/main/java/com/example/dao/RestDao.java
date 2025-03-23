package com.example.dao;


import java.util.List;

public interface RestDao<T, S> {
    T getById(S id);

    List<T> getAll();

    boolean delete(T entity);

    boolean update(T entity);

    T create(T entity);
}
