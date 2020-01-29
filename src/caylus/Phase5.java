package caylus;

import static caylus.Game.WARNING;
import entities.Block;
import entities.Resources;
import entities.buildings.Building;
import entities.buildings.NeutralBuilding;
import entities.buildings.PrestigeBuilding;
import entities.buildings.ResidentialBuilding;
import entities.buildings.StoneBuilding;
import entities.buildings.WoodBuilding;
import entities.players.Player;
import enums.Action;
import java.util.ArrayList;
import java.util.Scanner;
import utilities.Functions;

// activation of the buildings
public class Phase5 {

    public static void play(Game game, Scanner sc) {
        System.out.println("Phase 5: Activation of the Buildings");
        // activaing neutral buildings
        for (int i = 7; i <= 12; i++) { //TODO change limit 6
            Block block = game.getRoad().get(i);
            // if building not null and has worker
            if ((block.getBuilding() != null && block.getWorkers().size() > 0)
                    // and provost is not placed behind the block
                    && i <= game.getProvost().getPosition()) {
                Building building = block.getBuilding();
                // if building belongs to other pay rent
                if (block.getHouse() != block.getWorkers().get(0)) {
                    payRent(block, sc);
                }
                // activate building
                Building newBuilding = building.activate(game, block.getWorkers(), sc);
                if (newBuilding != null) {
                    if (newBuilding instanceof WoodBuilding
                            || newBuilding instanceof StoneBuilding) {
                        for (int j = 15; j < game.getRoad().size(); j++) {
                            Block block1 = game.getRoad().get(j);
                            if (block1.getBuilding() != null) {
                                block1.setBuilding(newBuilding);
                                block1.setHouse(block.getWorkers().get(0));
                            }
                        }
                        game.getBuildingList().remove(newBuilding);
                    } else if (newBuilding instanceof ResidentialBuilding) {
                        for (int j = 7; j < game.getRoad().size(); j++) {
                            Block block1 = game.getRoad().get(j);
                            if (block1.getTempBuilding() != null) {
                                if (!(block1.getBuilding() instanceof NeutralBuilding)) {
                                    game.getBuildingList().add(block1.getBuilding());
                                }
                                block1.setBuilding(block1.getTempBuilding());
                                block1.setTempBuilding(null);
                            }
                        }
                    }
                }
                // empty block workers
                block.setWorkers(new ArrayList<Player>());
            }
        }
    }

    public static void payRent(Block block, Scanner sc) {
        Resources rentResources = ((StoneBuilding) block.getBuilding())
                .getActivationRentResources();
        int choice = 0;
        if (block.getBuilding().getName().equals("Stone Farm")) {
            String message = block.getHouse().getColor()
                    + " select resource1\n1)1 Food\n2)1 Cloth";
            choice = Functions.inputValidation(1, 2, message, null, sc); // TODO null
            choice = choice == 2 ? 4 : 1;
        } else if (block.getBuilding().getName().equals("Workshop")) {
            String message = block.getHouse().getColor()
                    + " select resource\n1)1 Stone\n2)1 Cloth";
            choice = Functions.inputValidation(1, 2, message, null, sc);// TODO null
            choice = choice == 2 ? 4 : 3;
        } else if (block.getBuilding().getName().equals("Park")) {
            String message = block.getHouse().getColor()
                    + " select resource1\n1)1 Food\n2)1 Wood";
            choice = Functions.inputValidation(1, 2, message, null, sc);// TODO null
        }
        rentResources.modifyResources(choice, sc);
        block.getHouse().tradeMoneyResources(rentResources, 0,
                Action.ADD);
        ((StoneBuilding) block.getBuilding())
                .setActivationRentResources(new Resources());
    }
}
