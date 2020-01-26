package com.harshrk.assignments.MatchObjects;

import lombok.Getter;

import static com.harshrk.assignments.Constants.MatchConstants.*;

@Getter
public enum PlayerType {
    BATSMAN(BATSMAN_BATTING_STRIKERATE, BATSMAN_BALLS_PLAYED, BATSMAN_ECONOMY, BATSMAN_BOWLING_STRIKERATE),
    ALLROUNDER(ALLROUNDER_BATTING_STRIKERATE, ALLROUNDER_BALLS_PLAYED, ALLROUNDER_ECONOMY, ALLROUNDER_BOWLING_STRIKERATE),
    BOWLER(BOWLER_BATTING_STRIKERATE, BOWLER_BALLS_PLAYED, BOWLER_ECONOMY, BOWLER_BOWLING_STRIKERATE);

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
