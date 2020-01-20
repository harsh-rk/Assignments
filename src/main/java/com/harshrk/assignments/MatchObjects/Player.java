package com.harshrk.assignments.MatchObjects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.harshrk.assignments.Constants.MatchConstants.PLAYER_OUT;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)   //Static factory used to count number of instances
public class Player {

    private static int INSTANCES=0;

    @NonNull private String playerName;
    private int score;
    private int numberOfBalls;

    public static Player getPlayer() {
//        String name = "" + (char)((INSTANCES%26)+'A');
        StringBuilder name = new StringBuilder("");
        int instances = INSTANCES++;

        if(instances==0) name.append('A');
        while(instances>0) {
            name.append((char)((instances%26)+'A'));
            instances /= 26;
        }
        return new Player(name.reverse().toString());
    }

    public int play() {
        numberOfBalls++;

        if(playerNotOut()) {
            int runs = getRuns();
            score += runs;
            return runs;
        }
        else return PLAYER_OUT;
    }

    private int getRuns() {
        int runProbability = (int)(Math.random()*80);

        if(runProbability<40) return 0;
        else if(runProbability-40<16) return 1;
        else if(runProbability-56<6) return 2;
        else if(runProbability-62<3) return 3;
        else if(runProbability-65<9) return 4;
        else if(runProbability-74<1) return 5;
        else return 6;
    }

    private boolean playerNotOut() {
        int notOutProbability = (int)(Math.random()*30);
        return (notOutProbability > 0);
    }
}
