package com.harshrk.assignments.ServiceObjects;

import com.harshrk.assignments.MatchObjects.Player;
import com.harshrk.assignments.MatchObjects.Team;
import lombok.Getter;

import static com.harshrk.assignments.Constants.MatchConstants.ALL_OUT;
import static com.harshrk.assignments.Constants.MatchConstants.NUMBER_OF_BALLS_IN_OVER;
import static com.harshrk.assignments.Constants.MatchConstants.WICKETS_IN_INNINGS;

@Getter
public class Over {

    private int score = 0;
    private int ballsBowled = 0;

    public int simulateOver(Team team, int playerNumber, int target) {
        Player currentPlayer = team.getPlayers().get(playerNumber);
        for(int currentBall=0; currentBall<NUMBER_OF_BALLS_IN_OVER; currentBall++) {
            ballsBowled++;
            int runs = currentPlayer.play();

            if(runs<0) {
                if (playerNumber == WICKETS_IN_INNINGS-1) {
                    return ALL_OUT;
                }
                else {
                    currentPlayer = team.getPlayers().get(++playerNumber);
                    continue;
                }
            }

            score += runs;
            if(score >= target) return playerNumber;
        }
        return playerNumber;
    }
}
