package org.tomato.tennismatchscoreboardweb.util;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.tomato.tennismatchscoreboardweb.models.Match;
import org.tomato.tennismatchscoreboardweb.models.Player;

@Slf4j
public class HibernateUtil {

    public static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory () {
        if (sessionFactory == null){
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Player.class);
            configuration.addAnnotatedClass(Match.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }



}
