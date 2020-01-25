package caylus;

import static caylus.Game.WARNING;
import entities.Block;
import entities.Resources;
import entities.buildings.Building;
import entities.buildings.StoneBuilding;
import entities.players.Player;
import enums.SelectAction;
import java.util.ArrayList;
import java.util.Scanner;
import utilities.Functions;

// Activate buildings
public class Phase5 {

    public static void play(Game game, Scanner sc) {
        for (int i = 6; i < 12; i++) {
            Block block = game.getRoad().get(i);
            // if building is not null and has worker
            if ((block.getBuilding() != null && block.getWorkers().size() > 0)
                    // and provost is not placed behind the block
                    && i <= game.provost.getPosition()) {
                Building building = block.getBuilding();
                // activate building
                building = building.activate(game, block.getWorkers(), sc);
                if (block.getHouse() != block.getWorkers().get(0)) {
                    if (building.getName().equals("Stone Farm")) {
                        Resources rentResources = ((StoneBuilding) building)
                                .getActivationRentResources();
                        String message = block.getHouse().getColor()
                                + " select resource1\n1)1 Food\n2)1 Cloth";
                        int choice = Functions.inputValidation(1, 2, message, WARNING, sc);
                        choice = choice == 2 ? 4 : 1;
                        rentResources.modifyResources(choice, sc);
                        block.getHouse().collectFromBuilding(rentResources, 0,
                                SelectAction.ADD);
                        ((StoneBuilding) building)
                                .setActivationRentResources(new Resources());
                    }
                    if (building.getName().equals("Workshop")) {
                        Resources rentResources = ((StoneBuilding) building)
                                .getActivationRentResources();
                        //TODO delete
                        String message = block.getHouse().getColor()
                                + " select resource\n1)1 Stone\n2)1 Cloth";
                        int choice = Functions.inputValidation(1, 2, message, WARNING, sc);
                        choice = choice == 2 ? 4 : 3;
                        rentResources.modifyResources(choice, sc);
                        block.getHouse().collectFromBuilding(rentResources, 0,
                                SelectAction.ADD);
                        ((StoneBuilding) building)
                                .setActivationRentResources(new Resources());
                    }
                    if (building.getName().equals("Park")) {
                        Resources rentResources = ((StoneBuilding) building)
                                .getActivationRentResources();
                        String message = block.getHouse().getColor()
                                + " select resource1\n1)1 Food\n2)1 Wood";
                        int choice = Functions.inputValidation(1, 2, message, WARNING, sc);
                        rentResources.modifyResources(choice, sc);
                        block.getHouse().collectFromBuilding(rentResources, 0,
                                SelectAction.ADD);
                        ((StoneBuilding) building)
                                .setActivationRentResources(new Resources());
                    }
                }
                // empty block workers
                block.setWorkers(new ArrayList<Player>());
            }
        }
    }
}
