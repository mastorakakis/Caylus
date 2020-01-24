package caylus;

import entities.Block;
import entities.buildings.Building;
import entities.players.Player;
import interfaces.Phases;
import java.util.ArrayList;
import java.util.Scanner;

public class Phase3 implements Phases {

    @Override
    public void play(Game game, Scanner sc) {
        for (int i = 0; i < 6; i++) { // activate special buildings
            Block block = game.getRoad().get(i);
            // if building is not null and has worker
            if (block.getBuilding() != null && block.getWorkers().size() > 0) {
                Building building = block.getBuilding();
                // activate building
                building = building.activate(block.getWorkers(), sc, game);
                // empty block workers
                block.setWorkers(new ArrayList<Player>());
            }
        }
    }
}
