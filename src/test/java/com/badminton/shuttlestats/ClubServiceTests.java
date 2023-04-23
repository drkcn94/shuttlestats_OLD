package com.badminton.shuttlestats;

import com.badminton.shuttlestats.model.Club;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ClubServiceTests extends ShuttleStatsApplicationTests{

    @Test
    public void testSaveClub() {
        Club club = new Club("test_club", true);

        clubRepository.save(club);
        Club savedClub = clubRepository.findByClubId(club.getClubId());

        assertEquals(club.getClubId(), savedClub.getClubId());
    }
}
