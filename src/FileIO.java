import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;

public class FileIO {
    private String fileName;

    public FileIO() {
        fileName = "weapons.txt";
    }

    public FileIO(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void writeFile(String input) {
        try {
            FileWriter writer = new FileWriter(fileName);
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