package caylus;

import entities.Block;
import entities.Resources;
import entities.buildings.Building;
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
        System.out.println("\nPhase 5: Activation of the Buildings");
        // activating neutral buildings
        for (int i = 7; i < game.getRoad().size(); i++) {
            Block block = game.getRoad().get(i);
            // if building not null and has worker
            if ((block.getBuilding() != null && block.getWorkers().size() > 0)
                    // and provost is not placed behind the block
                    && i <= game.getProvost().getPosition()) {
                Building building = block.getBuilding();
                // if stone building belongs to other pay rent resources
                if (block.getHouse() != null
                        && block.getHouse() != block.getWorkers().get(0)
                        && block.getBuilding() instanceof StoneBuilding) {
                    payRent(block, sc);
                }
                // activate building / if return not null -> build
                Building newBuilding = building.activate(game, block.getWorkers(), sc);
                // if building returned
                if (newBuilding != null) {
                    // if stone or wood building
                    if (newBuilding instanceof WoodBuilding
                            || newBuilding instanceof StoneBuilding) {
                        // find first empty block
                        for (int j = 15; j < game.getRoad().size(); j++) {
                            Block block1 = game.getRoad().get(j);
                            if (block1.getBuilding() == null) {
                                // build for appropriate player
                                block1.setBuilding(newBuilding);
                                block1.setHouse(block.getWorkers().get(0));
                                System.out.println(block1.getHouse().getColor()
                                        + " build " + block1.getBuilding().getName());
                                break;
                            }
                        } // remove building from list
                        game.getBuildingList().remove(newBuilding);
                    } // if residential building
                }
                // empty block workers
                block.setWorkers(new ArrayList<Player>());
            } // end of if
        } // end of for
        for (int i = 7; i < game.getRoad().size(); i++) {
            Block block = game.getRoad().get(i);
            if (block.getBuilding() != null && block.getWorkers().size() > 0) {
                Player player = block.getWorkers().get(0);
                // if there is a building waiting to be transformed
                if (block.getTempBuilding() != null) {
                    block.setBuilding(block.getTempBuilding());
                    block.setTempBuilding(null);
                }
                player.setWorkers(player.getWorkers() + 1);
                player = null;
            }
        }
        System.out.println("");
        for (Player player : game.getPlayerList()) {
            System.out.println(player);
        }
    }

    // owner choose/ collect resource, reset resource
    public static void payRent(Block block, Scanner sc) {
        Resources rentResources = ((StoneBuilding) block.getBuilding())
                .getActivationRentResources();
        int choice = 0;
        if (block.getBuilding().getName().equals("Stone Farm")) {
            String message = block.getHouse().getColor()
                    + " select resource\n1)1 Food\n2)1 Cloth";
            choice = Functions.inputValidation(1, 2, message, block.getHouse(), sc);
            choice = choice == 2 ? 4 : 1;
        } else if (block.getBuilding().getName().equals("Workshop")) {
            String message = block.getHouse().getColor()
                    + " select resource\n1)1 Stone\n2)1 Cloth";
            choice = Functions.inputValidation(1, 2, message, block.getHouse(), sc);
            choice = choice == 2 ? 4 : 3;
        } // if park
        else if (block.getBuilding().getName().equals("Park")) {
            String message = block.getHouse().getColor()
                    + " select resource\n1)1 Food\n2)1 Wood";
            choice = Functions.inputValidation(1, 2, message, block.getHouse(), sc);
        }
        rentResources.modifyResources(choice, sc);
        block.getHouse().tradeMoneyResources(rentResources, 0,
                Action.ADD);
        ((StoneBuilding) block.getBuilding())
                .setActivationRentResources(new Resources());
    }
}
