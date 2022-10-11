package project;

public enum DiceRoll {
    MONKEY {
        @Override
        public String toString() {
            return "Monkey";
        }
    },
    SWORD {
        @Override
        public String toString() {
            return "Sword";
        }
    },
    PARROT {
        @Override
        public String toString() {
            return "Parrot";
        }
    },
    SKULL {
        @Override
        public String toString() {
            return "Skull";
        }
    },
    DIAMOND {
        @Override
        public String toString() {
            return "Diamond";
        }
    },
    GOLD {
        @Override
        public String toString() {
            return "Gold";
        }
    }
}
