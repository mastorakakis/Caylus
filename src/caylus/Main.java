package caylus;

import static caylus.CreateBuildings.gate;
import entities.Block;
import entities.players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Παναγιώτης Μαστορακάκης
 */
public class Main {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game();
        game.setPlayerList(SetUpGame.players(sc));
        game.setRoad(SetUpGame.road());

        List<Player> players = game.getPlayerList();
        for (Player player : players) {
            System.out.println(player);
        }
        System.out.println("");

        game.phase1();
        for (Player player : players) {
            System.out.println(player);
        }
        game.phase2(sc);
        for (Player player : players) {
            System.out.println(player);
        }
    }
}
