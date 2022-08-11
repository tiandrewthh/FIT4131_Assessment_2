/**
 * Class which stores information about a weapon
 *
 * @author Andrew
 * @version ver1.0.0
 */
public class Weapon {
    private int cost;
    private int damage;
    private int minFish;
    private int maxFish;
    private String name;
    private String strongAgainst;
    private String weakAgainst;

    /**
     * Default constructor for creating an object of Weapon class
     */
    public Weapon() {
        name = "Bob";
        damage = 10;
        strongAgainst = "Fish";
        weakAgainst = "Fishies";
        cost = 2;
        minFish = 1;
        maxFish = 5;
    }

    /**
     * Non-default constructor for creating an object of Weapon clas
     * @param cost              The cost of a weapon as an int
     * @param damage            The damage of a weapon as an int
     * @param minFish           The minimum fish that can be caught as an int
     * @param maxFish           The maximum fish that can be caught as an int
     * @param name              The name of weapon as a String
     * @param strongAgainst     The fish that the weapon is weak against as a String
     * @param weakAgainst       The fish that the weapon is strong against as a String
     */
    public Weapon(int cost, int damage, int minFish, int maxFish, String name, String strongAgainst, String weakAgainst) {
        this.cost = cost;
        this.damage = damage;
        this.minFish = minFish;
        this.maxFish = maxFish;
        this.name = name;
        this.strongAgainst = strongAgainst;
        this.weakAgainst = weakAgainst;
    }

    /**
     * Display method to return the state of the object as a String
     * @return                  The state of the object as String
     */
    public String display() {
        return  "Name = '" + name + '\'' +
                ", Cost = " + cost +
                ", Damage = " + damage +
                ", Min = " + minFish +
                ", Max = " + maxFish +
                ", Strong Against = '" + strongAgainst + '\'' +
                ", Weak Against = ' " + weakAgainst + '\'';
    }

    /**
     * Accessor method to return the cost of a weapon
     * @return                  The cost of a weapon as an int
     */
    public int getCost() {
        return cost;
    }

    /**
     * Accessor method to return the damage of a weapon
     * @return                  The damage of a weapon as an int
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Accessor method to return the minimum amount of fish that can be caught
     * @return                  The minimum amount of fish that can be caught as an int
     */
    public int getMinFish() {
        return minFish;
    }

    /**
     * Accessor method to return the maximum amount of fish that can be caught
     * @return                  The maximum amount of fish that can be caught as an int
     */
    public int getMaxFish() {
        return maxFish;
    }

    /**
     * Accessor method to return the name of a weapon
     * @return                  The name of weapon as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method to return the fish that a weapon is strong against
     * @return                  The fish that a weapon is strong against as a String
     */
    public String getStrongAgainst() {
        return strongAgainst;
    }

    /**
     * Accessor method to return the fish that a weapon is strong against
     * @return                  The fish that weapon is strong against as a String
     */
    public String getWeakAgainst() {
        return weakAgainst;
    }

    /**
     * Mutator method to set the cost of a weapon
     * @param cost              The cost of a weapon as an int
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Mutator method to set the damage of a weapon
     * @param damage            The damage of a weapon as an int
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Mutator method to set the maximum amount of fish that can be caught
     * @param maxFish           The maximum amount of fish that can be caught as an int
     */
    public void setMaxFish(int maxFish) {
        this.maxFish = maxFish;
    }

    /**
     * Mutator method to set minimum amount of fish that can be caught
     * @param minFish           The minimum amount of fish that can be caught as an int
     */
    public void setMinFish(int minFish) {
        this.minFish = minFish;
    }

    /**
     * Mutator method to set name of a weapon
     * @param name              The name of a weapon as a String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator method to set the fish that a weapon is strong against
     * @param strongAgainst     The fish that a weapon is strong against as a String
     */
    public void setStrongAgainst(String strongAgainst) {
        this.strongAgainst = strongAgainst;
    }

    /**
     * Mutator method to set the fish that a weapon is strong against
     * @param weakAgainst       The fish that a weapon is weak against as a String
     */
    public void setWeakAgainst(String weakAgainst) {
        this.weakAgainst = weakAgainst;
    }
}