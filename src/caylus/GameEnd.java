package caylus;

import entities.players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameEnd {

    public static void score(Game game) {
        System.out.println("\nFinal Score");
        int maxPoints = 0;
        List<Player> winners = new ArrayList();
        for (Player player : game.getPlayerList()) {
            int food = player.getResources().getFood();
            int wood = player.getResources().getWood();
            int stone = player.getResources().getStone();
            int cloth = player.getResources().getCloth();
            int cubePoints = (food + wood + stone + cloth) / 3;

            int goldPoints = player.getResources().getGold() * 3;

            int moneyPoints = player.getMoney() / 4;

            int finalPoints
                    = player.getPoints() + cubePoints + goldPoints + moneyPoints;
            player.setPoints(finalPoints);
            System.out.println(player);
            if (player.getPoints() == maxPoints) {
                winners.add(player);
            } else if (player.getPoints() > maxPoints) {
                maxPoints = player.getPoints();
                winners = new ArrayList();
                winners.add(player);
            }
        }
        System.out.println("\nWinners:");
        for (Player winner : winners) {
            System.out.println(winner);
        }
    }

    // if available score section
    public static void checkSectionScoring(Game game, Scanner sc) {
        if (game.getBailiff().getPosition() >= game.DUNGEON_SCORING
                || game.getCastle().getDungeon().getBuildSpaces().size() == 6) {
            if (!game.getCastle().getDungeon().isScored()) {
                System.out.println("Scoring Dungeon");
                game.getCastle().getDungeon().scoreSection(game, sc);
            }
        }
        if (game.getBailiff().getPosition() >= game.WALLS_SCORING
                || game.getCastle().getWalls().getBuildSpaces().size() == 10) {
            if (!game.getCastle().getWalls().isScored()) {
                System.out.println("Scoring the Walls");
                game.getCastle().getWalls().scoreSection(game, sc);
            }
        }
        if (game.getBailiff().getPosition() >= game.TOWERS_SCORING
                || game.getCastle().getTowers().getBuildSpaces().size() == 14) {
            if (!game.getCastle().getTowers().isScored()) {
                System.out.println("Scoring the Towers");
                game.getCastle().getTowers().scoreSection(game, sc);
            }
        }
    }
}
