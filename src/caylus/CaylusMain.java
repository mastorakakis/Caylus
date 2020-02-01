package caylus;

import caylussetup.SetUpGame;
import entities.players.Player;
import enums.Status;
import java.util.List;
import java.util.Scanner;
import utilities.LoadGame;
import utilities.SaveGame;

/**
 * @author Παναγιώτης Μαστορακάκης
 */
public class CaylusMain {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("C A Y L U S");
        Game game;

//        game = LoadGame.load();
        game = new Game();
        game.setPlayerList(SetUpGame.players(sc));
        game.setRoad(SetUpGame.road());
        game.setBuildingList(SetUpGame.buildingsList());

        Status gameStatus = Status.CONTIUNE;
        do {
            Phase1.play(game, sc);
            System.out.println("");

            Phase2.play(game, sc);
            System.out.println("");

            Phase3.play(game, sc);
            System.out.println("");

            Phase4.play(game, sc);
            System.out.println("");

            Phase5.play(game, sc);
            System.out.println("");

            Phase6.play(game, sc);
            System.out.println("");
            if (game.getCastle().getTowers().isScored()) {
                gameStatus = Status.FINISH;
            }
            Phase7.play(game, sc);
            System.out.println("");
            if (game.getCastle().getTowers().isScored()) {
                gameStatus = Status.FINISH;
            }
        } while (gameStatus == Status.CONTIUNE);

        System.out.println("Winners:");
        List<Player> winners = EndOfGame.score(game);
        for (Player winner : winners) {
            System.out.println(winner);
        }
        //        System.out.println("\nGame Finished");
        //        Player player1 = game.getPlayerList().get(0);
        //        Player player2 = game.getPlayerList().get(1);
        //        Player player3 = game.getPlayerList().get(2);
        //
        //        player1.setPoints(1);
        //        player2.setPoints(1);
        //        player3.setPoints(1);
        //
        //        player1.setWorkers(5);
        //        player2.setWorkers(5);
        //        player3.setWorkers(5);
        //
        //        player2.getResources().setFood(3);
        //        player2.getResources().setWood(2);
        //        player2.getResources().setStone(2);
        //        player2.getResources().setCloth(2);
        //        player2.getResources().setGold(1);
        //        player3.getResources().setFood(3);
        //        player3.getResources().setWood(2);
        //        player3.getResources().setStone(2);
        //        player3.getResources().setCloth(2);
        //        player3.getResources().setGold(1);
        //
        //        game.getRoad().get(0).getWorkers().add(player1);
        //        game.getRoad().get(0).getWorkers().add(player2);
        //        game.getRoad().get(0).getWorkers().add(player3);
        //
        //        game.getCastle().getDungeon().getBuildSpaces().add(player1);
        //        game.getCastle().getDungeon().getBuildSpaces().add(player1);
        //        game.getCastle().getDungeon().getBuildSpaces().add(player1);
        //        game.getCastle().getDungeon().getBuildSpaces().add(player1);
        //        game.getCastle().getDungeon().getBuildSpaces().add(player2);
        //        game.getCastle().getDungeon().getBuildSpaces().add(player3);
        //
        //        game.getCastle().getTowers().getBuildSpaces().add(player1);
        //        game.getCastle().getTowers().getBuildSpaces().add(player1);
        //        game.getCastle().getTowers().getBuildSpaces().add(player1);
        //        game.getCastle().getTowers().getBuildSpaces().add(player1);
        //        game.getCastle().getTowers().getBuildSpaces().add(player1);
        //        game.getCastle().getTowers().getBuildSpaces().add(player1);
        //        game.getCastle().getTowers().getBuildSpaces().add(player3);
        //        game.getCastle().getTowers().getBuildSpaces().add(player3);
        //        game.getCastle().getTowers().getBuildSpaces().add(player3);
        //        game.getCastle().getTowers().getBuildSpaces().add(player2);
        //        game.getCastle().getTowers().getBuildSpaces().add(player2);
        //        game.getCastle().getTowers().getBuildSpaces().add(player2);
        //        game.getCastle().getTowers().getBuildSpaces().add(player2);
        //
        //        game.getCastle().getDungeon().setScored(true);
        //        game.getCastle().getWalls().setScored(true);
        //            Phase1.play(game, sc);
        //            System.out.println("");
        //
        //            Phase2.play(game, sc);
        //            System.out.println("");
        //
        //            Phase3.play(game, sc);
        //            System.out.println("");
        //
        //            Phase4.play(game, sc);
        //            System.out.println("");
        //
        //            Phase5.play(game, sc);
        //            System.out.println("");
        //
        //            Phase6.play(game, sc);
        //            System.out.println("");
        //
        //            Phase7.play(game, sc);
        //            System.out.println("");
    } //        SaveGame.save(game);

}
