package entities.buildings;

import entities.Resources;

public class PrestigeBuilding extends Building {

    private int buildPoints;
    private Resources buildResources;
    private int buildFavors;

    // constructor
    public PrestigeBuilding() {
    }

    // constructor
    public PrestigeBuilding(int buildPoints, Resources buildResources, int buildFavors, String name) {
        super(name);
        this.buildPoints = buildPoints;
        this.buildResources = buildResources;
        this.buildFavors = buildFavors;
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
