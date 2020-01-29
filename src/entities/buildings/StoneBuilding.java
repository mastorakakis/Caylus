package entities.buildings;

import caylus.Game;
import static caylus.Game.WARNING;
import entities.Resources;
import entities.players.Player;
import enums.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class StoneBuilding extends Building {

    private int buildPoints;
    private int buildFavors;
    private Resources buildResources;
    private int activationMoney;
    private int activationPoints;
    private Resources activationResources;
    private Resources activationRentResources;

    // constructor
    public StoneBuilding() {
    }

    // constructor
    public StoneBuilding(int buildPoints, int buildFavors, Resources buildResources,
            int activationMoney, int activationPoints, Resources activationResources,
            Resources activationRentResources, String name) {
        super(name);
        this.buildPoints = buildPoints;
        this.buildFavors = buildFavors;
        this.buildResources = buildResources;
        this.activationMoney = activationMoney;
        this.activationPoints = activationPoints;
        this.activationResources = activationResources;
        this.activationRentResources = activationRentResources;

    }

    // getters setters
    public int getBuildPoints() {
        return buildPoints;
    }

    public void setBuildPoints(int buildPoints) {
        this.buildPoints = buildPoints;
    }

    public Resources getBuildResources() {
        return buildResources;
    }

    public void setBuildResources(Resources buildResources) {
        this.buildResources = buildResources;
    }

    public int getIncomeMoney() {
        return activationMoney;
    }

    public void setActivationMoney(int activationMoney) {
        this.activationMoney = activationMoney;
    }

    public Resources getActivationResources() {
        return activationResources;
    }

    public void setActivationResources(Resources activationResources) {
        this.activationResources = activationResources;
    }

    public int getBuildFavors() {
        return buildFavors;
    }

    public Resources getActivationRentResources() {
        return activationRentResources;
    }

    public void setActivationRentResources(Resources activationRentResources) {
        this.activationRentResources = activationRentResources;
    }

    public void setBuildFavors(int buildFavors) {
        this.buildFavors = buildFavors;
    } // end of getters setters

    @Override
    public Building activate(Game game, List<Player> workers, Scanner sc) {
        Player player = workers.get(0);
        if (this.getName().equals("Park")) {
            player.tradeMoneyResources(activationResources, activationMoney,
                    Action.ADD);
        }
        if (this.getName().equals("Stone Farm")) {
            player.tradeMoneyResources(activationResources, activationMoney,
                    Action.ADD);
        }
        if (this.getName().equals("Workshop")) {
            player.tradeMoneyResources(activationResources, activationMoney,
                    Action.ADD);
        }
        if (this.getName().equals("Bank")) {
            boolean askAgain = false;
            do {
                String message = player.getColor() + " select amount of money "
                        + "to trade\n1)2 deniers\n2)5 deniers\n3)Don't trade";
                int choice = Functions.inputValidation(1, 3, message, WARNING, sc);
                if (choice != 3) {
                    activationMoney = 2;
                    activationResources.modifyResources(5, sc);
                    if (choice == 2) {
                        activationResources.modifyResources(5, sc);
                        activationMoney = 5;
                    }
                    if (player.getMoney() < activationMoney) {
                        System.out.println("Not enough money to trade");
                        askAgain = true;
                    } else {
                        player.tradeMoneyResources(activationResources,
                                activationMoney, Action.ADD);
                        askAgain = false;
                    }
                    this.activationResources = new Resources();
                } else {
                    askAgain = false;
                }
            } while (askAgain == true);
        }
        if (this.getName().equals("Church")) {
            boolean askAgain = false;
            do {
                String message = player.getColor() + " select amount of money "
                        + "to trade\n1)2 deniers\n2)4 deniers\n3)Don't trade";
                int choice = Functions.inputValidation(1, 3, message, WARNING, sc);
                if (choice != 3) {
                    int points = 3;
                    activationMoney = 2;
                    if (choice == 2) {
                        points = 5;
                        activationMoney = 4;
                    }
                    if (player.getMoney() < activationMoney) {
                        System.out.println("Not enough money to trade");
                        askAgain = true;
                    } else {
                        player.setMoney(player.getMoney() - activationMoney);
                        player.setPoints(player.getPoints() + points);
                        askAgain = false;
                    }
                } else {
                    askAgain = false;
                }
            } while (askAgain == true);
        }
        if (this.getName().equals("Alchemist")) {
            boolean askAgain = false;
            do {
                String message = player.getColor() + " select number of resources "
                        + "to trade\n1)2 resources\n2)4 resources\n3)Don't trade";
                int choice = Functions.inputValidation(1, 3, message, WARNING, sc);
                if (choice != 3) {
                    choice = choice == 1 ? 2 : 4;
                    int gold = choice == 2 ? 1 : 2;
                    for (int i = 0; i < choice; i++) {
                        String message2 = player.getColor() + " select one Resource "
                                + "to trade\n1)Food\n2)Wood\n3)Stone\n4)Cloth";
                        int choice2 = Functions.inputValidation(1, 4, message2,
                                WARNING, sc);
                        this.activationResources.modifyResources(choice2, sc);
                    }
                    if (player.getResources().compareTo(activationResources) < 0) {
                        System.out.println("Not enough resources to trade");
                        askAgain = true;
                    } else {
                        player.tradeMoneyResources(activationResources,
                                activationMoney, Action.SUBTRACT);
                        this.activationResources = new Resources();
                        activationResources.modifyResources(5, sc);
                        if (gold == 2) {
                            activationResources.modifyResources(5, sc);
                        }
                        player.tradeMoneyResources(activationResources,
                                activationMoney, Action.ADD);
                        askAgain = false;
                    }
                    this.activationResources = new Resources();
                } else {
                    askAgain = false;
                }
            } while (askAgain == true);
        }
        if (this.getName().equals("Jeweler")) {
            boolean askAgain = false;
            do {
                String message = player.getColor() + " select number of resources "
                        + "to trade\n1)1 Gold\n2)2 Gold\n3)Don't trade";
                int choice = Functions.inputValidation(1, 3, message, WARNING, sc);
                if (choice != 3) {
                    int points = 5;
                    activationResources.modifyResources(5, sc);
                    if (choice == 2) {
                        activationResources.modifyResources(5, sc);
                        points = 9;
                    }
                    if (player.getResources().compareTo(activationResources) < 0) {
                        System.out.println("Not enough resources to trade");
                        askAgain = true;
                    } else {
                        player.tradeMoneyResources(activationResources,
                                activationMoney, Action.SUBTRACT);
                        player.setPoints(player.getPoints() + points);
                        askAgain = false;
                    }
                    this.activationResources = new Resources();
                } else {
                    askAgain = false;
                }
            } while (askAgain == true);
        }
        if (this.getName().equals("Tailor")) {
            boolean askAgain = false;
            do {
                String message = player.getColor() + " select number of resources "
                        + "to trade\n1)2 Cloth\n2)3 Cloth\n3)Don't trade";
                int choice = Functions.inputValidation(1, 3, message, WARNING, sc);
                if (choice != 3) {
                    int points = 4;
                    activationResources.modifyResources(4, sc);
                    activationResources.modifyResources(4, sc);
                    if (choice == 2) {
                        activationResources.modifyResources(4, sc);
                        points = 6;
                    }
                    if (player.getResources().compareTo(activationResources) < 0) {
                        System.out.println("Not enough resources to trade");
                        askAgain = true;
                    } else {
                        player.tradeMoneyResources(activationResources,
                                activationMoney, Action.SUBTRACT);
                        player.setPoints(player.getPoints() + points);
                        askAgain = false;
                    }
                    this.activationResources = new Resources();
                } else {
                    askAgain = false;
                }
            } while (askAgain == true);
        }
        if (this.getName().equals("Architect A")) {
            List<PrestigeBuilding> buildings = new ArrayList();
            for (Building building : game.getBuildingList()) {
                if (building instanceof PrestigeBuilding) {
                    buildings.add((PrestigeBuilding) building);
                }
            }
            return player.buildPrestige(game, buildings, sc);

        }
        if (this.getName().equals("Architect B")) {
            List<PrestigeBuilding> buildings = new ArrayList();
            for (Building building : game.getBuildingList()) {
                if (building instanceof PrestigeBuilding) {
                    buildings.add((PrestigeBuilding) building);
                }
            }
            return player.buildPrestige(game, buildings, sc);
        }
        return null;
    }

}
