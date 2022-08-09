public class Menu {
    final static public String ANSI_RESET = "\u001B[0m";
    final static public String ANSI_GREEN = "\u001B[32m";
    final static public String ANSI_RED = "\u001B[31m";
    final static public String ANSI_BLUE = "\u001B[34m";

    final static public String ANSI_YELLOW = "\u001B[33m";
    public Menu() {

    }
    public String getBankMenu() {
        return "====================================\n" +
                "🦈Maruice🦈: Welcome to my bank. How much are you borrowing today?";
    }

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
    public String getGameModeSelectionMenu() {
        return ("1: Arcade Mode\n" +
                "2: Story Mode");
    }

    public String getHuntMenuStats(int huntTurns, int fishes, int fishesOwed) {
        return ("====================================\n" +
                ANSI_GREEN + "Hunts left: " + ANSI_RESET + huntTurns + "\n" +
                ANSI_BLUE + "Fishes: " + ANSI_RESET + fishes + "\n" +
                ANSI_RED + "Total fishes owed: " + ANSI_RESET + fishesOwed + "\n");
    }

    public String getPlayerMenu() {
        return ("""
                1: catch fish
                2: borrow from loan shark
                3: Skip"""
                );
    }

    public String getPlayerLoans(String[] loans) {
        StringBuilder output = new StringBuilder();
        int loanNum = 1;
        for (String loan : loans) {
            output.append(String.format(ANSI_YELLOW + "%d: %s" + ANSI_RESET + "\n", loanNum, loan));
            loanNum++;
        }
        return output.toString();
    }

    public String getPlayerMenuStats(int turns, int fishes, int fishesToFamily, int insurance, int totalFishesOwed) {
        return String.format("====================================\n"+
                            ANSI_GREEN + "Turn: " + ANSI_RESET + "%d\n" +
                            ANSI_BLUE + "Fishes: " + ANSI_RESET + "%d\n" +
                            ANSI_BLUE + "Fishes to feed family: " + ANSI_RESET + "%d\n" +
                            ANSI_BLUE + "Daily insurance premium: " + ANSI_RESET +  "%d\n" +
                            ANSI_RED + "Total fishes owed: " + ANSI_RESET + "%d"
                            ,turns, fishes, fishesToFamily, insurance, totalFishesOwed);
    }

    public String getWeaponMenu(int index, String weapon) {
        return String.format("%d: " + weapon, index);
    }

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
}
