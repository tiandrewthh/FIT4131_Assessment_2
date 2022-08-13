/**
 * Class which stores information and logic about the Penguin game
 *
 * @author Andrew
 * @version ver1.0.0
 */
import java.lang.Math;
import java.util.ArrayList;

public class Game {
    private Hunter hunter;
    private String mode;
    private Menu menu;
    private int turns;
    private int turnLimit;
    private int targetFishBal;
    private int dailyFish;
    private int dailyInsurancePremium;
    private int naturalDisasterPenalty;
    private int totalFishesOwed;
    private boolean isWin;
    final static private String ARCADE = "Arcade";
    final static private String STORY = "Story";
    final static private String WEAPONS_TEXTFILE = "weapons.txt";
    final static private String SCORE_TEXTFILE = "score.txt";

    /**
     * Default constructor to create an object of Game class
     */
    public Game() {
        mode = ARCADE;
        hunter = new Hunter();
        menu = new Menu();
        turns = 1;
        turnLimit = 0;
        targetFishBal = 0;
        dailyFish = 0;
        dailyInsurancePremium = 0;
        naturalDisasterPenalty = 0;
        totalFishesOwed = 0;
        isWin = false;
    }

    /**
     * Method to borrow fishes as a loan
     */
    public void borrowFish() {
        Input input = new Input();
        boolean isValid = false;
        if (getHunter().getTotalLoans() <= 100) {
            System.out.println(getMenu().getBankMenu());
            while (!isValid) {
                try {
                    int loanAmount = input.acceptIntegerInput("Please choose a loan amount between 30 and 100");
                    if ((loanAmount + getHunter().getTotalLoans()) > 100) {
                        System.out.println(Menu.ANSI_RED + "Cannot exceed accumulated loan amount of 100" + Menu.ANSI_RESET);
                    }
                    else if (loanAmount >= 30 && loanAmount <= 100) {
                        System.out.printf("Borrowed" + Menu.ANSI_GREEN + " +%d " + Menu.ANSI_RESET + "fishes at 50 percent interest\n", loanAmount);
                        getHunter().addSpecificLoan(loanAmount, 1.5, 3);
                        getHunter().addFishes(loanAmount);
                        isValid = true;
                    }
                    else
                        System.out.println(Menu.ANSI_RED + "Not a valid amount" + Menu.ANSI_RESET);
                }
                catch (Exception e) {
                    System.out.println(Menu.ANSI_RED + "Please enter a valid number" + Menu.ANSI_RESET);
                }
            }
        }
        else {
            System.out.println(Menu.ANSI_RED + "Loan amount exceeds 100. Cannot borrow anymore");
        }

    }

    /**
     * Method to catch fishes using a weapon
     */
    public void catchFish() {
        Input input = new Input();
        int huntTurns = 2;
        char huntContinue = 'y';
        // Two hunt turns
        while (huntTurns > 0 && huntContinue == 'y') {
            System.out.println(getMenu().getHuntMenuStats(huntTurns, getHunter().getFishesSize(), getTotalFishesOwed()));
            displayWeapons();
            Weapon selectedWeapon = useWeapon();
            deductFishes(selectedWeapon.getCost());

            Fish randomFish = new Fish();
            int caughtFish = genRandomFishNo(selectedWeapon.getMinFish(), selectedWeapon.getMaxFish());
            // Check if weapon is strong or weak against fish
            if (selectedWeapon.getStrongAgainst().equals(randomFish.getType())) {
                caughtFish *= 2;
                System.out.printf(Menu.ANSI_GREEN + "DOUBLE FISH CAUGHT! %s is strong against %s\n" + Menu.ANSI_RESET, selectedWeapon.getName(), randomFish.getType());
            }
            else if (selectedWeapon.getWeakAgainst().equals(randomFish.getType())) {
                caughtFish /= 2;
                System.out.printf(Menu.ANSI_RED + "HALF FISH CAUGHT! %s is weak against %s\n" + Menu.ANSI_RESET, selectedWeapon.getName(), randomFish.getType());
            }
            // Add caught fish to hunter
            System.out.printf("Hunter %s caught " + Menu.ANSI_GREEN + "+%d" + Menu.ANSI_RESET + " %s fishes with %s\n", getHunter().getName(), caughtFish, randomFish.getType(), selectedWeapon.getName());
            getHunter().addFishes(caughtFish);

            huntTurns--;
            // Prompt hunter option to continue hunter if there are still hunting turns left
            if (huntTurns > 0) {
                boolean isValid = false;
                while (!isValid) {
                    try {
                        char huntContinueChoice = input.acceptCharInput("Continue with hunt: y or n");
                        if (Character.toUpperCase(huntContinueChoice) == 'Y' || Character.toUpperCase(huntContinueChoice) == 'N'){
                            huntContinue = huntContinueChoice;
                            isValid = true;
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Choice is not a character");
                    }
                }

            }

        }
    }

    /**
     * Method to deduct fishes
     * @param num               The number of fishes to deduct as an int
     */
    public void deductFishes(int num) {
        if (num < getHunter().getFishesSize()) {
            getHunter().removeFishes(num);
        }
        else {
            getHunter().removeFishes(getHunter().getFishesSize());
        }
        System.out.println("Fishes deducted: " + Menu.ANSI_RED + "-" + num + Menu.ANSI_RESET);
    }

    /**
     * Display method to output game mode selection on the console
     */
    public void displayGameMode() {
        System.out.println(getMenu().getStartMenu());
        System.out.println(getMenu().getGameModeSelectionMenu());
    }

    /**
     * Display method to return the state of a player's loans
     */
    public void displayPlayerLoans() {
        String[] strLoans = new String[getHunter().getLoanSize()];
        int index = 0;
        for (Loan loan : getHunter().getLoans()) {
            strLoans[index] = loan.display();
            index++;
        }
        System.out.println(getMenu().getPlayerLoans(strLoans));
    }

    /**
     * Display method to show the player's menu selection on the console
     */
    public void displayPlayerMenu() {
        System.out.println(getMenu().getPlayerMenu());
    }

    /**
     * Display method to return the state of a player's stats
     * @param turns             The number of turns elapsed as an int
     * @param fishes            The number of fishes a player has as an int
     * @param fishesToFamily    The number of fishes required to feed a family as an int
     * @param insurance         The number of fishes to pay as an insurance as an int
     * @param totalFishesOwed   The total number of fishes owing as an int
     */
    public void displayPlayerMenuStats(int turns, int fishes, int fishesToFamily, int insurance, int totalFishesOwed) {
        System.out.println(getMenu().getPlayerMenuStats(turns, fishes, fishesToFamily, insurance, totalFishesOwed));
    }

    public void displayWeapons() {
        ArrayList<Weapon> weapons = getHunter().getWeapons();
        // Display list of weapons
        int selection = 1;
        for (Weapon weapon : weapons) {
            System.out.println(getMenu().getWeaponMenu(selection, weapon.display()));
            selection++;
        }
    }

    /**
     * Method to get a random amount of fish
     * @param min               The lower range as an int
     * @param max               The upper range as an int
     * @return                  The random amount of fish as an int
     */
    public int genRandomFishNo(int min, int max) {
        return (int)(Math.random()*(max-min+1)+min);
    }

    /**
     * Accessor method to get the daily amount of fish required to feed the family
     * @return                 The daily amount of fish to feed family as an int
     */
    public int getDailyFish() {
        return dailyFish;
    }

    /**
     * Accessor method to get the daily insurance premium
     * @return                  The daily insurance premium as an int
     */
    public int getDailyInsurancePremium() {
        return this.dailyInsurancePremium;
    }

    /**
     * Accessor method to get the outcome of a game
     * @return                  The outcome of a game as a String
     */
    public String getGameResult() {
        String result = "";
        String gameOutcome = isWin() ? "Win" : "Lose";
        switch (getMode()) {
            case ARCADE:
                result = String.format("Mode: %s, Player name: %s, Days lasted: %d", getMode(), getHunter().getName(), getTurns());
                break;
            case STORY:
                result = String.format("Mode: %s, Player name: %s, Number of turns: %d, Target fish balance: %d, Final fish balance: %2d, Outcome: %s", getMode(), getHunter().getName(), getTurnLimit(), getTargetFishBal(), getHunter().getFishesSize(), gameOutcome);
        }
        return result;
    }

    /**
     * Accessor method to get an object of Hunter class
     * @return                  The object of Hunter class
     */
    public Hunter getHunter() {
        return hunter;
    }

    /**
     * Accessor method to get the game mode
     * @return                  The game mode as a String
     */
    public String getMode() {
        return mode;
    }

    /**
     * Accessor method to get the natural disaster penalty
     * @return                  The natural disaster penalty as an int
     */
    public int getNaturalDisasterPenalty() {
        return this.naturalDisasterPenalty;
    }

    /**
     * Accessor method to get an object of Menu class
     * @return                  The object of Menu class
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Accessor method to get the player's selection
     * @param min               The minimum amount of selections as an int
     * @param max               The maximum amount of selections as an int
     * @param menuPrompt        The menu's prompt as a String
     * @return                  The player's selection as an int
     */
    public int getPlayerSelection(int min, int max, String menuPrompt) {
        Input input = new Input();
        boolean isValid = false;
        int choice = 0;
        while (!isValid) {
            try {
                choice = input.acceptIntegerInput(menuPrompt);
                if (choice >= min && choice <= max)
                    isValid = true;
                else
                    System.out.println(Menu.ANSI_RED + "Please enter a valid selection" + Menu.ANSI_RESET);
            }
            catch (Exception e) {
                System.out.println(Menu.ANSI_RED + "Selection is not a number" + Menu.ANSI_RED);
            }
        }
        return choice;
    }

    /**
     * Accessor method to get the target fish balance of the game
     * @return                  The target fish balance as an int
     */
    public int getTargetFishBal() {
        return targetFishBal;
    }

    /**
     * Accessor method to get the total amount of fishes owing during a turn
     * @return                  The total amount of fishes owed as an int
     */
    public int getTotalFishesOwed() {
        return totalFishesOwed;
    }

    /**
     * Accessor method to get the turns of a game
     * @return                  The turns of a game as an int
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Accessor method to get the turn limit of a game
     * @return                  The turn limit as an int
     */
    public int getTurnLimit() {
        return turnLimit;
    }

    /**
     * Method to handle loans during a turn
     */
    public void handleLoans() {
        if (getHunter().getLoanSize() > 0) {
            for (Loan loan : getHunter().getLoans()) {
                if (loan.getLoanDueTurn() == 0) {
                    setTotalFishesOwed(getTotalFishesOwed() + loan.getLoanPayable());
                    System.out.println("Loan payable added to total fishes owed: " + Menu.ANSI_RED + loan.getLoanPayable() + Menu.ANSI_RESET);
                    getHunter().removeLoan(loan);
                    // Escape loop if number of loans is less than or equal to 0
                    if (getHunter().getLoanSize() <= 0)
                        return;
                }
                else {
                    loan.decrementLoanTurn();
                }
            }
        }
    }

    /**
     * Method to increment a turn during a game
     */
    public void incrementTurn() {
        this.turns++;
    }

    /**
     * Accessor method to get a natural disaster
     * @return                  The natural disaster as a boolean
     */
    public boolean isNaturalDisaster() {
        double chanceOfDisaster = Math.random();
        return chanceOfDisaster < 0.01;
    }

    /**
     * Accessor method to get the outcome of game
     * @return                  The outcome of a game as a boolean
     */
    public boolean isWin() {
        return isWin;
    }



    /**
     * Method to read a file to a weapon object
     * @param fileName          The filename as a String
     */
    public void readFileToWeapon(String fileName) {
        FileIO fileIO = new FileIO(fileName);
        String[] fileContents = fileIO.readFile().split("\n");

        for (String lineContent : fileContents) {
            String[] lineValues = lineContent.split(",");
            String weaponName = lineValues[0];
            int weaponDmg = Integer.parseInt(lineValues[1]);
            String weaponStrong = lineValues[2];
            String weaponWeak = lineValues[3];
            int weaponCost = Integer.parseInt(lineValues[4]);
            int weaponMin = Integer.parseInt(lineValues[5]);
            int weaponMax = Integer.parseInt(lineValues[6]);
            getHunter().addWeapon(new Weapon(weaponCost, weaponDmg, weaponMin, weaponMax, weaponName, weaponStrong, weaponWeak));
        }
    }

    /**
     * Mutator method to set the daily fish required to feed a family
     * @param dailyFish         The daily fish as an int
     */
    public void setDailyFish(int dailyFish) {
        this.dailyFish = dailyFish;
    }

    /**
     * Mutator method to set the daily fish insurance premium
     * @param newDailyInsurancePremium  The daily insurance premium as an int
     */
    public void setDailyInsurancePremium(int newDailyInsurancePremium) {
        this.dailyInsurancePremium = newDailyInsurancePremium;
    }

    /**
     * Mutator method to set the selection of the game mode
     * @param selection         The selection as an int
     */
    public void setGameMode(int selection) {
        switch (selection) {
            case 1:
                setMode(ARCADE);
                System.out.println(Menu.ANSI_GREEN + "Arcade mode selected" + Menu.ANSI_RESET);
                break;
            case 2:
                setMode(STORY);
                System.out.println(Menu.ANSI_GREEN + "Story mode selected" + Menu.ANSI_RESET);
                break;
        }
    }

    /**
     * Mutator method to set the name of a hunter
     */
    public void setHunterName() {
        Input input = new Input();
        Validation validation = new Validation();
        String hunterName;
        do {
            hunterName = input.acceptStringInput("Please enter a name between 3 and 12 characters:");
        } while (!validation.lengthWithinRange(hunterName, 3, 12));
        getHunter().setName(hunterName);
    }

    /**
     * Mutator method to set the mode of a game
     * @param mode              The mode of a game as a String
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * Mutator method to set the natural disaster penalty during a game turn
     * @param newNaturalDisasterPenalty     The natural disaster penalty as an int
     */
    public void setNaturalDisasterPenalty(int newNaturalDisasterPenalty) {
        this.naturalDisasterPenalty = newNaturalDisasterPenalty;
    }

    /**
     * Mutator method to set the number of fishes to add to a hunter's balance
     * @param num               The number of fishes as an int
     */
    public void setNoOfFishes(int num) {
        for (int i = 0; i < num; i++) {
            getHunter().addFish(new Fish());
        }
    }

    /**
     * Mutator method to set the player's selection for a specific action during a turn
     * @param selection         The selection of a player as an int
     */
    public void setPlayerSelection(int selection) {
        switch (selection) {
            case 1:
                catchFish();
                break;
            case 2:
                borrowFish();
                break;
            case 3:
                break;
        }
    }

    /**
     * Mutator method to set the player's target fish balance by the end of the game
     * @param targetFishBal     The target fish balance of a game as an int
     */
    public void setTargetFishBal(int targetFishBal) {
        this.targetFishBal = targetFishBal;
    }

    /**
     * Mutator method to set the total amount of fishes owed by a player
     * @param totalFishesOwed   The total fishes owed as an int
     */
    public void setTotalFishesOwed(int totalFishesOwed) {
        this.totalFishesOwed = totalFishesOwed;
    }

    /**
     * Mutator method to set the turn limit of a game
     * @param turnLimit         The turn limit of a game
     */
    public void setTurnLimit(int turnLimit) {
        this.turnLimit = turnLimit;
    }

    /**
     * Mutator method to set the win condition
     * @param win               The win condition as a boolean
     */
    public void setWin(boolean win) {
        isWin = win;
    }

    /**
     * Method to start the game's arcade mode
     */
    public void startArcadeMode() {
        boolean gameContinue = getHunter().getFishesSize() > 0;
        startTurn(getMode(), gameContinue , 0);
    }

    /**
     * Method to start a game
     */
    public static void startGame() {
        Game newGame = new Game();
        newGame.readFileToWeapon(WEAPONS_TEXTFILE);
        newGame.displayGameMode();
        int gameModeSelection = newGame.getPlayerSelection(1,2,"Please select a game mode");
        newGame.setGameMode(gameModeSelection);
        newGame.setHunterName();
        newGame.setNoOfFishes(10);

        switch (newGame.getMode()) {
            case ARCADE:
                newGame.startArcadeMode();
                break;
            case STORY:
                newGame.startStoryMode();
                break;
        }
        newGame.writeFile(SCORE_TEXTFILE);
    }

    /**
     * Method to start the game's story mode
     */
    public void startStoryMode() {
        int turnsLimit = 0;
        int targetFishBalance = 0;
        Input input = new Input();
        while (turnsLimit < 5) {
            try {
                turnsLimit = input.acceptIntegerInput("Please enter number of turns (5 or more)");
            }
            catch (Exception e) {
                System.out.println("Please enter a valid number");
            }
        }
        while (targetFishBalance < 25) {
            try {
                targetFishBalance = input.acceptIntegerInput("Please enter target fish balance (25 or more)");
            }
            catch (Exception e) {
                System.out.println("Please enter a valid number");
            }
        }
        setTurnLimit(turnsLimit);
        setTargetFishBal(targetFishBalance);
        boolean gameContinue = getTurns() >= getTurnLimit();
        startTurn(getMode(), !gameContinue, getTargetFishBal());
    }

    /**
     * Method to start the sequence of a game's turn
     * @param mode              The game's mode as a String
     * @param gameContinue      The game's turn continue condition as a boolean
     * @param targetFishBal     The target fish balance of the game as an int
     */
    public void startTurn(String mode, boolean gameContinue, int targetFishBal) {
        System.out.println(getTurns());
        System.out.println(getTurnLimit());
        do {
            int totalFishesOwed = 0;
            setTotalFishesOwed(totalFishesOwed);
            setDailyFish(genRandomFishNo(10,20));
            setDailyInsurancePremium(genRandomFishNo(1, 10));
            if (isNaturalDisaster()) {
                setNaturalDisasterPenalty(genRandomFishNo(50, 100));
                System.out.printf(Menu.ANSI_RED + "Natural disaster has occurred. Lose %d fishes\n" + Menu.ANSI_RESET, getNaturalDisasterPenalty());
            }
            // Set fishes to be owed by the end of a turn
            totalFishesOwed += getDailyFish() + getDailyInsurancePremium() + getNaturalDisasterPenalty();
            setTotalFishesOwed(totalFishesOwed);
            handleLoans(); // Handle any loans
            displayPlayerMenuStats(getTurns(), getHunter().getFishesSize(), getDailyFish(), getDailyInsurancePremium(), getTotalFishesOwed());
            // If hunter does have loan, then display loans
            if (getHunter().getLoanSize() > 0)
                displayPlayerLoans();
            // Player selection
            displayPlayerMenu();
            int playSelection = getPlayerSelection(1,3,"Please make a selection");
            setPlayerSelection(playSelection);
            deductFishes(getTotalFishesOwed());
            // Lose condition
            if (mode.equals(STORY) && getTurns() >= getTurnLimit())
                gameContinue = false;
            else if (mode.equals(ARCADE) && getHunter().getFishesSize() <= 0)
                gameContinue = false;
            else
                incrementTurn(); //Increment turn if lose condition is not satisfied
        } while (gameContinue);
        // If game mode is story mode, the hunters fish balance is within the target fish balance or exceeds the target fish balance then set the win condition to true
        if (mode.equals(STORY) && ((getHunter().getFishesSize() >= targetFishBal * 0.9 && getHunter().getFishesSize() <= targetFishBal * 1.1) || getHunter().getFishesSize() > targetFishBal)) {
            System.out.println(getMenu().getGameWinMenu());
            setWin(true);
        }
        else
            System.out.println(getMenu().getGameOverMenu());
    }

    public Weapon useWeapon() {
        ArrayList<Weapon> weapons = getHunter().getWeapons();
        int weaponSelection = getPlayerSelection(1, weapons.size(),"Please select a weapon to hunt fish with");
        return weapons.get(weaponSelection - 1);
    }

    /**
     * Method to write the results of a game's outcome to a file
     * @param fileName          The file name as a String
     */
    public void writeFile(String fileName) {
        FileIO fileIO = new FileIO(fileName);
        fileIO.writeFile(getGameResult());
    }
}