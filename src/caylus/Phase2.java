package caylus;

import static caylussetup.CreateBuildings.inn;
import static caylussetup.CreateBuildings.stables;
import entities.Block;
import entities.buildings.Bridge;
import entities.buildings.Building;
import entities.buildings.Castle;
import entities.buildings.PrestigeBuilding;
import entities.buildings.ResidentialBuilding;
import entities.players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class Phase2 {

    public static void play(Game game, Scanner sc) {
        while (Bridge.getPositionList().size() < game.getPlayerList().size()) { // untill all player pass
            for (Player player : game.getPlayerList()) {
                if (Bridge.getPositionList().contains(player)) { // if player has passed
                    continue; // continue to next player
                }
                placeWorker(game, player, sc);
            }
        }
    }

    // place worker
    protected static void placeWorker(Game game, Player player, Scanner sc) {
        List<Integer> availableBuildingsList
                = getAvailableBuildings(game.getRoad(), player, game); // available buildings to place workers
        String message
                = printOptions(availableBuildingsList, game.getRoad(), player); // get availabale options for the user
        int max = availableBuildingsList.size() + 1; // plus one for -pass-
        if (!Castle.getPositionList().contains(player)) { // if worker not placed in the castle
            max++; // plus one for -castle-
        }
        if (player.getMoney() < Bridge.getActivationMoney()) { // if player has not enough money
            Bridge.getPositionList().add(player); // add player to bridge ( = pass)
            Bridge.setActivationMoney(Bridge.getPositionList().size() + 1); // increase activation amount of bridge
            System.out.println(player.getColor() + " doesn't have enough money and passes");
            return; // continue to next player
        }
        int choice = Functions.inputValidation(1, max, // player choice
                player.getColor() + " place a worker or pass\n" + message,
                Game.WARNING, sc); // print complete message

        if (choice == max) { // if choose to -pass-
            Bridge.getPositionList().add(player); // add player to bridge
            if (Bridge.getPositionList().size() == 1) { // if he is the first player
                player.setMoney(player.getMoney() + 1); // player gets 1 denier
            }
            Bridge.setActivationMoney(Bridge.getPositionList().size() + 1); // increase activation amount of bridge
            System.out.println(player.getColor() + " passes");
            return;
        } else if (!Castle.getPositionList().contains(player) && choice == max - 1) { // if worker not placed in castle and choose castle
            Castle.getPositionList().add(player); // add worker to castle
        } else if (game.getRoad().get(availableBuildingsList.get(choice - 1)).getBuilding() == inn) { // if choose inn
            game.getInnPosition()[0] = player; // add worker to left position of inn
        } else { // if player chooses a building
            Block block = game.getRoad().get(availableBuildingsList.get(choice - 1));
            block.getWorkers().add(player); // add player worker to block
            if (block.getHouse() != null && block.getHouse() != player) { // if house belongs to other player
                block.getHouse().setPoints(block.getHouse().getPoints() + 1);
            } else if (block.getHouse() != null && block.getHouse() == player) { // if house belongs to player
                int newBalance = player.getMoney() + Bridge.getPositionList().size(); // player will pay 1 denier total considering last line of the method
                player.setMoney(newBalance); // set new money balance
            }
        }
        int newBalance = player.getMoney() - (Bridge.getPositionList().size() + 1); // pay proper amount
        player.setMoney(newBalance); // set new money balance
    }

// Return index list with available buildings for the player to place workers
    protected static List<Integer> getAvailableBuildings(List<Block> road, Player player, Game game) {
        List<Integer> indexList = new ArrayList();
        for (int i = 0; i < road.size(); i++) { // for every block of the road
            Block block = road.get(i);
            Building building = road.get(i).getBuilding();
            if (building != null || (!(building instanceof PrestigeBuilding)
                    && !(building instanceof ResidentialBuilding))) { // residential-prestige  buildings and empty blocks are not available
                if (building == stables
                        && block.getWorkers().size() < 3 && !block.getWorkers().contains(player)) { // if stables don't have player or is full
                    indexList.add(i);
                } else if (building == inn) {
                    if (game.getInnPosition()[0] == null) {  // if left position is empty in inn
                        indexList.add(i);
                    }
                } else {
                    if (block.getWorkers().isEmpty()) { // if building is epmtpy
                        indexList.add(i);
                    }
                }
            }
        }
        return indexList; // return list
    }

    // create a string with available options (from a list) for the user
    protected static String printOptions(List<Integer> index, List<Block> road,
            Player player) {
        StringBuilder message = new StringBuilder();
        int i;
        for (i = 0; i < index.size(); i++) { // for every index
            Building building = road.get(index.get(i)).getBuilding(); // get the building according to index
            if (building != null) { // if building is not empty
                message.append(String.format("%02d)%-20s\t", i + 1, building.getName()));
                if ((i + 1) % 4 == 0) {
                    message.append("\n");
                }
            }
        }
        if (!Castle.getPositionList().contains(player)) { // if player not in the castle already
            message.append(String.format("%d)%s\n", ++i, "-Castle-")); // add castle to the message
        }
        message.append(String.format("%d)%s", ++i, "-Pass-"));
        return message.toString();
    }

}
// TODO maybe delete (block.getHouse() != null)
