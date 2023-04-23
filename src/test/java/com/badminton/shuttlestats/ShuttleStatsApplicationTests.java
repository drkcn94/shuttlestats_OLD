package com.badminton.shuttlestats;

import com.badminton.shuttlestats.repositories.ClubRepository;
import com.badminton.shuttlestats.repositories.PlayerRepository;
import com.badminton.shuttlestats.repositories.RosterRepository;
import com.badminton.shuttlestats.repositories.SessionRepository;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
public abstract class ShuttleStatsApplicationTests {

    @ClassRule
    public static MySQLContainer mySQLContainer = new MySQLContainer()
            .withDatabaseName("testdb")
            .withUsername("d_test")
            .withPassword("test1234");

    @Autowired
    protected ClubRepository clubRepository;

    @Autowired
    protected PlayerRepository playerRepository;

    @Autowired
    protected SessionRepository sessionRepository;

    @Autowired
    protected RosterRepository rosterRepository;

    @Test
    public void testContainerIsRunning() {
        assertTrue(mySQLContainer.isRunning());
    }
}
