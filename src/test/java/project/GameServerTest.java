package project;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameServerTest {

    GameServer gameServer;
    Player player;

    @BeforeEach
    void setup() {
        gameServer = new GameServer();
        player = new Player();
    }

    // die with 3 skulls 5 swords on first roll: player gets a score of 0
    @Test
    void Test45() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.SKULL, DiceRoll.SKULL, DiceRoll.SKULL, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD};
        int score = gameServer.calculateScore(rolls);
        assertEquals(score, 0);
    }

    // roll 1 skull, 4 parrots, 3 swords, reroll 3 swords, get 2 skulls 1 sword  die
    @Test
    void Test46() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.SKULL, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD};
        DiceRoll[] newRoll = player.reroll(rolls, new int[]{5, 6, 7});
        newRoll[5] = DiceRoll.SKULL;
        newRoll[6] = DiceRoll.SKULL;
        newRoll[7] = DiceRoll.SWORD;
        int score = gameServer.calculateScore(newRoll);
        assertEquals(score, 0);
    }

    // roll 2 skulls, 4 parrots, 2 swords, reroll swords, get 1 skull 1 sword  die
    @Test
    void Test47() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.SKULL, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SKULL};
        DiceRoll[] newRoll = player.reroll(rolls, new int[]{5, 6});
        newRoll[5] = DiceRoll.SKULL;
        newRoll[6] = DiceRoll.SWORD;
        int score = gameServer.calculateScore(newRoll);
        assertEquals(score, 0);
    }
}
