package com.mycomp.app.dao;

public interface IDAO<T> {
    T create(T t);
    T find(Object id);
    T update(T t);
    void delete(T t);
}
