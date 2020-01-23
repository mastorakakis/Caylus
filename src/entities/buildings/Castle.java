package entities.buildings;

import entities.players.Player;
import java.util.ArrayList;
import java.util.List;

public class Castle extends Building {

    private String name = "Castle";
    private static List<Player> positionList = new ArrayList();

    // constructor
    public Castle() {
    }

    // getters setters
    public static List<Player> getPositionList() {
        return positionList;
    }

    public static void setPositionList(List<Player> positionList) {
        Castle.positionList = positionList;
    }// end of getters setters

    @Override
    public void activate(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
