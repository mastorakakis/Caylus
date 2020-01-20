package entities.players;

import entities.buildings.Building;
import entities.buildings.WoodBuilding;
import java.util.Scanner;
import otherClasses.Resources;
import otherClasses.Color;
import otherClasses.SelectAction;
import utilities.Functions;

public abstract class Player {

    private Color color;
    private int points = 0; // player begins with zero points
    private int money; // players initial amount depends on order of play
    private Resources resources = new Resources(2, 1, 0, 0, 0); // player starts with two food cubes and one wood
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

    public void setMoney(int money) {
        if (money <= 0) {
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

    // get points for building
    public void build(Building b, Scanner sc) {
        int choice = 0;
        // check for "market place" or "peddler" and prompt
        if (b == WoodBuilding.MARKET_PLACE || b == WoodBuilding.PEDDLER) {
            // ask user for resource of choice to spend
            choice = Functions.inputValidation(1, 5, "Building a " + b
                    + " costs 1 wood plus 1 resource of choice\n"
                    + "1.Food 2.Wood 3.Stone 4.Cloth 5.Gold", "Invalid input.", sc);
            // increase selected building resource cost
            b.getCostResources().modifyResources(SelectAction.ADD, choice, sc);
        }
        // check if resources are enough
        if (resources.compareTo(b.getCostResources()) < 0) {
            System.out.println("Not enough resources to build.");
        } else {
            // pay resources
            payResources(b.getCostResources());
            this.points += b.getPoints();
        }
        // restore building resources cost if changed
        if (choice != 0) {
            b.getCostResources().modifyResources(SelectAction.SUBTRACT, choice, sc);
        }

    }

    // pay by subtracting resources one by one
    public void payResources(Resources costResources) {
        resources.setFood(resources.getFood() - costResources.getFood());
        resources.setWood(resources.getWood() - costResources.getWood());
        resources.setStone(resources.getStone() - costResources.getStone());
        resources.setCloth(resources.getCloth() - costResources.getCloth());
        resources.setGold(resources.getGold() - costResources.getGold());
    }

    // select resources by adding one by one
    public void selectFromBuilding(Resources productionResources, int productionMoney) {
        resources.setFood(resources.getFood() + productionResources.getFood());
        resources.setWood(resources.getWood() + productionResources.getWood());
        resources.setStone(resources.getStone() + productionResources.getStone());
        resources.setCloth(resources.getCloth() + productionResources.getCloth());
        resources.setGold(resources.getGold() + productionResources.getGold());
        money += productionMoney;
    }

    @Override
    public String toString() {
        return "Player " + color + ": Points=" + points + " Money=" + money + " "
                + resources + " Workers=" + workers;
    }
    //TODO select resource for market place & peddler, toString()
}
