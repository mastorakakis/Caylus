package entities.buildings;

import caylus.Game;
import entities.Resources;
import entities.players.Player;
import enums.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class WoodBuilding extends Building {

    private int buildPoints;
    private Resources buildResources;
    private int activationMoney;
    private Resources activationResources;

    public WoodBuilding() {
    }

    public WoodBuilding(int buildPoints, Resources buildResources,
            int activationMoney, Resources activationResources, String name) {
        super(name);
        this.buildPoints = buildPoints;
        this.buildResources = buildResources;
        this.activationMoney = activationMoney;
        this.activationResources = activationResources;
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
    } // end of getters setters

    @Override
    public Building activate(Game game, List<Player> workers, Scanner sc) {
        Player player = workers.get(0);
        // if wood quarry
        if (this.getName().equals("Wood Quarry")) {
            System.out.println("\nActivating Wood Quarry");
            player.tradeMoneyResources(activationResources, activationMoney,
                    Action.ADD); // collect resources
        } // if wood farm A
        else if (this.getName().equals("Wood Farm A")) {
            System.out.println("\nActivating Farm A");
            // choose resource
            String message = player.getColor()
                    + " select resources\n1)2 Food\n2)1 Cloth";
            int choice = Functions.inputValidation(1, 2, message, player, sc);
            choice = choice == 2 ? 4 : 1;
            activationResources.modifyResources(choice);
            if (choice == 1) { // if choice 1 add one more resource
                activationResources.modifyResources(choice);
            }// treade resources
            player.tradeMoneyResources(activationResources, activationMoney,
                    Action.ADD);
            this.activationResources = new Resources();
        } // if wood farm B
        else if (this.getName().equals("Wood Farm B")) {
            System.out.println("\nActivating Wood Farm B");
            String message = player.getColor()
                    + " select resources\n1)1 Food\n2)2 Cloth";
            int choice = Functions.inputValidation(1, 2, message, player, sc);
            choice = choice == 2 ? 4 : 1;
            activationResources.modifyResources(choice);
            if (choice == 4) {
                activationResources.modifyResources(choice);
            }
            player.tradeMoneyResources(activationResources, activationMoney,
                    Action.ADD);
            this.activationResources = new Resources();
        } // if wood market place
        else if (this.getName().equals("Wood Market Place")) {
            System.out.println("\nActivating Wood Market Place");
            do { // choose resource
                String message = player.getColor() + " select one Resource to trade\n"
                        + "1)Food\n2)Wood\n3)Stone\n4)Cloth\n5)Don't trade";
                int choice = Functions.inputValidation(1, 5, message, player, sc);
                if (choice != 5) { // if have enough resources
                    this.activationResources.modifyResources(choice);
                    if (player.getResources().compareTo(this.activationResources) < 0) {
                        System.out.println("Not enough resources to trade");
                        this.activationResources = new Resources();
                        continue;
                    }
                    player.tradeMoneyResources(this.activationResources,
                            activationMoney, Action.SUBTRACT);
                    this.activationResources = new Resources();
                    break;
                } // if don't trade
                break;
            } while (true);
        } // if wood peddler
        else if (this.getName().equals("Wood Peddler")) {
            System.out.println("\nActivating Wood Peddler");
            do { // choose amount
                String message = player.getColor() + " select amount of money "
                        + "to spend\n1)1 denier\n2)2 deniers\n3)Don't trade";
                int choice = Functions.inputValidation(1, 3, message, player, sc);
                if (choice != 3) {
                    activationMoney = choice;
                    if (player.getMoney() < activationMoney) {
                        System.out.println("Not enough money to trade");
                        continue;
                    } else {
                        for (int i = 0; i < choice; i++) {
                            String message2 = player.getColor()
                                    + " select one Resource to collect\n"
                                    + "1)Food\n2)Wood\n3)Stone\n4)Cloth";
                            int choice2 = Functions.inputValidation(1, 4, message2,
                                    player, sc);
                            activationResources.modifyResources(choice2);
                        }
                        player.tradeMoneyResources(activationResources,
                                activationMoney, Action.ADD);
                        this.activationResources = new Resources();
                    }
                }
                break;
            } while (true);
        } // if wood sawmill
        else if (this.getName().equals("Wood Sawmill")) {
            System.out.println("\nActivating Wood Sawmill");
            player.tradeMoneyResources(activationResources, activationMoney,
                    Action.ADD);
        } // if mason
        else if (this.getName().equals("Mason")) {
            System.out.println("\nActivating Mason");
            List<StoneBuilding> stoneList = new ArrayList();
            for (Building building : game.getBuildingList()) {
                if (building instanceof StoneBuilding) {
                    stoneList.add((StoneBuilding) building);
                }
            }
            player.setWorkers(player.getWorkers() + 1);
            System.out.println(player.getColor() + " Worker=" + player.getWorkers());
            return player.buildStone(game, stoneList, sc);
        } // if lawyer
        else if (this.getName().equals("Lawyer")) {
            System.out.println("\nActivating Lawyer");
            // create residential building
            ResidentialBuilding building = new ResidentialBuilding();
            player.setWorkers(player.getWorkers() + 1);
            System.out.println(player.getColor() + " Worker=" + player.getWorkers());
            // build
            return player.buildResidential(game, building, sc);
        }
        player.setWorkers(player.getWorkers() + 1);
        System.out.println(player.getColor() + " Worker=" + player.getWorkers());
        return null;
    }

}
