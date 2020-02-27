package com.harshrk.assignments.Services;

import com.harshrk.assignments.Beans.BattingScorecard;
import com.harshrk.assignments.Beans.BowlingScorecard;
import com.harshrk.assignments.Beans.OverScorecard;
import com.harshrk.assignments.Beans.Player;
import com.harshrk.assignments.Beans.Scorecard;
import com.harshrk.assignments.Beans.Team;
import com.harshrk.assignments.Constants.MatchConstants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        firstInnings = new Innings(firstTeam, secondTeam, MatchConstants.NO_TARGET);
        firstInnings.simulateInnings();

        secondInnings = new Innings(secondTeam, firstTeam, firstInnings.getInningsScore()+1);
        secondInnings.simulateInnings();

        if(firstInnings.getInningsScore()==secondInnings.getInningsScore()) matchTied = true;
        else firstTeamWins = firstInnings.getInningsScore() > secondInnings.getInningsScore();

        Scorecard scorecard = generateScorecard();
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

    private Scorecard generateScorecard() {
        Scorecard scorecard = Scorecard.builder()
            .firstInnings(getInningsResult(firstInnings, firstTeam))
            .secondInnings(getInningsResult(secondInnings, secondTeam))
            .result(declareResult(matchTied, firstTeamWins))
            .firstTeamBatting(generateBattingStats(firstTeam))
            .secondTeamBowling(generateBowlingStats(secondTeam))
            .secondTeamBatting(generateBattingStats(secondTeam))
            .firstTeamBowling(generateBowlingStats(firstTeam))
            .firstTeamPartnerships(firstInnings.getPartnerships())
            .secondTeamPartnerships(secondInnings.getPartnerships())
            .firstInningsOvers(generateOverStats(firstInnings))
            .secondInningsOvers(generateOverStats(secondInnings))
            .build();
        return scorecard;
    }

    private String getInningsResult(Innings innings, Team team) {
        String inningsResult = String.format("%s scored %d/%d in %.1f overs with a run rate of %.2f", team.getTeamName(),
                innings.getInningsScore(), innings.getWickets(), innings.getOversPlayed(), innings.getRunRate());
        return inningsResult;
    }

    private String declareResult(boolean matchTied, boolean firstTeamWins) {
        StringBuilder sb = new StringBuilder();

        if(matchTied) {
            sb.append("No one won the match!");
            return sb.toString();
        }

        if(firstTeamWins) {
            sb.append(firstTeam.getTeamName());
            sb.append(" won the match by ");
            sb.append(firstInnings.getInningsScore()-secondInnings.getInningsScore());
            sb.append(" runs!");
        }
        else {
            sb.append(secondTeam.getTeamName());
            sb.append(" won the match by ");
            sb.append(MatchConstants.WICKETS_IN_INNINGS - secondInnings.getWickets());
            sb.append(" wickets!");
        }

        return sb.toString();
    }

    private List<BattingScorecard> generateBattingStats(Team team) {
        List<BattingScorecard> battingStats = new ArrayList<BattingScorecard>();
        List<Player> players = team.getPlayers();
        for(int i=0; i< MatchConstants.NUMBER_OF_PLAYERS_IN_TEAM; i++) {
            Player player = players.get(i);
            BattingScorecard battingScorecard = BattingScorecard.builder()
                .playerType(player.getType().name())
                .playerName(player.getPlayerName())
                .isNotOut(player.isNotOut())
                .runsScored(player.getRunsScored())
                .ballsPlayed(player.getBallsPlayed())
                .fours(player.getFours())
                .sixes(player.getSixes())
                .strikeRate(player.getStrikeRate())
                .build();
            battingStats.add(battingScorecard);
        }
        return battingStats;
    }

    private List<BowlingScorecard> generateBowlingStats(Team team) {
        List<BowlingScorecard> bowlingStats = new ArrayList<BowlingScorecard>();
        List<Player> players = team.getPlayers();
        for(int i=MatchConstants.NUMBER_OF_BATSMEN_IN_TEAM; i<MatchConstants.NUMBER_OF_PLAYERS_IN_TEAM; i++) {
            Player player = players.get(i);
            BowlingScorecard bowlingScorecard = BowlingScorecard.builder()
                .playerType(player.getType())
                .playerName(player.getPlayerName())
                .hasIncompleteOver(player.isHasIncompleteOver())
                .oversBowled(player.getOversBowled())
                .maidenOvers(player.getMaidenOvers())
                .runsGiven(player.getRunsGiven())
                .wicketsTaken(player.getWicketsTaken())
                .economy(player.getEconomy())
                .build();
            bowlingStats.add(bowlingScorecard);
        }
        return bowlingStats;
    }

    private List<OverScorecard> generateOverStats(Innings innings) {
        List<OverScorecard> overStats = new ArrayList<OverScorecard>();
        List<Over> overs = innings.getOvers();
        for(int i=0; i<overs.size(); i++) {
            Over over = overs.get(i);
            OverScorecard stat = OverScorecard.builder()
                .bowler(over.getBowler().getPlayerName())
                .battingTeam(over.getBattingTeam().getTeamName())
                .overScore(over.getOverScore())
                .ballsBowled(over.getBallsBowled())
                .overWickets(over.getOverWickets())
                .deliveries(over.getDeliveries())
                .build();
            overStats.add(stat);
        }
        return overStats;
    }
}
