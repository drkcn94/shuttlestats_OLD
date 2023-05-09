package com.badminton.shuttlestats.services;

import com.badminton.shuttlestats.model.Match;
import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.model.enums.Gender;
import com.badminton.shuttlestats.model.enums.matchType;
import com.badminton.shuttlestats.model.keys.MatchId;
import com.badminton.shuttlestats.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }


    // TO BE COMPLETED
    public Match saveMatch(UUID sessionId, List<Player> teamOnePlayers, List<Player> teamTwoPlayers, int teamOneScore, int teamTwoScore) {
        if(teamOnePlayers == null || teamTwoPlayers == null) {
            throw new IllegalArgumentException();
        }

        Match toSave = new Match(sessionId);

        toSave.setMatchType(defineMatchType(teamOnePlayers, teamTwoPlayers).toString());
        toSave.setTeamOneScore(teamOneScore);
        toSave.setTeamTwoScore(teamTwoScore);

        if (teamOnePlayers.size() == 1 && teamTwoPlayers.size() == 1) {
            toSave.setPlayerOneId(teamOnePlayers.get(0).getPlayerId());
            toSave.setPlayerTwoId(teamTwoPlayers.get(0).getPlayerId());
        }
        else if (teamOnePlayers.size() == 2 && teamTwoPlayers.size() == 2) {
            toSave.setPlayerOneId(teamOnePlayers.get(0).getPlayerId());
            toSave.setPlayerTwoId(teamOnePlayers.get(1).getPlayerId());
            toSave.setPlayerThreeId(teamTwoPlayers.get(0).getPlayerId());
            toSave.setPlayerFourId(teamTwoPlayers.get(1).getPlayerId());
        }
        else {
            throw new IllegalArgumentException();
        }

        return matchRepository.save(toSave);
    }

    public Match updateMatch(UUID sessionId, UUID matchId, List<Player> teamOnePlayers, List<Player> teamTwoPlayers, int teamOneScore, int teamTwoScore) {
        if(teamOnePlayers == null || teamTwoPlayers == null) {
            throw new IllegalArgumentException();
        }
        validateScore(teamOneScore,teamTwoScore);

        MatchId matchToFind = new MatchId(sessionId, matchId);

        Match toUpdate = matchRepository.getById(matchToFind);

        if(toUpdate.getMatchType() != defineMatchType(teamOnePlayers, teamTwoPlayers).toString()) {
            toUpdate.setMatchType(defineMatchType(teamOnePlayers, teamTwoPlayers).toString());
        }

        toUpdate.setPlayerOneId(teamOnePlayers.get(0).getPlayerId());
        toUpdate.setPlayerTwoId(teamOnePlayers.get(1).getPlayerId());
        toUpdate.setPlayerThreeId(teamTwoPlayers.get(0).getPlayerId());
        toUpdate.setPlayerFourId(teamTwoPlayers.get(1).getPlayerId());

        toUpdate.setTeamOneScore(teamOneScore);
        toUpdate.setTeamTwoScore(teamTwoScore);

        return matchRepository.save(toUpdate);
    }

    public void deleteMatchById(MatchId id) {
        matchRepository.deleteById(id);
    }

    public Enum defineMatchType(List<Player> teamOnePlayers, List<Player> teamTwoPlayers) {
        if(teamOnePlayers == null || teamTwoPlayers == null) {
            throw new IllegalArgumentException();
        }

        Enum<matchType> matchReturnType = matchType.OTHER_SINGLES;
        if (teamOnePlayers.size() == 1 && teamTwoPlayers.size() == 1) {
            if (teamOnePlayers.get(0).getPlayerGender() == Gender.MALE.toString() && teamTwoPlayers.get(0).getPlayerGender() == Gender.MALE.toString()) {
                matchReturnType = matchType.MENS_SINGLES;
            }
            else if (teamOnePlayers.get(0).getPlayerGender() == Gender.FEMALE.toString() && teamTwoPlayers.get(0).getPlayerGender() == Gender.FEMALE.toString()) {
                matchReturnType = matchType.WOMENS_SINGLES;
            }
        }
        else {
            matchReturnType = matchType.OTHER_DOUBLES;
            int teamOneGender = 0;
            int teamTwoGender = 0;

            for (Player player: teamOnePlayers) {
                if (player.getPlayerGender() == Gender.MALE.toString()) {
                    teamOneGender++;
                }
                else {
                    teamOneGender--;
                }
            }

            for (Player player: teamTwoPlayers) {
                if (player.getPlayerGender() == Gender.MALE.toString()) {
                    teamTwoGender++;
                }
                else {
                    teamTwoGender--;
                }
            }

            if (teamOneGender == 2 && teamTwoGender == 2) {
                matchReturnType = matchType.MENS_DOUBLES;
            } else if (teamOneGender == -2 && teamTwoGender == -2) {
                matchReturnType = matchType.WOMENS_DOUBLES;
            } else if (teamOneGender == 0 && teamTwoGender == 0) {
                matchReturnType = matchType.MIXED_DOUBLES;
            }
        }
        return matchReturnType;
    }

    public void validateScore(int teamOneScore, int teamTwoScore) {
        // Check for same score on both teams
        if (teamOneScore == teamTwoScore) {
            throw new IllegalArgumentException();
        }

        // Check for out of bounds score
        if (teamOneScore < 0 || teamOneScore > 30 ||
            teamTwoScore < 0 || teamTwoScore > 30) {
            throw new IllegalArgumentException();
        }

        // Check for overtime score
        if ((teamOneScore >= 22 && teamOneScore <= 30) &&
                (teamTwoScore >= 22 && teamTwoScore <= 30)) {
            int scoreDifference = Math.abs(teamOneScore - teamTwoScore);

            // Overtime match win conditions
            // 1. Point Difference of 2 (up to 30-28/28-30)
            // 2. First to 30 (30-29/29-30)
            if (scoreDifference == 1) {
                if ((teamOneScore != 30 && teamTwoScore != 29) ||
                        (teamOneScore != 29 && teamTwoScore != 30)) {
                }
                else {
                    throw new IllegalArgumentException();
                }
            }
            if (scoreDifference == 2) {}
            else {
                throw new IllegalArgumentException();
            }
            // Check for regular score
        } else if (teamOneScore == 21 && teamTwoScore <= 19 ||
            teamOneScore <= 19 && teamTwoScore == 21) {

        } else {
            throw new IllegalArgumentException();
        }

    }
}
