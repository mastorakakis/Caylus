package caylus;

import static caylus.CaylusMain.sc;
import caylussetup.CreatePlayers;
import caylussetup.SetUpGame;
import entities.FavorTable;
import entities.buildings.Castle;
import entities.players.Player;
import enums.Status;
import java.util.List;

public class TestMain { // TODO delete class

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {

            System.out.println("C A Y L U S");
            Game game = new Game();
            CreatePlayers cp = new CreatePlayers();
            SetUpGame sug = new SetUpGame();
            FavorTable favorTable = new FavorTable();
            favorTable.createPlayerFavorTable();

            game.setPlayerList(cp.getPlayers(sc));
            game.setFavorTable(favorTable);
            game.setRoad(sug.getRoad());
            game.setBuildingList(sug.getBuildingList());
            game.setCastle((Castle) game.getRoad().get(0).getBuilding());

            Status gameStatus = Status.CONTINUE;

            Player player = game.getPlayerList().get(0);
            player.setMoney(10);
            player.getResources().setCloth(10);
            player.getResources().setFood(10);
            player.getResources().setWood(10);
            player.getResources().setStone(10);
            player.getResources().setGold(10);
            player.setPoints(10);
            Player player2 = game.getPlayerList().get(1);
            player2.setMoney(10);
            player2.getResources().setCloth(10);
            player2.getResources().setFood(10);
            player2.getResources().setWood(10);
            player2.getResources().setStone(10);
            player2.getResources().setGold(10);
            player2.setPoints(10);

            player.setWorkers(4);
            player2.setWorkers(4);

            game.getRoad().get(1).getWorkers().add(player);
            game.getRoad().get(2).getWorkers().add(player2);

            game.getBridge().getPositionList().add(player);
            game.getBridge().getPositionList().add(player2);

            Phase3.play(game, sc);
            System.out.println("");

            Phase4.play(game, sc);
            System.out.println("");

            Phase5.play(game, sc);
            System.out.println("");

            Phase6.play(game, sc);
            System.out.println("");

            Phase7.play(game, sc);
            System.out.println("");
            if (game.getCastle().getTowers().isScored()) {
                gameStatus = Status.FINISH;
            }
            List<Player> winners = Phase7.scoreGame(game);
            System.out.println("\nWinners:");
            for (Player winner : winners) {
                System.out.println(winner);
            }
        }
    }
}
