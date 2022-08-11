import java.util.Scanner;

/**
 * Class which takes input from the user
 *
 * @author Andrew Tran
 * @version ver1.0.0
 */
public class Input {
    /**
     * Default constructor to create an object of Input class
     */
    public Input() {

    }

    /**
     * Method to accept and return character input
     * @param input             The prompt of the input as a String
     * @return                  The input as a char
     */
    public char acceptCharInput(String input) {
        System.out.println(input);
        Scanner console = new Scanner(System.in);
        char charInput = console.next().charAt(0);
        return charInput;
    }

    /**
     * Method to accept and return double input
     * @param input             The prompt of the input as a String
     * @return                  The input as double
     */
    public double acceptDoubleInput(String input) {
        System.out.println(input);
        Scanner console = new Scanner(System.in);
        double doubleInput = Double.parseDouble(console.nextLine());
        return doubleInput;
    }

    /**
     * Method to accept and return int input
     * @param input             The prompt of the input as a String
     * @return                  The input as an int
     */
    public int acceptIntegerInput(String input) {
        System.out.println(input);
        Scanner console = new Scanner(System.in);
        int intInput = Integer.parseInt(console.nextLine());
        return intInput;

    }

    /**
     * Method to accept and return String
     * @param input         The prompt of the input as a String
     * @return              The input as a String
     */
    public String acceptStringInput(String input) {
        System.out.println(input);
        Scanner console = new Scanner(System.in);
        String stringInput = console.nextLine();
        return stringInput;
    }
}