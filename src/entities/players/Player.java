package entities.players;

import entities.Resources;
import enums.Color;
import enums.SelectAction;

public abstract class Player {

    private Color color;
    private int points;
    private int money; // players initial amount depends on order of play
    private Resources resources = new Resources(2, 1, 0, 0, 0); // player starts with two food cubes and one wood
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

    // cast building to the appropriate type to build
//    public void build(Building b, Scanner sc) {
//        if (b instanceof WoodBuilding) {
//            buildWood((WoodBuilding) b, sc);
//        } else if (b instanceof StoneBuilding) {
//            buildStone((StoneBuilding) b, sc);
//        }
//    }
    // build wood, select resources & money, restore resources
//    public void buildWood(WoodBuilding b, Scanner sc) {
//        int choice = 0;
//        if (b == WoodBuilding.MARKET_PLACE || b == WoodBuilding.PEDDLER) { // check for "market place" or "peddler" and prompt
//            choice = Functions.inputValidation(1, 5, "Building a " + b // ask user for resource of choice to spend
//                    + " costs 1 wood plus 1 resource of choice\n"
//                    + "1)Food\n2Wood\n3)Stone\n4)Cloth\n5)Gold", "Invalid input.", sc);
//            b.getResources().modifyResources(SelectAction.ADD, choice, sc); // increase selected building resource cost
//        }
//        if (resources.compareTo(b.getResources()) < 0) { // check if resources are enough
//            System.out.println("Not enough resources to build.");
//            choice = 0;
//        } else {
//            payResources(b.getResources()); // pay resources
//            this.points += b.getPoints(); // get the points
//        }
//        if (choice != 0) { // restore building resources cost if changed
//            b.getResources().modifyResources(SelectAction.SUBTRACT, choice, sc);
//        }
// build wood, select resources & money & favors, restore resources
//    public void buildStone(StoneBuilding b, Scanner sc) {
//        if (resources.compareTo(b.getResources()) < 0) { // check if resources are enough
//            System.out.println("Not enough resources to build.");
//        } else {
//            payResources(b.getResources()); // pay resources
//            this.points += b.getPoints(); // get the points
//            this.favors += b.getFavors(); // get the favors
//        }
//    }
    // pay by subtracting resources one by one
    public void payResources(Resources resources) {
        this.resources.setFood(this.resources.getFood() - resources.getFood());
        this.resources.setWood(this.resources.getWood() - resources.getWood());
        this.resources.setStone(this.resources.getStone() - resources.getStone());
        this.resources.setCloth(this.resources.getCloth() - resources.getCloth());
        this.resources.setGold(this.resources.getGold() - resources.getGold());
    }

    // select resources from production by adding one by one
    public void collectFromBuilding(Resources resources, int money, SelectAction s) {
        int modifier = (s == SelectAction.ADD ? 1 : -1);
        int number = modifier * resources.getGold(); // TODO delete
        this.resources.setFood(this.resources.getFood() + modifier * resources.getFood());
        this.resources.setWood(this.resources.getWood() + modifier * resources.getWood());
        this.resources.setStone(this.resources.getStone() + modifier * resources.getStone());
        this.resources.setCloth(this.resources.getCloth() + modifier * resources.getCloth());
        this.resources.setGold(this.resources.getGold() + modifier * resources.getGold());
        int amount = -1 * modifier * money; //TODO delete
        this.money += -1 * modifier * money;
    }

    @Override
    public String toString() {
        return "Player " + color + ": Points=" + points + " Money=" + money
                + " Favors=" + favors + " Workers=" + workers + "\n\t"
                + resources;
    }
//TODO maybe replace select resources with setResources
}
