package caylus;

import static caylus.CreateBuildings.*;
import entities.Block;
import entities.buildings.Bridge;
import entities.buildings.Building;
import entities.players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class Game {

    public static final String warning = "--Invalid input--";

    private List<Player> playerList;
    private List<Block> road;

    // getters setters
    public List<Player> getPlayerList() {
        return playerList;
    }

    public List<Block> getRoad() {
        return road;
    }

    public void setRoad(List<Block> road) {
        this.road = road;
    } // end of getters setters

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    // Phase 1
    public void phase1() {
        for (Player p : playerList) {
            p.setMoney(p.getMoney() + 2); // each player collects income
        }
        Player player;
        for (Block block : road) { // for every block of the setUpRoad
            Building building = block.getBuilding(); // take the building
            if (building.getName().equals(library.getName())) { // if it's the library
                player = block.getHouse(); // the owner of the house
                player.setMoney(player.getMoney() + 1); // collects income
            }
            if (building.getName().equals(hotel.getName())) { // if it's the hotel
                player = block.getHouse(); // the owner of the house
                player.setMoney(player.getMoney() + 2); // collects income
            }
        }
    }

    // place worker
    public void phase2(Scanner sc) {
        Boolean innIsEmpty = true;
        while (Bridge.getPositionList().size() < playerList.size()) { // untill all player pass
            for (Player player : playerList) {
                List<Integer> index = getAvailableBuildings(road, player); // available buildings to place workers
                int max = index.size() + 1; // plus one for the -pass- option
                if (Bridge.getPositionList().contains(player)) { // if player has passed continue to the next
                    continue;
                }
                if (player.getMoney() < Bridge.getActivationMoney()) {
                    Bridge.getPositionList().add(player); // add player to bridge and continue to the next
                    Bridge.setActivationMoney(Bridge.getPositionList().size() + 1); // increase activation amount of bridge
                    System.out.println(player.getColor() + " doesn't have enough money and passes");
                    continue;
                }
                String message = printOptions(index, road); // print availabale options for the user
                System.out.print(player.getColor());
                int choice = Functions.inputValidation(1, max,
                        " place a worker or pass\n" + message, Game.warning, sc); // print complete message
                int number = max;
                if (choice == max) { // if choose to -pass- or has less money than bridge activation amount
                    Bridge.getPositionList().add(player); // add player to bridge and continue to the next
                    if (Bridge.getPositionList().size() == 1) { // if he is the first player
                        player.setMoney(player.getMoney() + 1); // player gets 1 denier
                    }
                    Bridge.setActivationMoney(Bridge.getPositionList().size() + 1); // increase activation amount of bridge
                    System.out.println(player.getColor() + " passes");
                } else {
                    int newBalance = player.getMoney() - Bridge.getPositionList().size() + 1; // pay proper amount
                    player.setMoney(newBalance); // set new money balance
                    road.get(index.get(choice - 1)).getWorkers().add(player); // add player worker to block
                }
            }
        }
    }

    // Return index list with available buildings for the player to place workers
    public List<Integer> getAvailableBuildings(List<Block> road, Player player) {
        List<Integer> indexList = new ArrayList();
        for (int i = 0; i < road.size(); i++) { // for every block of the setUpRoad
            Block block = road.get(i);
            Building building = road.get(i).getBuilding();
            if (building == stables && block.getWorkers().size() < 3 && !block.getWorkers().contains(player)) { // if stables don't have player or is full
                indexList.add(i);
            } else if (building == inn && block.getWorkers().size() < 2) {
                indexList.add(i);
            } else {
                if (block.getWorkers().isEmpty()) {
                    indexList.add(i);
                }
            }
        }
        return indexList; // return list
    }

    // print available options (from the list) for the user plus one more for -pass-
    public static String printOptions(List<Integer> index, List<Block> road) {
        int size = index.size();
        String message = "";
        for (int i = 0; i < size; i++) {
            Building building = road.get(index.get(i)).getBuilding();
            message += String.format("%d)%s\n", i + 1, building.getName());
        }
        return message + (size + 1) + ")-Pass-";
    }
}
