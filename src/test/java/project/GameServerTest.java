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

    // roll 1 skull, 4 parrots, 3 swords, reroll swords, get 1 skull 2 monkeys
    //      reroll 2 monkeys, get 1 skull 1 monkey and die
    @Test
    void Test48() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.SKULL, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD};
        DiceRoll[] newRoll = player.reroll(rolls, new int[]{5, 6, 7});
        newRoll[5] = DiceRoll.SKULL;
        newRoll[6] = DiceRoll.MONKEY;
        newRoll[7] = DiceRoll.MONKEY;
        newRoll = player.reroll(newRoll, new int[]{6, 7});
        newRoll[6] = DiceRoll.SKULL;
        newRoll[7] = DiceRoll.MONKEY;
        int score = gameServer.calculateScore(newRoll);
        assertEquals(score, 0);
    }

    //roll 1 skull, 2 parrots, 3 swords, 2 coins, reroll parrots get 2 coins
    //      reroll 3 swords, get 3 coins (SC 4000 for seq of 8 (with FC coin) + 8x100=800 = 4800)
    @Test
    void Test50(){
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.SKULL, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD};
        DiceRoll[] newRoll = player.reroll(rolls, new int[]{1, 2});
        newRoll[1] = DiceRoll.GOLD;
        newRoll[2] = DiceRoll.GOLD;
        newRoll = player.reroll(newRoll, new int[]{5, 6, 7});
        newRoll[5] = DiceRoll.GOLD;
        newRoll[6] = DiceRoll.GOLD;
        newRoll[7] = DiceRoll.GOLD;
        int score = gameServer.calculateScore(newRoll);
        assertEquals(score, 4800);
    }
}
