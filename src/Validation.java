/**
 * Class which stores information about validation
 *
 * @author Andrew
 * @version ver1.0.0
 */
public class Validation {

    /**
     * Method to check if an input is blank
     * @param input             The input as a String
     * @return                  Whether the input is blank as a boolean
     */
    public boolean isBlank(String input){
        if (input == null || input.isBlank() || input.isEmpty())
            return true;
        else
            return false;
    }

    /**
     * Method to check if an input's length is within range
     * @param input             The input as a String
     * @param lowerRange        The lower range as an int
     * @param upperRange        The upper range as an int
     * @return                  Whether the input is within range as a boolean
     */
    public boolean lengthWithinRange(String input, int lowerRange, int upperRange) {
        if (input.length() >= lowerRange && input.length() <= upperRange) {
            return true;
        }
        else
            return false;
    }
}