package com.harshrk.assignments.ServiceObjects;

import com.harshrk.assignments.MatchObjects.BattingScorecard;
import com.harshrk.assignments.MatchObjects.BowlingScorecard;
import com.harshrk.assignments.MatchObjects.OverScorecard;
import com.harshrk.assignments.MatchObjects.Player;
import com.harshrk.assignments.MatchObjects.Scorecard;
import com.harshrk.assignments.MatchObjects.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.harshrk.assignments.Constants.MatchConstants.NO_TARGET;
import static com.harshrk.assignments.Constants.MatchConstants.NUMBER_OF_BATSMEN_IN_TEAM;
import static com.harshrk.assignments.Constants.MatchConstants.NUMBER_OF_PLAYERS_IN_TEAM;
import static com.harshrk.assignments.Constants.MatchConstants.WICKETS_IN_INNINGS;

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

        firstInnings = new Innings(firstTeam, secondTeam, NO_TARGET);
        firstInnings.simulateInnings();

        secondInnings = new Innings(secondTeam, firstTeam, firstInnings.getInningsScore()+1);
        secondInnings.simulateInnings();

        if(firstInnings.getInningsScore()==secondInnings.getInningsScore()) matchTied = true;
        else firstTeamWins = firstInnings.getInningsScore() > secondInnings.getInningsScore();

        Scorecard scorecard = new Scorecard(
            getInningsResult(firstInnings, firstTeam), getInningsResult(secondInnings, secondTeam),
            declareResult(matchTied, firstTeamWins), generateBattingStats(firstTeam), generateBowlingStats(secondTeam),
            generateBattingStats(secondTeam), generateBowlingStats(firstTeam),
            firstInnings.getPartnerships(), secondInnings.getPartnerships(),
            generateOverStats(firstInnings), generateOverStats(secondInnings)
        );
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
        String inningsResult = String.format("%s scored %d/%d in %.1f overs with a run rate of %.2f", team.getTeamName(),
                innings.getInningsScore(), innings.getWickets(), innings.getOversPlayed(), innings.getRunRate());
        return inningsResult;
    }

    private String declareResult(boolean matchTied, boolean firstTeamWins) {
        StringBuilder sb = new StringBuilder();

        if(matchTied) sb.append("No one won the match!");
        else if(firstTeamWins) {
            sb.append(firstTeam.getTeamName());
            sb.append(" won the match by ");
            sb.append(firstInnings.getInningsScore()-secondInnings.getInningsScore());
            sb.append(" runs!");
        }
        else {
            sb.append(secondTeam.getTeamName());
            sb.append(" won the match by ");
            sb.append(WICKETS_IN_INNINGS - secondInnings.getWickets());
            sb.append(" wickets!");
        }

        return sb.toString();
    }

    private List<BattingScorecard> generateBattingStats(Team team) {
        List<BattingScorecard> battingStats = new ArrayList<BattingScorecard>();
        List<Player> players = team.getPlayers();
        for(int i=0; i<NUMBER_OF_PLAYERS_IN_TEAM; i++) {
            Player player = players.get(i);
            BattingScorecard battingScorecard = new BattingScorecard(player.getType().name(), player.getPlayerName(),
                player.isNotOut(), player.getRunsScored(), player.getBallsPlayed(), player.getFours(),
                player.getSixes(), player.getStrikeRate());
            battingStats.add(battingScorecard);
        }
        return battingStats;
    }

    private List<BowlingScorecard> generateBowlingStats(Team team) {
        List<BowlingScorecard> bowlingStats = new ArrayList<BowlingScorecard>();
        List<Player> players = team.getPlayers();
        for(int i=NUMBER_OF_BATSMEN_IN_TEAM; i<NUMBER_OF_PLAYERS_IN_TEAM; i++) {
            Player player = players.get(i);
            BowlingScorecard bowlingScorecard = new BowlingScorecard(player.getType(), player.getPlayerName(),
                player.isHasIncompleteOver(), player.getOversBowled(), player.getMaidenOvers(), player.getRunsGiven(),
                player.getWicketsTaken(), player.getEconomy());
            bowlingStats.add(bowlingScorecard);
        }
        return bowlingStats;
    }

    private List<OverScorecard> generateOverStats(Innings innings) {
        List<OverScorecard> overStats = new ArrayList<OverScorecard>();
        List<Over> overs = innings.getOvers();
        for(int i=0; i<overs.size(); i++) {
            Over over = overs.get(i);
            OverScorecard stat = new OverScorecard(over.getBowler().getPlayerName(), over.getBattingTeam().getTeamName(),
                over.getOverScore(), over.getBallsBowled(), over.getOverWickets(), over.getDeliveries());
            overStats.add(stat);
        }
        return overStats;
    }
}
