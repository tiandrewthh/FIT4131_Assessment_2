import java.util.Scanner;

/**
 * Class which takes input from the user
 *
 * @author Andrew Tran
 * @version ver1.0.0
 */
public class Input {
    public Input() {

    }

    public char acceptCharInput(String input) {
        System.out.println(input);
        Scanner console = new Scanner(System.in);
        char charInput = console.next().charAt(0);
        return charInput;
    }

    public double acceptDoubleInput(String input) {
        System.out.println(input);
        Scanner console = new Scanner(System.in);
        double doubleInput = Double.parseDouble(console.nextLine());
        return doubleInput;
    }

    public int acceptIntegerInput(String input) {
        System.out.println(input);
        Scanner console = new Scanner(System.in);
        int intInput = Integer.parseInt(console.nextLine());
        return intInput;

    }

    public String acceptStringInput(String input) {
        System.out.println(input);
        Scanner console = new Scanner(System.in);
        String stringInput = console.nextLine();
        return stringInput;
    }
}