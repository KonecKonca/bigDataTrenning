package com.epam.kozitski.service;

import java.util.List;
import java.util.Optional;

public interface CommonService<T> {

    Optional<T> searchById(long id);
    List<T> searchAll();
    boolean update(T t);
    boolean insert(T t);
    boolean deleteById(long id);

}
