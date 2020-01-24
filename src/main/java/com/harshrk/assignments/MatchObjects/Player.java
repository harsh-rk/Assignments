package com.harshrk.assignments.MatchObjects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static com.harshrk.assignments.Constants.MatchConstants.PLAYER_OUT;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)   //Static factory used to count number of instances
public class Player {

    private static int INSTANCES=0;

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

        if(bowler.takeWicket()) {
            bowler.incrementWickets();
            batsman.computeStrikeRate();
            batsman.isNotOut = false;
            return PLAYER_OUT;
        }
        else {
            int runs = batsman.getRuns();
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

    private int getRuns() {
        double runRandomVariable = Math.random()*100;
        for(int i=type.getPrefixPercentage().size()-1; i>=0; i--) {
            if(runRandomVariable > type.getPrefixPercentage().get(i)) return (i+1);
        }
        return 0;
    }

    private boolean takeWicket() {
        double wicketRandomVariable = Math.random()*100;
        return (wicketRandomVariable < type.getWicketPercentage());
    }
}
