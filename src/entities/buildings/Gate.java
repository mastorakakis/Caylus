package entities.buildings;

import caylus.Game;
import caylus.Phase2;
import entities.players.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Singleton Pattern
public class Gate extends SpecialBuilding implements Serializable {

    private static Gate gateInstance = new Gate("Gate");
    private static List<Player> positionList = new ArrayList();

    // constructor private
    private Gate(String name) {
        super(name);
    }

    // getters setters
    public static Gate getGateInstance() {
        return gateInstance;
    } // end of getters setters

    @Override
    public Building activate(Game game, List<Player> workers, Scanner sc) {
        System.out.println("Activating Gate");
        // player gets back one worker
        Player player = workers.get(0);
        player.setWorkers(player.getWorkers() + 1);
        // place worker again
        Phase2.placeWorker(game, workers.get(0), sc);
        return null;
    }

}
