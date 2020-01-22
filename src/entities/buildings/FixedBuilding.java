package entities.buildings;

import interfaces.BoardBulding;
import entities.Resources;

public class FixedBuilding extends Building implements BoardBulding {

    private int activationMoney;
    private Resources activationResources;

    // constructor
    public FixedBuilding() {
    }

    // constructor
    public FixedBuilding(int activationMoney, Resources activationResources,
            String name) {
        super(name);
        this.activationMoney = activationMoney;
        this.activationResources = activationResources;
    }

    // getters setters
    public int getActivationMoney() {
        return activationMoney;
    }

    public void setActivationMoney(int activationMoney) {
        if (activationMoney < 0) {
            throw new IllegalArgumentException("Amount of money cannot be negative");
        }
        this.activationMoney = activationMoney;
    }

    public Resources getActivationResources() {
        return activationResources;
    }

    public void setActivationResources(Resources activationResources) {
        this.activationResources = activationResources;
    } //  end of getters setters

}
