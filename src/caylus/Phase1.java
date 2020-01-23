package caylus;

import static caylussetup.CreateBuildings.hotel;
import static caylussetup.CreateBuildings.library;
import entities.Block;
import entities.buildings.Building;
import entities.players.Player;
import java.util.Scanner;

public class Phase1 {

    // select income
    public static void play(Game game, Scanner sc) {
        for (Player p : game.getPlayerList()) {
            p.setMoney(p.getMoney() + 2); // each player collects income
        }
        Player player;
        for (Block block : game.getRoad()) { // for every block of the setUpRoad
            if (block.getBuilding() != null) { // if block is not empty
                Building building = block.getBuilding(); // take the building
                if (building.getName().equals(library.getName())) { // if it's the library
                    player = block.getHouse(); // the owner of the house
                    player.setMoney(player.getMoney() + library.getActivationMoney()); // collects income
                } else if (building.getName().equals(hotel.getName())) { // if it's the hotel
                    player = block.getHouse(); // the owner of the house
                    player.setMoney(player.getMoney() + hotel.getActivationMoney()); // collects income
                }
            }
        }
    }
}
