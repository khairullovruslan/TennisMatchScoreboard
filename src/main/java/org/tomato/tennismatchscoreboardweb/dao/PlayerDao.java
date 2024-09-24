package org.tomato.tennismatchscoreboardweb.dao;

import org.hibernate.Session;
import org.tomato.tennismatchscoreboardweb.models.Player;
import org.tomato.tennismatchscoreboardweb.util.HibernateUtil;

import java.util.List;

public class PlayerDao implements BaseDao<Player> {
    @Override
    public Player findById(long id, Session session) {
        return session.get(Player.class, id);

    }

    @Override
    public Player save(Player entity, Session session) {
        System.out.println("saving " + entity);
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
        try(  var sessionFactory = HibernateUtil.getSessionFactory();
              var session = sessionFactory.openSession();) {
            var transaction = session.beginTransaction();
            List<Player> players = session.createQuery("select p from Player p ", Player.class).list();
            transaction.commit();

            return players;
        }

    }

    public static void main(String[] args) {
    }


}
