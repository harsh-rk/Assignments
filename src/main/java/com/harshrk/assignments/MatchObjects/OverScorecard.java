package com.harshrk.assignments.MatchObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OverScorecard {

    private String bowler;
    private String battingTeam;
    private int overScore = 0;
    private int ballsBowled = 0;
    private int overWickets = 0;
    private List<Character> deliveries;
}
