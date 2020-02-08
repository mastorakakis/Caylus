package caylus;

import entities.Block;
import entities.players.Player;
import java.util.ArrayList;
import java.util.Scanner;

// activating special buildings
public class Phase3 {

    public static void play(Game game, Scanner sc) {
        System.out.println("\nPhase 3: Activating Special Buildings");
        System.out.println("-------------------------------------");
        // for every block
        for (int i = 1; i <= 6; i++) {
            Block block = game.getRoad().get(i);
            // if building is not null and has worker
            if ((block.getBuilding() != null && block.getWorkers().size() > 0)
                    // or inn has a worker in any position
                    || (block.getBuilding() == game.getInn()
                    && (game.getInn().getInnPosition()[0] != null || game.getInn().getInnPosition()[1] != null))) {
                // activate building
                block.getBuilding().activate(game, block.getWorkers(), sc);
                System.out.println("");
                // remove workers
                block.setWorkers(new ArrayList<Player>());
            }
        }
        System.out.println("");
        for (Player player : game.getPlayerList()) {
            player.newFavorTableIndex();
            System.out.println(player);
        }
    }
}
