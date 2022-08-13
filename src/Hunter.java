/**
 * Class which stores information about a hunter (player)
 *
 * @author Andrew
 * @version ver1.0.0
 */
import java.util.ArrayList;
import java.util.Iterator;
public class Hunter {

    private ArrayList<Fish> fishes;
    private String name;
    private ArrayList<Loan> loans;
    private ArrayList<Weapon> weapons;

    /**
     * Default constructor which creates an object of Hunter class
     */
    public Hunter() {
        name = "Bob";
        fishes = new ArrayList<Fish>();
        weapons = new ArrayList<Weapon>();
        loans = new ArrayList<Loan>();
    }

    /**
     * Non-default constructor which creates an object of Hunter class
     * @param name
     * @param fishes
     * @param weapons
     */
    public Hunter(String name, ArrayList<Fish> fishes, ArrayList<Weapon> weapons) {
        this.name = name;
        this.fishes = fishes;
        this.weapons = weapons;
    }

    /**
     * Method to add Fish object to fishes arraylist
     * @param newFish               The fish to add as a Fish object
     */
    public void addFish(Fish newFish) {
        fishes.add(newFish);
    }

    /**
     * Method to add multiple Fish objects to fishes arraylist
     * @param num                   The number of fishes to add as an int
     */
    public void addFishes(int num) {
        for (int i = 0; i < num; i++) {
            addFish(new Fish());
        }
    }

    /**
     * Method to add Loan object to loans Arraylist
     * @param loan                  The loan to add as a Loan object
     */
    public void addLoad(Loan loan) {
        loans.add(new Loan());
    }

    /**
     * Method to add specific loan object to loans ArrayList
     * @param loanAmount           The amount that is loaned as an int
     * @param loanInterest         The interest of a loan as an int
     * @param loanDueTurn          The amount of payable loan that is due in turns as an int
     */
    public void addSpecificLoan(int loanAmount, double loanInterest, int loanDueTurn) {
        loans.add(new Loan(loanAmount, loanInterest, loanDueTurn));
    }

    /**
     * Method to add Weapon object to weapons ArrayList
     * @param newWeapon            The weapon to add as Weapon object
     */
    public void addWeapon(Weapon newWeapon) {
        weapons.add(newWeapon);
    }

    /**
     * Accessor method to get ArrayList of Fish class
     * @return                     The fishes that are returned as an ArrayList<Fish>
     */
    public ArrayList<Fish> getFishes() {
        return fishes;
    }

    /**
     * Accessor method to get size of Fish class ArrayList
     * @return                     The number of fishes as an int
     */
    public int getFishesSize() {
        return fishes.size();
    }

    /**
     * Accessor method to get ArrayList of Loan class
     * @return                     The loans that are returned as an ArrayList<Loan>
     */
    public ArrayList<Loan> getLoans() {
        return loans;
    }

    /**
     * Accessor method to get the size of ArrayList of Loan class
     * @return                      The number of loans as an int
     */
    public int getLoanSize() {
        return loans.size();
    }

    /**
     * Accessor method to get the index value of ArrayList of Fish class
     * @param index             The index of ArrayList<Fish> as an int
     * @return
     */
    public Fish getSpecificFish(int index) {
        return fishes.get(index);
    }

    /**
     * Accessor method to get the name of the hunter
     * @return                  The name of a hunter as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method to get the ArrayList of Weapon class
     * @return                  The weapons that are returned as an ArrayList<Weapon>
     */
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Accessor method to get the size of the ArrayList of Weapon class
     * @return                  The number of weapons as an int
     */
    public int getWeaponsSize() {
        return weapons.size();
    }

    /**
     * Accessor method to get the specific Weapon class object
     * @param index             The index of ArrayList<Weapon> as an int
     * @return                  The position of a weapon in the ArrayList<Weapon>
     */
    public Weapon getSpecificWeapon(int index) {
        return weapons.get(index);
    }

    /**
     * Accessor method to get the amount loaned from each object in the ArrayList of Loan class
     * @return                  The total amount of loans accumulated across several different loans
     */
    public int getTotalLoans() {
        int total = 0;
        for (Loan loan : loans) {
            total += loan.getLoanAmount();
        }
        return total;
    }

    /**
     * Method to remove Fish objects from the ArrayList of Fish class
     * @param num               The number of fishes to remove as an int
     */
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

    /**
     * Method to remove a Loan object from the ArrayList of Loan class
     * @param loan              The loan to remove as a Loan object
     */
    public void removeLoan(Loan loan) {
        Iterator<Loan> iterator = getLoans().iterator();
        while (iterator.hasNext()) {
            Loan thisLoan = iterator.next();
            if (loan == thisLoan)
                iterator.remove();
        }
    }

    /**
     * Mutator method to set the ArrayList of Fish class objects
     * @param fishes            The fishes as a ArrayList<Fish>
     */
    public void setFishes(ArrayList<Fish> fishes) {
        this.fishes = fishes;
    }

    /**
     * Mutator method to set the ArrayList of Loan class objects
     * @param loans             The loans as an ArrayList<Loan>
     */
    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    /**
     * Mutator method to set the name of the hunter
     * @param name              The name as a String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator method to set an ArrayList of Weapon objects
     * @param weapons           The weapons as a ArrayList<Weapon>
     */
    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    /**
     * Method to begin the program
     * @param args              An array of Strings representing command line arguments
     */
    public static void main(String[] args) {
        Game.startGame();
    }
}