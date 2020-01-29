package caylus;

import entities.Block;
import entities.buildings.Building;
import entities.players.Player;
import java.util.ArrayList;
import java.util.Scanner;

// activating special buildings
public class Phase3 {

    public static void play(Game game, Scanner sc) {
        System.out.println("\nPhase 3: Activating Special Buildings");
        // for every block
        for (int i = 1; i <= 6; i++) {
            Block block = game.getRoad().get(i);
            // if building is not null and has worker
            if ((block.getBuilding() != null && block.getWorkers().size() > 0)
                    // or inn has a worker in any position
                    || (block.getBuilding() == game.getInn()
                    && game.getInn().getInnPosition().length > 0)) {
                Building building = block.getBuilding();
                // activate building
                building.activate(game, block.getWorkers(), sc);
                // remove workers
                block.setWorkers(new ArrayList<Player>());
            }
        }
    }
}
