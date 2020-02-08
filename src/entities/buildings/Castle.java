package entities.buildings;

import caylus.Game;
import entities.Resources;
import entities.Section;
import entities.players.Player;
import enums.Action;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class Castle extends Building implements Serializable {

    private Section dungeon = new Section("Dungeon", new ArrayList(), 5, 1, 2);
    private Section walls = new Section("Walls", new ArrayList(), 4, 1, 3);
    private Section towers = new Section("Towers", new ArrayList(), 3, 1, 4);
    private int buildFavor = 1;
    private int penaltyBuildPoints = 2;

    // constructor private
    public Castle(String name) {
        super(name);
    }

    // getters setters
    public Section getDungeon() {
        return dungeon;
    }

    public Section getWalls() {
        return walls;
    }

    public Section getTowers() {
        return towers;
    } // end of getters setters

    @Override
    public Building activate(Game game, List<Player> castlePlayers, Scanner sc) {
        // if workers in the castle
        if (!castlePlayers.isEmpty()) {
            List<Integer> numberOfBuildings = new ArrayList();
            // for every player in the castle
            for (int i = 0; i < castlePlayers.size(); i++) {
                Player player = castlePlayers.get(i);
                // add zero integer
                numberOfBuildings.add(0);
                // if player has 3 different resources
                if (hasResourceBatch(player)) {
                    System.out.println(player.getColor() + " building of the Castle "
                            + "costs 3 different resource cubes (1 is food)");
                    while (true) {
                        // select valid compination of resources
                        Resources payResources = selectResources(player, sc);
                        // if don't build loose points
                        if (payResources == null) {
                            loosePoints(player, numberOfBuildings);
                            break;
                        } // if not enough resources try again
                        if (player.getResources().compareTo(payResources) < 0) {
                            System.out.println("Invalid payment");
                            continue;
                        } // if able to build
                        if (buildSection(player)) {
                            player.tradeMoneyResources(payResources, 0, Action.SUBTRACT);
                            // increase number of buildings for player
                            int number = numberOfBuildings.get(i);
                            numberOfBuildings.set(i, ++number);
                            // ask to build more
                            String message = player.getColor() + " build another?\n"
                                    + "1)Yes\t2)No:";
                            int choice = Functions.inputValidation(1, 2, message,
                                    player, sc);
                            if (choice == 2) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } // end of while
                    // return worker
                    player.setWorkers(player.getWorkers() + 1);
                    System.out.println(player.getColor() + " Worker=" + player.getWorkers());
                } else {// if not enough resources
                    System.out.println("Not enough resources");
                    loosePoints(player, numberOfBuildings);
                    player.setWorkers(player.getWorkers() + 1);
                    System.out.println(player.getColor() + " Worker=" + player.getWorkers());
                }
            } // end of for
            earnFavor(game, castlePlayers, numberOfBuildings, sc);
        }
        return null;
    }

    // first player with most buildings wins a favor
    public void earnFavor(Game game, List<Player> castlePlayers, List<Integer> numOfBuildings,
            Scanner sc) {
        int max = numOfBuildings.get(0);
        Player maxPlayer = castlePlayers.get(0);
        for (int i = 0; i < castlePlayers.size(); i++) {
            if (numOfBuildings.get(i) > max) {
                max = numOfBuildings.get(i);
                maxPlayer = castlePlayers.get(i);
            }
        }
        if (max > 0) {
            maxPlayer.setFavors(maxPlayer.getFavors() + buildFavor);
            System.out.println(maxPlayer.getColor() + " placed the most houses in"
                    + " the Castle during this turn");
            System.out.println(maxPlayer.getColor() + " earns " + buildFavor
                    + " favor");
            // use favor
            int maxFavor = maxPlayer.getFavors();
            if (maxPlayer.getFavors() > 4) {
                maxFavor = 4;
            }
            maxPlayer.setFavors(0);
            for (int i = maxFavor; i > 0; i--) {
                game.getFavorTable().useFavor(game, maxPlayer, sc);
            }
        }
    }

    // every player in the castle looses 2 points if hasn't built
    public void loosePoints(Player player, List<Integer> numOfBuildings) {
        if (numOfBuildings.get(numOfBuildings.size() - 1) == 0) {
            if (player.getPoints() < 2) {
                player.setPoints(0);
            } else {
                player.setPoints(player.getPoints() - penaltyBuildPoints);
            }
            System.out.println(player.getColor() + " looses "
                    + penaltyBuildPoints + " points");
        }
    }

    // build houses in the castle and return true
    public boolean buildSection(Player player) {
        // if dungeon not full or scored

        if (dungeon.getBuildSpaces().size() < 6 && !dungeon.isScored()) {
            // build house
            dungeon.getBuildSpaces().add(player);
            // get points
            player.setPoints(player.getPoints() + dungeon.getBuildPoints());
            System.out.println(player.getColor() + " earns "
                    + dungeon.getBuildPoints() + " points");
            return true;
        } // if walls not full or scored
        else if (walls.getBuildSpaces().size() < 10 && !walls.isScored()) {
            // build house
            walls.getBuildSpaces().add(player);
            // get points
            player.setPoints(player.getPoints() + walls.getBuildPoints());
            System.out.println(player.getColor() + " earns "
                    + walls.getBuildPoints() + " points");
            return true;
        } // if towers not full or scored
        else if (towers.getBuildSpaces().size() < 14 && !towers.isScored()) {
            // build house
            towers.getBuildSpaces().add(player);
            // get points
            player.setPoints(player.getPoints() + towers.getBuildPoints());
            System.out.println(player.getColor() + " earns "
                    + towers.getBuildPoints() + " points");
            return true;
        }
        System.out.println("No space to build in the Castle");
        return false;
    }

    // return 1 food + other resources to pay or null
    public Resources selectResources(Player player, Scanner sc) {
        while (true) {
            Resources payResources = new Resources(1, 0, 0, 0, 0);
            // choose three resources
            for (int i = 0; i < 2; i++) {
                String message = "Select a resource cube"
                        + "\n2)Wood\n3)Stone\n4)Cloth\n5)Gold\n6)Don't build";
                int choice = Functions.inputValidation(2, 6, message, player, sc);
                // if don't build return
                if (choice == 6) {
                    return null;
                } else { // else modify resources
                    payResources.modifyResources(choice);
                }
            } // if valid return resources
            if (validBatch(payResources)) {
                return payResources;
            } else { // else try again
                System.out.print("Invalid payment - Select again");
            }
        }
    }

    // return false if batch is not of 3 different resources including one food
    public boolean validBatch(Resources payResources) {
        return !(payResources.getFood() > 1
                || payResources.getWood() > 1
                || payResources.getStone() > 1
                || payResources.getCloth() > 1
                || payResources.getGold() > 1);
    }

    // returns true if player has 3 different resources including one food
    public boolean hasResourceBatch(Player player) {
        int differentResources = 0;
        if (player.getResources().getFood() > 0) {
            differentResources++;
            if (player.getResources().getWood() > 0) {
                differentResources++;
            }
            if (player.getResources().getStone() > 0) {
                differentResources++;
            }
            if (player.getResources().getCloth() > 0) {
                differentResources++;
            }
            if (player.getResources().getGold() > 0) {
                differentResources++;
            }
        }
        if (differentResources >= 3) {
            return true;
        }
        return false;
    }
}
