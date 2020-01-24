package com.harshrk.assignments.MatchObjects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.harshrk.assignments.Constants.MatchConstants.ALL_OUT;
import static com.harshrk.assignments.Constants.MatchConstants.NUMBER_OF_ALLROUNDERS_IN_TEAM;
import static com.harshrk.assignments.Constants.MatchConstants.NUMBER_OF_BATSMEN_IN_TEAM;
import static com.harshrk.assignments.Constants.MatchConstants.NUMBER_OF_BOWLERS_IN_TEAM;
import static com.harshrk.assignments.Constants.MatchConstants.NUMBER_OF_OVERS_PER_BOWLER;
import static com.harshrk.assignments.Constants.MatchConstants.NUMBER_OF_PLAYERS_IN_TEAM;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)   //Static factory ensures exactly 'NUMBER_OF_PLAYERS_IN_TEAM' players on team
public class Team {

    private String teamName;
    private List<Player> players;
    private int nextBatsmanNumber;

    public static Team getTeam(String name) {
        List<Player> playerList = new ArrayList<Player>();
        for(int i=0; i<NUMBER_OF_BATSMEN_IN_TEAM; i++) {
            playerList.add(Player.getPlayer(PlayerType.BATSMAN));
        }
        for(int i=0; i<NUMBER_OF_ALLROUNDERS_IN_TEAM; i++) {
            playerList.add(Player.getPlayer(PlayerType.ALLROUNDER));
        }
        for(int i=0; i<NUMBER_OF_BOWLERS_IN_TEAM; i++) {
            playerList.add(Player.getPlayer(PlayerType.BOWLER));
        }
        return new Team(name, playerList, 2);
    }

    public int getNextBatsmanNumber() {
        if(nextBatsmanNumber == NUMBER_OF_PLAYERS_IN_TEAM) return ALL_OUT;
        else return nextBatsmanNumber++;
    }

    public Player getBatsman(int batsmanNumber) {
        Player batsman = players.get(batsmanNumber);
        batsman.setNotOut(true);
        return batsman;
    }

    public int getNextBowlerNumber(int currentBowlerNumber) {
        while(true) {
            int nextBowlerNumber = (int) (Math.random() * (NUMBER_OF_ALLROUNDERS_IN_TEAM + NUMBER_OF_BOWLERS_IN_TEAM));
            if (nextBowlerNumber == currentBowlerNumber) continue;
            else if (Math.round(players.get(nextBowlerNumber).getOversBowled()) == NUMBER_OF_OVERS_PER_BOWLER) continue;
            else return nextBowlerNumber;
        }
    }

    public Player getBowler(int bowlerNumber) {
        return players.get(NUMBER_OF_BATSMEN_IN_TEAM + bowlerNumber);
    }
}
