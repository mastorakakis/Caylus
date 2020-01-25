package caylus;

import entities.Block;
import entities.buildings.Bridge;
import entities.buildings.Building;
import entities.buildings.Inn;
import entities.buildings.PrestigeBuilding;
import entities.buildings.ResidentialBuilding;
import entities.players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class Phase2 {

    public static void play(Game game, Scanner sc) {
        // untill all player pass
        while (Bridge.getPositionList().size() < game.getPlayerList().size()) {
            for (Player player : game.getPlayerList()) {
                // if player has passed
                if (Bridge.getPositionList().contains(player)) { // continue
                }// if player has not enough money or workers
                else if ((Game.inn.getInnPosition()[1] == player && player.getMoney() < 1)
                        || player.getMoney() < Bridge.getActivationMoney()
                        || player.getWorkers() == 0) {
                    // add player to bridge ( = pass)
                    Bridge.getPositionList().add(player);
                    // increase activation amount of bridge
                    Bridge.setActivationMoney(Bridge.getPositionList().size() + 1);
                    System.out.println(player.getColor() + " has no money or"
                            + " workers and passes");
                } else {
                    placeWorker(game, player, sc);
                }
            }
        }
    }

    // place worker
    public static void placeWorker(Game game, Player player, Scanner sc) {
        // get available buildings to place workers
        List<Integer> availableBuildingsList
                = getAvailableBuildings(game.getRoad(), player, game);
        String message // string of availabale options for the user
                = printOptions(availableBuildingsList, game.getRoad(), player, game);
        int size = availableBuildingsList.size();
        int max = availableBuildingsList.size() + 1; // plus one for pass
        // if worker not placed in the castle
        if (!game.castle.getPositionList().contains(player)) {
            max++; // plus one for -castle-
        }// player choice
        int choice = Functions.inputValidation(1, max,
                player.getColor() + " place a worker or pass\n" + message,
                Game.WARNING, sc); // print complete message
        // if choose to -pass-
        if (choice == max) {
            Bridge.getPositionList().add(player); // add player to bridge
            // if he is the first player
            if (Bridge.getPositionList().size() == 1) {
                player.setMoney(player.getMoney() + 1); // player gets 1 denier
            }// increase activation amount of bridge
            Bridge.setActivationMoney(Bridge.getPositionList().size() + 1);
            System.out.println(player.getColor() + " passes");
            return; // to not pay according to the last line
        } // if worker not placed in castle and chooses castle
        else if ((!game.castle.getPositionList().contains(player)
                && choice == max - 1)) {
            game.castle.getPositionList().add(player); // add worker to castle
        } // if choose inn
        else if (game.getRoad().get(availableBuildingsList.get(choice - 1))
                .getBuilding().getName().equals("Inn")) {
            Block block = game.getRoad().get(availableBuildingsList.get(choice - 1));
            Inn inn = (Inn) block.getBuilding(); // add worker to left position of inn
            inn.getInnPosition()[0] = player;
        } // if player chooses a building
        else {
            Block block = game.getRoad()
                    .get(availableBuildingsList.get(choice - 1));
            block.getWorkers().add(player); // add player worker to block
            System.out.println(player.getColor() + " places worker in "
                    + block.getBuilding().getName());
            // if house belongs to other player he gets a point
            if (block.getHouse() != null && block.getHouse() != player) {
                block.getHouse().setPoints(block.getHouse().getPoints() + 1);
                System.out.println(block.getHouse().getColor()
                        + " Points=" + block.getHouse().getPoints());
            } // if house belongs to player
            else if (block.getHouse() != null && block.getHouse() == player) {
                // player pays 1 denier total with last line of the method
                int newBalance = player.getMoney()
                        + Bridge.getPositionList().size();
                player.setMoney(newBalance); // set new money balance
            }
        } // if player hasn't passed thus not coming from gate pay proper amount
        if (!Bridge.getPositionList().contains(player)) {
            // if player has a worker in inn right position
            if (player == Game.inn.getInnPosition()[1]) {
                // player pays 1 denier total with last line of the method
                int newBalance = player.getMoney()
                        + Bridge.getPositionList().size();
                player.setMoney(newBalance); // set new money balance
            }
            int newBalance = player.getMoney()
                    - (Bridge.getPositionList().size() + 1);
            player.setMoney(newBalance); // set new money balance
        } // reduce number of workers
        player.setWorkers(player.getWorkers() - 1);
    }

// Return index list with available buildings for the player to place workers
    protected static List<Integer> getAvailableBuildings(List<Block> road,
            Player player, Game game) {
        List<Integer> indexList = new ArrayList();
        for (int i = 0; i < road.size(); i++) { // for every block of the road
            Block block = road.get(i);
            Building building = road.get(i).getBuilding();
            // residential & prestige buildings and empty blocks are not available
            if (building != null && (!(building instanceof PrestigeBuilding)
                    || !(building instanceof ResidentialBuilding))) {
                // if stables don't have player or is full
                if (building.getName().equals("Stables")
                        && block.getWorkers().size() < 3
                        && !block.getWorkers().contains(player)) {
                    indexList.add(i);
                } // if left position is empty in inn
                else if (building.getName().equals("Inn")) {
                    if (game.inn.getInnPosition()[0] == null) {
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
            Player player, Game game) {
        StringBuilder message = new StringBuilder();
        int i;
        for (i = 0; i < index.size(); i++) { // for every index
            // get the building according to index
            Building building = road.get(index.get(i)).getBuilding();
            if (building != null) { // if building is not empty
                message.append(String.format("%02d)%-20s\t", i + 1,
                        building.getName()));
                if ((i + 1) % 4 == 0) {
                    message.append("\n");
                }
            }
        }// if player not in the castle already
        // add castle to the message
        if (!game.castle.getPositionList().contains(player)) {
            message.append(String.format("%d)%s\n", ++i, "-Castle-"));
        }
        message.append(String.format("%d)%s", ++i, "-Pass-"));
        return message.toString();
    }

}
