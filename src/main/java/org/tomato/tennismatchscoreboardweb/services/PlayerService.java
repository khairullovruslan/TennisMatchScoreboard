package org.tomato.tennismatchscoreboardweb.services;

import org.hibernate.Transaction;
import org.tomato.tennismatchscoreboardweb.dao.PlayerDao;
import org.tomato.tennismatchscoreboardweb.models.Match;
import org.tomato.tennismatchscoreboardweb.models.MatchScore;
import org.tomato.tennismatchscoreboardweb.models.Player;
import org.tomato.tennismatchscoreboardweb.models.Score;
import org.tomato.tennismatchscoreboardweb.util.HibernateUtil;

import java.util.HashMap;
import java.util.UUID;

public class PlayerService {
    private final PlayerDao playerDao = new PlayerDao();

    public UUID generateMatch(String name1, String name2){
        Transaction transaction = null;

        try (var sessionFactory = HibernateUtil.getSessionFactory();
             var session = sessionFactory.openSession();){
            transaction = session.beginTransaction();

            Player player1 = playerDao.findByName(name1, session);
            Player player2 = playerDao.findByName(name2, session);
            if (player1 == null){
                player1 = playerDao.save(buildPlayer(name1), session);
            }
            if (player2 == null){
                player2 = playerDao.save(buildPlayer(name2), session);
            }

            transaction.commit();
            Score score = Score
                    .builder()
                    .firstScore(0)
                    .secondScore(0)
                    .build();
            Match match = Match
                    .builder()
                    .player1(player1)
                    .player2(player2).build();
            MatchScore matchScore = MatchScore
                    .builder()
                    .match(match)
                    .score(score)
                    .build();
            UUID uuid = UUID.randomUUID();
            if (MatchScore.scores == null){
                MatchScore.scores = new HashMap<>();
            }
            MatchScore.scores.put(uuid, matchScore);
            return uuid;


        }
        catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;

    }
    private Player buildPlayer(String name){
        return Player
                .builder()
                .name(name)
                .build();
    }
}
