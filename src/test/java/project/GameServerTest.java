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
        int score = gameServer.calculateScore(rolls, fortuneCard);
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
        int score = gameServer.calculateScore(newRoll, fortuneCard);
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
        int score = gameServer.calculateScore(newRoll, fortuneCard);
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
        int score = gameServer.calculateScore(newRoll, fortuneCard);
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
        int score = gameServer.calculateScore(newRoll, fortuneCard);
        assertEquals(score, 4800);
    }

    // score first roll with 2 (monkeys/parrot/diamonds/coins) and FC is captain (SC 800)
    @Test
    void Test52(){
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.CAPTAIN;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.DIAMOND, DiceRoll.DIAMOND, DiceRoll.MONKEY, DiceRoll.MONKEY};
        int score = gameServer.calculateScore(rolls, fortuneCard);
        assertEquals(score, 800);
    }

    // roll 2 (monkeys/skulls/swords/parrots), reroll parrots and get 1 sword & 1 monkey (SC 300 since FC is coin)
    @Test
    void Test53() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.SKULL, DiceRoll.SKULL, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.MONKEY, DiceRoll.MONKEY};
        DiceRoll[] newRoll = player.reroll(rolls, new int[]{0, 1});
        newRoll[1] = DiceRoll.SWORD;
        newRoll[0] = DiceRoll.MONKEY;
        int score = gameServer.calculateScore(newRoll, fortuneCard);
        assertEquals(score, 300);
    }

    // roll 3 (monkey, swords) + 2 skulls and score   (SC 300)
    @Test
    void Test54() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.MONKEY, DiceRoll.SWORD, DiceRoll.SKULL, DiceRoll.SKULL, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.MONKEY, DiceRoll.MONKEY};
        int score = gameServer.calculateScore(rolls, fortuneCard);
        assertEquals(score, 300);
    }

    // roll 3 diamonds, 2 skulls, 1 monkey, 1 sword, 1 parrot, score (diamonds = 100 + 300 points)   (SC 500)
    @Test
    void Test55() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.MONKEY, DiceRoll.SWORD, DiceRoll.SKULL, DiceRoll.SKULL, DiceRoll.PARROT, DiceRoll.DIAMOND, DiceRoll.DIAMOND, DiceRoll.DIAMOND};
        int score = gameServer.calculateScore(rolls, fortuneCard);
        assertEquals(score, 500);
    }

    // roll 4 coins, 2 skulls, 2 swords and score (coins: 200 + 400 points) with FC is a diamond (SC 700)
    @Test
    void Test56() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.DIAMOND;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SKULL, DiceRoll.SKULL, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD};
        int score = gameServer.calculateScore(rolls, fortuneCard);
        assertEquals(score, 700);
    }

    // roll 3 swords, 4 parrots, 1 skull and score (SC 100+200+100= 400)
    @Test
    void Test57() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.SKULL, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD};
        int score = gameServer.calculateScore(rolls, fortuneCard);
        assertEquals(score, 400);
    }

    // roll 1 skull, 2 coins/parrots & 3 swords, reroll parrots, get 1 coin and 1 sword, score (SC = 200+400+200 = 800)
    @Test
    void Test58() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.SKULL, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD};
        DiceRoll[] newRoll = player.reroll(rolls, new int[]{3, 4});
        newRoll[3] = DiceRoll.GOLD;
        newRoll[4] = DiceRoll.SWORD;
        int score = gameServer.calculateScore(newRoll, fortuneCard);
        assertEquals(score, 800);
    }

    // roll 1 skull, 2 coins/parrots & 3 swords, reroll parrots, get 1 coin and 1 sword, FC is a captain score (SC = (100 + 300 + 200)*2 = 1200)
    @Test
    void Test59() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.CAPTAIN;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.SKULL, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD};
        DiceRoll[] newRoll = player.reroll(rolls, new int[]{3, 4});
        newRoll[3] = DiceRoll.GOLD;
        newRoll[4] = DiceRoll.SWORD;
        int score = gameServer.calculateScore(newRoll, fortuneCard);
        assertEquals(score, 1200);
    }

    // roll 1 skull, 2 (monkeys/parrots) 3 swords, reroll 2 monkeys, get 1 skull 1 sword,
    //          then reroll parrots get 1 sword 1 monkey (SC 600)
    @Test
    void Test60() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.SKULL, DiceRoll.MONKEY, DiceRoll.MONKEY, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD};
        DiceRoll[] newRoll = player.reroll(rolls, new int[]{1, 2});
        newRoll[1] = DiceRoll.SKULL;
        newRoll[2] = DiceRoll.SWORD;
        newRoll = player.reroll(newRoll, new int[]{3, 4});
        newRoll[3] = DiceRoll.MONKEY;
        newRoll[4] = DiceRoll.SWORD;
        int score = gameServer.calculateScore(newRoll, fortuneCard);
        assertEquals(score, 600);
    }

    // score set of 6 monkeys and 2 skulls on first roll (SC 1100)
    @Test
    void Test62() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.MONKEY, DiceRoll.MONKEY, DiceRoll.MONKEY, DiceRoll.MONKEY, DiceRoll.MONKEY, DiceRoll.MONKEY, DiceRoll.SKULL, DiceRoll.SKULL};
        int score = gameServer.calculateScore(rolls, fortuneCard);
        assertEquals(score, 1100);
    }

    // score set of 7 monkeys and 1 skulls on first roll (SC 1100)
    @Test
    void Test63() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.SKULL};
        int score = gameServer.calculateScore(rolls, fortuneCard);
        assertEquals(score, 2100);
    }

    // score set of 8 coins on first roll (SC 5400)  seq of 8 + 9 coins(FC is coin) +  full chest  (no extra points for 9 coins)
    @Test
    void Test64() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD};
        int score = gameServer.calculateScore(rolls, fortuneCard);
        assertEquals(score, 5400);
    }

    // score set of 8 coins on first roll and FC is diamond (SC 5400)
    @Test
    void Test65() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.DIAMOND;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD, DiceRoll.GOLD};
        int score = gameServer.calculateScore(rolls, fortuneCard);
        assertEquals(score, 5400);
    }

    // score set of 8 swords on first roll and FC is captain (SC 4500x2 = 9000) since full chest
    @Test
    void Test66() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.CAPTAIN;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD};
        int score = gameServer.calculateScore(rolls, fortuneCard);
        assertEquals(score, 9000);
    }

    // roll 6 monkeys and 2 swords, reroll swords, get 2 monkeys, score (SC 4600 because of FC is coin and full chest)
    @Test
    void Test67() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.MONKEY, DiceRoll.MONKEY, DiceRoll.MONKEY, DiceRoll.MONKEY, DiceRoll.MONKEY, DiceRoll.MONKEY, DiceRoll.SWORD, DiceRoll.SWORD};
        DiceRoll[] newRoll = player.reroll(rolls, new int[]{6, 7});
        newRoll[6] = DiceRoll.MONKEY;
        newRoll[7] = DiceRoll.MONKEY;
        int score = gameServer.calculateScore(newRoll, fortuneCard);
        assertEquals(score, 4600);
    }

    // roll 2 (monkeys/skulls/swords/parrots), reroll parrots, get 2 diamonds, score with FC is diamond (SC 400)
    @Test
    void Test68() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.DIAMOND;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.MONKEY, DiceRoll.MONKEY, DiceRoll.SKULL, DiceRoll.SKULL, DiceRoll.PARROT, DiceRoll.PARROT, DiceRoll.SWORD, DiceRoll.SWORD};
        DiceRoll[] newRoll = player.reroll(rolls, new int[]{4, 5});
        newRoll[4] = DiceRoll.DIAMOND;
        newRoll[5] = DiceRoll.DIAMOND;
        int score = gameServer.calculateScore(newRoll, fortuneCard);
        assertEquals(score, 400);
    }

    // roll 2 (monkeys, skulls, swords), 1 diamond, 1 parrot, reroll 2 monkeys, get 2 diamonds, score 500
    @Test
    void Test69() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.MONKEY, DiceRoll.MONKEY, DiceRoll.SKULL, DiceRoll.SKULL, DiceRoll.PARROT, DiceRoll.DIAMOND, DiceRoll.SWORD, DiceRoll.SWORD};
        DiceRoll[] newRoll = player.reroll(rolls, new int[]{0, 1});
        newRoll[0] = DiceRoll.DIAMOND;
        newRoll[1] = DiceRoll.DIAMOND;
        int score = gameServer.calculateScore(newRoll, fortuneCard);
        assertEquals(score, 500);
    }

    // roll 1 skull, 2 coins, 1 (monkey/parrot), 3 swords, reroll 3 swords, get 1 (coin/monkey/parrot)  (SC 600)
    @Test
    void Test70() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.GOLD;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.MONKEY, DiceRoll.PARROT, DiceRoll.SKULL, DiceRoll.GOLD, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.GOLD};
        DiceRoll[] newRoll = player.reroll(rolls, new int[]{4, 5, 6});
        newRoll[4] = DiceRoll.GOLD;
        newRoll[5] = DiceRoll.MONKEY;
        newRoll[6] = DiceRoll.PARROT;
        int score = gameServer.calculateScore(newRoll, fortuneCard);
        assertEquals(score, 600);
    }

    // roll 1 skull, 2 coins, 1 (monkey/parrot), 3 swords, reroll swords, get 1 (coin/monkey/parrot) with FC is diamond (SC 500)
    @Test
    void Test71() {
        FortuneCard fortuneCard = gameServer.drawFortuneCard();
        fortuneCard = FortuneCard.DIAMOND;
        DiceRoll[] rolls = player.rollAllDice();
        rolls = new DiceRoll[]{DiceRoll.MONKEY, DiceRoll.PARROT, DiceRoll.SKULL, DiceRoll.GOLD, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.SWORD, DiceRoll.GOLD};
        DiceRoll[] newRoll = player.reroll(rolls, new int[]{4, 5, 6});
        newRoll[4] = DiceRoll.GOLD;
        newRoll[5] = DiceRoll.MONKEY;
        newRoll[6] = DiceRoll.PARROT;
        int score = gameServer.calculateScore(newRoll, fortuneCard);
        assertEquals(score, 500);
    }
}
