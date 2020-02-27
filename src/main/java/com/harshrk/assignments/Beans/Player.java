package com.harshrk.assignments.Beans;

import com.harshrk.assignments.Constants.MatchConstants;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)   //Static factory used to count number of instances
public class Player {

    private static int INSTANCES=0;
    private static Random random = new Random();

    @NonNull private String playerName;
    @NonNull PlayerType type;

    @Setter(AccessLevel.PACKAGE) private boolean isNotOut = false;
    private int runsScored;
    private int ballsPlayed;
    private int fours;
    private int sixes;
    private double strikeRate;
    private boolean hasIncompleteOver = false;
    private double oversBowled;
    private int runsGiven;
    private int wicketsTaken;
    private int maidenOvers;
    private double economy;

    public static Player getPlayer(PlayerType playerType) {
        StringBuilder name = new StringBuilder("");
        int instances = INSTANCES++;

        if(instances == 0) name.append('A');
        while(instances>0) {
            name.append((char)((instances%26)+'A'));
            instances /= 26;
        }
        return new Player(name.reverse().toString(), playerType);
    }

    public static int play(Player batsman, Player bowler) {
        batsman.incrementBallsPlayed();

        if(bowler.takeWicket(batsman, 0.05, 0.95)) {
            bowler.incrementWickets();
            batsman.computeStrikeRate();
            batsman.isNotOut = false;
            return MatchConstants.PLAYER_OUT;
        }
        else {
            int runs = batsman.getRuns(bowler, 6.0/95, 5.5*3.5);
            if(runs == 4) batsman.incrementFours();
            else if(runs == 6) batsman.incrementSixes();

            batsman.incrementRunsScored(runs);
            bowler.incrementRunsGiven(runs);
            return runs;
        }
    }

    public void computeStrikeRate() {
        if(ballsPlayed == 0) strikeRate = 0;
        else strikeRate = ((double)runsScored*100)/ballsPlayed;
    }

    public void incrementOversBowled(double overLength) {
        if(overLength<1) hasIncompleteOver = true;
        oversBowled += overLength;
    }

    public void incrementMaidenOvers() {
        maidenOvers++;
    }

    public void computeEconomy() {
        if(oversBowled == 0) economy = 0;
        else economy = ((double)runsGiven)/oversBowled;
    }

    private void incrementBallsPlayed() {
        ballsPlayed++;
    }

    private void incrementFours() {
        fours++;
    }

    private void incrementSixes() {
        sixes++;
    }

    private void incrementRunsScored(int runs) {
        runsScored += runs;
    }

    private void incrementRunsGiven(int runs) {
        runsGiven += runs;
    }

    private void incrementWickets() {
        wicketsTaken++;
    }

    private int getRuns(Player bowler, double alpha, double beta) {
        double battingStrikerate = Math.max(0, type.getBattingStrikerate() +
            random.nextGaussian()*MatchConstants.BATTING_STRIKERATE_VARIANCE);
        double bowlingEconomy = Math.max(0, bowler.type.getBowlingEconomy() +
            random.nextGaussian()*MatchConstants.BOWLING_ECONOMY_VARIANCE);
        double runEstimate = alpha*battingStrikerate - beta/bowlingEconomy;
        int runs = Math.min(6, Math.max(0, (int)Math.round(runEstimate)));
        return runs;
    }

    private boolean takeWicket(Player batsman, double gamma, double delta) {
        double ballsPlayedPerInnings = Math.max(0, batsman.type.getBallsPlayedPerInnings() +
            random.nextGaussian()*MatchConstants.BALLS_PER_INNINGS_VARIANCE);
        double bowlingStrikerate = Math.max(0, type.getBowlingStrikerate() +
            random.nextGaussian()*MatchConstants.BOWLING_STRIKERATE_VARIANCE);
        double ballsPerWicket = gamma*ballsPlayedPerInnings + delta*bowlingStrikerate;
        double wicketRandomVariable = Math.random()*ballsPerWicket;
        return (wicketRandomVariable < 1);
    }
}
