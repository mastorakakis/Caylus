package caylus;

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
        System.out.println("\nPhase 5: Activation of the Buildings");
        System.out.println("------------------------------------");
        // activating neutral buildings
        for (int i = 7; i < game.getRoad().size(); i++) {
            Block block = game.getRoad().get(i);
            // if building not null and has worker
            if ((block.getBuilding() != null && block.getWorkers().size() > 0)
                    // and provost is not placed behind the block
                    && i <= game.getProvost().getPosition()) {
                Building building = block.getBuilding();
                Building newBuilding = null;
                // activate building / if return not null -> build
                if (!(building instanceof ResidentialBuilding
                        || building instanceof PrestigeBuilding)) {
                    newBuilding = building.activate(game, block.getWorkers(), sc);
                }
                // if stone building belongs to other pay rent resources
                if (block.getHouse() != null
                        && block.getHouse() != block.getWorkers().get(0)
                        && block.getBuilding() instanceof StoneBuilding) {
                    payRent(block, sc);
                }
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
                                        + " built " + block1.getBuilding().getName());
                                break;
                            }
                        } // remove building from list
                        game.getBuildingList().remove(newBuilding);
                    } // if residential building
                }
                // empty block workers
                block.setWorkers(new ArrayList<Player>());
            } // end of if
            if (i > 22 && block.getBuilding() == null) {
                break;
            }
        } // end of for
        // remove workers from non activating buildings / build temp building
        System.out.println("\nReturning workers from non activated buildings");
        for (int i = 7; i < game.getRoad().size(); i++) {
            Block block = game.getRoad().get(i);
            if (block.getBuilding() != null && block.getWorkers().size() > 0) {
                Player player = block.getWorkers().get(0);
                // if there is a building waiting to be transformed
                if (block.getTempBuilding() != null) {
                    // wood and stone buildings go back to the list
                    if (!(block.getBuilding() instanceof NeutralBuilding)) {
                        // add craft building back to building list
                        game.getBuildingList().add(block.getBuilding());
                    }
                    // transform block building
                    block.setBuilding(block.getTempBuilding());
                    block.setTempBuilding(null);
                    System.out.println(player.getColor() + " build "
                            + block.getBuilding().getName());
                } // return all workers if buildings not activated
                player.setWorkers(player.getWorkers() + 1);
                System.out.println(player.getColor() + " Worker=" + player.getWorkers());
                // empty block workers
                block.setWorkers(new ArrayList<Player>());
            }
            if (i > 22 && block.getBuilding() == null) {
                break;
            }
        }
        System.out.println("");
        for (Player player : game.getPlayerList()) {
            player.newFavorTableIndex();
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
                    + " select resource cube\n1)1 Food\n2)1 Cloth";
            choice = Functions.inputValidation(1, 2, message, block.getHouse(), sc);
            choice = choice == 2 ? 4 : 1;
        } else if (block.getBuilding().getName().equals("Workshop")) {
            String message = block.getHouse().getColor()
                    + " select resource cube\n1)1 Stone\n2)1 Cloth";
            choice = Functions.inputValidation(1, 2, message, block.getHouse(), sc);
            choice = choice == 2 ? 4 : 3;
        } // if park
        else if (block.getBuilding().getName().equals("Park")) {
            String message = block.getHouse().getColor()
                    + " select resource cube\n1)1 Food\n2)1 Wood";
            choice = Functions.inputValidation(1, 2, message, block.getHouse(), sc);
        }
        rentResources.modifyResources(choice);
        block.getHouse().tradeMoneyResources(rentResources, 0,
                Action.ADD);
        ((StoneBuilding) block.getBuilding())
                .setActivationRentResources(new Resources());
    }
}
