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
    public Match saveMatch(UUID sessionId, List<Player> playersInGame, int teamOneScore, int teamTwoScore) {
        if(playersInGame.size() != 2 || playersInGame.size() != 4) {
            throw new IllegalArgumentException("Invalid number of players");
        }

        Match toSave = new Match(sessionId);

        toSave.setMatchType(defineMatchType(playersInGame));
        toSave.setTeamOneScore(teamOneScore);
        toSave.setTeamTwoScore(teamTwoScore);

        if (playersInGame.size() == 2) {
            toSave.setPlayerOneId(playersInGame.get(0).getPlayerId());
            toSave.setPlayerTwoId(playersInGame.get(1).getPlayerId());

        } else {
            toSave.setPlayerOneId(playersInGame.get(0).getPlayerId());
            toSave.setPlayerTwoId(playersInGame.get(1).getPlayerId());
            toSave.setPlayerThreeId(playersInGame.get(2).getPlayerId());
            toSave.setPlayerFourId(playersInGame.get(3).getPlayerId());
        }

        matchRepository.save(toSave);
        return toSave;
    }

    public void deleteMatchById(MatchId id) { matchRepository.deleteById(id);}

    public String defineMatchType(List<Player> playersInGame) {

        String matchReturnType = matchType.OTHER_SINGLES.toString();
        if (playersInGame.size() == 2) {
            if (playersInGame.get(0).getPlayerGender() == Gender.MALE.toString() && playersInGame.get(1).getPlayerGender() == Gender.MALE.toString()) {
                matchReturnType = matchType.MENS_SINGLES.toString();
            }
            else if (playersInGame.get(0).getPlayerGender() == Gender.FEMALE.toString() && playersInGame.get(1).getPlayerGender() == Gender.FEMALE.toString()) {
                matchReturnType = matchType.WOMENS_SINGLES.toString();
            }
        }
        else {
            matchReturnType = matchType.OTHER_DOUBLES.toString();
            int teamOneGender = 0;
            int teamTwoGender = 0;

            for (int i = 0; i < playersInGame.size(); i++) {
                if(i <= 1) {
                    if (playersInGame.get(i).getPlayerGender() == Gender.MALE.toString()) {
                        teamOneGender++;
                    }
                    else {
                        teamOneGender--;
                    }
                }
                else {
                    if (playersInGame.get(i).getPlayerGender() == Gender.MALE.toString()) {
                        teamTwoGender++;
                    }
                    else {
                        teamTwoGender--;
                    }
                }
            }
            if (teamOneGender == 2 && teamTwoGender == 2) {
                matchReturnType = matchType.MENS_DOUBLES.toString();
            } else if (teamOneGender == -2 && teamTwoGender == 2) {
                matchReturnType = matchType.WOMENS_DOUBLES.toString();
            } else if (teamOneGender == 0 && teamTwoGender == 0) {
                matchReturnType = matchType.MIXED_DOUBLES.toString();
            }
        }
        return matchReturnType;
    }
}
