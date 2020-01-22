package entities.buildings;

import interfaces.BoardBulding;
import entities.Resources;

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

}
