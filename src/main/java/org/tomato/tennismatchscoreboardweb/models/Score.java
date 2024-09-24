package org.tomato.tennismatchscoreboardweb.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Score {
    private int countSets;

    private int countGames;

    private int points;



}
