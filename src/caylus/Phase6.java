package caylus;

import entities.Block;
import entities.buildings.Building;
import entities.players.Player;
import java.util.ArrayList;
import java.util.Scanner;

// building of the castle
public class Phase6 {

    public static void play(Game game, Scanner sc) {
        System.out.println("\nPhase 6: Building of the Castle");
        Block castleBlock = game.getRoad().get(0);
        Building castle = game.getRoad().get(0).getBuilding();
        castle.activate(game, castleBlock.getWorkers(), sc);
        // remove workers from castle
        castleBlock.setWorkers(new ArrayList());
        // check if building of castle is finished
        Phase7.checkSectionScoring(game, sc);
        for (Player player : game.getPlayerList()) {
            player.newFavorTableIndex();
            System.out.println(player);
        }
    }

}
