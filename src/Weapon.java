public class Weapon {
    private int cost;
    private int damage;
    private int minFish;
    private int maxFish;
    private String name;
    private String strongAgainst;
    private String weakAgainst;

    public Weapon() {
        name = "Bob";
        damage = 10;
        strongAgainst = "Fish";
        weakAgainst = "Fishies";
        cost = 2;
        minFish = 1;
        maxFish = 5;
    }

    public Weapon(int cost, int damage, int minFish, int maxFish, String name, String strongAgainst, String weakAgainst) {
        this.cost = cost;
        this.damage = damage;
        this.minFish = minFish;
        this.maxFish = maxFish;
        this.name = name;
        this.strongAgainst = strongAgainst;
        this.weakAgainst = weakAgainst;
    }

    public String display() {
        return "Weapon{" +
                "cost=" + cost +
                ", damage=" + damage +
                ", minFish=" + minFish +
                ", maxFish=" + maxFish +
                ", name='" + name + '\'' +
                ", strongAgainst='" + strongAgainst + '\'' +
                ", weakAgainst='" + weakAgainst + '\'' +
                '}';
    }

    public int getCost() {
        return cost;
    }

    public int getDamage() {
        return damage;
    }

    public int getMinFish() {
        return minFish;
    }

    public int getMaxFish() {
        return maxFish;
    }

    public String getStrongAgainst() {
        return strongAgainst;
    }

    public String getWeakAgainst() {
        return weakAgainst;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setMinFish(int minFish) {
        this.minFish = minFish;
    }

    public void setMaxFish(int maxFish) {
        this.maxFish = maxFish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrongAgainst(String strongAgainst) {
        this.strongAgainst = strongAgainst;
    }

    public void setWeakAgainst(String weakAgainst) {
        this.weakAgainst = weakAgainst;
    }
}