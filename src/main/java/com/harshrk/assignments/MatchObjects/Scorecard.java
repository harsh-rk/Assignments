package com.harshrk.assignments.MatchObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Scorecard {

    private String firstInnings;
    private String secondInnings;
    private String result;
    private Team firstTeam;
    private Team secondTeam;
}