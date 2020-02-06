package utilities;

import caylus.Game;
import entities.Block;
import entities.players.Player;
import java.util.List;

public class Synopsis {

    public static void print(Game game) {
        System.out.println("\nSynopsis:");
        for (Player player : game.getPlayerList()) {
            System.out.println(player);
        }
        System.out.println("");
        // favor table
        List<Player>[][] playerFavorTable = game.getFavorTable().getPlayerFavorTable();
        System.out.println("Favor table");
        for (Player player : game.getPlayerList()) {
            System.out.print(player.getColor() + ": ");
            for (int i = 0; i < playerFavorTable.length; i++) {
                for (int j = playerFavorTable[i].length - 1; j >= 0; j--) {
                    if (playerFavorTable[i][j].contains(player)) {
                        System.out.printf("Line[%d][%d]\t", i + 1, j + 1);
                        break;
                    }
                }
            }
            System.out.println("");
        }
        // houses in castle
        System.out.println("\nCastle");
        for (Player player : game.getPlayerList()) {
            int numberOfBuildings = 0;
            for (Player buildSpace : game.getCastle().getDungeon().getBuildSpaces()) {
                if (buildSpace == player) {
                    numberOfBuildings++;
                }
            }
            if (numberOfBuildings > 0) {
                System.out.print(player.getColor()
                        + " buildings in Dungeon=" + numberOfBuildings + "\n");
            }
        }
        for (Player player : game.getPlayerList()) {
            int numberOfBuildings = 0;
            for (Player buildSpace : game.getCastle().getWalls().getBuildSpaces()) {
                if (buildSpace == player) {
                    numberOfBuildings++;
                }
            }
            if (numberOfBuildings > 0) {
                System.out.print(player.getColor()
                        + " buildings in the Walls=" + numberOfBuildings + "\n");
            }
        }
        for (Player player : game.getPlayerList()) {
            int numberOfBuildings = 0;
            for (Player buildSpace : game.getCastle().getTowers().getBuildSpaces()) {
                if (buildSpace == player) {
                    numberOfBuildings++;
                }
            }
            if (numberOfBuildings > 0) {
                System.out.print(player.getColor()
                        + " buildings in the Towers=" + numberOfBuildings + "\n");
            }
        }
        // bailiff position
        System.out.println("\nBailiff/Povost position=" + game.getBailiff().getPosition());
        System.out.println("");
        // inn has player
        if (game.getInn().getInnPosition()[1] != null) {
            System.out.println(game.getInn().getInnPosition()[1].getColor()
                    + " has a worker in Inn");
        }
        // new buildings
        System.out.println("\nNew buildings");
        for (int i = 7; i < game.getRoad().size(); i++) {
            Block block = game.getRoad().get(i);
            if (block.getHouse() != null) {
                System.out.println(block.getHouse().getColor() + " built "
                        + block.getBuilding().getName() + " in Block[" + i + "]");
            }
        }
    }
}
