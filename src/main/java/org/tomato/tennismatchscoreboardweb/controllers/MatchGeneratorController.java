package org.tomato.tennismatchscoreboardweb.controllers;

import org.tomato.tennismatchscoreboardweb.services.PlayerService;

import java.util.UUID;

public class MatchGeneratorController {
    private final PlayerService  playerService = new PlayerService();

    public UUID generateMatch(String name1, String name2){
        return playerService.generateMatch(name1, name2);


    }

}
