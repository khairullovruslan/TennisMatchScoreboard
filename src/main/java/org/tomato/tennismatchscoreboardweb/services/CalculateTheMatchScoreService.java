package org.tomato.tennismatchscoreboardweb.services;

import org.tomato.tennismatchscoreboardweb.dao.MatchDao;
import org.tomato.tennismatchscoreboardweb.models.Match;
import org.tomato.tennismatchscoreboardweb.models.MatchScore;
import org.tomato.tennismatchscoreboardweb.models.Score;

public class CalculateTheMatchScoreService {
    private final static CalculateTheMatchScoreService INSTANCE = new CalculateTheMatchScoreService();
    private CalculateTheMatchScoreService(){
    }

    public static CalculateTheMatchScoreService getInstance() {
        return INSTANCE;
    }

    private final MatchDao matchDao = MatchDao.getInstance();
    public boolean calculate(MatchScore matchScore, String winner){
        Score score1 = matchScore.getScore1();
        Score score2 = matchScore.getScore2();
        if (winner.equals("player1")){
            settingPoint(matchScore, score1, score2);
        }
        else {
            settingPoint(matchScore, score2, score1);
        }

        if (score1.getCountSets() == 2 || score2.getCountSets() == 2) {
            Match match = matchScore.getMatch();
            match.setWinner(score1.getCountSets() == 2 ? match.getPlayer1() : match.getPlayer2());
            try {
                matchDao.save(match);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return true;
        }
        return false;

    }
    private void clearGames(Score score1, Score score2){
        score1.setCountGames(0);
        score2.setCountGames(0);
    }
    private void clearPoints(Score score1, Score score2){
        score1.setPoints(0);
        score2.setPoints(0);
    }
    private void settingPoint(MatchScore matchScore, Score score1, Score score2){
        if (matchScore.isDop()){
            score1.setPoints(score1.getPoints() + 1);
            if (score1.getPoints() >= 7 && score1.getPoints() - score2.getPoints() >= 2){
                if (score1.getCountGames() == 6 && score2.getCountGames() == 6){
                    score1.setCountSets(score1.getCountSets() + 1);
                    clearGames(score1, score2);
                }
                else {
                    score1.setCountGames(score2.getCountGames() + 1);
                }
                matchScore.setDop(false);
                clearPoints(score1, score2);
            }
        }
        else {
            if (score1.getPoints() == 40 && score2.getPoints() == 40){
                matchScore.setDop(true);

                score1.setPoints(1);
                score2.setPoints(0);
            }
            else{
                score1.setPoints(score1.getPoints() + (score1.getPoints() == 30 ? 10 : 15));
                if (score1.getPoints() > 40){
                    score1.setCountGames(score1.getCountGames() + 1);
                    if (score1.getCountGames() == 6 && score2.getCountGames() == 6){
                        matchScore.setDop(true);
                    } else if (score1.getCountGames() > 6) {
                        score1.setCountSets(score1.getCountSets() + 1);
                        clearGames(score1, score2);
                    }
                    clearPoints(score1, score2);
                }
            }
        }
    }

}
