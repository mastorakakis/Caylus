package caylus;

import entities.Block;
import entities.buildings.Building;
import entities.buildings.Inn;
import entities.buildings.PrestigeBuilding;
import entities.buildings.ResidentialBuilding;
import entities.players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

// placing workers
public class Phase2 {

    public static void play(Game game, Scanner sc) {
        System.out.println("\nPhase 2: Placing Workers");
        // untill all player pass
        while (game.getBridge().getPositionList().size() < game.getPlayerList().size()) {
            for (Player player : game.getPlayerList()) {
                // if player has passed continue
                if (game.getBridge().getPositionList().contains(player)) {
                }// if player has not enough money or workers
                else if ((game.getInn().getInnPosition()[1] == player && player.getMoney() < 1)
                        || player.getMoney() < game.getBridge().getPositionList().size() + 1
                        || player.getWorkers() == 0) {
                    // add player to bridge ( = pass)
                    game.getBridge().playerPass(player);
                    System.out.println("(Not enough workers or money)");
                } else {
                    placeWorker(game, player, sc);
                }
            }
        }
        for (Player player : game.getPlayerList()) {
            player.newFavorTableIndex();
            System.out.println(player);
        }
    }

    // place worker
    public static void placeWorker(Game game, Player player, Scanner sc) {
        // get available buildings to place workers
        List<Integer> availableBuildingList
                = getAvailableBuildings(game.getRoad(), player, game);
        String message // string of availabale options for the user
                = Functions.printIndexedOptions(availableBuildingList, game.getRoad());
        int max = availableBuildingList.size() + 1; // plus one for pass
        System.out.println("\n" + player.getColor() + " Money=" + player.getMoney()
                + " Worker=" + player.getWorkers());
        int choice = Functions.inputValidation(1, max, player.getColor()
                + " place a worker or pass\n" + message, player, sc);
        // if -pass-
        if (choice == max) {
            // add player to bridge ( = pass)
            game.getBridge().playerPass(player);
            return; // to not pay according to end of method
        } // if Inn and has no worker there
        Block block = game.getRoad().get(availableBuildingList.get(choice - 1));
        if (block.getBuilding().getName().equals("Inn")) {
            // add worker to left position of inn
            ((Inn) block.getBuilding()).getInnPosition()[0] = player;
            System.out.println(player.getColor() + " places worker in "
                    + block.getBuilding().getName());
        } // if other building
        else {
            block.getWorkers().add(player);
            // add worker to block
            System.out.println(player.getColor() + " places worker in "
                    + block.getBuilding().getName());
            // if house belongs to other player he gets a point
//            if (block.getHouse() != null && block.getHouse() != player) {
//                block.getHouse().setPoints(block.getHouse().getPoints() + 1);
//                System.out.println(block.getHouse().getColor() + " earns 1 point");
//            }
        } // if player hasn't passed thus not coming from gate pay proper amount
        if (!game.getBridge().getPositionList().contains(player)) {
            // if building belongs to player or player has worker in inn right side
            if (block.getHouse() != null && (block.getHouse() == player
                    || player == game.getInn().getInnPosition()[1])) {
                player.setMoney(player.getMoney() - 1); // pay only 1 denier
                System.out.println(player.getColor() + " pays only 1 denier");
            } else {// pay full price
                int newBalance = player.getMoney()
                        - game.getBridge().getPositionList().size() - 1;
                player.setMoney(newBalance);
            }
        } // if house belongs to other the owner gets one point
        if (block.getHouse() != null && block.getHouse() != player) {
            block.getHouse().setPoints(block.getHouse().getPoints() + 1);
            System.out.println(block.getHouse() + " earns one point");
        }
        // reduce number of workers
        player.setWorkers(player.getWorkers() - 1);
        System.out.println(player.getColor() + " Money=" + player.getMoney()
                + " Workers=" + player.getWorkers());
    }

    // return index list with available buildings for the player to place workers
    protected static List<Integer> getAvailableBuildings(List<Block> road,
            Player player, Game game) {
        List<Integer> indexList = new ArrayList();
        for (int i = 0; i < road.size(); i++) { // for every block of the road
            Block block = road.get(i);
            Building building = road.get(i).getBuilding();
            // residential & prestige buildings and empty blocks are not available
            if (building != null && (!(building instanceof PrestigeBuilding)
                    || !(building instanceof ResidentialBuilding))) {
                // if stables don't have player or full
                if (building.getName().equals("Stables")
                        && block.getWorkers().size() < 3
                        && !block.getWorkers().contains(player)) {
                    indexList.add(i);
                } // if left position is empty in inn
                else if (building.getName().equals("Inn")) {
                    if (game.getInn().getInnPosition()[0] == null) {
                        indexList.add(i);
                    }
                    // if player not in castle
                } else if (building == game.getCastle()
                        && !block.getWorkers().contains(player)) {
                    indexList.add(i);
                    // if building is epmtpy
                } else {
                    if (block.getWorkers().isEmpty()) {
                        indexList.add(i);
                    }
                }
            }
        }
        return indexList; // return list
    }
}
