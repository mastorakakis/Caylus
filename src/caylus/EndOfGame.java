package caylus;

import entities.players.Player;
import java.util.ArrayList;
import java.util.List;

// scoring
public class EndOfGame {

    public static List<Player> score(Game game) {
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
            if (player.getMoney() == maxPoints) {
                maxPoints = player.getMoney();
                winners.add(player);
            } else if (player.getMoney() > maxPoints) {
                maxPoints = player.getMoney();
                winners = new ArrayList();
                winners.add(player);
            }
        }
        return winners;
    }

    // if available score section
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
