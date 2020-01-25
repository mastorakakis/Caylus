package entities.buildings;

import caylus.Game;
import static caylus.Game.WARNING;
import entities.Resources;
import entities.players.Player;
import enums.SelectAction;
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
        if (this.getName().equals("Neutral Quarry")) {
            player.collectFromBuilding(activationResources, activationMoney,
                    SelectAction.ADD);
        } else if (this.getName().equals("Neutral Sawmill")) {
            player.collectFromBuilding(activationResources, activationMoney,
                    SelectAction.ADD);
        } else if (this.getName().equals("Neutral Farm")) {
            String message = player.getColor()
                    + " select resource\n1)1 Food\n2)1 Cloth";
            int choice = Functions.inputValidation(1, 2, message, WARNING, sc);
            choice = choice == 2 ? 4 : 1;
            activationResources.modifyResources(choice, sc);
            player.collectFromBuilding(activationResources, activationMoney,
                    SelectAction.ADD);
            this.activationResources = new Resources();
        } else if (this.getName().equals("Forest")) {
            String message = player.getColor()
                    + " select resource\n1)1 Food\n2)1 Wood";
            int choice = Functions.inputValidation(1, 2, message, WARNING, sc);
            activationResources.modifyResources(choice, sc);
            player.collectFromBuilding(activationResources, activationMoney,
                    SelectAction.ADD);
            this.activationResources = new Resources();
        } else if (this.getName().equals("Neutral Market Place")) {
            boolean askAgain = true;
            do {
                String message = player.getColor() + " select one Resource to trade\n"
                        + "1)Food\n2)Wood\n3)Stone\n4)Cloth\n5)Don't trade";
                int choice = Functions.inputValidation(1, 5, message, WARNING, sc);
                if (choice != 5) {
                    this.activationResources.modifyResources(choice, sc);
                    if (player.getResources().compareTo(this.activationResources) < 0) {
                        System.out.println("Not enough resources to trade.");
                        askAgain = true;
                    } else {
                        player.collectFromBuilding(this.activationResources,
                                activationMoney, SelectAction.SUBTRACT);
                        askAgain = false;
                    }
                    this.activationResources = new Resources();
                }
            } while (askAgain == true);
        } else if (this.getName().equals("Neutral Carpenter")) {
            //TODO
        }
        return this;
    }

}
