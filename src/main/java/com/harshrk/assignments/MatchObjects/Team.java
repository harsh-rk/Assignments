package com.harshrk.assignments.MatchObjects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.harshrk.assignments.Constants.MatchConstants.NUMBER_OF_PLAYERS_IN_TEAM;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)   //Static factory ensures exactly 'NUMBER_OF_PLAYERS_IN_TEAM' players on team
public class Team {

    private String teamName;
    private List<Player> players;

    public static Team getTeam(String name) {
        List<Player> playerList = new ArrayList<Player>();
        for(int i=0; i<NUMBER_OF_PLAYERS_IN_TEAM; i++) {
            playerList.add(Player.getPlayer());
        }
        return new Team(name, playerList);
    }
}
