package org.tomato.tennismatchscoreboardweb.dao;

import org.hibernate.Session;
import org.tomato.tennismatchscoreboardweb.models.Player;
import org.tomato.tennismatchscoreboardweb.util.HibernateUtil;

import java.util.List;

public class PlayerDao implements BaseDao<Player> {
    private final static PlayerDao INSTANCE = new PlayerDao();
    private PlayerDao(){

    }

    public static PlayerDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Player findById(long id, Session session) {
        return session.get(Player.class, id);

    }

    @Override
    public Player save(Player entity, Session session) {
        session.persist(entity);
        return entity;
    }

    public Player findByName(String name, Session session){
        String hql = "select p from Player p where p.name = :name";
        return (Player) session.createQuery(hql)
                .setParameter("name", name)
                .uniqueResult();
    }
    public List<Player> findAll(){
        var sessionFactory = HibernateUtil.getSessionFactory();
        try(var session = sessionFactory.openSession();) {
            var transaction = session.beginTransaction();
            List<Player> players = session.createQuery("select p from Player p ", Player.class).list();
            transaction.commit();

            return players;
        }

    }


}
