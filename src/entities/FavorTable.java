package entities;

import caylus.Game;
import entities.buildings.Building;
import entities.players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class FavorTable {

    private int[] pointsLine = {1, 2, 3, 4, 5};
    private int[] moneyLine = {3, 4, 5, 6, 7};
    private Resources[] resourcesLine = {new Resources(1, 0, 0, 0, 0), new Resources(),
        new Resources(0, 0, 0, 4, 0), new Resources(), new Resources(0, 0, 0, 0, 1)};
    private String buildLine[] = {"Empty", "Build Wood", "Build Stone",
        "Build, Residential", "Build Prestige"};
    private List<Player>[][] playerFavorTable = new List[4][5];

    //constructor
    public FavorTable() {
    }

    public void useFavor(Game game, Player player, Scanner sc) {
        String choice = selectFavorLine(game, player, sc);
        if (choice.equals("Points Line")) {

        } else if (choice.equals("Money Line")) {

        } else if (choice.equals("Resources Line")) {

        } else if (choice.equals("Build Line")) {

        }
    }

    public void selectPointFavor(Game game, Player player, Scanner sc) {

    }

    // select favor line
    public String selectFavorLine(Game game, Player player, Scanner sc) {
        String[] playerFavorOptions = player.getFavorTableOptions();
        List<Integer> indexList = new ArrayList();
        for (int i = 0; i < playerFavorOptions.length; i++) {
            if (playerFavorOptions[i] != null) {
                indexList.add(i);
            }
        }
        String message = "Select Favor Line"
                + printIndexedOptions(indexList, playerFavorOptions);
        int choice = Functions.inputValidation(1, indexList.size(), message, player, sc);
        movePlayer(game, player, playerFavorOptions[indexList.get(choice - 1)]);
        return playerFavorOptions[indexList.get(choice - 1)];
    }

    private String printIndexedOptions(List<Integer> indexList, String[] options) {
        StringBuilder message = new StringBuilder();
        int i;
        for (i = 0; i < indexList.size(); i++) {
            message.append(String.format("\n%d)%s", i + 1, options[indexList.get(i)]));
        }
        return message.toString();
    }

    // move player down the favor line after choosing
    private void movePlayer(Game game, Player player, String choice) {
        int line = 0;
        if (choice.equals("Points Line")) {
        } else if (choice.equals("Money Line")) {
            line = 1;
        } else if (choice.equals("Resources Line")) {
            line = 2;
        } else if (choice.equals("Build Line")) {
            line = 3;
        }
        if (choice.equals("Points Line")) {
            for (int i = 0; i < playerFavorTable[line].length; i++) {
                if (!game.getCastle().getDungeon().isScored() && i > 2) {
                    break;
                } else if (!game.getCastle().getWalls().isScored() && i > 4) {
                    break;
                }
                if (!playerFavorTable[line][i].contains(player)) {
                    playerFavorTable[line][i].add(player);
                    break;
                }
            }
        }
    }

    // create players' favor table
    public List<Player>[][] createPlayerFavorTable() {
        // create empty lists of players in every cell
        for (int i = 0; i < playerFavorTable.length; i++) {
            for (int j = 0; j < playerFavorTable[i].length; j++) {
                playerFavorTable[i][j] = new ArrayList<Player>();
            }
        }
        return playerFavorTable;
    }

}
