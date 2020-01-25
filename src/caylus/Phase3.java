package caylus;

import entities.Block;
import entities.buildings.Building;
import entities.players.Player;
import interfaces.Phases;
import java.util.ArrayList;
import java.util.Scanner;

// Activate special buildings
public class Phase3 {

    public static void play(Game game, Scanner sc) {
        // activate special buildings
        for (int i = 0; i < 6; i++) {
            Block block = game.getRoad().get(i);
            // if building is not null and has worker
            if ((block.getBuilding() != null && block.getWorkers().size() > 0)
                    // or Inn has a worker in any position
                    || (block.getBuilding() == game.inn && game.inn.getInnPosition().length > 0)) {
                Building building = block.getBuilding();
                // activate building
                building = building.activate(game, block.getWorkers(), sc);
                // empty block workers
                block.setWorkers(new ArrayList<Player>());
            }
        }
    }
}
