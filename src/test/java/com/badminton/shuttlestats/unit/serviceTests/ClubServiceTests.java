package com.badminton.shuttlestats.unit.serviceTests;

import com.badminton.shuttlestats.model.Club;
import com.badminton.shuttlestats.repositories.ClubRepository;
import com.badminton.shuttlestats.services.ClubMemberService;
import com.badminton.shuttlestats.services.ClubService;
import com.badminton.shuttlestats.services.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClubServiceTests {

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
        Club club = new Club("test_club", true);

        when(clubService.saveClub(club)).thenReturn(club);
        Club toCheck = clubRepository.save(club);

        assertEquals(club.getClubId(), toCheck.getClubId());
    }
}
