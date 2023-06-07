package com.example.jpa.service;

import java.util.List;

public interface IGeneralService<T> {
    List<T> findAll();
    T findById(Long id);
    void save(T t);
    void remove(Long id);

}
