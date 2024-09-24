package org.tomato.tennismatchscoreboardweb.dao;

import org.hibernate.Session;

public interface BaseDao<T> {
    T findById(long id, Session session);
    T save(T entity, Session session);
}
