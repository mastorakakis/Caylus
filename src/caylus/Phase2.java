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
        System.out.println("------------------------");
        // untill all player pass
        while (game.getBridge().getPositionList().size() < game.getPlayerList().size()) {
            for (Player player : game.getPlayerList()) {
                boolean hasAHouse = false;
                // check if player owns at least 1 building not residential or prestige
                for (int i = 7; i < game.getRoad().size(); i++) {
                    Block block = game.getRoad().get(i);
                    if (block.getBuilding() != null
                            && !(block.getBuilding() instanceof ResidentialBuilding)
                            && !(block.getBuilding() instanceof PrestigeBuilding)
                            && block.getHouse() == player) {
                        hasAHouse = true;
                        break;
                    }
                }
                // if has passed continue
                if (game.getBridge().getPositionList().contains(player)) {
                } // if has enough money/workers or worker in Inn or owns a house
                else if (player.getWorkers() > 0
                        && (player.getMoney() >= game.getBridge().getPositionList().size() + 1
                        || (game.getInn().getInnPosition()[1] == player && player.getMoney() >= 1)
                        || (hasAHouse == true && player.getMoney() >= 1))) {
                    placeWorker(game, player, sc);
                } // if has no money or workers -> pass
                else {
                    game.getBridge().playerPass(player);
                    System.out.println("Not enough workers or money");
                }
            } // end for every player
        } // end while
        System.out.println("");
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
        String message // string of available options for the user
                = Functions.printIndexedOptions(availableBuildingList, game.getRoad());
        int max = availableBuildingList.size() + 1; // plus one for pass
        System.out.println("\n" + player.getColor() + " Money=" + player.getMoney()
                + " Worker=" + player.getWorkers());
        Block block;
        while (true) {
            int choice = Functions.inputValidation(1, max, player.getColor()
                    + " place a worker or pass\n" + message, player, sc);
            // if -pass-
            if (choice == max) {
                // add player to bridge ( = pass)
                game.getBridge().playerPass(player);
                return; // to not pay according to end of method
            }
            block = game.getRoad().get(availableBuildingList.get(choice - 1));
            // if player hasn't passed thus not coming from gate pay proper amount
            if (!game.getBridge().getPositionList().contains(player)) {
                // if building belongs to player or player has worker in inn right side
                if ((block.getHouse() != null && block.getHouse() == player)
                        || player == game.getInn().getInnPosition()[1]) {
                    player.setMoney(player.getMoney() - 1); // pay only 1 denier
                    System.out.println(player.getColor() + " pays only 1 denier");
                    break;
//                    }
                } else { // pay full price
                    int newBalance = player.getMoney()
                            - game.getBridge().getPositionList().size() - 1;
                    // if not enough money place worker in own building
                    if (newBalance < 0) {
                        System.out.println("Not enough workers or money");
                        System.out.println(player.getColor() + " you can only place "
                                + "a worker in one of your own buildings");
                        continue;
                    } else {
                        player.setMoney(newBalance);
                        break;
                    }
                }
            }
            break;
        }
        // if Inn and has no worker there
        if (block.getBuilding().getName().equals("Inn")) {
            // add worker to left position of inn
            ((Inn) block.getBuilding()).getInnPosition()[0] = player;
            System.out.println(player.getColor() + " places a worker in "
                    + block.getBuilding().getName());
        } else { // if other building
            block.getWorkers().add(player);
            // add worker to block
            System.out.println(player.getColor() + " places a worker in "
                    + block.getBuilding().getName());
        }

        // if house belongs to other the owner gets one point
        if (block.getHouse() != null && block.getHouse() != player) {
            block.getHouse().setPoints(block.getHouse().getPoints() + 1);
            System.out.println(block.getHouse().getColor() + " earns 1 point");
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
        // if not enough money and not in Inn not from gate - return only buildings player owns
        if (player.getMoney() < game.getBridge().getPositionList().size() + 1
                && game.getInn().getInnPosition()[1] != player
                && !game.getRoad().get(1).getWorkers().contains(player)) {
            for (int i = 0; i < road.size(); i++) {
                Block block = road.get(i);
                Building building = road.get(i).getBuilding();
                if (building != null && (!(building instanceof PrestigeBuilding)
                        || !(building instanceof ResidentialBuilding))
                        && block.getHouse() == player
                        && block.getWorkers().isEmpty()) {
                    indexList.add(i);
                }
            }
            return indexList;
        }
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
