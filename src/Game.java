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
    final static private String ARCADE = "Arcade";
    final static private String STORY = "Story";
    final static private String WEAPONS_TEXTFILE = "weapons.txt";
    final static public String ANSI_RESET = "\u001B[0m";
    final static public String ANSI_GREEN = "\u001B[32m";
    final static public String ANSI_RED = "\u001B[31m";
    final static public String ANSI_BLUE = "\u001B[34m";
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
    }

    public Game(String mode, Hunter hunter) {
        this.mode = mode;
        this.hunter = hunter;
    }

    public String display() {
        return "Game{" +
                "mode='" + mode + '\'' +
                ", hunter=" + hunter +
                '}';
    }

    public void borrowFish() {
        Input input = new Input();
        boolean isValid = false;
        if (getHunter().getTotalLoans() <= 100) {
            System.out.println(getMenu().getBankMenu());
            while (!isValid) {
                try {
                    int loanAmount = input.acceptIntegerInput("Please choose a loan amount between 30 and 100");
                    if (loanAmount >= 30 && loanAmount <= 100) {
                        System.out.printf("Borrowed %d fishes at 50 percent interest\n", loanAmount);
                        getHunter().addSpecificLoan(loanAmount, 1.5, 3);
                        getHunter().addFishes(loanAmount);
                        isValid = true;
                    }
                    else
                        System.out.println(ANSI_RED + "Not a valid amount" + ANSI_RESET);
                }
                catch (Exception e) {
                    System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                }
            }
        }
        else {
            System.out.println(ANSI_RED + "Loan amount exceeds 100. Cannot borrow anymore");
        }

    }

    public void catchFish() {
        Input input = new Input();
        ArrayList<Weapon> weapons = getHunter().getWeapons();
        int huntTurns = 2;
        char huntContinue = 'y';
        while (huntTurns > 0 && huntContinue == 'y') {
            int selection = 1;
            System.out.println(getMenu().getHuntMenuStats(huntTurns, getHunter().getFishesSize(), getTotalFishesOwed()));
            for (Weapon weapon : weapons) {
                System.out.println(getMenu().getWeaponMenu(selection, weapon.display()));
                selection++;
            }
            int weaponSelection = getPlayerSelection(1, weapons.size(),"Please select a weapon to hunt fish with");
            Weapon selectedWeapon = weapons.get(weaponSelection - 1);
            deductFishes(selectedWeapon.getCost());
            System.out.println();
            Fish randomFish = new Fish();
            int caughtFish = genRandomFishNo(selectedWeapon.getMinFish(), selectedWeapon.getMaxFish());
            if (selectedWeapon.getStrongAgainst().equals(randomFish.getType())) {
                caughtFish *= 2;
                System.out.printf(ANSI_GREEN + "DOUBLE FISH CAUGHT! %s is strong against %s\n" + ANSI_RESET, selectedWeapon.getName(), randomFish.getType());
            }
            else if (selectedWeapon.getWeakAgainst().equals(randomFish.getType())) {
                caughtFish /= 2;
                System.out.printf(ANSI_RED + "HALF FISH CAUGHT! %s is weak against %s\n" + ANSI_RESET, selectedWeapon.getName(), randomFish.getType());
            }

            System.out.printf("Hunter %s caught %d %s fishes with %s\n", getHunter().getName(), caughtFish, randomFish.getType(), selectedWeapon.getName());
            for (int i = 0; i < caughtFish; i++)
                getHunter().addFish(new Fish(randomFish.getType()));

            huntTurns--;

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

    public int genRandomFishNo(int min, int max) {
        return (int)(Math.random()*(max-min+1)+min);
    }

    public int getDailyInsurancePremium() {
        return this.dailyInsurancePremium;
    }

    public String getGameResult() {
        String result = "";
        result += String.format("Mode: %s, Player name: %s, Days lasted: %d", getMode(), getHunter().getName(), getTurns());
        return result;
    }

    public int getNaturalDisasterPenalty() {
        return this.naturalDisasterPenalty;
    }
    public Hunter getHunter() {
        return hunter;
    }

    public String getMode() {
        return mode;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getTurns() {
        return turns;
    }

    public int getTurnLimit() {
        return turnLimit;
    }

    public int getTargetFishBal() {
        return targetFishBal;
    }

    public int getDailyFish() {
        return dailyFish;
    }

    public void getPlayerLoansDisplay() {
        String[] strLoans = new String[getHunter().getLoanSize()];
        int index = 0;
        for (Loan loan : getHunter().getLoans()) {
            strLoans[index] = loan.display();
            index++;
        }
        System.out.println(getMenu().getPlayerLoans(strLoans));
    }

    public void getPlayerMenuStats(int turns, int fishes, int fishesToFamily, int insurance, int totalFishesOwed) {
        System.out.println(getMenu().getPlayerMenuStats(turns, fishes, fishesToFamily, insurance, totalFishesOwed));
    }

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
                    System.out.println(ANSI_RED + "Please enter a valid selection" + ANSI_RESET);
            }
            catch (Exception e) {
                System.out.println(ANSI_RED + "Selection is not a number" + ANSI_RED);
            }
        }
        return choice;
    }

    public int getTotalFishesOwed() {
        return totalFishesOwed;
    }

    public void incrementTurn() {
        this.turns++;
    }

    public boolean isNaturalDisaster() {
        double chanceOfDisaster = Math.random();
        return chanceOfDisaster < 0.01;
    }

    public static void main(String[] args) {
        startGame();
    }

    public void promptGameMode() {
        System.out.println(getMenu().getStartMenu());
        System.out.println(getMenu().getGameModeSelectionMenu());
    }

    public void promptPlayerMenu() {
        System.out.println(getMenu().getPlayerMenu());
    }

    public void readFile(String fileName) {
        FileIO fileIO = new FileIO(fileName);
        String[] fileContents = fileIO.readFile().split("\n");
        int counter = 0;

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

    public void deductFishes(int num) {
        if (num < getHunter().getFishesSize()) {
            getHunter().removeFishes(num);
        }
        else {
            getHunter().removeFishes(getHunter().getFishesSize());
        }
        System.out.println("Fishes deducted: " + ANSI_RED + "-" + num + ANSI_RESET);
    }

    public void handleLoans() {
        if (getHunter().getLoanSize() > 0) {
            for (Loan loan : getHunter().getLoans()) {
                if (loan.getLoanDueTurn() == 0) {
                    setTotalFishesOwed(getTotalFishesOwed() + loan.getLoanPayable());
                    System.out.println("Loan payable added to total fishes owed: " + ANSI_RED + loan.getLoanPayable() + ANSI_RESET);
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
    public void setGameMode(int selection) {
        switch (selection) {
            case 1:
                setMode(ARCADE);
                System.out.println(ANSI_GREEN + "Arcade mode selected" + ANSI_RESET);
                break;
            case 2:
                setMode(STORY);
                System.out.println(ANSI_GREEN + "Story mode selected" + ANSI_RESET);
                break;
        }
    }

    public void setHunterName() {
        Input input = new Input();
        Validation validation = new Validation();
        String hunterName;
        do {
            hunterName = input.acceptStringInput("Please enter a name between 3 and 12 characters:");
        } while (!validation.lengthWithinRange(hunterName, 3, 12));
        getHunter().setName(hunterName);
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setNoOfFishes(int num) {
        for (int i = 0; i < num; i++) {
            getHunter().addFish(new Fish());
        }
    }

    public void setHunter(Hunter hunter) {
        this.hunter = hunter;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public void setTurnLimit(int turnLimit) {
        this.turnLimit = turnLimit;
    }

    public void setTargetFishBal(int targetFishBal) {
        this.targetFishBal = targetFishBal;
    }

    public void setDailyFish(int dailyFish) {
        this.dailyFish = dailyFish;
    }

    public void setDailyInsurancePremium(int newDailyInsurancePremium) {
        this.dailyInsurancePremium = newDailyInsurancePremium;
    }

    public void setNaturalDisasterPenalty(int newNaturalDisasterPenalty) {
        this.naturalDisasterPenalty = newNaturalDisasterPenalty;
    }

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

    public void setTotalFishesOwed(int totalFishesOwed) {
        this.totalFishesOwed = totalFishesOwed;
    }

    public void startArcadeMode() {
        do {
            int totalFishesOwed = 0;
            setTotalFishesOwed(totalFishesOwed);
            setDailyFish(genRandomFishNo(10,20));
            setDailyInsurancePremium(genRandomFishNo(1, 10));
            if (isNaturalDisaster()) {
                setNaturalDisasterPenalty(genRandomFishNo(50, 100));
                System.out.printf(ANSI_RED + "Natural disaster has occurred. Lose %d fishes\n" + ANSI_RESET, getNaturalDisasterPenalty());
            }

            totalFishesOwed += getDailyFish() + getDailyInsurancePremium() + getNaturalDisasterPenalty();
            setTotalFishesOwed(totalFishesOwed);
            handleLoans();
            getPlayerMenuStats(getTurns(), getHunter().getFishesSize(), getDailyFish(), getDailyInsurancePremium(), getTotalFishesOwed());
            // prompt player menu
            // If hunter does have loan, then display loans
            if (getHunter().getLoanSize() > 0)
                getPlayerLoansDisplay();
            promptPlayerMenu();
            int playSelection = getPlayerSelection(1,3,"Please make a selection");
            setPlayerSelection(playSelection);
            deductFishes(getTotalFishesOwed());
            if (getHunter().getFishesSize() > 0)
                incrementTurn();
        } while (getHunter().getFishesSize() > 0);
        System.out.println(getMenu().getGameOverMenu());
        System.out.println("Game Over! You ran out of fish!");
    }

    public void startStoryMode() {

    }

    public void startTurn() {

    }

    public static void startGame() {
        Game newGame = new Game();
        newGame.readFile(WEAPONS_TEXTFILE);
        newGame.promptGameMode();
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
        newGame.writeFile(newGame.getMode());
    }

    public void writeFile(String mode) {
        FileIO fileIO = new FileIO("score.txt");
        switch (mode) {
            case ARCADE:
                fileIO.writeFile(getGameResult());
                break;
        }
    }

}
