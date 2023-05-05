package com.badminton.shuttlestats.unit.serviceTests;

import com.badminton.shuttlestats.model.Club;
import com.badminton.shuttlestats.repositories.ClubRepository;
import com.badminton.shuttlestats.services.ClubMemberService;
import com.badminton.shuttlestats.services.ClubService;
import com.badminton.shuttlestats.services.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClubServiceTests {

    // To work on more later when classes are more fleshed out
    // Arrange
    // Act
    // Assert

    @Mock
    private ClubRepository clubRepository;
    @Mock
    private PlayerService playerService;
    @Mock
    private ClubMemberService clubMemberService;
    @InjectMocks
    private ClubService clubService;

    @Test
    public void saveClub() {
        Club club = new Club();
        club.setClubName("test_club");
        club.setPublicVisibility(true);

        when(clubRepository.save(any(Club.class))).thenReturn(club);
        Club savedClub = clubService.saveClub(club);

        when(clubRepository.findById(savedClub.getClubId())).thenReturn(Optional.of(savedClub));
        Club toCompare = clubService.getClubById(savedClub.getClubId()).get();

        assertEquals(savedClub.getClubId().toString(), toCompare.getClubId().toString());
        assertEquals(savedClub.getClubName(), toCompare.getClubName());
        assertEquals(savedClub.getCreationDate(), toCompare.getCreationDate());
        assertEquals(savedClub.getPublicVisibility(), toCompare.getPublicVisibility());
    }

    @Test
    public void updateCLub() {
        Club club = new Club();
        club.setClubName("test_club");
        club.setPublicVisibility(true);

        when(clubRepository.save(any(Club.class))).thenReturn(club);
        Club savedClub = clubService.saveClub(club);

        when(clubRepository.findById(savedClub.getClubId())).thenReturn(Optional.of(savedClub));
        Club retrievedClub = clubService.getClubById(savedClub.getClubId()).get();

        retrievedClub.setClubName("test_club_changed");
        retrievedClub.setPublicVisibility(false);



    }
}
