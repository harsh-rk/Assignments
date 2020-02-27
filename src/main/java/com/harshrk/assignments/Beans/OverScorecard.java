package com.harshrk.assignments.Beans;

import com.harshrk.assignments.Constants.MatchConstants;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
public class OverScorecard {

    private String bowler;
    private String battingTeam;
    private int overScore = 0;
    private int ballsBowled = 0;
    private int overWickets = 0;
    private List<Character> deliveries;

    public List<Character> getDeliveries() {
        return Collections.unmodifiableList(deliveries);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Over:");
        for(Character c : deliveries) {
            sb.append(" ");
            switch(c) {
                case MatchConstants.WICKET:
                    sb.append("W");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}
