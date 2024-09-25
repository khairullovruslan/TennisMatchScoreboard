package org.tomato.tennismatchscoreboardweb.services;

import org.hibernate.Transaction;
import org.tomato.tennismatchscoreboardweb.dao.PlayerDao;
import org.tomato.tennismatchscoreboardweb.models.Match;
import org.tomato.tennismatchscoreboardweb.models.MatchScore;
import org.tomato.tennismatchscoreboardweb.models.Player;
import org.tomato.tennismatchscoreboardweb.models.Score;
import org.tomato.tennismatchscoreboardweb.util.HibernateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerService {
    private final PlayerDao playerDao = PlayerDao.getInstance();
    private final static PlayerService INSTANCE = new PlayerService();

    private PlayerService() {

    }

    public static PlayerService getInstance() {
        return INSTANCE;
    }

    public UUID generateMatch(String name1, String name2) {
        Player player1 = buildPlayer(name1);
        Player player2 = buildPlayer(name2);

        Score score1 = Score
                .builder()
                .countGames(0)
                .points(0)
                .countSets(0)
                .build();

        Score score2 = Score
                .builder()
                .countGames(0)
                .points(0)
                .countSets(0)
                .build();

        Match match = Match
                .builder()
                .player1(player1)
                .player2(player2).build();
        MatchScore matchScore = MatchScore
                .builder()
                .match(match)
                .score1(score1)
                .score2(score2)
                .build();
        UUID uuid = UUID.randomUUID();
        MatchScore.addMatch(uuid, matchScore);
        return uuid;


    }



    private Player buildPlayer(String name) {
        Player player = Player
                .builder()
                .name(name)
                .build();
        try {
            return playerDao.save(player);
        }
        catch (Exception e){
            e.printStackTrace();
            return playerDao.findByName(name);
        }
    }
}
