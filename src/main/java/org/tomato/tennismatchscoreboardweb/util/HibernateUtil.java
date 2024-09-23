package org.tomato.tennismatchscoreboardweb.util;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.tomato.tennismatchscoreboardweb.models.Player;

@Slf4j
public class HibernateUtil {

    public static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory () {
        if (sessionFactory == null){
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Player.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
        @Cleanup var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(Player
                .builder()
                .name("vasya")
                .build());
        transaction.commit();
    }


}
