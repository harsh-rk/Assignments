package com.harshrk.assignments.MatchObjects;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Partnership {

    @NonNull private String batsmanOne;
    private PlayerType batsmanOneType;
    private boolean batsmanOneNotOut = false;
    private int batsmanOneContribution = 0;
    private int batsmanOneBallsPlayed = 0;
    @NonNull private String batsmanTwo;
    private PlayerType batsmanTwoType;
    private boolean batsmanTwoNotOut = false;
    private int batsmanTwoContribution = 0;
    private int batsmanTwoBallsPlayed = 0;

    private int partnershipRuns = 0;
    private int partnershipBalls = 0;

    public Partnership(Player playerOne, Player playerTwo) {
        batsmanOne = playerOne.getPlayerName();
        batsmanOneType = playerOne.getType();
        batsmanTwo = playerTwo.getPlayerName();
        batsmanTwoType = playerTwo.getType();
    }

    public void setNotOut(Player player) {
        if(player.getPlayerName() == batsmanOne) batsmanOneNotOut = player.isNotOut();
        else if(player.getPlayerName() == batsmanTwo) batsmanTwoNotOut = player.isNotOut();
    }

    public void incrementRunsScored(Player player, int runs) {
        incrementBatsmanScore(player, runs);
        partnershipRuns += runs;
    }

    public void incrementBallsPlayed(Player player) {
        incrementBatsmanBallsPlayed(player);
        partnershipBalls++;
    }

    private void incrementBatsmanScore(Player player, int runs) {
        if(player.getPlayerName() == batsmanOne) batsmanOneContribution += runs;
        else batsmanTwoContribution += runs;
    }

    private void incrementBatsmanBallsPlayed(Player player) {
        if(player.getPlayerName() == batsmanOne) batsmanOneBallsPlayed++;
        else batsmanTwoBallsPlayed++;
    }

    @Override
    public String toString() {
        String partnership = String.format("%s: %s(%d%s runs off %d balls) and %s: %s(%d%s runs off %d balls) had a partnership of %d%s runs in %d balls",
            batsmanOneType, batsmanOne, batsmanOneContribution, (batsmanOneNotOut ? "*" : ""), batsmanOneBallsPlayed,
            batsmanTwoType, batsmanTwo, batsmanTwoContribution, (batsmanTwoNotOut ? "*" : ""), batsmanTwoBallsPlayed,
            partnershipRuns, (batsmanOneNotOut && batsmanTwoNotOut ? "*" : ""), partnershipBalls);
        return partnership;
    }
}
