/**
 * Class which stores information about a Fish
 *
 * @author Andrew
 * @version ver1.0.0
 */
import java.util.Random;

public class Fish {
    private String type;

    /**
     * Default constructor which creates an object of Fish class
     */
    public Fish() {
        String[] defaultTypes = {"Sardine", "Mackerel", "Shrimp", "Cod"};
        Random random = new Random();
        int randomNumber=random.nextInt(defaultTypes.length);
        this.type = defaultTypes[randomNumber];
    }

    /**
     * Non-default constructor which creates an object of Fish class
     * @param type          The type of fish as a String
     */
    public Fish(String type) {
        this.type = type;
    }

    /**
     * Display method to return the state of the object
     * @return              The state of the object as a String
     */
    public String display() {
        return "Fish type: " + type;
    }

    /**
     * Accessor method to get the type of fish
     * @return              The type of fish as a String
     */
    public String getType() {
        return type;
    }

    /**
     * Mutator method to set the type of fish
     * @param type          The type of fish as a String
     */
    public void setType(String type) {
        this.type = type;
    }
}