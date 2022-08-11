/**
 * Class which stores information about a player's loan
 *
 * @author Andrew
 * @version ver1.0.0
 */
public class Loan {
    private int loanAmount;
    private double loanInterest;
    private int loanPayable;

    private int loanDueTurn;

    /**
     * Default constructor to create an object of Loan class
     */
    public Loan() {
        this.loanAmount = 0;
        this.loanInterest = 0;
        this.loanPayable = 0;
        this.loanDueTurn = 0;
    }

    /**
     * Non-default constructor to create an object of Loan class
     * @param loanAmount            The loan amount as an int
     * @param loanInterest          The loan interest as a double
     * @param loanDueTurn           The number of turns a loan is due as an int
     */
    public Loan(int loanAmount, double loanInterest, int loanDueTurn) {
        this.loanAmount = loanAmount;
        this.loanInterest = loanInterest;
        this.loanPayable = (int)(loanAmount * loanInterest);
        this.loanDueTurn = loanDueTurn;
    }

    /**
     * Display method to return that state of the object
     * @return                   The state of the object as a String
     */
    public String display() {
        return  "Loan Amount = " + loanAmount +
                ", Loan Interest = " + (loanInterest - 1)*100 + "%" +
                ", Loan Payable = " + loanPayable +
                ", Loan Due in: " + (loanDueTurn + 1) + " turns";
    }

    /**
     * Method to decrement the amount of turns left till a loan is due
     */
    public void decrementLoanTurn() {
        loanDueTurn--;
    }

    /**
     * Accessor method to get the loan amount
     * @return                  The loan amount as an int
     */
    public int getLoanAmount() {
        return loanAmount;
    }

    /**
     * Accessor method to get the number of turns a loan is due in
     * @return                  The number of turns a loan is due in as an int
     */
    public int getLoanDueTurn() {
        return loanDueTurn;
    }

    /**
     * Accessor method to get the interest of a loan
     * @return                  The interest of a loan as a double
     */
    public double getLoanInterest() {
        return loanInterest;
    }

    /**
     * Accessor method to get the loan payable
     * @return                  The loan payable as an int
     */
    public int getLoanPayable() {
        return loanPayable;
    }

    /**
     *  loan amount
     * @param loanAmount        The loan amount as an int
     */
    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * Mutator method to set the number of turns a loan is due in
     * @param loanDueTurn       The number of turns a loan is due in as an int
     */
    public void setLoanDueTurn(int loanDueTurn) {
        this.loanDueTurn = loanDueTurn;
    }

    /**
     * Mutator method to set the interest of a loan
     * @param loanInterest      The interest of a loan as a double
     */
    public void setLoanInterest(double loanInterest) {
        this.loanInterest = loanInterest;
    }

    /**
     * Mutator method to set the loan amount that is payable
     * @param loanPayable       The loan payable as an int
     */
    public void setLoanPayable(int loanPayable) {
        this.loanPayable = loanPayable;
    }
}
