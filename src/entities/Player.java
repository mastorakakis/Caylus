package entities;

import enums.Color;

public abstract class Player {

    protected Color color;
    protected int points = 0; // player starts with zero points
    protected int money;
    protected Resources playerResources = new Resources(2, 1, 0, 0, 0); // player starts with two food cubes and one wood
    protected int workers = 6; // player has six workers;

    //constructor
    public Player(Color color) {
        this.color = color;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Player{" + "color=" + color + ", points=" + points + ", money=" + money + ", playerResources=" + playerResources + ", workers=" + workers + '}';
    }
    //TODO getters, setters, validate setters
}
