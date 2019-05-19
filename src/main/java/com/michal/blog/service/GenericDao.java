package com.michal.blog.service;

import java.util.Optional;
import java.util.Set;

public interface GenericDao<T, PK,SK> {

    T create(T t);

    Optional<T> readById(PK id);

    Set<T> readAll(SK id);

    T update(T t);

    void delete(T t);

    void deleteById(PK id);

}
