package com.harshrk.assignments.ServiceObjects;

import com.harshrk.assignments.MatchObjects.Scorecard;
import com.harshrk.assignments.MatchObjects.Team;
import org.springframework.stereotype.Service;

@Service
public class Match {

    private Team firstTeam;
    private Team secondTeam;
    private boolean matchTied = false;
    private boolean firstTeamWins = false;
    private Innings firstInnings;
    private Innings secondInnings;

    public Scorecard simulateMatch() {

        matchTied = false;
        firstTeamWins = false;

        toss();

        firstInnings = new Innings();
        firstInnings.simulateInnings(firstTeam);

        secondInnings = new Innings(firstInnings.getScore()+1);
        secondInnings.simulateInnings(secondTeam);

        if(firstInnings.getScore()==secondInnings.getScore()) matchTied = true;
        else firstTeamWins = firstInnings.getScore()>secondInnings.getScore();

        Scorecard scorecard = new Scorecard(
                getInningsResult(firstInnings, firstTeam), getInningsResult(secondInnings, secondTeam),
                declareResult(matchTied, firstTeamWins), firstTeam, secondTeam);
        return scorecard;
    }

    private void toss() {
        boolean teamABatsFirst = ((int)(Math.random()*2)==1);

        if(teamABatsFirst) {
            firstTeam = Team.getTeam("Team-A");
            secondTeam = Team.getTeam("Team-B");
        }
        else {
            firstTeam = Team.getTeam("Team-B");
            secondTeam = Team.getTeam("Team-A");
        }
    }

    private String getInningsResult(Innings innings, Team team) {
        String inningsResult = String.format("%s scored %d/%d in %.1f overs", team.getTeamName(),
                innings.getScore(), innings.getWickets(), innings.getOversPlayed());
        return inningsResult;
    }

    private String declareResult(boolean matchTied, boolean firstTeamWins) {
        String result = String.format("%s won the match!", (matchTied ? "No one"
                : (firstTeamWins ? firstTeam.getTeamName() : secondTeam.getTeamName()) ) );
        return result;
    }
}
