package com.harshrk.assignments.MatchObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static com.harshrk.assignments.Constants.MatchConstants.WICKET;

@Getter
@AllArgsConstructor
public class OverScorecard {

    private String bowler;
    private String battingTeam;
    private int overScore = 0;
    private int ballsBowled = 0;
    private int overWickets = 0;
    private List<Character> deliveries;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Over:");
        for(Character c : deliveries) {
            sb.append(" ");
            switch(c) {
                case WICKET:
                    sb.append("W");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}
