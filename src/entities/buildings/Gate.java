package entities.buildings;

import caylus.Game;
import caylus.Phase2;
import entities.players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Singleton Pattern
public class Gate extends SpecialBuilding {

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
        workers.get(0).setWorkers(workers.get(0).getWorkers() + 1);
        Phase2.placeWorker(game, workers.get(0), sc);
        return null;
    }

}
