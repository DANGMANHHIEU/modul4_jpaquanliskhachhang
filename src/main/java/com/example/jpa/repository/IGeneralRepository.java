package com.example.jpa.repository;

import java.util.List;

public interface IGeneralRepository<T> {
    List<T> findALL();
    T findById(Long id);
    void save(T t);
    void delete(long id);

}
