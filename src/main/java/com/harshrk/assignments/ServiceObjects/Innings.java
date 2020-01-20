package com.harshrk.assignments.ServiceObjects;

import com.harshrk.assignments.MatchObjects.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.harshrk.assignments.Constants.MatchConstants.*;

@Getter
@NoArgsConstructor
public class Innings {

    private List<Over> overs;
    private int target = Integer.MAX_VALUE;
    private int score = 0;
    private int wickets = 0;
    private double oversPlayed = 0;

    public Innings(int target) {
        this.target = target;
    }

    public void simulateInnings(Team team) {
        overs = new ArrayList<Over>();
        for(int currentPlayer = 0, currentOver = 0; currentOver< NUMBER_OF_OVERS_IN_ODI; currentOver++) {

            Over over = new Over();
            currentPlayer = over.simulateOver(team, currentPlayer, target-score);
            score += over.getScore();
            overs.add(over);

            if(currentPlayer<0) {
                wickets = 10;
                computeOversPlayed(currentOver, over);
                return;
            }
            else if(score >= target) {
                wickets = currentPlayer;
                computeOversPlayed(currentOver, over);
                return;
            }

            if(currentOver == NUMBER_OF_OVERS_IN_ODI -1) {
                wickets = currentPlayer;
                oversPlayed = NUMBER_OF_OVERS_IN_ODI;
            }
        }
    }

    private void computeOversPlayed(int currentOver, Over finalOver) {
        oversPlayed = currentOver;
        if (finalOver.getBallsBowled() == NUMBER_OF_BALLS_IN_OVER) oversPlayed = oversPlayed + 1;
        else oversPlayed = oversPlayed + ((double)finalOver.getBallsBowled())/10;
    }
}
