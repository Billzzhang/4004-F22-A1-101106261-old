package project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

/**
 * Player object that connects to GameServer
 *
 */

public class Player implements Serializable{
    public DiceRoll[] reroll(DiceRoll[] originalRoll, int[] indices) {
        for (int i = 0; i < indices.length; i++) {
            if (indices[i] < originalRoll.length && indices[i] >= 0) {
                originalRoll[indices[i]] = rerollDice();
            }
        }

        return originalRoll;
    }

    public DiceRoll[] rollAllDice() {
        DiceRoll[] diceRolls = new DiceRoll[8];
        for (int i = 0; i < diceRolls.length; i++) {
            diceRolls[i] = rerollDice();
        }
        return diceRolls;
    }

    private DiceRoll rerollDice() {
        DiceRoll[] rolls = {DiceRoll.SKULL, DiceRoll.MONKEY, DiceRoll.GOLD, DiceRoll.DIAMOND, DiceRoll.PARROT, DiceRoll.SWORD};
        int random = (int) ((Math.random() * (6)));
        return rolls[random];
    }
}
