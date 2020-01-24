package entities.buildings;

// Singleton Pattern
import caylus.Game;
import entities.players.Player;
import java.util.List;
import java.util.Scanner;

public class Inn extends Building {

    private static Inn innInstance = new Inn("inn");
    private static Player[] innPosition = new Player[2];
    private static int activationMoney = 1;

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
    public Building activate(List<Player> players, Scanner sc, Game game) {

        return this;
    }
}
