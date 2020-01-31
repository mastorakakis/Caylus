package caylus;

import entities.Block;
import entities.buildings.Building;
import entities.players.Player;
import java.util.Scanner;

// building of the castle
public class Phase6 {

    public static void play(Game game, Scanner sc) {
        System.out.println("\nPhase 6: Building of the Castle");
        Block castleBlock = game.getRoad().get(0);
        Building castle = game.getRoad().get(0).getBuilding();
        castle.activate(game, castleBlock.getWorkers(), sc);
        for (Player player : game.getPlayerList()) {
            System.out.println(player);
        }
    }

}
