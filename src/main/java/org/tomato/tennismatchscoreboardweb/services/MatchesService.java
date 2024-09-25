package org.tomato.tennismatchscoreboardweb.services;

import org.tomato.tennismatchscoreboardweb.dao.MatchDao;
import org.tomato.tennismatchscoreboardweb.models.Match;

import java.util.List;

public class MatchesService {
    private final static MatchesService INSTANCE = new MatchesService();
    private MatchesService(){

    }

    public static MatchesService getInstance() {
        return INSTANCE;
    }

    private final MatchDao matchDao = MatchDao.getInstance();

    public List<Match> findAll(int page, int pageSize){
       return matchDao.findAll(page, pageSize);
    }
    public List<Match> findAllWithName(int page, int pageSize, String name){
        return matchDao.findAllWithName(page, pageSize, name);
    }
}
