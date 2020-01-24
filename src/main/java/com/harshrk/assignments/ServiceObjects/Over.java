package com.harshrk.assignments.ServiceObjects;

import com.harshrk.assignments.MatchObjects.Partnership;
import com.harshrk.assignments.MatchObjects.Player;
import com.harshrk.assignments.MatchObjects.Team;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.harshrk.assignments.Constants.MatchConstants.ALL_OUT;
import static com.harshrk.assignments.Constants.MatchConstants.NUMBER_OF_BALLS_IN_OVER;
import static com.harshrk.assignments.Constants.MatchConstants.PLAYER_OUT;
import static com.harshrk.assignments.Constants.MatchConstants.TARGET_REACHED;
import static com.harshrk.assignments.Constants.MatchConstants.WICKET;

@Getter
public class Over {

    private Player batsmanStriker;
    private Player batsmanNonStriker;
    private Player bowler;
    private Team battingTeam;
    private List<Partnership> endedPartnerships;
    private Partnership currentPartnership;
    private int overScore = 0;
    private int ballsBowled = 0;
    private int overWickets = 0;
    private List<Character> deliveries;

    public Over(Team battingTeam, Player batsmanStriker, Player batsmanNonStriker, Player bowler,
                Partnership currentPartnership) {
        this.battingTeam = battingTeam;
        this.batsmanStriker = batsmanStriker;
        this.batsmanNonStriker = batsmanNonStriker;
        endedPartnerships = new ArrayList<Partnership>();
        this.currentPartnership = currentPartnership;
        this.bowler = bowler;
        deliveries = new ArrayList<Character>();
    }

    public int simulateOver(int overTarget) {
        for(int currentBall=0; currentBall<NUMBER_OF_BALLS_IN_OVER; currentBall++) {
            incrementBallsBowled();
            currentPartnership.incrementBallsPlayed(batsmanStriker);
            int runs = Player.play(batsmanStriker, bowler);

            if(runs == PLAYER_OUT) {
                incrementOverWickets();
                deliveries.add(WICKET);
                endedPartnerships.add(currentPartnership);

                int batsmanNumber = battingTeam.getNextBatsmanNumber();
                if(batsmanNumber == ALL_OUT) return ALL_OUT;
                else {
                    batsmanStriker = battingTeam.getBatsman(batsmanNumber);
                    currentPartnership = new Partnership(batsmanNonStriker, batsmanStriker);
                    continue;
                }
            }

            overScore += runs;
            deliveries.add((char)(runs+'0'));
            currentPartnership.incrementRunsScored(batsmanStriker, runs);
            if((runs&1)==1) rotateStrike();
            if(overScore >= overTarget) {
                endedPartnerships.add(currentPartnership);
                return TARGET_REACHED;
            }
        }
        return 0;
    }

    private void rotateStrike() {
        Player newStriker = batsmanNonStriker;
        Player newNonStriker = batsmanStriker;
        batsmanStriker = newStriker;
        batsmanNonStriker = newNonStriker;
    }

    private void incrementOverWickets() {
        overWickets++;
    }

    private void incrementBallsBowled() {
        ballsBowled++;
    }
}
