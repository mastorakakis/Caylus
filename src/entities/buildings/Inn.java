package entities.buildings;

// Singleton Pattern
import caylus.Game;
import static caylus.Game.WARNING;
import entities.players.Player;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

// Singleton pattern
public class Inn extends Building {

    private static Inn innInstance = new Inn("Inn");
    private static Player[] innPosition = new Player[2];
    private static int activationMoney = 1;

    // constructor private
    private Inn(String name) {
        super(name);
    }

    // getters setters
    public static Inn getInnInstance() {
        return innInstance;
    }

    public Player[] getInnPosition() {
        return innPosition;
    }// end of getters setters

    @Override
    public Building activate(Game game, List<Player> players, Scanner sc) {
        if (innPosition[0] != null) {
            innPosition[1] = innPosition[0];
            innPosition[0] = null;
        } else {
            String message = innPosition[1].getColor() + " remove worker from Inn:"
                    + " 1)Yes 2)No";
            int choice = Functions.inputValidation(1, 2, message, WARNING, sc);
            if (choice == 1) {
                innPosition[1] = null;
            }
        }
        return this;

    }
}
