package caylus;

import entities.buildings.Building;
import entities.buildings.WoodBuilding;
import entities.players.Player;
import java.util.List;
import java.util.Scanner;

/**
 * @author Παναγιώτης Μαστορακάκης
 */
public class Main {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game();
        game.createPlayerList(sc);
        List<Player> players = game.getPlayerList();
        Player p = players.get(0);
        Building f = WoodBuilding.MARKET_PLACE;

        System.out.println(p);
        System.out.println(f.getCostResources());

        f.activation(p, sc);
        System.out.println(p);
        System.out.println(f.getCostResources());
    }
}
