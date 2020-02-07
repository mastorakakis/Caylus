package entities.buildings;

import caylus.Game;
import entities.Resources;
import entities.players.Player;
import enums.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class NeutralBuilding extends Building {

    private int activationMoney;
    private Resources activationResources;

    // constructor
    public NeutralBuilding() {
    }

    // constructor
    public NeutralBuilding(int activationMoney, Resources activationResources, String name) {
        super(name);
        this.activationMoney = activationMoney;
        this.activationResources = activationResources;
    }

    @Override
    public Building activate(Game game, List<Player> workers, Scanner sc) {
        Player player = workers.get(0);
        // if neutral quarry
        if (this.getName().equals("Neutral Quarry")) {
            System.out.println("\nActivating Neutral Quarry");
            // get resources
            player.tradeMoneyResources(activationResources, activationMoney,
                    Action.ADD);
        } // if neutral sawmill
        else if (this.getName().equals("Neutral Sawmill")) {
            System.out.println("\nActivating Neutral Sawmill");
            // collect resource
            player.tradeMoneyResources(activationResources, activationMoney,
                    Action.ADD);
        } // if neutral farm
        else if (this.getName().equals("Neutral Farm")) {
            System.out.println("\nActivating Neutral Farm");
            String message = player.getColor()
                    + " select resource\n1)1 Food\n2)1 Cloth";
            // choose resources
            int choice = Functions.inputValidation(1, 2, message, player, sc);
            choice = choice == 2 ? 4 : 1;
            activationResources.modifyResources(choice);
            // collect chosen resources
            player.tradeMoneyResources(activationResources, activationMoney,
                    Action.ADD);
            // reset building resources
            this.activationResources = new Resources();
        } // if forest
        else if (this.getName().equals("Forest")) {
            System.out.println("\nActivating Forest");
            // choose resource
            String message = player.getColor()
                    + " select resource\n1)1 Food\n2)1 Wood";
            int choice = Functions.inputValidation(1, 2, message, player, sc);
            activationResources.modifyResources(choice);
            // collect resource
            player.tradeMoneyResources(activationResources, activationMoney,
                    Action.ADD);
            // reset building resources
            this.activationResources = new Resources();
        } // if neutral market place
        else if (this.getName().equals("Neutral Market Place")) {
            System.out.println("\nActivating Neutral Market Place");
            do { // choose resource to trade
                String message = player.getColor() + " select one Resource to trade\n"
                        + "1)Food\n2)Wood\n3)Stone\n4)Cloth\n5)Don't trade";
                int choice = Functions.inputValidation(1, 5, message, player, sc);
                if (choice != 5) { // if trade
                    this.activationResources.modifyResources(choice);
                    // if not enough resources
                    if (player.getResources().compareTo(this.activationResources) < 0) {
                        System.out.println("Not enough resources to trade.");
                        this.activationResources = new Resources();
                    } else { // pay resource - collect money
                        player.tradeMoneyResources(this.activationResources,
                                activationMoney, Action.SUBTRACT);
                        this.activationResources = new Resources();
                        break;
                    }
                } else { // if don't trade
                    break;
                }
            } while (true);
        } else if (this.getName().equals("Neutral Carpenter")) {
            System.out.println("\nActivating Neutral Carpenter");
            List<WoodBuilding> woodList = new ArrayList();
            for (Building building : game.getBuildingList()) {
                if (building instanceof WoodBuilding) {
                    woodList.add((WoodBuilding) building);
                }
            }
            player.setWorkers(player.getWorkers() + 1);
            System.out.println(player.getColor() + " Worker=" + player.getWorkers());
            return player.buildWood(woodList, sc);
        }
        player.setWorkers(player.getWorkers() + 1);
        System.out.println(player.getColor() + " Worker=" + player.getWorkers()
                + " Money=" + player.getMoney());
        return null;
    }

}
