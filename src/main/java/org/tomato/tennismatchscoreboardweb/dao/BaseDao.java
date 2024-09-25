package org.tomato.tennismatchscoreboardweb.dao;
public interface BaseDao<T> {
    T save(T entity);
}
