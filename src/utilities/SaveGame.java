package utilities;

import caylus.Game;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

public class SaveGame {

    private static ObjectOutputStream output;

    public static void openFile() {
        try {
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get("saveFile.ser")));
        } catch (IOException ex) {
            System.err.println("Error opening file");
            System.exit(1);
        }
    }

    public static void addRecords(Game game) {
        try {
            output.writeObject(game);

        } catch (NoSuchElementException ex) {
            System.err.println("Invalid input");
        } catch (IOException ex) {
            System.out.println("Error saving file");
        }
    }

    public static void close() {
        if (output != null) {
            try {
                output.close();
            } catch (IOException ex) {
                System.out.println("Error closing file");
            }
        }
    }
}
