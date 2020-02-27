package com.harshrk.assignments.Beans;

import com.harshrk.assignments.Constants.MatchConstants;
import lombok.Getter;

@Getter
public enum PlayerType {
    BATSMAN(MatchConstants.BATSMAN_BATTING_STRIKERATE, MatchConstants.BATSMAN_BALLS_PLAYED,
        MatchConstants.BATSMAN_ECONOMY, MatchConstants.BATSMAN_BOWLING_STRIKERATE),
    ALLROUNDER(MatchConstants.ALLROUNDER_BATTING_STRIKERATE, MatchConstants.ALLROUNDER_BALLS_PLAYED,
        MatchConstants.ALLROUNDER_ECONOMY, MatchConstants.ALLROUNDER_BOWLING_STRIKERATE),
    BOWLER(MatchConstants.BOWLER_BATTING_STRIKERATE, MatchConstants.BOWLER_BALLS_PLAYED,
        MatchConstants.BOWLER_ECONOMY, MatchConstants.BOWLER_BOWLING_STRIKERATE);

    private double battingStrikerate;
    private double ballsPlayedPerInnings;
    private double bowlingEconomy;
    private double bowlingStrikerate;

    PlayerType(double battingStrikerate, double ballsPlayedPerInnings, double bowlingEconomy, double bowlingStrikerate) {
        this.battingStrikerate = battingStrikerate;
        this.ballsPlayedPerInnings = ballsPlayedPerInnings;
        this.bowlingEconomy = bowlingEconomy;
        this.bowlingStrikerate = bowlingStrikerate;
    }
}
