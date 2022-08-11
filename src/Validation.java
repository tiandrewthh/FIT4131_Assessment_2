/**
 * Class which stores information about validation
 *
 * @author Andrew
 * @version ver1.0.0
 */
public class Validation {

    public boolean isBlank(String input){
        if (input == null || input.isBlank() || input.isEmpty())
            return true;
        else
            return false;
    }

    public boolean lengthWithinRange(String input, int lowerRange, int upperRange) {
        if (input.length() >= lowerRange && input.length() <= upperRange) {
            return true;
        }
        else
            return false;
    }
}