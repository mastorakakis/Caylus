package caylus;

import java.util.Scanner;
import utilities.Game;
import utilities.SetUpGame;

/**
 * @author Παναγιώτης Μαστορακάκης
 */
public class Main {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game();
        game.setPlayers(SetUpGame.createPlayerList(sc));

    }

}
