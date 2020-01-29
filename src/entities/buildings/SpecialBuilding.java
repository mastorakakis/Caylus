package entities.buildings;

import caylus.Game;
import static caylus.Game.WARNING;
import interfaces.BoardBulding;
import entities.Resources;
import entities.players.Player;
import entities.players.UserPlayer;
import enums.Action;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class SpecialBuilding extends Building implements BoardBulding {

    private int activationMoney;
    private Resources activationResources;
    private int activationFavors;

    // constructor
    public SpecialBuilding() {
    }

    public SpecialBuilding(String name) {
        super(name);
    }

    // constructor
    public SpecialBuilding(int activationMoney, Resources activationResources,
            int activationFavors, String name) {
        super(name);
        this.activationMoney = activationMoney;
        this.activationResources = activationResources;
        this.activationFavors = activationFavors;
    }

    @Override
    public Building activate(Game game, List<Player> workers, Scanner sc) {
        Player player = workers.get(0);
        // if trading post
        if (this.getName().equals("Trading Post")) {
            System.out.println("Activating Trading Post");
            // collect income
            player.setMoney(player.getMoney() + this.activationMoney);
            System.out.println(player.getColor() + " earns "
                    + this.activationMoney + " deniers");
        } // if merchant's guild
        else if (this.getName().equals("Merchant's Guild")) {
            System.out.println("Activating Merchant's Guild");
            // move provost
            String message = player.getColor()
                    + " move Provost\n1)Forward 2)Backwards";
            String message2 = "How many steps\n0)0\n1)1\n2)2\n3)3";
            int choice = Functions.inputValidation(1, 2, message, player, sc);
            int choice2 = Functions.inputValidation(0, 3, message2, player, sc);
            if (choice == 1) {
                game.getProvost().setPosition(game.getProvost().getPosition() + choice2);
            } else {
                game.getProvost().setPosition(game.getProvost().getPosition() - choice2);
            }
            System.out.println("Provost new position = "
                    + (game.getProvost().getPosition() + 1));
        }// if joust field
        else if (this.getName().equals("Joust Field")) {
            System.out.println("Activating Joust Field");
            // if player has  money and resources
            if (player.getMoney() >= this.activationMoney
                    && player.getResources().compareTo(activationResources) >= 0) {
                // pay money
//                player.setMoney(player.getMoney() - this.activationMoney);
                player.tradeMoneyResources(this.activationResources,
                        -this.activationMoney, Action.SUBTRACT);
                player.setFavors(player.getFavors() + this.activationFavors);
                System.out.println(player.getColor() + " earns "
                        + this.activationFavors + " favor");
                // TODO Go to favors table
            } else {
                System.out.println("Not enough money or resources");
            }
        } else if (this.getName().equals("Stables")) {
            System.out.println("Activating Stables");
            System.out.println("Changing playing order");
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
            return null;
        } // increase player's available workers
        player.setWorkers(player.getWorkers() + 1);
        return null;
    }
}
