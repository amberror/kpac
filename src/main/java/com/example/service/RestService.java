package com.example.service;


import java.util.List;

public interface RestService<T> {
    List<T> getAll();
    T create(T model);
    boolean delete(T model);
}
