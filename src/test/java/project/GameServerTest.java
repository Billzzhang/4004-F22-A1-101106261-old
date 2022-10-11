package project;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameServerTest {

    GameServer gameServer;

    @BeforeEach
    void setup() {
        gameServer = new GameServer();
    }

    // die with 3 skulls 5 swords on first roll: player gets a score of 0
    @Test
    void Test45() {
        DiceRoll[] rolls = {DiceRoll.SKULL,DiceRoll.SKULL,DiceRoll.SKULL,DiceRoll.SWORD,DiceRoll.SWORD,DiceRoll.SWORD,DiceRoll.SWORD,DiceRoll.SWORD};
        int score = gameServer.calculateScore(rolls);
        assertEquals(score, 0);
    }
}
