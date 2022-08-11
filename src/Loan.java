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
     *
     */
    public void decrementLoanTurn() {
        loanDueTurn--;
    }

    /**
     * @return
     */
    public int getLoanAmount() {
        return loanAmount;
    }

    /**
     * @return
     */
    public int getLoanDueTurn() {
        return loanDueTurn;
    }

    /**
     * @return
     */
    public double getLoanInterest() {
        return loanInterest;
    }

    /**
     * @return
     */
    public int getLoanPayable() {
        return loanPayable;
    }

    /**
     * @param loanAmount
     */
    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * @param loanDueTurn
     */
    public void setLoanDueTurn(int loanDueTurn) {
        this.loanDueTurn = loanDueTurn;
    }

    /**
     * @param loanInterest
     */
    public void setLoanInterest(double loanInterest) {
        this.loanInterest = loanInterest;
    }

    /**
     * @param loanPayable
     */
    public void setLoanPayable(int loanPayable) {
        this.loanPayable = loanPayable;
    }
}
