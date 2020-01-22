package entities.buildings;

import entities.Resources;

public class PrestigeBuilding extends Building {

    private int buildPoints;
    private int buildFavors;
    private Resources buildResources;
    private int activationMoney;

    // constructor
    public PrestigeBuilding() {
    }

    // constructor
    public PrestigeBuilding(int buildPoints, int buildFavors, Resources buildResources,
            int activationMoney, String name) {
        super(name);
        this.buildPoints = buildPoints;
        this.buildFavors = buildFavors;
        this.buildResources = buildResources;
        this.activationMoney = activationMoney;
    }

    // getters setters
    public int getBuildPoints() {
        return buildPoints;
    }

    public void setBuildPoints(int buildPoints) {
        this.buildPoints = buildPoints;
    }

    public Resources getBuildResources() {
        return buildResources;
    }

    public void setBuildResources(Resources buildResources) {
        this.buildResources = buildResources;
    }

    public int getBuildFavors() {
        return buildFavors;
    }

    public void setBuildFavors(int buildFavors) {
        this.buildFavors = buildFavors;
    }// end of getters setters

}
