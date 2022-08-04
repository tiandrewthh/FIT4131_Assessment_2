import java.util.Scanner;

public class Game {
    private Hunter hunter;
    private String mode;
    private Menu menu;
    private int turns;
    private int turnLimit;
    private int targetFishBal;
    private int dailyFish;
    final static private String ARCADE = "Arcade";
    final static private String STORY = "Story";
    final static private String WEAPONS_TEXTFILE = "weapons.txt";
    public Game() {
        mode = ARCADE;
        hunter = new Hunter();
        menu = new Menu();

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

    public void inputHunterName(String name) {
        Input input = new Input();
        String hunterName = input.acceptStringInput("");
        getHunter().setName(name);
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

    public static void main(String[] args) {
        startGame();
    }

    public void readFile(String fileName) {
        FileIO fileIO = new FileIO(fileName);
        String[] fileContents = fileIO.readFile().split(System.lineSeparator());
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

    public static void startGame() {
        Game newGame = new Game();
        newGame.readFile(WEAPONS_TEXTFILE);
    }
}
