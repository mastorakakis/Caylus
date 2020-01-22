package entities.buildings;

import entities.Resources;

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

}
