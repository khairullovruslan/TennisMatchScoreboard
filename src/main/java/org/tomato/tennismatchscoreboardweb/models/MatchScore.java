package org.tomato.tennismatchscoreboardweb.models;

import lombok.*;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Builder
@Data
@ToString
public class MatchScore {
    private static ConcurrentHashMap<UUID, MatchScore> scores;
    private Match match;
    private Score score1;
    private Score score2;
    private boolean isDop;

    public static void addMatch(UUID uuid, MatchScore matchScore){
        if (scores == null){
            scores = new ConcurrentHashMap<>();
        }
        scores.put(uuid, matchScore);
    }
    public static MatchScore getMatch(UUID uuid){
        return scores.getOrDefault(uuid, null);
    }
    public static void removeMatch(UUID uuid){
        scores.remove(uuid);
    }


}
