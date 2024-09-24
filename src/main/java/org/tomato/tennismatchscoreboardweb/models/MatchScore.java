package org.tomato.tennismatchscoreboardweb.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.UUID;

@Builder
@Data
@ToString
public class MatchScore {
    public static HashMap<UUID, MatchScore> scores;
    private Match match;
    private Score score1;
    private Score score2;
    private boolean isDop;
}
