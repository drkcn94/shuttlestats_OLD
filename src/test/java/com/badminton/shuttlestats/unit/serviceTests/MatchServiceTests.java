package com.badminton.shuttlestats.unit.serviceTests;

import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.repositories.MatchRepository;
import com.badminton.shuttlestats.services.MatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTests {

    @Mock
    private MatchRepository matchRepository;
    @InjectMocks
    private MatchService matchService;

    @Test
    public void testDefineMatchType_MensSingles() {
        List<Player> male1 = List.of(new Player("male1", "MALE", "RIGHT"));
        List<Player> male2 = List.of(new Player("male2", "MALE", "RIGHT"));

        assertEquals(matchService.defineMatchType(male1, male2).toString(), "MENS_SINGLES");
    }

    @Test
    public void testDefineMatchType_WomensSingles() {
        List<Player> female1 = List.of(new Player("female1", "FEMALE", "RIGHT"));
        List<Player> female2 = List.of(new Player("female2", "FEMALE", "RIGHT"));

        assertEquals(matchService.defineMatchType(female1,female2).toString(), "WOMENS_SINGLES");
    }

    @Test
    public void testDefineMatchType_OtherSingles() {
        List<Player> female1 = List.of(new Player("female1", "FEMALE", "RIGHT"));
        List<Player> male1 = List.of(new Player("male1", "MALE", "RIGHT"));

        assertEquals(matchService.defineMatchType(female1,male1).toString(), "OTHER_SINGLES");
    }

    @Test
    public void testDefineMatchType_MensDoubles() {
        List<Player> team1 = List.of(
                new Player("male1", "MALE", "RIGHT"),
                new Player("male2", "MALE", "RIGHT"));

        List<Player> team2 = List.of(
                new Player("male3", "MALE", "RIGHT"),
                new Player("male4", "MALE", "RIGHT"));

        assertEquals(matchService.defineMatchType(team1, team2).toString(), "MENS_DOUBLES");
    }

    @Test
    public void testDefineMatchType_WomensDoubles() {
        List<Player> team1 = List.of(
                new Player("female1", "FEMALE", "RIGHT"),
                new Player("female2", "FEMALE", "RIGHT"));

        List<Player> team2 = List.of(
                new Player("female3", "FEMALE", "RIGHT"),
                new Player("female4", "FEMALE", "RIGHT"));

        assertEquals(matchService.defineMatchType(team1, team2).toString(), "WOMENS_DOUBLES");
    }

    @Test
    public void testDefineMatchType_MixedDoubles() {
        List<Player> team1 = List.of(
                new Player("male1", "MALE", "RIGHT"),
                new Player("female1", "FEMALE", "RIGHT"));

        List<Player> team2 = List.of(
                new Player("male2", "MALE", "RIGHT"),
                new Player("female2", "FEMALE", "RIGHT"));

        assertEquals(matchService.defineMatchType(team1, team2).toString(), "MIXED_DOUBLES");
    }

    @Test
    public void testDefineMatchType_OtherDoubles2F2M() {
        List<Player> team1 = List.of(
                new Player("female1", "FEMALE", "RIGHT"),
                new Player("female2", "FEMALE", "RIGHT"));

        List<Player> team2 = List.of(
                new Player("male1", "MALE", "RIGHT"),
                new Player("male2", "MALE", "RIGHT"));

        assertEquals(matchService.defineMatchType(team1, team2).toString(), "OTHER_DOUBLES");
    }

    @Test
    public void testDefineMatchType_OtherDoubles3F1M() {
        List<Player> team1 = List.of(
                new Player("female1", "FEMALE", "RIGHT"),
                new Player("female2", "FEMALE", "RIGHT"));

        List<Player> team2 = List.of(
                new Player("female3", "FEMALE", "RIGHT"),
                new Player("male1", "MALE", "RIGHT"));

        assertEquals(matchService.defineMatchType(team1, team2).toString(), "OTHER_DOUBLES");
    }

    @Test
    public void testDefineMatchType_OtherDoubles1F3M() {
        List<Player> team1 = List.of(
                new Player("female1", "FEMALE", "RIGHT"),
                new Player("male1", "MALE", "RIGHT"));

        List<Player> team2 = List.of(
                new Player("male2", "MALE", "RIGHT"),
                new Player("male3", "MALE", "RIGHT"));

        assertEquals(matchService.defineMatchType(team1, team2).toString(), "OTHER_DOUBLES");
    }
}
