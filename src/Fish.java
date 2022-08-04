import java.util.Random;

public class Fish {
    private String type;

    public Fish() {
        String[] defaultTypes = {"Sardine", "Mackerel", "Shrimp", "Cod"};
        Random random = new Random();
        int randomNumber=random.nextInt(defaultTypes.length);
        this.type = defaultTypes[randomNumber];
    }

    public Fish(String type) {
        this.type = type;
    }

    public String display() {
        return "Fish type: " + type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}