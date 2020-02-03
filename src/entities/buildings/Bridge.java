package entities.buildings;

import entities.players.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bridge implements Serializable {

    private List<Player> positionList = new ArrayList();

    // constructor private
    public Bridge() {
    }

    // getters setters
    public List<Player> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Player> positionList) {
        if (positionList.size() < 0) {
            throw new IllegalArgumentException("Bridge position list cannot be negative");
        }
        if (positionList.size() > 5) {
            throw new IllegalArgumentException("Bridge position list cannot be over 5");
        }
        this.positionList = positionList;
    }// end of getters setters

    public void playerPass(Player player) {
        // if coming from gate player already passed
        if (!positionList.contains(player)) {
            positionList.add(player); // add player to bridge
            if (positionList.size() == 1) { // if player is first
                player.setMoney(player.getMoney() + 1); // player earns 1 denier
                System.out.println(player.getColor() + " earns 1 denier");
            }
        }
        System.out.println(player.getColor() + " passes");
    }
}
