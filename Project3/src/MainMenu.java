import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMenu {

    public Buratino buratino;
    public static Scanner scanner = new Scanner(System.in);

    public static void getBuratinoFromFile() {
        File inputFile = new File("Files/Buratino.csv");
        Buratino buratino;
        try {
            scanner = new Scanner(inputFile);
            String line = scanner.nextLine();
            String[] data = line.split(",");
            buratino = new Buratino(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(buratino.toString());
    }

}
