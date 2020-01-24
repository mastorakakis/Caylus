package caylus;

import entities.Block;
import entities.buildings.Building;
import entities.players.Player;
import java.util.Scanner;

public class Phase1 {

    // select income
    public static void play(Game game, Scanner sc) {
        // Each player collects income
        for (Player p : game.getPlayerList()) {
            p.setMoney(p.getMoney() + 2);
        }
        Player player;
        // for every block of the road
        for (Block block : game.getRoad()) {
            // if block is not empty
            if (block.getBuilding() != null) {
                Building building = block.getBuilding(); // take the building
                // if it's the library
                if (building.getName().equals("Library")) {
                    player = block.getHouse(); // the owner of the house
                    player.setMoney(player.getMoney() + 1); // collects income
                    // TODO change 1 to variable
                } // if it's the hotel
                else if (building.getName().equals("Hotel")) {
                    player = block.getHouse(); // the owner of the house
                    player.setMoney(player.getMoney() + 2); // collects income
                    // TODO change 2 to variable
                }
            }
        }
    }
}
