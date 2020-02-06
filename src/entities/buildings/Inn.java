package entities.buildings;

// Singleton Pattern
import caylus.Game;
import entities.players.Player;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class Inn extends SpecialBuilding implements Serializable {

    private Player[] innPosition = new Player[2];
    private int activationMoney = 1;

    // constructor private
    public Inn(String name) {
        super(name);
    }

    // getters setters
    public Player[] getInnPosition() {
        return innPosition;
    }// end of getters setters

    @Override
    public Building activate(Game game, List<Player> players, Scanner sc) {
        System.out.println("Activating Inn");
        // move left worker to the right
        if (innPosition[0] != null) {
            if (innPosition[1] != null) {
                innPosition[1].setWorkers(innPosition[1].getWorkers() + 1);
                System.out.println(innPosition[1].getColor() + " Workers="
                        + innPosition[1].getWorkers());
            }
            innPosition[1] = innPosition[0];
            innPosition[0] = null;
            System.out.println("Moving worker to the right position");
        } // ask player to remove worker
        else {
            String message = innPosition[1].getColor() + " remove worker from Inn:"
                    + " 1)Yes 2)No";
            int choice = Functions.inputValidation(1, 2, message, innPosition[1], sc);
            if (choice == 1) {
                // return worker to player
                innPosition[1].setWorkers(innPosition[1].getWorkers() + 1);
                System.out.println(innPosition[1].getColor() + " Workers="
                        + innPosition[1].getWorkers());
                innPosition[1] = null;
                System.out.println("Removing worker");
            } else {
                System.out.println("Worker remains in Inn");
            }
        }
        return null;
    }
}
