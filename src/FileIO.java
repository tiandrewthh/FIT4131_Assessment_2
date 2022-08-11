/**
 * Class which stores information about reading and writing to a file
 *
 * @author Andrew
 * @version ver1.0.0
 */
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;

public class FileIO {
    private String fileName;

    /**
     * Default constructor to create an object of FileIO class
     */
    public FileIO() {
        fileName = "weapons.txt";
    }

    /**
     * Non-default constructor to create an object of FileIO class
     * @param fileName          The file name as a String
     */
    public FileIO(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Accessor to get the name of a file
     * @return                  The name of a file as a String
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return
     */
    public String readFile() {
        String fileContents = "";
        try {
            FileReader reader = new FileReader(fileName);
            try {
                Scanner fileInput = new Scanner(reader);
                while (fileInput.hasNextLine()) {
                    fileContents += fileInput.nextLine() + "\n";
                }
            }
            finally {
                reader.close();
            }
        }
        catch (Exception e) {
            System.out.println("Unable to read file");
        }
        return fileContents;
    }

    /**
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @param input
     */
    public void writeFile(String input) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            try {
                String value = input + "\n";
                writer.append(value);
            }
            finally {
                writer.close();
            }
        }
        catch (Exception e) {
            System.out.println("Unable to write to file");
        }
    }
}