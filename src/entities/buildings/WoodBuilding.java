package entities.buildings;

import caylus.Game;
import static caylus.Game.WARNING;
import entities.Resources;
import entities.players.Player;
import enums.SelectAction;
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
        if (this.getName().equals("Wood Quarry")) {
            player.collectFromBuilding(activationResources, activationMoney,
                    SelectAction.ADD);
        }
        if (this.getName().equals("Wood Farm A")) {
            String message = player.getColor()
                    + " select resources\n1)2 Food\n2)1 Cloth";
            int choice = Functions.inputValidation(1, 2, message, WARNING, sc);
            choice = choice == 2 ? 4 : 1;
            activationResources.modifyResources(choice, sc);
            if (choice == 1) {
                activationResources.modifyResources(choice, sc);
            }
            player.collectFromBuilding(activationResources, activationMoney,
                    SelectAction.ADD);
            this.activationResources = new Resources();
        }
        if (this.getName().equals("Wood Farm B")) {
            String message = player.getColor()
                    + " select resources\n1)1 Food\n2)2 Cloth";
            int choice = Functions.inputValidation(1, 2, message, WARNING, sc);
            choice = choice == 2 ? 4 : 1;
            activationResources.modifyResources(choice, sc);
            if (choice == 4) {
                activationResources.modifyResources(choice, sc);
            }
            player.collectFromBuilding(activationResources, activationMoney,
                    SelectAction.ADD);
            this.activationResources = new Resources();
        }
        if (this.getName().equals("Wood Market Place")) {
            boolean askAgain = false;
            do {
                String message = player.getColor() + " select one Resource to trade\n"
                        + "1)Food\n2)Wood\n3)Stone\n4)Cloth\n5)Don't trade";
                int choice = Functions.inputValidation(1, 5, message, WARNING, sc);
                if (choice != 5) {
                    this.activationResources.modifyResources(choice, sc);
                    if (player.getResources().compareTo(this.activationResources) < 0) {
                        System.out.println("Not enough resources to trade");
                        askAgain = true;
                    } else {
                        player.collectFromBuilding(this.activationResources,
                                activationMoney, SelectAction.SUBTRACT);
                        askAgain = false;
                    }
                    this.activationResources = new Resources();
                } else {
                    askAgain = false;
                }
            } while (askAgain == true);
        }
        if (this.getName().equals("Peddler")) {
            boolean askAgain = false;
            do {
                String message = player.getColor() + " select how amount of money to spend\n"
                        + "1)1 denier\n2)2 deniers\n3)Don't trade";
                int choice = Functions.inputValidation(1, 3, message, WARNING, sc);
                if (choice != 3) {
                    activationMoney = choice;
                    if (player.getMoney() < activationMoney) {
                        System.out.println("Not enough money to trade");
                        askAgain = true;
                    } else {
                        for (int i = 0; i < choice; i++) {
                            String message2 = player.getColor()
                                    + " select one Resource to trade\n"
                                    + "1)Food\n2)Wood\n3)Stone\n4)Cloth";
                            int choice2 = Functions.inputValidation(1, 4, message2,
                                    WARNING, sc);
                            activationResources.modifyResources(choice2, sc);
                        }
                        player.collectFromBuilding(activationResources,
                                activationMoney, SelectAction.ADD);
                        this.activationResources = new Resources();
                        askAgain = false;
                    }
                } else {
                    askAgain = false;
                }
            } while (askAgain == true);
        }

        if (this.getName().equals("Wood Sawmill")) {
            player.collectFromBuilding(activationResources, activationMoney,
                    SelectAction.ADD);
        }
        if (this.getName().equals("Mason")) {
//TODO
        }
        if (this.getName().equals("Lawyer")) {
//TODO
        }
        return this;
    }

}
