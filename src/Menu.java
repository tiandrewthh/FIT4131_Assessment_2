/**
 * Class which stores information about the different menu options in the game
 *
 * @author Andrew
 * @version ver1.0.0
 */
public class Menu {
    final static public String ANSI_RESET = "\u001B[0m";
    final static public String ANSI_GREEN = "\u001B[32m";
    final static public String ANSI_RED = "\u001B[31m";
    final static public String ANSI_BLUE = "\u001B[34m";

    final static public String ANSI_YELLOW = "\u001B[33m";

    /**
     * Accessor method to get the bank menu
     * @return          The bank menu as a String
     */
    public String getBankMenu() {
        return "====================================\n" +
                "🦈Maruice🦈: Welcome to my bank. How much are you borrowing today?";
    }

    /**
     * Accessor method to get the game mode selection menu
     * @return          The game mode selection menu as a String
     */
    public String getGameModeSelectionMenu() {
        return ("1: Arcade Mode\n" +
                "2: Story Mode");
    }

    /**
     * Accessor method to get the game over menu
     * @return          The game over menu as a String
     */
    public String getGameOverMenu() {
        return ("   _____          __  __ ______    ______      ________ _____  \n" +
                "  / ____|   /\\   |  \\/  |  ____|  / __ \\ \\    / /  ____|  __ \\ \n" +
                " | |  __   /  \\  | \\  / | |__    | |  | \\ \\  / /| |__  | |__) |\n" +
                " | | |_ | / /\\ \\ | |\\/| |  __|   | |  | |\\ \\/ / |  __| |  _  / \n" +
                " | |__| |/ ____ \\| |  | | |____  | |__| | \\  /  | |____| | \\ \\ \n" +
                "  \\_____/_/    \\_\\_|  |_|______|  \\____/   \\/   |______|_|  \\_\\\n" +
                "                                                               \n" +
                "                                                               ");
    }

    /**
     * Accessor method to get game win menu
     * @return          The game win menu as a String
     */
    public String getGameWinMenu() {
        return (" __     __          __          ___       _ \n" +
                " \\ \\   / /          \\ \\        / (_)     | |\n" +
                "  \\ \\_/ /__  _   _   \\ \\  /\\  / / _ _ __ | |\n" +
                "   \\   / _ \\| | | |   \\ \\/  \\/ / | | '_ \\| |\n" +
                "    | | (_) | |_| |    \\  /\\  /  | | | | |_|\n" +
                "    |_|\\___/ \\__,_|     \\/  \\/   |_|_| |_(_)\n" +
                "                                            \n" +
                "                                            ");
    }

    /**
     * Accessor method to get the current stats of a hunter during a hunting turn
     * @param huntTurns         The hunts left as an int
     * @param fishes            The fishes a hunter has as an int
     * @param fishesOwed        The fishes owed by of a hunter as an int
     * @return                  The current stats of a hunter as a String
     */
    public String getHuntMenuStats(int huntTurns, int fishes, int fishesOwed) {
        return ("====================================\n" +
                ANSI_GREEN + "Hunts left: " + ANSI_RESET + huntTurns + "\n" +
                ANSI_BLUE + "Fishes: " + ANSI_RESET + fishes + "\n" +
                ANSI_RED + "Total fishes owed: " + ANSI_RESET + fishesOwed + "\n");
    }

    /**
     * Accessor method to get the loans a player has owing
     * @param loans             The loans a player has a String[]
     * @return                  The loans a player has a String
     */
    public String getPlayerLoans(String[] loans) {
        StringBuilder output = new StringBuilder();
        int loanNum = 1;
        for (String loan : loans) {
            output.append(String.format(ANSI_YELLOW + "%d: %s" + ANSI_RESET + "\n", loanNum, loan));
            loanNum++;
        }
        return output.toString();
    }

    /**
     * Accessor method to get the main player options
     * @return                  The player options as a String
     */
    public String getPlayerMenu() {
        return ("""
                1: catch fish
                2: borrow from loan shark
                3: Skip"""
        );
    }

    /**
     * Accessor method to get the player's stats during a turn
     * @param turns             The turns a player has taken as an int
     * @param fishes            The fishes a player has as an int
     * @param fishesToFamily    The fishes a player needs to feed their family as an int
     * @param insurance         The fishes a player needs to pay to the insurer as an int
     * @param totalFishesOwed   The total number of fishes a player owed by the end of a turn as an int
     * @return                  The player's menu stats as a String
     */
    public String getPlayerMenuStats(int turns, int fishes, int fishesToFamily, int insurance, int totalFishesOwed) {
        return String.format("====================================\n"+
                        ANSI_GREEN + "Turn: " + ANSI_RESET + "%d\n" +
                        ANSI_BLUE + "Fishes: " + ANSI_RESET + "%d\n" +
                        ANSI_BLUE + "Fishes to feed family: " + ANSI_RESET + "%d\n" +
                        ANSI_BLUE + "Daily insurance premium: " + ANSI_RESET +  "%d\n" +
                        ANSI_RED + "Total fishes owed: " + ANSI_RESET + "%d"
                ,turns, fishes, fishesToFamily, insurance, totalFishesOwed);
    }

    /**
     * Accessor method to get the start menu logo of the game
     * @return                  The start menu logo of the game as a String
     */
    public String getStartMenu() {
        return ("""
                  _____                       _         _    _             _           \s
                 |  __ \\                     (_)       | |  | |           | |          \s
                 | |__) |__ _ __   __ _ _   _ _ _ __   | |__| |_   _ _ __ | |_ ___ _ __\s
                 |  ___/ _ \\ '_ \\ / _` | | | | | '_ \\  |  __  | | | | '_ \\| __/ _ \\ '__|
                 | |  |  __/ | | | (_| | |_| | | | | | | |  | | |_| | | | | ||  __/ |  \s
                 |_|   \\___|_| |_|\\__, |\\__,_|_|_| |_| |_|  |_|\\__,_|_| |_|\\__\\___|_|  \s
                                   __/ |                                               \s
                                  |___/                                                \s
                """);
    }

    /**
     * Accessor method to get the the list of weapons available for selection
     * @param index             The position of a weapon as a String
     * @param weapon            The weapon as a String
     * @return                  The list of weapons as a String
     */
    public String getWeaponMenu(int index, String weapon) {
        return String.format("%d: " + weapon, index);
    }
}
