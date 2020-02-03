package caylus;

import caylussetup.CreatePlayers;
import caylussetup.SetUpGame;
import entities.FavorTable;
import entities.buildings.Castle;
import entities.players.Player;
import entities.players.UserPlayer;
import enums.Color;
import enums.Status;
import java.util.List;
import java.util.Scanner;

/**
 * @author Παναγιώτης Μαστορακάκης
 */
public class CaylusMain {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("C A Y L U S");
////////////////////////////////////////////////////////////////////////////////
//        for (int i = 0; i < 1000; i++) {

        Game game = new Game();
//        if (Functions.inputValidation(1, 2, "\n1)Start new Game\t2)Load Game",
//                new UserPlayer(Color.BLUE), sc) == 2) {
//            game = LoadGame.load();
//        } else {
        CreatePlayers cp = new CreatePlayers();
        SetUpGame sug = new SetUpGame();
        FavorTable favorTable = new FavorTable();
        favorTable.createPlayerFavorTable();

        game.setPlayerList(cp.getPlayers(sc));
        game.setFavorTable(favorTable);
        game.setRoad(sug.getRoad());
        game.setBuildingList(sug.getBuildingList());
        game.setCastle((Castle) game.getRoad().get(0).getBuilding());
//        }
////////////////////////////////////////////////////////////////////////////////

//        Status gameStatus = Status.CONTIUNE;
//        do {
////            if (Functions.inputValidation(1, 2, "Save Game\n1)Yes\t2)No",
////                    new UserPlayer(Color.BLUE), sc) == 1) {
////                SaveGame.save(game);
////            }
//            Phase1.play(game, sc);
//            System.out.println("");
//
//            Phase2.play(game, sc);
//            System.out.println("");
////            if (Functions.inputValidation(1, 2, "Save Game\n1)Yes\t2)No",
////                    new UserPlayer(Color.BLUE), sc) == 1) {
////                SaveGame.save(game);
////            }
//            Phase3.play(game, sc);
//            System.out.println("");
////            if (Functions.inputValidation(1, 2, "Save Game\n1)Yes\t2)No",
////                    new UserPlayer(Color.BLUE), sc) == 1) {
////                SaveGame.save(game);
////            }
//            Phase4.play(game, sc);
//            System.out.println("");
////            if (Functions.inputValidation(1, 2, "Save Game\n1)Yes\t2)No",
////                    new UserPlayer(Color.BLUE), sc) == 1) {
////                SaveGame.save(game);
////            }
//            Phase5.play(game, sc);
//            System.out.println("");
////            if (Functions.inputValidation(1, 2, "Save Game\n1)Yes\t2)No",
////                    new UserPlayer(Color.BLUE), sc) == 1) {
////                SaveGame.save(game);
////            }
//            Phase6.play(game, sc);
//            System.out.println("");
//            if (game.getCastle().getTowers().isScored()) {
//                gameStatus = Status.FINISH;
//            }
//            if (gameStatus == Status.CONTIUNE) {
//                Phase7.play(game, sc);
//                System.out.println("");
//                if (game.getCastle().getTowers().isScored()) {
//                    gameStatus = Status.FINISH;
//                }
//            }
//
//        } while (gameStatus == Status.CONTIUNE);
//        List<Player> winners = Phase7.scoreGame(game);
//        System.out.println("\nWinners:");
//        for (Player winner : winners) {
//            System.out.println(winner);
//        }
        Player player = game.getPlayerList().get(0);
        player.getFavorTableOptions()[1] = null;
        favorTable.selectFavorLine(game, (UserPlayer) player, sc);
    } // end of main
}
