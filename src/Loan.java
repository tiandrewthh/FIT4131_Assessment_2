public class Loan {
    private int loanAmount;
    private double loanInterest;
    private int loanPayable;

    private int loanDueTurn;

    public Loan() {
        this.loanAmount = 0;
        this.loanInterest = 0;
        this.loanPayable = 0;
        this.loanDueTurn = 0;
    }

    public Loan(int loanAmount, double loanInterest, int loanDueTurn) {
        this.loanAmount = loanAmount;
        this.loanInterest = loanInterest;
        this.loanPayable = (int)(loanAmount * loanInterest);
        this.loanDueTurn = loanDueTurn;
    }

    public String display() {
        return  "Loan Amount = " + loanAmount +
                ", Loan Interest = " + (loanInterest - 1)*100 + "%" +
                ", Loan Payable = " + loanPayable +
                ", Loan Due in: " + (loanDueTurn + 1) + " turns";
    }

    public void decrementLoanTurn() {
        loanDueTurn--;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public double getLoanInterest() {
        return loanInterest;
    }

    public int getLoanPayable() {
        return loanPayable;
    }

    public int getLoanDueTurn() {
        return loanDueTurn;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setLoanInterest(double loanInterest) {
        this.loanInterest = loanInterest;
    }

    public void setLoanPayable(int loanPayable) {
        this.loanPayable = loanPayable;
    }

    public void setLoanDueTurn(int loanDueTurn) {
        this.loanDueTurn = loanDueTurn;
    }
}
