package entities.buildings;

import caylus.Game;
import entities.Resources;
import entities.players.Player;
import enums.Action;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class SpecialBuilding extends Building {

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
            System.out.println("\nActivating Trading Post");
            // collect income
            player.setMoney(player.getMoney() + this.activationMoney);
            System.out.println(player.getColor() + " earns "
                    + this.activationMoney + " deniers");
        } // if merchant's guild
        else if (this.getName().equals("Merchant's Guild")) {
            System.out.println("\nActivating Merchant's Guild");
            while (true) {
                int provostPosition = game.getProvost().getPosition();
                try {
                    System.out.println("Provost position = " + game.getProvost().getPosition());
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
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    // cancel provost movement
                    game.getProvost().setPosition(provostPosition);
                }
            }
            System.out.println("Provost new position = " + game.getProvost().getPosition());
            player.setWorkers(player.getWorkers() + 1);
            System.out.println(player.getColor() + " Workers=" + player.getWorkers());
            return null;
        }// if joust field
        else if (this.getName()
                .equals("Joust Field")) {
            System.out.println("\nActivating Joust Field");
            // if player has  money and resources
            if (player.getMoney() >= this.activationMoney
                    && player.getResources().compareTo(activationResources) >= 0) {
                // pay money
                player.tradeMoneyResources(this.activationResources,
                        -this.activationMoney, Action.SUBTRACT);
                player.setFavors(player.getFavors() + this.activationFavors);
                System.out.println(player.getColor() + " earns "
                        + this.activationFavors + " favor");
                // use favor
                int max = player.getFavors();
                if (player.getFavors() > 4) {
                    max = 4;
                }
                player.setFavors(0);
                for (int i = max; i > 0; i--) {
                    game.getFavorTable().useFavor(game, player, sc);
                }
            } else {
                System.out.println("Not enough money or resources");
            }
        } // if stables
        else if (this.getName()
                .equals("Stables")) {
            System.out.println("\nActivating Stables");
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
                System.out.println(p.getColor() + " Workers=" + p.getWorkers());
            }
            return null;
        } // increase player's available workers
        player.setWorkers(player.getWorkers() + 1);
        System.out.println(player);
        return null;
    }
}
