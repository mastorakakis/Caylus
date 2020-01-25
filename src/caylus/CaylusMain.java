package caylus;

import caylussetup.SetUpGame;
import entities.Resources;
import entities.buildings.StoneBuilding;
import entities.players.Player;
import enums.SelectAction;
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

//        Phase1.play(game, sc);
//        for (Player player : players) {
//            System.out.println(player);
//        }
        Player player1 = game.playerList.get(0);
        player1.setMoney(5);
        player1.collectFromBuilding(new Resources(0, 0, 1, 3, 1), 0, SelectAction.ADD);
        Player player2 = game.playerList.get(1);
//        Player player3 = game.playerList.get(2);
//        player1.setWorkers(3);
//        player2.setWorkers(3);
        game.road.get(6).getWorkers().add(player1);
//        game.road.get(7).getWorkers().add(player2);
//        game.road.get(8).getWorkers().add(player3);
//        game.road.get(4).getWorkers().add(game.playerList.get(1));
        game.road.get(6).setBuilding(new StoneBuilding(6, 0,
                new Resources(0, 0, 1, 1, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
                new Resources(0, 0, 0, 0, 0), "Jeweler"));
//        game.road.get(6).setBuilding(new StoneBuilding(6, 0,
//                new Resources(0, 1, 1, 0, 0), 0, 4, new Resources(0, 0, 0, 0, 0),
//                new Resources(0, 0, 0, 0, 0), "Tailor"));

//        game.road.get(6).setBuilding(new StoneBuilding(6, 0,
//                new Resources(1, 0, 1, 0, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
//                new Resources(0, 0, 0, 0, 0), "Alchemist"));
//        game.road.get(6).setBuilding(new WoodBuilding(4, new Resources(0, 1, 0, 0, 0),
//                6, new Resources(0, 0, 0, 0, 0), "Wood Market Place"));
        game.road.get(6).setHouse(player2);
//        game.bridge.positionList.add(player1);
//        game.bridge.positionList.add(player2);
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
//        Phase4.play(game, sc);
//        for (Player player : players) {
//            System.out.println(player);
//        }
//        System.out.println("");
        Phase5.play(game, sc);
        for (Player player : players) {
            System.out.println(player);
        }

    }
}
