package caylus;

import entities.Block;
import entities.buildings.Building;
import entities.buildings.PrestigeBuilding;
import entities.buildings.ResidentialBuilding;
import entities.players.Player;
import java.util.Scanner;

// collecting income
public class Phase1 {

    public static void play(Game game, Scanner sc) {
        System.out.println("\nPhase 1: Collecting Income\nAll players earn 2 deniers");
        // collect income from library and hotel
        for (Block block : game.getRoad()) {
            Player player;
            // if block is not empty
            if (block.getBuilding() != null) {
                Building building = block.getBuilding(); // take the building
                // if library
                if (building.getName().equals("Library")) {
                    PrestigeBuilding pb = (PrestigeBuilding) building;
                    player = block.getHouse(); // owner of the house
                    // collect income
                    player.setMoney(player.getMoney() + 1);
                    System.out.println(player.getColor() + " earns 1 denier");
                } // if  hotel
                else if (building.getName().equals("Hotel")) {
                    PrestigeBuilding pb = (PrestigeBuilding) building;
                    player = block.getHouse(); // owner of the house
                    // collect income
                    player.setMoney(player.getMoney() + pb.getIncomeMoney());
                    System.out.println(player.getColor() + " earns 2 deniers");
                } // if residential building
                else if (building instanceof ResidentialBuilding) {
                    ResidentialBuilding pb = (ResidentialBuilding) building;
                    player = block.getHouse(); // owner of the house
                    // collect income
                    player.setMoney(player.getMoney() + pb.getIncomeMoney());
                    System.out.println(player.getColor() + " earns 1 denier");
                }
            }
        }
        // Each player collects income
        for (Player player : game.getPlayerList()) {
            player.setMoney(player.getMoney() + 2);
            System.out.println(player);
        }
    }

}
