package entities.buildings;

import caylus.Game;
import interfaces.BoardBulding;
import entities.Resources;
import entities.players.Player;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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
            //TODO
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
            for (Player p : workers) {
                p.setWorkers(p.getWorkers() + 1);
            }
            return this;
        } // increase player's available workers
        player.setWorkers(player.getWorkers() + 1);
        return this;
    }

}
