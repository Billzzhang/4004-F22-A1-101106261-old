package project;

public enum FortuneCard {
    GOLD {
        @Override
        public String toString() {
            return "Gold";
        }
    },
    CHEST {
        @Override
        public String toString() {
            return "Chest";
        }
    },
    SORCERESS {
        @Override
        public String toString() {
            return "Sorceress";
        }
    },
    CAPTAIN {
        @Override
        public String toString() {
            return "Captain";
        }
    },
    MONKEYPARROT {
        @Override
        public String toString() {
            return "Monkey and Parrots";
        }
    },
    DIAMOND {
        @Override
        public String toString() {
            return "Diamond";
        }
    },
    SINGLESKULL {
        @Override
        public String toString() {
            return "Single Skull";
        }
    },
    DOUBLESKULL {
        @Override
        public String toString() {
            return "Double Skull";
        }
    },
    DOUBLESWORDS {
        @Override
        public String toString() {
            return "Double Swords (300 Points)";
        }
    },
    TRIPLESWORDS {
        @Override
        public String toString() {
            return "Triple Swords (500 Points)";
        }
    },
    QUADRUPLESWORDS {
        @Override
        public String toString() {
            return "Quadruple Swords (1000 Points)";
        }
    },
}
