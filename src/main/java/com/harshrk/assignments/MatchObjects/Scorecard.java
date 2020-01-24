package com.harshrk.assignments.MatchObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Scorecard {

    private String firstInnings;
    private String secondInnings;
    private String result;
    private List<BattingScorecard> firstTeamBatting;
    private List<BowlingScorecard> secondTeamBowling;
    private List<BattingScorecard> secondTeamBatting;
    private List<BowlingScorecard> firstTeamBowling;
    private List<Partnership> firstTeamPartnerships;
    private List<Partnership> secondTeamPartnerships;
    private List<OverScorecard> firstInningsOvers;
    private List<OverScorecard> secondInningsOvers;
}