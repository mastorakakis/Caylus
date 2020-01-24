package entities.buildings;

import entities.players.Player;
import java.util.ArrayList;
import java.util.List;

// Singleton Pattern
public class Castle {

    private static Castle castleInstance = new Castle("Castle");
    private String name;
    private List<Player> positionList = new ArrayList();

    // constructor private
    private Castle(String name) {
        this.name = name;
    }

    // getters setters
    public static Castle getCastleInstance() {
        return castleInstance;
    }

    public List<Player> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Player> positionList) {
        this.positionList = positionList;
    }// end of getters setters

    public void activate(List<Player> players) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
