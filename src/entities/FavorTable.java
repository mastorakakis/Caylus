package entities;

import caylus.Game;
import entities.buildings.Building;
import entities.buildings.PrestigeBuilding;
import entities.buildings.ResidentialBuilding;
import entities.buildings.StoneBuilding;
import entities.buildings.WoodBuilding;
import entities.players.Player;
import enums.Action;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class FavorTable implements Serializable {

    private int[] pointsLine = {1, 2, 3, 4, 5};
    private int[] moneyLine = {3, 4, 5, 6, 7};
    private Resources[] resourcesLine = {new Resources(1, 0, 0, 0, 0), new Resources(),
        new Resources(0, 0, 0, 1, 0), new Resources(), new Resources(0, 0, 0, 0, 1)};
    private String[] resourcesLineStrings = {"1 Food", "1 Wood or 1 Stone", "1 Cloth",
        "Trade 1 resource cube for 2 others (apart from Gold)", "1 Gold"};
    private String buildLine[] = {"Empty", "Build Wood Building with 1 less Wood",
        "Build Stone Building with 1 less stone",
        "Build Residential Building with 1 less denier", "Build Prestige Building"};
    private List<Player>[][] playerFavorTable = new List[4][5];

    //constructor
    public FavorTable() {
    }

    public List<Player>[][] getPlayerFavorTable() {
        return playerFavorTable;
    }

    public void useFavor(Game game, Player player, Scanner sc) {
//        if (player.getFavors() > 4) {
//            player.setFavors(4);
//        }
//        int max = player.getFavors();
//        for (int i = max; i > 0; i--) {
//            player.setFavors(player.getFavors() - 1);
        String choice = selectFavorLine(game, player, sc);
        switch (choice) {
            case "Points Line":
                selectPointFavor(player, sc);
                break;
            case "Money Line":
                selectMoneyFavor(player, sc);
                break;
            case "Resources Line":
                selectResourceFavor(player, sc);
                break;
            case "Build Line":
                selectBuildFavor(game, player, sc);
                break;
            default:
//            }
        }
    }

    // select build favor
    private void selectBuildFavor(Game game, Player player, Scanner sc) {
        StringBuilder message = new StringBuilder(player.getColor() + " select Build Favor");
        int i;
        // print options
        for (i = 0; i < buildLine.length; i++) {
            if (playerFavorTable[3][i].contains(player)) {
                message.append(String.format("\n%d)%s", i + 1, buildLine[i]));
            }
            if (!playerFavorTable[3][i].contains(player)) {
                break;
            }
        }
        int choice = Functions.inputValidation(1, i, message.toString(), player, sc);
        // if build wood
        Building newBuilding = null;
        if (choice == 2) {
            List<WoodBuilding> woodList = new ArrayList();
            // get list of available wood buildings
            for (Building building : game.getBuildingList()) {
                if (building instanceof WoodBuilding) {
                    woodList.add((WoodBuilding) building);
                }
            } // add 1 wood to player because it costs 1 less wood to build
            player.getResources().modifyResources(2);
            newBuilding = player.buildWood(woodList, sc);
            // if nothing built return the extra wood from player
            if (newBuilding == null) {
                player.getResources().setWood(player.getResources().getWood() - 1);
            }
        } // if build stone
        if (choice == 3) {
            List<StoneBuilding> stoneList = new ArrayList();
            // get list of available wood buildings
            for (Building building : game.getBuildingList()) {
                if (building instanceof StoneBuilding) {
                    stoneList.add((StoneBuilding) building);
                }
            } // add 1 wood to player because it costs 1 less stone to build
            player.getResources().modifyResources(3);
            newBuilding = player.buildStone(game, stoneList, sc);
            // if nothing built return the extra stone from player
            if (newBuilding == null) {
                player.getResources().setStone(player.getResources().getStone() - 1);
            }
        } // if built find the first empty block and add building, house
        if (newBuilding != null) {
            for (int j = 15; j < game.getRoad().size(); j++) {
                Block block = game.getRoad().get(j);
                if (block.getBuilding() == null) {
                    block.setBuilding(newBuilding);
                    block.setHouse(player);
                    break;
                }
            } // remove building from list
            game.getBuildingList().remove(newBuilding);
        } // if build residential
        if (choice == 4) {
            // building residential costs 1 less denier
            ResidentialBuilding building = new ResidentialBuilding();
            building.setBuildMoney(0);
            player.buildResidential(game, building, sc);
        }
        if (choice == 5) {
            List<PrestigeBuilding> prestigeList = new ArrayList();
            // get list of available wood buildings
            for (Building building : game.getBuildingList()) {
                if (building instanceof PrestigeBuilding) {
                    prestigeList.add((PrestigeBuilding) building);
                }
            }
            player.buildPrestige(game, prestigeList, sc);
        }
    }

    // select resource favor
    private void selectResourceFavor(Player player, Scanner sc) {
        StringBuilder message = new StringBuilder(player.getColor() + " select Resource Favor");
        int i;
        // print options from the String array for the player
        for (i = 0; i < resourcesLine.length; i++) {
            if (playerFavorTable[2][i].contains(player)) {
                message.append(String.format("\n%d)%s", i + 1, resourcesLineStrings[i]));
            }
            if (!playerFavorTable[2][i].contains(player)) {
                break;
            }
        }
        int choice = Functions.inputValidation(1, i, message.toString(), player, sc);
        // for columns 0,2 & 4 resource trade is predetermined
        if (choice == 1 || choice == 3 || choice == 5) {
            player.tradeMoneyResources(resourcesLine[choice - 1], 0, Action.ADD);
            // select 1 stone or 1 wood
        } else if (choice == 2) {
            String message2 = player.getColor()
                    + " select resource cube\n1)1 Stone\n2)1 Wood";
            int choice2 = Functions.inputValidation(1, 2, message2, player, sc);
            choice2 = choice2 == 1 ? 3 : 2;
            resourcesLine[choice - 1].modifyResources(choice2);
            // collect resources
            player.tradeMoneyResources(resourcesLine[choice - 1], 0, Action.ADD);
            // reset resources
            resourcesLine[choice - 1] = new Resources();
        } else {
            do { // select resource to give away
                String message3 = player.getColor() + " select resource cube to trade\n"
                        + "1)Food\n2)Wood\n3)Stone\n4)Cloth\n5)Don't trade";
                int choice3 = Functions.inputValidation(1, 5, message3, player, sc);
                if (choice != 5) { // if trade
                    resourcesLine[choice - 1].modifyResources(choice3);
                    // if not enough resources
                    if (player.getResources().compareTo(resourcesLine[choice - 1]) < 0) {
                        System.out.println("Not enough resources to trade");
                        // reset resources
                        resourcesLine[choice - 1] = new Resources();
                        continue; // ask again
                    }  // if enough resources trade
                    player.tradeMoneyResources(resourcesLine[choice - 1],
                            0, Action.SUBTRACT);
                    // reset resources
                    resourcesLine[choice - 1] = new Resources();
                    // select 2 resource cubes
                    for (int j = 0; j < 2; j++) {
                        String message4 = player.getColor()
                                + " select 1 resource cube to collect\n"
                                + "1)Food\n2)Wood\n3)Stone\n4)Cloth";
                        int choice4 = Functions.inputValidation(1, 4, message4,
                                player, sc);
                        resourcesLine[choice - 1].modifyResources(choice4);
                    } // colect resources
                    player.tradeMoneyResources(resourcesLine[choice - 1],
                            0, Action.ADD);
                    // reset resources
                    resourcesLine[choice - 1] = new Resources();
                } // if don't trade
                break;
            } while (true);
        }
        System.out.println(player.getColor() + "\t" + player.getResources());
    }

    // select money favor
    private void selectMoneyFavor(Player player, Scanner sc) {
        StringBuilder message = new StringBuilder(player.getColor() + " select Money Favor");
        int i;
        // print options for user
        for (i = 0; i < moneyLine.length; i++) {
            if (playerFavorTable[1][i].contains(player)) {
                message.append(String.format("\n%d)%s deniers", i + 1, moneyLine[i]));
            }
            if (!playerFavorTable[1][i].contains(player)) {
                break;
            }
        }
        int choice = Functions.inputValidation(1, i, message.toString(), player, sc);
        // add selected amount of money to player
        player.setMoney(player.getMoney() + moneyLine[choice - 1]);
        System.out.println(player.getColor() + " Money=" + player.getMoney());
    }

    // select point favor
    private void selectPointFavor(Player player, Scanner sc) {
        StringBuilder message = new StringBuilder(player.getColor() + " select Point Favor");
        int i;
        // print options
        for (i = 0; i < pointsLine.length; i++) {
            if (playerFavorTable[0][i].contains(player)) {
                String word = i == 0 ? "point" : "points";
                message.append(String.format("\n%d)%s %s", i + 1, pointsLine[i], word));
            }
            if (!playerFavorTable[0][i].contains(player)) {
                break;
            }
        }
        int choice = Functions.inputValidation(1, i, message.toString(), player, sc);
        // add selected points to player
        player.setPoints(player.getPoints() + pointsLine[choice - 1]);
        System.out.println(player.getColor() + " Points=" + player.getPoints());
    }

    // select favor line
    private String selectFavorLine(Game game, Player player, Scanner sc) {
        // get available options for player
        String[] playerFavorOptions = player.getFavorTableOptions();
        List<Integer> indexList = new ArrayList();
        for (int i = 0; i < playerFavorOptions.length; i++) {
            if (playerFavorOptions[i] != null) {
                indexList.add(i);
            }
        }
        String message = "\n" + player.getColor() + " select Favor Line"
                + printIndexedOptions(indexList, playerFavorOptions);
        // select line
        int choice = Functions.inputValidation(1, indexList.size(), message, player, sc);
        movePlayer(game, player, playerFavorOptions[indexList.get(choice - 1)]);
        String selection = playerFavorOptions[indexList.get(choice - 1)];
        // remove line from options (for the rest of the phase)
        playerFavorOptions[indexList.get(choice - 1)] = null;
        System.out.println(selection);
        return selection;
    }

    // print message from list with indexed options
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
        for (int i = 0; i < playerFavorTable[line].length; i++) {
            // columns 2 and 3 are available after scoring the dungeon
            if (!game.getCastle().getDungeon().isScored() && i > 1) {
                break;
                // column 4 is available after scoring the walls
            } else if (!game.getCastle().getWalls().isScored() && i > 3) {
                break;
            }
            if (!playerFavorTable[line][i].contains(player)) {
                playerFavorTable[line][i].add(player);
                break;
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
