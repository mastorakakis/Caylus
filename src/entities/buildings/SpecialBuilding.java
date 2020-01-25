package entities.buildings;

import caylus.Game;
import static caylus.Game.WARNING;
import interfaces.BoardBulding;
import entities.Resources;
import entities.players.Player;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class SpecialBuilding extends Building implements BoardBulding {

    private int activationMoney;
    private Resources activationResources;
    private int activationPoints;
    private int activationFavors;

    // constructor
    public SpecialBuilding() {
    }

    // constructor
    public SpecialBuilding(int activationMoney, Resources activationResources,
            int activationPoints, int activationFavors, String name) {
        super(name);
        this.activationMoney = activationMoney;
        this.activationResources = activationResources;
        this.activationPoints = activationPoints;
        this.activationFavors = activationFavors;
    }

    @Override
    public Building activate(List<Player> workers, Scanner sc, Game game) {
        Player player = workers.get(0);
        if (this.getName().equals("Trading Post")) {
            player.setMoney(player.getMoney() + this.activationMoney);
        } else if (this.getName().equals("Merchant's Guild")) {
            String message = player.getColor()
                    + " move Provost\n1)Forward 2)Backwards";
            String message2 = "How many steps\n0)0\n1)1\n2)2\n3)3";
            int choice = Functions.inputValidation(1, 2, message, WARNING, sc);
            int choice2 = Functions.inputValidation(0, 3, message2, WARNING, sc);
            if (choice == 1) {
                game.provost.setPosition(game.provost.getPosition() + choice2);
            } else {
                game.provost.setPosition(game.provost.getPosition() - choice2);
            }
            System.out.println("Provost new position = " + (game.provost.getPosition() + 1));
        } else if (this.getName().equals("Joust Field")) {
            // if player has  money and resources
            if (player.getMoney() >= this.activationMoney
                    && player.getResources().compareTo(activationResources) >= 0) {
                // pay money
                player.setMoney(player.getMoney() - this.activationMoney);
                player.payResources(this.activationResources);
                player.setFavors(player.getFavors() + this.activationFavors);
                // TODO Go to favors table
            }
        } else if (this.getName().equals("Stables")) {
            // remove all players in Stables from game playerList
            Iterator<Player> iterator = game.getPlayerList().iterator();
            while (iterator.hasNext()) {
                if (workers.contains(iterator.next())) {
                    iterator.remove();
                }
            }
            // add players from workers in the beginning of game playerList
            game.getPlayerList().addAll(0, workers);
            // increase players' available workers
            for (Player p : workers) {
                p.setWorkers(p.getWorkers() + 1);
            }
            return this;
        } // increase player's available workers
        player.setWorkers(player.getWorkers() + 1);
        return this;
    }

}
