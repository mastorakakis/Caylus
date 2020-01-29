package utilities;

import caylus.Game;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadGame {

    private static ObjectInputStream input;

    public static void openFile() {
        try {
            input = new ObjectInputStream(Files.newInputStream(Paths.get("saveFile.ser")));
        } catch (IOException ex) {
            System.err.println("Error opening file");
            System.exit(1);
        }
    }

    public static Game readRecords() {
        try {
            while (true) {
                Game game = (Game) input.readObject();
                return game;
            }
        } catch (EOFException endOfFileException) {
            System.err.println("No more records. Game loaded");
            return null;
        } catch (IOException ex) {
            System.err.println("Error reading from file");
            return null;
        } catch (ClassNotFoundException ex) {
            System.err.println("Invalid object type");
            return null;
        }
    }

    public static void close() {
        if (input != null) {
            try {
                input.close();
            } catch (IOException ex) {
                System.err.println("Error closing file");
                System.exit(1);
            }
        }
    }
}
