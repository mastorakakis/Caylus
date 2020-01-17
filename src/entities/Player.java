package entities;

import enums.Color;
import secondaryclasses.Resource;

public class Player {

    private Color color;
    private int points = 0; // player starts with zero points
    private int money;
    // player starts with two food cubes and one wood
    private Resource playerResources = new Resource(2, 1, 0, 0, 0);
    private int workers = 6; // player has six workers;

    private int favors;
    private int houses;

    //constructor
    public Player(Color color, int money) {
        this.color = color;
        this.money = money; // initial amount depends on turn order
    }

    @Override
    public String toString() {
        return "player{" + "color=" + color + ", points=" + points + ", money="
                + money + ", playerResources=" + playerResources + ", workers="
                + workers + ", favors=" + favors + ", houses=" + houses + '}';
    }

}
