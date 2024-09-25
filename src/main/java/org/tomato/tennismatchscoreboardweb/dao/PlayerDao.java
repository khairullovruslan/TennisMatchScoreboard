package org.tomato.tennismatchscoreboardweb.dao;

import org.tomato.tennismatchscoreboardweb.models.Player;
import org.tomato.tennismatchscoreboardweb.util.HibernateUtil;

public class PlayerDao implements BaseDao<Player> {
    private final static PlayerDao INSTANCE = new PlayerDao();

    private PlayerDao() {
    }

    public static PlayerDao getInstance() {
        return INSTANCE;
    }


    @Override
    public Player save(Player entity) {

        var sessionFactory = HibernateUtil.getSessionFactory();
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        }
    }

    public Player findByName(String name) {
        var sessionFactory = HibernateUtil.getSessionFactory();
        try (var session = sessionFactory.openSession()) {
            String hql = "select p from Player p where p.name = :name";
            return session.createQuery(hql, Player.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }

    }

}
