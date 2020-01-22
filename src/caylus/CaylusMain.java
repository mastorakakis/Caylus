package caylus;

import caylussetup.SetUpGame;
import entities.players.Player;
import java.util.List;
import java.util.Scanner;

/**
 * @author Παναγιώτης Μαστορακάκης
 */
public class CaylusMain {

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

        Phase1.play();
        for (Player player : players) {
            System.out.println(player);
        }
        Phase2.play(sc);
        for (Player player : players) {
            System.out.println(player);
        }

    }
}
