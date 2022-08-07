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

    public void catchFish() {
        ArrayList<Weapon> weapons = getHunter().getWeapons();
        int selection = 1;
        for (Weapon weapon : weapons) {
            System.out.println(getMenu().getWeaponMenu(selection, weapon.display()));
            selection++;
        }

        int weaponSelection = getPlayerSelection(1, weapons.size(),"Please select a weapon to hunt fish with");
        Weapon selectedWeapon = weapons.get(weaponSelection - 1);
        Fish randomFish = new Fish();
        int caughtFish = genRandomFishNo(selectedWeapon.getMinFish(), selectedWeapon.getMaxFish());
        if (selectedWeapon.getStrongAgainst().equals(randomFish.getType())) {
            caughtFish *= 2;
            System.out.printf(ANSI_GREEN + "DOUBLE FISH CAUGHT! %s is strong against %s\n" + ANSI_RESET, selectedWeapon.getName(), randomFish.getType());
        }
        else if (selectedWeapon.getWeakAgainst().equals(randomFish.getType())) {
            caughtFish /= 2;
            System.out.printf(ANSI_RED + "HALF FISH CAUGHT! %s is weak1 against %s\n" + ANSI_RESET, selectedWeapon.getName(), randomFish.getType());
        }


        System.out.printf("Hunter %s caught %d %s fishes with %s\n", getHunter().getName(), caughtFish, randomFish.getType(), selectedWeapon.getName());
        for (int i = 0; i < caughtFish; i++)
            getHunter().addFish(new Fish(randomFish.getType()));

    }

    public int genRandomFishNo(int min, int max) {
        return (int)(Math.random()*(max-min+1)+min);
    }

    public int getDailyInsurancePremium() {
        return this.dailyInsurancePremium;
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

    public void setPlayerSelection(int selection) {
        switch (selection) {
            case 1:
                catchFish();
        }
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

    public void startArcadeMode() {
        int totalFishesOwed = 0;
        do {
            setDailyFish(genRandomFishNo(10,20));
            setDailyInsurancePremium(genRandomFishNo(1, 10));
            if (isNaturalDisaster()) {
                setNaturalDisasterPenalty(genRandomFishNo(50, 100));
                System.out.printf(ANSI_RED + "Natural disaster has occurred. Lose %d fishes" + ANSI_RESET, getNaturalDisasterPenalty());
            }
            totalFishesOwed += getDailyFish() + getDailyInsurancePremium() + getNaturalDisasterPenalty();
            System.out.println(ANSI_BLUE + "Turn: " + ANSI_RESET + getTurns());
            System.out.println(ANSI_BLUE + "Fishes: "  + ANSI_RESET + getHunter().getFishesSize());
            System.out.println(ANSI_BLUE + "Fishes to feed family: " +  ANSI_RESET + getDailyFish());
            System.out.println(ANSI_BLUE + "Daily insurance premium: "  + ANSI_RESET + getDailyInsurancePremium());
            System.out.println(ANSI_RED + "Total fishes owed: " + ANSI_RESET + totalFishesOwed);
            // prompt player menu
            promptPlayerMenu();
            int playSelection = getPlayerSelection(1,3,"Please make a selection");
            setPlayerSelection(playSelection);
            incrementTurn();

        } while (getHunter().getFishesSize() > getDailyFish());
    }

    public void startStoryMode() {

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
    }
}
