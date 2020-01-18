package caylus;

import entities.Player;
import entities.UserPlayer;
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
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            if (player instanceof UserPlayer) {
                System.out.println(player);
            }
        }
    }
}
