package caylus;

import static caylus.CaylusMain.sc;
import caylussetup.CreatePlayers;
import caylussetup.SetUpGame;
import entities.FavorTable;
import entities.Resources;
import entities.buildings.Castle;
import entities.buildings.ResidentialBuilding;
import entities.buildings.WoodBuilding;
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

            player.getResources().setStone(2);
            player.getResources().setGold(1);
            player.getResources().setWood(2);
            player.getResources().setCloth(2);

            player.setWorkers(3);

            game.getCastle().getDungeon().setScored(true);
            game.getCastle().getWalls().setScored(true);

            game.getFavorTable().getPlayerFavorTable()[2][4].add(player);
            game.getFavorTable().getPlayerFavorTable()[3][3].add(player);
            game.getFavorTable().getPlayerFavorTable()[3][2].add(player);
            game.getFavorTable().getPlayerFavorTable()[3][1].add(player);
            game.getFavorTable().getPlayerFavorTable()[3][0].add(player);

            game.getBailiff().setPosition(29);
            game.getProvost().setPosition(29);

            game.getRoad().get(12).setBuilding(new ResidentialBuilding());
            game.getRoad().get(12).setHouse(player);

            game.getCastle().getTowers().getBuildSpaces().add(player);
            game.getCastle().getTowers().getBuildSpaces().add(player);
            game.getCastle().getTowers().getBuildSpaces().add(player);
            game.getCastle().getTowers().getBuildSpaces().add(player);

            game.getRoad().get(0).getWorkers().add(player);

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
