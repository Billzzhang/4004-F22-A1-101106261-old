package project;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Config contains all configuration values needed for this project
 *
 */

public class GameServer implements Serializable, Runnable {
    public void run() {
    }

    ArrayList<FortuneCard> fortuneCards;
    public GameServer() {
        fortuneCards = initializeFortuneCards();
    }

    /**
     * Calculates Score given dice rolls
     * @param rolls An array of dice rolls
     * @return The score of the rolls, 0 if it results in death
     */
    public int calculateScore( DiceRoll[] rolls, FortuneCard card) {
        Map<DiceRoll, Integer> rollFreq = new HashMap<>();
        int score = 0;

        switch (card) {
            case GOLD -> rollFreq.put(DiceRoll.GOLD, 1);
        }
        // Add Dice Roll Frequency to hashmap
        for (int i = 0; i < rolls.length; i++) {
            if (!rollFreq.containsKey(rolls[i])) {
                rollFreq.put(rolls[i], 0);
            }
            rollFreq.put(rolls[i], rollFreq.get(rolls[i]) + 1);
        }

        if (rollFreq.containsKey(DiceRoll.SKULL) && rollFreq.get(DiceRoll.SKULL) == 3) {
            return 0;
        }

        for (DiceRoll roll: rollFreq.keySet()) {
            switch (roll) {
                case GOLD, DIAMOND -> score += 100 * rollFreq.get(roll);
            }
            switch (rollFreq.get(roll)) {
                case 3 -> score += 100;
                case 4 -> score += 200;
                case 5 -> score += 500;
                case 6 -> score += 1000;
                case 7 -> score += 2000;
                case 8 -> score += 4000;
            }

        }

        if (card == FortuneCard.CAPTAIN) score = score * 2;

        return score;
    }

    /**
     * Draws the first fortune card in the deck and returns it to the bottom
     * @return the first fortune card
     */
    public FortuneCard drawFortuneCard() {
        FortuneCard card = fortuneCards.remove(0);
        fortuneCards.add(card);
        return card;
    }

    /**
     * Creates an array with the following cards shuffled
     * 4xChest, 4xSorceress, 4xCaptain, 4xMonkey&Parrot, 4xDiamond, 4xCoin (Doublon),
     * 2x2skulls, 3x1skull, 2x2swords(300 bonus), 2x3swords(500 bonus), 2x4swords(1000 bonus)
     * @return An array list with the above fortune cards
     */
    private ArrayList<FortuneCard> initializeFortuneCards() {
        ArrayList<FortuneCard> cards = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            cards.add(FortuneCard.CHEST);
            cards.add(FortuneCard.SORCERESS);
            cards.add(FortuneCard.CAPTAIN);
            cards.add(FortuneCard.MONKEYPARROT);
            cards.add(FortuneCard.DIAMOND);
            cards.add(FortuneCard.GOLD);
        }
        for(int i = 0; i < 3; i++) {
            cards.add(FortuneCard.SINGLESKULL);
        }
        for(int i = 0; i < 2; i++) {
            cards.add(FortuneCard.DOUBLESKULL);
            cards.add(FortuneCard.DOUBLESWORDS);
            cards.add(FortuneCard.TRIPLESWORDS);
            cards.add(FortuneCard.QUADRUPLESWORDS);
        }
        Collections.shuffle(cards);
        return cards;
    }
}
