package com.harshrk.assignments.Services;

import com.harshrk.assignments.Beans.Partnership;
import com.harshrk.assignments.Beans.Player;
import com.harshrk.assignments.Beans.Team;
import com.harshrk.assignments.Constants.MatchConstants;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Innings {

    @NonNull private Team battingTeam;
    @NonNull private Team bowlingTeam;
    @NonNull private int inningsTarget;
    private Player batsmanStriker;
    private Player batsmanNonStriker;
    private List<Partnership> partnerships;
    private Partnership currentPartnership;
    private List<Over> overs;
    private int inningsScore = 0;
    private int wickets = 0;
    private double oversPlayed = 0;
    private double runRate;

    public void simulateInnings() {
        overs = new ArrayList<Over>();
        partnerships = new ArrayList<Partnership>();
        int overResult;

        batsmanStriker = battingTeam.getBatsman(0);
        batsmanNonStriker = battingTeam.getBatsman(1);
        currentPartnership = new Partnership(batsmanStriker, batsmanNonStriker);

        int bowlerNumber = bowlingTeam.getNextBowlerNumber(MatchConstants.NUMBER_OF_ALLROUNDERS_IN_TEAM +
            MatchConstants.NUMBER_OF_BOWLERS_IN_TEAM);
        Player bowler = bowlingTeam.getBowler(bowlerNumber);

        for(int currentOver = 0; currentOver < MatchConstants.NUMBER_OF_OVERS_IN_ODI; currentOver++) {

            Over over = new Over(battingTeam, batsmanStriker, batsmanNonStriker, bowler, currentPartnership);
            overResult = over.simulateOver(inningsTarget-inningsScore);
            incrementInningsScore(over.getOverScore());
            incrementWickets(over.getOverWickets());
            partnerships.addAll(over.getEndedPartnerships());
            overs.add(over);

            if(overResult == MatchConstants.ALL_OUT || overResult == MatchConstants.TARGET_REACHED) {
                double overLength = computeOverLength(over);
                incrementOversPlayed(overLength);
                bowler.incrementOversBowled(overLength);
                break;
            }

            incrementOversPlayed(1);

            batsmanStriker = over.getBatsmanStriker();
            batsmanNonStriker = over.getBatsmanNonStriker();
            rotateStrike();
            currentPartnership = over.getCurrentPartnership();

            bowler.incrementOversBowled(1);
            if(over.getOverScore() == 0) bowler.incrementMaidenOvers();
            bowlerNumber = bowlingTeam.getNextBowlerNumber(bowlerNumber);
            bowler = bowlingTeam.getBowler(bowlerNumber);

            if(currentOver == MatchConstants.NUMBER_OF_OVERS_IN_ODI-1) {
                oversPlayed = MatchConstants.NUMBER_OF_OVERS_IN_ODI;
                partnerships.add(currentPartnership);
            }
        }

        computeRunRate();
        batsmanStriker.computeStrikeRate();
        batsmanNonStriker.computeStrikeRate();
        computeEconomy();

        for(Player player : battingTeam.getPlayers()) {
            for (Partnership partnership : partnerships) {
                partnership.setNotOut(player);
            }
        }
    }

    private void rotateStrike() {
        Player newStriker = batsmanNonStriker;
        Player newNonStriker = batsmanStriker;
        batsmanStriker = newStriker;
        batsmanNonStriker = newNonStriker;
    }

    private void incrementInningsScore(int overScore) {
        inningsScore += overScore;
    }

    private void incrementWickets(int overWickets) {
        wickets += overWickets;
    }

    private void incrementOversPlayed(double overLength) {
        oversPlayed += overLength;
    }

    private double computeOverLength(Over currentOVer) {
        if (currentOVer.getBallsBowled() == MatchConstants.NUMBER_OF_BALLS_IN_OVER) return 1;
        else return ((double)currentOVer.getBallsBowled())/10;
    }

    private void computeEconomy() {
        List<Player> players = bowlingTeam.getPlayers();
        for(int i=MatchConstants.NUMBER_OF_BATSMEN_IN_TEAM; i<MatchConstants.NUMBER_OF_PLAYERS_IN_TEAM; i++) {
            players.get(i).computeEconomy();
        }
    }

    private void computeRunRate() {
        if(oversPlayed == 0) runRate = 0;
        else runRate = ((double)inningsScore)/oversPlayed;
    }
}
