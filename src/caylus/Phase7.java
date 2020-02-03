package caylus;
// end of turn

import entities.players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phase7 {

    public static void play(Game game, Scanner sc) {
        System.out.println("\nPhase 7: End of turn");
        moveBailiff(game);

    }

    // move bailiff
    public static void moveBailiff(Game game) {
        int bailiffPosition = game.getBailiff().getPosition();
        int provostPosition = game.getProvost().getPosition();
        if (bailiffPosition < provostPosition) {
            game.getBailiff().setPosition(bailiffPosition + 2);
        } else {
            game.getBailiff().setPosition(bailiffPosition + 1);
        }
        game.getProvost().setPosition(game.getBailiff().getPosition());
        System.out.println("Bailiff new position=" + game.getBailiff().getPosition());
        System.out.println("Provost new position=" + game.getProvost().getPosition());

        checkSectionScoring(game);
        for (Player player : game.getPlayerList()) {
            System.out.println(player);
        }
    }

    public static List<Player> scoreGame(Game game) {
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
        return winners;
    }

    // if available scoreGame section
    public static void checkSectionScoring(Game game) {
        if (game.getBailiff().getPosition() >= game.DUNGEON_SCORING
                || game.getCastle().getDungeon().getBuildSpaces().size() == 6) {
            if (!game.getCastle().getDungeon().isScored()) {
                System.out.println("Scoring Dungeon");
                game.getCastle().getDungeon().scoreSection(game);
            }
        }
        if (game.getBailiff().getPosition() >= game.WALLS_SCORING
                || game.getCastle().getWalls().getBuildSpaces().size() == 10) {
            if (!game.getCastle().getWalls().isScored()) {
                System.out.println("Scoring the Walls");
                game.getCastle().getWalls().scoreSection(game);
            }
        }
        if (game.getBailiff().getPosition() >= game.TOWERS_SCORING
                || game.getCastle().getTowers().getBuildSpaces().size() == 14) {
            if (!game.getCastle().getTowers().isScored()) {
                System.out.println("Scoring the Towers");
                game.getCastle().getTowers().scoreSection(game);
            }
        }
    }
}