package com.harshrk.assignments.MatchObjects;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum PlayerType {
    BATSMAN(Arrays.asList(50.0, 22.5, 10.0, 4.5, 6.0, 1.0, 6.0), ((double)100)/45),
    BOWLER(Arrays.asList(65.0, 16.0, 9.5, 4.5, 2.5, 1.0, 2.5), ((double)100)/30),
    ALLROUNDER(Arrays.asList(55.0, 20.0, 9.5, 4.5, 5.0, 1.0, 5.0), ((double)100)/35);

    private List<Double> runPercentage;
    private List<Double> prefixPercentage;
    private double wicketPercentage;

    PlayerType(List<Double> runPercentage, double wicketPercentage) {
        this.runPercentage = runPercentage;
        this.wicketPercentage = wicketPercentage;

        prefixPercentage = new ArrayList<Double>();
        prefixPercentage.add(runPercentage.get(0));
        for(int i=1; i<runPercentage.size(); i++) {
            prefixPercentage.add(prefixPercentage.get(i-1)+runPercentage.get(i));
        }
    }
}
