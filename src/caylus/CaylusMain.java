package caylus;

import caylussetup.SetUpGame;
import entities.Block;
import entities.buildings.Building;
import entities.buildings.ResidentialBuilding;
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
        game.setBuildingList(SetUpGame.buildingsList());
        for (Player player : game.getPlayerList()) {
            System.out.println(player);
        }
        System.out.println("");

        Player player1 = game.getPlayerList().get(0);
        Player player2 = game.getPlayerList().get(1);
        Player player3 = game.getPlayerList().get(2);
        List<Block> road = game.getRoad();
        List<Building> buildings = game.getBuildingList();
        Building building1 = null;
        Building building2 = null;
//        for (Building b : buildings) {
//            if (b.getName().equals("Hotel")) {
//                building1 = b;
//            }
//        }
//        for (Building b : buildings) {
//            if (b.getName().equals("Library")) {
//                building2 = b;
//            }
//        }
//        road.get(0).setBuilding(new ResidentialBuilding());
//        road.get(0).setHouse(player1);
//        road.get(1).setBuilding(new ResidentialBuilding());
//        road.get(1).setHouse(player2);
//        road.get(2).setBuilding(building1);
//        road.get(2).setHouse(player2);
//        road.get(3).setBuilding(building2);
//        road.get(3).setHouse(player3);

        Phase1.play(game, sc);
        for (Player player : game.getPlayerList()) {
            System.out.println(player);
        }
        System.out.println("");

        Phase2.play(game, sc);
        for (Player player : game.getPlayerList()) {
            System.out.println(player);
        }
//        Phase3.play(game, sc);
//        for (Player player : game.getPlayerList()) {
//            System.out.println(player);
//        }
//        System.out.println("");
//        Phase4.play(game, sc);
//        for (Player player : game.getPlayerList()) {
//            System.out.println(player);
//        }
//        System.out.println("");
//        Phase5.play(game, sc);
//        for (Player player : game.getPlayerList()) {
//            System.out.println(player);
//        }
    }

}
