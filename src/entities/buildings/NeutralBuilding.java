package entities.buildings;

import entities.Resources;
import entities.players.Player;

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
    public void activate(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
