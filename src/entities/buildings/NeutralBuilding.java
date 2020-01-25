package entities.buildings;

import caylus.Game;
import entities.Resources;
import entities.players.Player;
import java.util.List;
import java.util.Scanner;

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
    public Building activate(Game game, List<Player> players, Scanner sc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
