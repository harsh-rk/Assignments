package com.harshrk.assignments.Constants;

public interface MatchConstants {

    int NUMBER_OF_OVERS_IN_ODI = 50;
    int NUMBER_OF_BALLS_IN_OVER = 6;
    int NUMBER_OF_PLAYERS_IN_TEAM = 11;
    int NUMBER_OF_BATSMEN_IN_TEAM = 4;
    int NUMBER_OF_ALLROUNDERS_IN_TEAM = 3;
    int NUMBER_OF_BOWLERS_IN_TEAM = 4;
    int WICKETS_IN_INNINGS = 10;
    int NUMBER_OF_OVERS_PER_BOWLER = 10;
    int PLAYER_OUT = -1;
    int ALL_OUT = -1;
    int NO_TARGET = Integer.MAX_VALUE;
    int TARGET_REACHED = 42;

    char WICKET = 'w';
    char WIDE = 'd';
    char NO_BALL = 'n';
    char BYE = 'b';
    char LEG_BYE = 'l';

    double BATTING_STRIKERATE_VARIANCE = 30.0;
    double BALLS_PER_INNINGS_VARIANCE = 50.0;
    double BOWLING_ECONOMY_VARIANCE = 1.0;
    double BOWLING_STRIKERATE_VARIANCE = 20;

    double BATSMAN_BATTING_STRIKERATE = 95.0;
    double BATSMAN_BALLS_PLAYED = 140.0;
    double BATSMAN_ECONOMY = 5.5;
    double BATSMAN_BOWLING_STRIKERATE = 70.0;
    double ALLROUNDER_BATTING_STRIKERATE = 70.0;
    double ALLROUNDER_BALLS_PLAYED = 95;
    double ALLROUNDER_ECONOMY = 4.5;
    double ALLROUNDER_BOWLING_STRIKERATE = 55.0;
    double BOWLER_BATTING_STRIKERATE = 45.0;
    double BOWLER_BALLS_PLAYED = 50.0;
    double BOWLER_ECONOMY = 3.5;
    double BOWLER_BOWLING_STRIKERATE = 40.0;
}
