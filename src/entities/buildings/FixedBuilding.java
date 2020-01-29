package entities.buildings;

import caylus.Game;
import static caylus.Game.WARNING;
import interfaces.BoardBulding;
import entities.Resources;
import entities.players.Player;
import enums.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class FixedBuilding extends Building implements BoardBulding {

    private int activationMoney;
    private Resources activationResources;

    // constructor
    public FixedBuilding() {
    }

    // constructor
    public FixedBuilding(int activationMoney, Resources activationResources,
            String name) {
        super(name);
        this.activationMoney = activationMoney;
        this.activationResources = activationResources;
    }

    // getters setters
    public int getIncomeMoney() {
        return activationMoney;
    }

    public void setActivationMoney(int activationMoney) {
        if (activationMoney < 0) {
            throw new IllegalArgumentException("Amount of money cannot be negative");
        }
        this.activationMoney = activationMoney;
    }

    public Resources getActivationResources() {
        return activationResources;
    }

    public void setActivationResources(Resources activationResources) {
        this.activationResources = activationResources;
    } //  end of getters setters

    @Override
    public Building activate(Game game, List<Player> workers, Scanner sc) {
        Player player = workers.get(0);
        if (this.getName().equals("Fixed Peddler")) {
            if (player.getMoney() < activationMoney) {
                System.out.println("Not enough money to trade");
                return null;
            } else {
                String message = player.getColor()
                        + " select one Resource to collect\n"
                        + "1)Food\n2)Wood\n3)Stone\n4)Cloth";
                int choice = Functions.inputValidation(1, 4, message,
                        WARNING, sc);
                activationResources.modifyResources(choice, sc);
            }
            player.tradeMoneyResources(activationResources,
                    activationMoney, Action.ADD);
            this.activationResources = new Resources();
            return null;
        } else if (this.getName().equals("Fixed Carpenter")) {
            List<WoodBuilding> woodList = new ArrayList();
            for (Building building : game.getBuildingList()) {
                if (building instanceof WoodBuilding) {
                    woodList.add((WoodBuilding) building);
                }
            }
            return player.buildWood(woodList, sc);

        } // if gold mine
        else {
            // collect resources
            player.tradeMoneyResources(activationResources, activationMoney, Action.ADD);
        }
        return null;
    }

}
