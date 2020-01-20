package entities.buildings;

import entities.players.Player;
import java.util.Scanner;
import otherClasses.Color;
import otherClasses.Resources;
import otherClasses.SelectAction;
import utilities.Functions;

public enum WoodBuilding implements Building {
    FARM_A(2, new Resources(1, 1, 0, 0, 0), new Resources(0, 0, 0, 0, 0), 0), // 2 food or 1 cloth
    FARM_B(2, new Resources(1, 1, 0, 0, 0), new Resources(0, 0, 0, 0, 0), 0), // 2 cloth or 1 food
    MARKET_PLACE(4, new Resources(0, 1, 0, 0, 0), new Resources(0, 0, 0, 0, 0), 6), // plus resource of choice cost
    PEDDLER(4, new Resources(0, 1, 0, 0, 0), new Resources(0, 0, 0, 0, 0), 0), // plus resource of choice cost
    QUARRY(2, new Resources(1, 1, 0, 0, 0), new Resources(0, 0, 2, 0, 0), 0),
    SAWMILL(2, new Resources(1, 1, 0, 0, 0), new Resources(0, 2, 0, 0, 0), 0),
    MASON(4, new Resources(1, 1, 0, 0, 0), new Resources(0, 0, 0, 0, 0), 0),
    LAWYER(4, new Resources(0, 1, 0, 1, 0), new Resources(0, 0, 0, 0, 0), 0);

    private final int points;
    private Resources costResources;
    private Resources productionResources; //    private final Resources resIncome;
    private int productionMoney;
    private int costMoney;
    private Player player;
    private Color house;

    // constructor
    private WoodBuilding(int points, Resources costResources, Resources productionResources, int money) {
        this.points = points;
        this.costResources = costResources;
        this.productionResources = productionResources;
        this.productionMoney = money;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public Resources getCostResources() {
        return costResources;
    }

    @Override
    public Resources getProductionResources() {
        return productionResources;
    }

    // building construction
    public void activation(String s) {
    }

    // trade
    public void activation(Resources r, int money) {
    }

    // resource production for farms
    @Override
    public void activation(Player player, Scanner sc) {
        int choice = 0;
        if (this == FARM_A) {
            choice = Functions.inputValidation(1, 2, "Select production\n"
                    + "1. 1 Cloth 2. 2 Food ", "Invalid input.", sc);
            switch (choice) {
                case 1:
                    productionResources.setCloth(1);
                    productionResources.setFood(0);
                    break;
                case 2:
                    productionResources.setFood(2);
                    productionResources.setCloth(0);
                    break;
            }
        }
        if (this == FARM_B) {
            choice = Functions.inputValidation(1, 2, "Select production\n"
                    + "1. 1 Food 2. 2 Cloth ", "Invalid input.", sc);
            switch (choice) {
                case 1:
                    productionResources.setFood(1);
                    productionResources.setCloth(0);
                    break;
                case 2:
                    productionResources.setCloth(2);
                    productionResources.setFood(0);
                    break;
            }
        }
        if (this == MARKET_PLACE) {
            costResources.modifyResources(SelectAction.SUBTRACT, 2, sc);
            choice = Functions.inputValidation(1, 4, "Select one resource to trade\n"
                    + "1.Food 2.Wood 3.Stone 4.Cloth", "Invalid input.", sc);
            costResources.modifyResources(SelectAction.ADD, choice, sc);
            if (player.getResources().compareTo(costResources) < 0) {
                System.out.println("Not enough resources to build.");
            } else {
                player.payResources(costResources);
                player.selectFromBuilding(productionResources, productionMoney);
            }
            costResources.modifyResources(SelectAction.SUBTRACT, choice, sc);
            costResources.modifyResources(SelectAction.ADD, 2, sc);
            return;
        }
        player.selectFromBuilding(productionResources, productionMoney);
    }
    //    TODO  construction method, getters setters, comments, check building values with cards
}
