package com.harshrk.assignments.MatchObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BowlingScorecard {

    private PlayerType playerType;
    private String playerName;
    private boolean hasIncompleteOver;
    private double oversBowled;
    private int maidenOvers;
    private int runsGiven;
    private int wicketsTaken;
    private double economy;

    @Override
    public String toString() {
        String stat = String.format("%s: %s%s Overs: %.1f, Maiden: %d, Runs: %d, Wickets: %d, Economy: %.2f",
            playerType, playerName, (hasIncompleteOver ? "*" : ""), oversBowled,
            maidenOvers, runsGiven, wicketsTaken, economy);
        return stat;
    }
}
