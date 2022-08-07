import java.util.ArrayList;
import java.util.Iterator;
public class Hunter {
    private ArrayList<Fish> fishes;
    private String name;
    private ArrayList<Weapon> weapons;

    public Hunter() {
        name = "Bob";
        fishes = new ArrayList<Fish>();
        weapons = new ArrayList<Weapon>();
    }

    public Hunter(String name, ArrayList<Fish> fishes, ArrayList<Weapon> weapons) {
        this.name = name;
        this.fishes = fishes;
        this.weapons = weapons;
    }

    public void addFish(Fish newFish) {
        fishes.add(newFish);
    }
    public void addWeapon(Weapon newWeapon) {
        weapons.add(newWeapon);
    }

    public ArrayList<Fish> getFishes() {
        return fishes;
    }

    public int getFishesSize() {
        return fishes.size();
    }

    public Fish getSpecificFish(int index) {
        return fishes.get(index);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public int getWeaponsSize() {
        return weapons.size();
    }

    public Weapon getSpecificWeapon(int index) {
        return weapons.get(index);
    }

    public void removeFishes(int num) {
        Iterator<Fish> iterator = getFishes().iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (count < num) {
                iterator.remove();
            }
            count ++;
        }
    }

    public void setFishes(ArrayList<Fish> fishes) {
        this.fishes = fishes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }
}