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
        Player player1 = game.playerList.get(0);
        Player player2 = game.playerList.get(1);
//        player1.setWorkers(3);
//        player2.setWorkers(3);
//        game.road.get(0).getWorkers().add(player1);
//        game.road.get(0).setHouse(player1);
//        game.road.get(4).getWorkers().add(game.playerList.get(1));
        game.bridge.positionList.add(player1);
        game.bridge.positionList.add(player2);
        game.provost.setPosition(6);
//        game.inn.getInnPosition()[1] = player1;
//        Phase2.play(game, sc);
//        for (Player player : players) {
//            System.out.println(player);
//        }

//        Phase3.play(game, sc);
//        for (Player player : players) {
//            System.out.println(player);
//        }
        // System.out.println("");
        Phase4.play(game, sc);
        for (Player player : players) {
            System.out.println(player);
        }
        System.out.println("");

    }
}
