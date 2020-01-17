package caylus;

import entities.Player;
import enums.Color;

/**
 * @author Παναγιώτης Μαστορακάκης
 */
public class Main {

    public static void main(String[] args) {
        Player p1 = new Player(Color.BLUE, 0);
        System.out.println(p1);
    }

}
