package entities.buildings;

import entities.Resources;

public class ResidentialBuilding extends Building {

    private final String name = "Residential Building";
    private final int buildPoints = 2;
    private final int buildMoney = 1;
    private final Resources buildResources = new Resources(0, 0, 0, 1, 0);
    private final int activationMoney = 1;

    // getters setters
    public int getBuildPoints() {
        return buildPoints;
    }

    public int getBuildMoney() {
        return buildMoney;
    }

    public Resources getBuildResources() {
        return buildResources;
    }

    public int getActivationMoney() {
        return activationMoney;
    } // end of getters setters

}
