import java.util.Scanner;

public class Game {
    private Hunter hunter;
    private String mode;
    final static private String ARCADE = "Arcade";
    final static private String STORY = "Story";
    final static private String WEAPONS_TEXTFILE = "weapons.txt";
    public Game() {
        mode = ARCADE;
        hunter = new Hunter();
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


    public Hunter getHunter() {
        return hunter;
    }

    public String getMode() {
        return mode;
    }

    public static void main(String[] args) {
        startGame();
    }

    public void readFile() {
        FileIO fileIO = new FileIO(WEAPONS_TEXTFILE);
        String[] fileContents = fileIO.readFile().split(System.lineSeparator());
        int counter = 0;

        for (String lineContent : fileContents) {
            String[] lineValues = lineContent.split(",");
            String weaponName = lineValues[0];
            String weaponDmg = lineValues[1];
            String weaponStrong = lineValues[2];
            String weaponWeak = lineValues[3];
            String weaponCost = lineValues[4];
            String weaponMin = lineValues[5];
            String weaponMax = lineValues[6];
        }
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public static void startGame() {
        Game newGame = new Game();
    }
}
