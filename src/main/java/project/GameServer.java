package project;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Config contains all configuration values needed for this project
 *
 */

public class GameServer implements Serializable, Runnable {
    public void run() {
    }

    /**
     * Calculates Score given dice rolls
     * @param rolls An array of dice rolls
     * @return The score of the rolls, 0 if it results in death
     */
    public int calculateScore( DiceRoll[] rolls ) {
        Map<DiceRoll, Integer> rollFreq = new HashMap<>();

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

        return Integer.parseInt(null);
    }
}
