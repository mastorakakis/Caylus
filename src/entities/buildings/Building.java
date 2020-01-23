package entities.buildings;

public abstract class Building {

    private String name = "";

    // constructor
    public Building() {
    }

    // constructor
    public Building(String name) {
        this.name = name;

    }

    // getters setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }// end of getters setters
}
