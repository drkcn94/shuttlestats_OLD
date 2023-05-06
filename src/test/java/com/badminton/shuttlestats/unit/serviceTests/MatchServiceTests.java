package com.badminton.shuttlestats.unit.serviceTests;

import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.repositories.MatchRepository;
import com.badminton.shuttlestats.services.MatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTests {

    @Mock
    private MatchRepository matchRepository;
    @InjectMocks
    private MatchService matchService;

    Player male1 = new Player("male1", "MALE", "RIGHT");
    Player male2 = new Player("male2", "MALE", "RIGHT");
    Player male3 = new Player("male3", "MALE", "RIGHT");
    Player male4 = new Player("male4", "MALE", "RIGHT");
    Player female1 = new Player("female1", "FEMALE", "RIGHT");
    Player female2 = new Player("female2", "FEMALE", "RIGHT");
    Player female3 = new Player("female3", "FEMALE", "RIGHT");
    Player female4 = new Player("female4", "FEMALE", "RIGHT");

    List<Player> team1 = new ArrayList<>();
    List<Player> team2 = new ArrayList<>();
    @Test
    public void testDefineMatchType_MensSingles() {
        team1.add(male1);
        team2.add(male2);

        assertEquals(matchService.defineMatchType(team1, team2).toString(), "MENS_SINGLES");
    }

    @Test
    public void testDefineMatchType_WomensSingles() {
        team1.add(female1);
        team2.add(female2);

        assertEquals(matchService.defineMatchType(team1,team2).toString(), "WOMENS_SINGLES");
    }

    @Test
    public void testDefineMatchType_OtherSingles() {
        team1.add(female1);
        team2.add(male1);

        assertEquals(matchService.defineMatchType(team1,team2).toString(), "OTHER_SINGLES");
    }

    @Test
    public void testDefineMatchType_MensDoubles() {
        List<Player> team1 = List.of(male1, male2);
        List<Player> team2 = List.of(male3, male4);

        assertEquals(matchService.defineMatchType(team1, team2).toString(), "MENS_DOUBLES");
    }

    @Test
    public void testDefineMatchType_WomensDoubles() {
        List<Player> team1 = List.of(female1, female2);
        List<Player> team2 = List.of(female3, female4);

        assertEquals(matchService.defineMatchType(team1, team2).toString(), "WOMENS_DOUBLES");
    }

    @Test
    public void testDefineMatchType_MixedDoubles() {
        List<Player> team1 = List.of(male1, female1);
        List<Player> team2 = List.of(male2, female2);

        assertEquals(matchService.defineMatchType(team1, team2).toString(), "MIXED_DOUBLES");
    }

    @Test
    public void testDefineMatchType_OtherDoubles2F2M() {
        List<Player> team1 = List.of(female1, female2);
        List<Player> team2 = List.of(male1, male2);

        assertEquals(matchService.defineMatchType(team1, team2).toString(), "OTHER_DOUBLES");
    }

    @Test
    public void testDefineMatchType_OtherDoubles3F1M() {
        List<Player> team1 = List.of(female1, female2);
        List<Player> team2 = List.of(female3, male1);

        assertEquals(matchService.defineMatchType(team1, team2).toString(), "OTHER_DOUBLES");
    }

    @Test
    public void testDefineMatchType_OtherDoubles1F3M() {
        List<Player> team1 = List.of(female1, male1);
        List<Player> team2 = List.of(male2, male3);

        assertEquals(matchService.defineMatchType(team1, team2).toString(), "OTHER_DOUBLES");
    }

    @Test
    public void testValidateScore_BeyondBoundsThrows() {
        int teamOneScore = 40;
        int teamTwoScore = 15;

        assertThrows(IllegalArgumentException.class, () -> matchService.validateScore(teamOneScore,teamTwoScore));
    }

    @Test
    public void testValidateScore_InvalidOvertimeScoreThrows() {
        int teamOneScore = 24;
        int teamTwoScore = 19;

        assertThrows(IllegalArgumentException.class, () -> matchService.validateScore(teamOneScore,teamTwoScore));
    }

    @Test
    public void testValidateScore_ValidRegularScore() {
        int teamOneScore = 9;
        int teamTwoScore = 21;

        assertDoesNotThrow(() -> matchService.validateScore(teamOneScore,teamTwoScore));
    }

    @Test
    public void testValidateScore_InvalidRegularScore() {
        int teamOneScore = 21;
        int teamTwoScore = 20;

        assertThrows(IllegalArgumentException.class, () -> matchService.validateScore(teamOneScore,teamTwoScore));
    }
}
