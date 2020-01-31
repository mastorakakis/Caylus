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

// Singleton Pattern
public class Castle extends Building implements Serializable {

    private static Castle castleInstance = new Castle("Castle");
    private Section dungeon = new Section("Dungeon", new ArrayList(), 5);
    private Section walls = new Section("Walls", new ArrayList(), 4);
    private Section towers = new Section("Towers", new ArrayList(), 3);
    private int buildFavor = 1;
    private int penaltyPoints = 2;

    // constructor private
    private Castle(String name) {
        super(name);
    }

    // getters setters
    public static Castle getCastleInstance() {
        return castleInstance;
    }

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
            for (int i = 0; i < castlePlayers.size(); i++) {
                Player player = castlePlayers.get(i);
                numberOfBuildings.add(0);
                System.out.println(player.getColor() + " building of the Castle costs "
                        + "three different resources including one food");
                while (true) {
                    // if player has 3 different resources
                    if (hasResourceBatch(player)) {
                        // gather resources
                        Resources payResources = selectResources(player, sc);
                        if (payResources == null) {
                            break;
                        } // if not enough resources
                        if (player.getResources().compareTo(payResources) < 0) {
                            System.out.println("Not enough resources");
                            continue;
                        } // pay
                        player.tradeMoneyResources(payResources, 0, Action.SUBTRACT);
                        // build
                        buildSection(player);
                        // increase number of buildings for player
                        int number = numberOfBuildings.get(i);
                        numberOfBuildings.set(i, ++number);
                        dungeon.setScored(true); // TODO check for scoring;
                        // ask to build more
                        String message = player.getColor() + " build another?\n"
                                + "1)Yes\t2)No:";
                        int choice = Functions.inputValidation(1, 2, message,
                                player, sc);
                        if (choice == 2) {
                            break;
                        }
                    } else {// if not enough resources return
                        System.out.println("Not enough resources");
                        break;
                    }
                } // end of while
                // return worker
                player.setWorkers(player.getWorkers() + 1);
            } // end of for
            earnFavor(castlePlayers, numberOfBuildings);
            loosPoints(castlePlayers, numberOfBuildings);
        }
        return null;
    }

    // first player with most buildings wins a favor
    public void earnFavor(List<Player> castlePlayers, List<Integer> numOfBuildings) {
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
            System.out.println(maxPlayer.getColor() + " earns " + buildFavor
                    + " favor");
        }
    }

    public void loosPoints(List<Player> castlePlayers, List<Integer> numOfBuildings) {
        for (int i = 0; i < castlePlayers.size(); i++) {
            if (numOfBuildings.get(i) == 0) {
                Player player = castlePlayers.get(i);
                if (player.getPoints() < 2) {
                    player.setPoints(0);
                } else {
                    player.setPoints(player.getPoints() - penaltyPoints);
                    System.out.println(player.getColor() + " looses " + penaltyPoints
                            + " points");
                }
            }
        }
    }

    // build houses in the castle
    public void buildSection(Player player) {
        if (!dungeon.isScored()) { // if dungeon hasn't scored
            // build house
            dungeon.getBuildSpaces().add(player);
            // get points
            player.setPoints(player.getPoints() + dungeon.getBuildPoints());
        } else if (!walls.isScored()) { // if walls haven't scored
            // build house
            walls.getBuildSpaces().add(player);
            // get points
            player.setPoints(player.getPoints() + walls.getBuildPoints());
        } // if towers haven't scored
        else { // build house
            towers.getBuildSpaces().add(player);
            // get points
            player.setPoints(player.getPoints() + towers.getBuildPoints());
        }
    }

    // check if section is scored
    public boolean checkScoring(Game game, Section section) {
        if (section == dungeon) {
            if (game.getBailiff().getPosition() >= game.DUNGEON_SCORING
                    || dungeon.getBuildSpaces().size() == 6) {
                dungeon.setScored(true);
            }
        }
        if (section == walls) {
            if (game.getBailiff().getPosition() >= game.WALLS_SCORING
                    || walls.getBuildSpaces().size() == 10) {
                walls.setScored(true);
            }
        }
        if (section == towers) {
            if (game.getBailiff().getPosition() >= game.TOWERS_SCORING
                    || towers.getBuildSpaces().size() == 14) {
                towers.setScored(true);
            }
        }
        return false;

    }

    // return resources to pay or null
    public Resources selectResources(Player player, Scanner sc) {
        while (true) {
            Resources payResources = new Resources(1, 0, 0, 0, 0);
            // choose three resources
            for (int i = 0; i < 2; i++) {
                String message = "Select a resource"
                        + "\n2)Wood\n3)Stone\n4)Cloth\n5)Gold\n6)Don't build";
                int choice = Functions.inputValidation(2, 6, message, player, sc);
                // if don't build return
                if (choice == 6) {
                    return null;
                } else { // else modify resources
                    payResources.modifyResources(choice, sc);
                }
            } // if valid return resources
            if (validBatch(payResources)) {
                return payResources;
            } else { // else try again
                System.out.println("Invalid payment");
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
