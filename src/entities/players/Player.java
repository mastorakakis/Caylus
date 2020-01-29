package entities.players;

import caylus.Game;
import entities.Block;
import entities.Resources;
import entities.buildings.Building;
import entities.buildings.NeutralBuilding;
import entities.buildings.PrestigeBuilding;
import entities.buildings.ResidentialBuilding;
import entities.buildings.StoneBuilding;
import entities.buildings.WoodBuilding;
import enums.Color;
import enums.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public abstract class Player {

    private Color color;
    private int points;
    private int money; // players initial amount depends on order of play
    private Resources resources = new Resources(2, 1, 0, 0, 0); // start with 2 food 1 wood
    private int favors;
    private int workers = 6; // player has six workers;

    // constructor
    public Player(Color color) {
        this.color = color;
    }

    // getters setters
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getMoney() {
        return money;
    }

//    public void setResources(Resources resources) {
//        this.resources = resources;
//    }
    public int getFavors() {
        return favors;
    }

    public void setFavors(int favors) {
        this.favors = favors;
    }

    public void setMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Amount of money cannot be negative");
        }
        this.money = money;
    }

    public Resources getResources() {
        return resources;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
        this.points = points;
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        if (workers < 0 || workers > 6) {
            throw new IllegalArgumentException("Workers must be 0-6");
        }
        this.workers = workers;
    }// end of getters setters

    // build wood, collect resources & money, restore resources
    public Building buildWood(List<WoodBuilding> buildings, Scanner sc) {
        int choice = 0;
        do {
            String message = this.color + "select building\n"
                    + Functions.printOptions(buildings);
            int max = buildings.size() + 1;
            choice = Functions.inputValidation(1, max, message, Game.WARNING, sc);
            // if don't build
            if (choice == max) {
                return null;
            } // if "market place" or "peddler" and prompt
            else if (buildings.get(choice - 1).getName().equals("Wood Market Place")
                    || buildings.get(choice - 1).getName().equals("Wood Peddler")) {
                // ask user for resource of choice to spend
                int choice2 = Functions.inputValidation(1, 5, "Building a "
                        + buildings.get(choice - 1).getName()
                        + " costs 1 wood plus 1 resource of choice\n"
                        + "1)Food\n2Wood\n3)Stone\n4)Cloth\n5)Gold",
                        "Invalid input.", sc);
                // increase selected building resource cost
                buildings.get(choice - 1).getBuildResources().modifyResources(choice2, sc);
            }// check if resources are enough
            if (resources.compareTo(buildings.get(choice - 1).getBuildResources()) < 0) {
                System.out.println("Not enough resources to build.");
                choice = 0;
                // TODO continue;
            } else {// pay resources
                tradeMoneyResources(buildings.get(choice - 1).getBuildResources(), 0,
                        Action.SUBTRACT);
                // get the pointss
                this.points += buildings.get(choice - 1).getBuildPoints();
            }// restore building resources cost if changed
            buildings.get(choice - 1).setBuildResources(new Resources(0, 1, 0, 0, 0));
        } while (choice == 0);
        return buildings.get(choice - 1);
    }

    // build stone, select resources & money & favors, restore resources
    public Building buildStone(List<StoneBuilding> buildings, Scanner sc) {
        int choice = 0;
        do {
            String message = this.color + "select building\n"
                    + Functions.printOptions(buildings);
            int max = buildings.size() + 1;
            choice = Functions.inputValidation(1, max, message, Game.WARNING, sc);
            // if don't build
            if (choice == max) {
                return null;
            }
            // check if resources are enough
            if (resources.compareTo(buildings.get(choice - 1).getBuildResources()) < 0) {
                System.out.println("Not enough resources to build.");
                choice = 0;
            } else {
                tradeMoneyResources(buildings.get(choice - 1).getBuildResources(), 0,
                        Action.SUBTRACT); // pay resources
                // get points and favors
                this.points += buildings.get(choice - 1).getBuildPoints();
                this.favors += buildings.get(choice - 1).getBuildFavors();
            }
        } while (choice == 0);
        return buildings.get(choice - 1);
    }

    public Building buildResidential(Game game, ResidentialBuilding building, Scanner sc) {
        List<Integer> availableBuildingsList = getAvailableBuildings(game.getRoad());
        String message = Functions.printIndexedOptions(availableBuildingsList,
                game.getRoad());
        int max = availableBuildingsList.size() + 1; // plus one for pass
        int choice = Functions.inputValidation(1, max, color
                + " select building or pass\n" + message, Game.WARNING, sc);
        if (choice != max) {
            Block block = game.getRoad()
                    .get(availableBuildingsList.get(choice - 1));
            if (block.getWorkers() != null) {
                block.setTempBuilding(building);
            } else {
                block.setBuilding(building);
            }
        } else {
            tradeMoneyResources(resources,
                    -building.getBuildMoney(), Action.ADD);
            setPoints(points - building.getBuildPoints());
            return null;
        }
        return building;
    }

    public Building buildPrestige(Game game, List<PrestigeBuilding> buildings, Scanner sc) {
        int choice = 0;
        do {
            String message = this.color + "select Prestige building\n"
                    + Functions.printOptions(buildings);
            int max = buildings.size() + 1;
            choice = Functions.inputValidation(1, max, message, Game.WARNING, sc);
            // if don't build
            if (choice == max) {
                return null;
            }
            // check if resources are enough
            if (resources.compareTo(buildings.get(choice - 1).getBuildResources()) < 0) {
                System.out.println("Not enough resources to build.");
                choice = 0;
            } else {
                tradeMoneyResources(buildings.get(choice - 1).getBuildResources(), 0,
                        Action.SUBTRACT); // pay resources
                // get points and favors
                this.points += buildings.get(choice - 1).getBuildPoints();
                this.favors += buildings.get(choice - 1).getBuildFavors();
            }
        } while (choice == 0);
        PrestigeBuilding prestigeBuilding = buildings.get(choice - 1);

        List<Integer> availableBuildingsList
                = getAvailableResidentialBuildings(game.getRoad());
        String message = Functions.printIndexedOptions(availableBuildingsList,
                game.getRoad());
        int max = availableBuildingsList.size() + 1; // plus one for pass
        int choice2 = Functions.inputValidation(1, max, color
                + " select building or pass\n" + message, Game.WARNING, sc);
        if (choice2 != max) {
            Block block = game.getRoad()
                    .get(availableBuildingsList.get(choice2 - 1));
            block.setBuilding(prestigeBuilding);
        } else {
            tradeMoneyResources(prestigeBuilding.getBuildResources(), 0, Action.ADD); // pay resources
            // get points and favors
            this.points -= prestigeBuilding.getBuildPoints();
            this.favors -= prestigeBuilding.getBuildFavors();
            return null;
        }
        return prestigeBuilding;
    }

    // return indexes of available buildings for transformation
    public List<Integer> getAvailableBuildings(List<Block> road) {
        List<Integer> indexList = new ArrayList();
        for (int i = 7; i < road.size(); i++) {
            Block block = road.get(i);
            // if building neutral or craft not Lawyer and belongs to player
            if (!road.get(i).getBuilding().getName().equals("Lawyer")
                    && road.get(i).getBuilding() instanceof NeutralBuilding
                    || ((road.get(i).getBuilding() instanceof WoodBuilding
                    || road.get(i).getBuilding() instanceof StoneBuilding)
                    && (block.getHouse() == this))) {
                indexList.add(i);
            }
        }
        return indexList;
    }

    public List<Integer> getAvailableResidentialBuildings(List<Block> road) {
        List<Integer> indexList = new ArrayList();
        for (int i = 7; i < road.size(); i++) {
            Block block = road.get(i);
            if (road.get(i).getBuilding() instanceof ResidentialBuilding) {
                indexList.add(i);
            }
        }
        return indexList;
    }

    // ADD = collect resources from production by adding one by one / pay money
    // SUB = pay resources / collect money
    public void tradeMoneyResources(Resources resources, int money, Action s) {
        int modifier = (s == Action.ADD ? 1 : -1);
        this.resources.setFood(this.resources.getFood() + modifier * resources.getFood());
        this.resources.setWood(this.resources.getWood() + modifier * resources.getWood());
        this.resources.setStone(this.resources.getStone() + modifier * resources.getStone());
        this.resources.setCloth(this.resources.getCloth() + modifier * resources.getCloth());
        this.resources.setGold(this.resources.getGold() + modifier * resources.getGold());
        this.money += -1 * modifier * money;
    }

    @Override
    public String toString() {
        return "Player " + color + ":\n\tPoints=" + points + " Money=" + money
                + " Favors=" + favors + " Workers=" + workers + "\n\t"
                + resources;
    }
}
