package entities.buildings;

import caylus.Game;
import caylus.Phase2;
import entities.players.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gate extends SpecialBuilding implements Serializable {

    private List<Player> positionList = new ArrayList();

    // constructor private
    public Gate(String name) {
        super(name);
    }

    @Override
    public Building activate(Game game, List<Player> workers, Scanner sc) {
        System.out.println("Activating Gate");
        // player gets back one worker
        Player player = workers.get(0);
        player.setWorkers(player.getWorkers() + 1);
        System.out.println(player.getColor() + " Worker=" + player.getWorkers());
        // place worker again
        Phase2.placeWorker(game, workers.get(0), sc);
        return null;
    }

}
