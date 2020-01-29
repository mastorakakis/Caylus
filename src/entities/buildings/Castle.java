package entities.buildings;

import caylus.Game;
import entities.players.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Singleton Pattern
public class Castle extends Building implements Serializable {

    private static Castle castleInstance = new Castle("Castle");
//    private List<Player> positionList = new ArrayList();

    // constructor private
    private Castle(String name) {
        super(name);
    }

    // getters setters
    public static Castle getCastleInstance() {
        return castleInstance;
    }

//    public List<Player> getPositionList() {
//        return positionList;
//    }
//
//    public void setPositionList(List<Player> positionList) {
//        this.positionList = positionList;
//    }// end of getters setters
    public void activate(List<Player> players) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // remove static members from singletons

    @Override
    public Building activate(Game game, List<Player> players, Scanner sc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
