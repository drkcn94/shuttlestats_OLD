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
    @Autowired
    private MatchRepository matchRepository;

    public MatchService() {}

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

        matchRepository.save(toSave);
        return toSave;
    }

    public void deleteMatchById(MatchId id) { matchRepository.deleteById(id);}

    public Enum defineMatchType(List<Player> teamOnePlayers, List<Player> teamTwoPlayers) {

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
                    teamOneGender++;
                }
                else {
                    teamTwoGender--;
                }
            }

            if (teamOneGender == 2 && teamTwoGender == 2) {
                matchReturnType = matchType.MENS_DOUBLES;
            } else if (teamOneGender == -2 && teamTwoGender == 2) {
                matchReturnType = matchType.WOMENS_DOUBLES;
            } else if (teamOneGender == 0 && teamTwoGender == 0) {
                matchReturnType = matchType.MIXED_DOUBLES;
            }
        }
        return matchReturnType;
    }
}
