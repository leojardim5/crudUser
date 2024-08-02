package com.leonardojardim.crudLojaLivro.service;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T> {

    T save(T entity);

    List<T> findAll();

    Optional<T> findById(long id);

    void deleteById(long id);
}
