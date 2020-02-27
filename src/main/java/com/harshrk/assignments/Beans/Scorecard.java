package com.harshrk.assignments.Beans;

import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
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

    public List<BattingScorecard> getFirstTeamBatting() {
        return Collections.unmodifiableList(firstTeamBatting);
    }

    public List<BowlingScorecard> getSecondTeamBowling() {
        return Collections.unmodifiableList(secondTeamBowling);
    }

    public List<BattingScorecard> getSecondTeamBatting() {
        return Collections.unmodifiableList(secondTeamBatting);
    }

    public List<BowlingScorecard> getFirstTeamBowling() {
        return Collections.unmodifiableList(firstTeamBowling);
    }

    public List<Partnership> getFirstTeamPartnerships() {
        return Collections.unmodifiableList(firstTeamPartnerships);
    }

    public List<Partnership> getSecondTeamPartnerships() {
        return Collections.unmodifiableList(secondTeamPartnerships);
    }

    public List<OverScorecard> getFirstInningsOvers() {
        return Collections.unmodifiableList(firstInningsOvers);
    }

    public List<OverScorecard> getSecondInningsOvers() {
        return Collections.unmodifiableList(secondInningsOvers);
    }
}