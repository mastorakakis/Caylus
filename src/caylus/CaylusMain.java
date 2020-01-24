package caylus;

import caylussetup.BuildingObjects;
import caylussetup.SetUpGame;
import entities.players.Player;
import entities.players.UserPlayer;
import enums.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
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

//        Phase1.play(game, sc);
//        for (Player player : players) {
//            System.out.println(player);
//        }
//        Phase2.play(game, sc);
//        for (Player player : players) {
//            System.out.println(player);
//        }
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list2.add(9);
        list2.add(8);
        list2.add(7);
        System.out.println(list);
        System.out.println(list2);
        for (int i = 0; i < 10; i++) {
            System.out.println(list);
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (list2.contains(iterator.next())) {
                iterator.remove();
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(list);
        }
        list.addAll(0, list2);
        for (int i = 0; i < 10; i++) {
            System.out.println(list);
        }
    }
}
