import java.lang.Math;
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
    public Game() {
        mode = ARCADE;
        hunter = new Hunter();
        menu = new Menu();
        turns = 0;
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

    public int genDailyFishLimit(int min, int max) {
        return (int)(Math.random()*(max-min+1)+min);
    }

    public int getDailyInsurancePremium() {
        return this.dailyInsurancePremium;
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

    public boolean isNaturalDisaster() {
        double chanceOfDisaster = Math.random();
        return chanceOfDisaster < 0.01;
    }

    public static void main(String[] args) {
        startGame();
    }

    public void promptGameMode() {
        Input input = new Input();
        Validation validation = new Validation();
        boolean isValid = false;
        System.out.println(getMenu().getStartMenu());
        System.out.println(getMenu().getGameModeSelectionMenu());
        do {
            try {
                int usrSelection = input.acceptIntegerInput("Please enter 1 or 2 to select a game mode");
                if (usrSelection == 1 || usrSelection == 2) {
                    switch (usrSelection) {
                        case 1:
                            setMode(ARCADE);
                            System.out.println(ANSI_GREEN + "Arcade mode selected" + ANSI_RESET);
                            break;
                        case 2:
                            setMode(STORY);
                            System.out.println(ANSI_GREEN + "Story mode selected" + ANSI_RESET);
                    }
                    isValid = true;
                }
            }
            catch (Exception e) {
                System.out.println("Selection is not a number");
            }
        } while (!isValid);
    }

    public void promptHunterName() {
        Input input = new Input();
        Validation validation = new Validation();
        String hunterName;
        do {
            hunterName = input.acceptStringInput("Please enter a name between 3 and 12 characters:");
        } while (!validation.lengthWithinRange(hunterName, 3, 12));
        getHunter().setName(hunterName);
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

    public static void startGame() {
        Game newGame = new Game();
        newGame.readFile(WEAPONS_TEXTFILE);
        newGame.promptGameMode();
        newGame.promptHunterName();
        newGame.setNoOfFishes(10);
        do {
            newGame.setDailyFish(newGame.genDailyFishLimit(10,20));
            newGame.setDailyInsurancePremium(newGame.genDailyFishLimit(1, 10));
            if (newGame.isNaturalDisaster()) {
                newGame.setNaturalDisasterPenalty(newGame.genDailyFishLimit(50, 100));
            }
            // prompt player menu
            newGame.promptPlayerMenu();

        } while(newGame.getHunter().getFishesSize() > newGame.getDailyFish());
    }
}
