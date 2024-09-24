package org.tomato.tennismatchscoreboardweb.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.tomato.tennismatchscoreboardweb.models.Match;
import org.tomato.tennismatchscoreboardweb.models.Player;
import org.tomato.tennismatchscoreboardweb.util.HibernateUtil;

import java.util.List;

public class MatchDao implements BaseDao<Match> {
    @Override
    public Match findById(long id, Session session) {
        return null;
    }

    public List<Match> findAll(int page, int pageSize) {
        var sessionFactory = HibernateUtil.getSessionFactory();
        try (var session = sessionFactory.openSession()){
            String hql = "select p from Match p";
            Query<Match> query = session.createQuery(hql, Match.class);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.list();
        }

    }
    public List<Match> findAllWithName(int page, int pageSize, String name) {
        var sessionFactory = HibernateUtil.getSessionFactory();
        try (var session = sessionFactory.openSession()){
            String hql = "select p from Match p where p.player1.name = :name1 or p.player2.name = :name2";
            Query<Match> query = session.createQuery(hql, Match.class);
            query.setParameter("name1", name);
            query.setParameter("name2", name);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.list();
        }

    }

    @Override
    public Match save(Match entity, Session session) {
        return null;
    }

    public void save(Match entity) {
        var sessionFactory = HibernateUtil.getSessionFactory();
        try(
            var session = sessionFactory.openSession();) {
            var transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

}
