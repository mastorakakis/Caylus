package caylus;

import static caylussetup.CreateBuildings.hotel;
import static caylussetup.CreateBuildings.library;
import entities.Block;
import entities.buildings.Building;
import entities.players.Player;

public class Phase1 extends Game {

    // select income
    public static void play() {
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
}
