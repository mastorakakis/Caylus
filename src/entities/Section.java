package entities;

import caylus.Game;
import entities.players.Player;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Section implements Serializable {

    private String name;
    private List<Player> buildSpaces;
    private int buildPoints;
    private int scoreFavors;
    private int penaltyScorePoints;
    private boolean scored = false;

    // constructor
    public Section(String name, List<Player> buildSpaces, int buildPoints,
            int scoreFavors, int penaltyScorePoints) {
        this.name = name;
        this.buildSpaces = buildSpaces;
        this.buildPoints = buildPoints;
        this.scoreFavors = scoreFavors;
        this.penaltyScorePoints = penaltyScorePoints;
    }

    // getters setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getBuildSpaces() {
        return buildSpaces;
    }

    public void setBuildSpaces(List<Player> buildSpaces) {
        this.buildSpaces = buildSpaces;
    }

    public int getBuildPoints() {
        return buildPoints;
    }

    public boolean isScored() {
        return scored;
    }

    public int getPenaltyScorePoints() {
        return penaltyScorePoints;
    }

    public int getScoreFavors() {
        return scoreFavors;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }

    public void setBuildPoints(int buildPoints) {
        this.buildPoints = buildPoints;
    }// end of getters setters

    // score section
    public void scoreSection(Game game, Scanner sc) {
        for (Player player : game.getPlayerList()) {
            int numberOfHouses = 0;
            for (Player house : buildSpaces) {
                if (player == house) {
                    numberOfHouses++;
                }
            }
            if (numberOfHouses == 0) {
                if (player.getPoints() < penaltyScorePoints) {
                    player.setPoints(0);
                } else {
                    player.setPoints(player.getPoints() - penaltyScorePoints);
                }
                System.out.println(player.getColor() + " looses "
                        + penaltyScorePoints + " points");
            } else if (numberOfHouses == 2) {
                player.setFavors(player.getFavors() + this.scoreFavors);
                System.out.println(player.getColor() + " earns "
                        + scoreFavors + " favor");
                int max = player.getFavors();
                if (player.getFavors() > 4) {
                    max = 4;
                }
                player.setFavors(0);
                for (int i = max; i > 0; i--) {
                    game.getFavorTable().useFavor(game, player, sc);
                }
            } else if (this == game.getCastle().getWalls()) {
                switch (numberOfHouses) {
                    case 1:
                        break;
                    case 3:
                    case 4:
                        player.setFavors(player.getFavors() + this.scoreFavors + 1);
                        System.out.println(player.getColor() + " earns "
                                + (scoreFavors + 1) + " favors");
                        int max = player.getFavors();
                        if (player.getFavors() > 4) {
                            max = 4;
                        }
                        player.setFavors(0);
                        for (int i = max; i > 0; i--) {
                            game.getFavorTable().useFavor(game, player, sc);
                        }
                        break;
                    default:
                        player.setFavors(player.getFavors() + this.scoreFavors + 2);
                        System.out.println(player.getColor() + " earns "
                                + (scoreFavors + 2) + " favors");
                        max = player.getFavors();
                        if (player.getFavors() > 4) {
                            max = 4;
                        }
                        player.setFavors(0);
                        for (int i = max; i > 0; i--) {
                            game.getFavorTable().useFavor(game, player, sc);
                        }
                }
            } else if (this == game.getCastle().getTowers()) {
                switch (numberOfHouses) {
                    case 1:
                        break;
                    case 3:
                        player.setFavors(player.getFavors() + this.scoreFavors);
                        System.out.println(player.getColor() + " earns "
                                + scoreFavors + " favor");
                        int max = player.getFavors();
                        if (player.getFavors() > 4) {
                            max = 4;
                        }
                        player.setFavors(0);
                        for (int i = max; i > 0; i--) {
                            game.getFavorTable().useFavor(game, player, sc);
                        }
                        break;
                    case 4:
                    case 5:
                        player.setFavors(player.getFavors() + this.scoreFavors + 1);
                        System.out.println(player.getColor() + " earns "
                                + (scoreFavors + 1) + " favors");
                        max = player.getFavors();
                        if (player.getFavors() > 4) {
                            max = 4;
                        }
                        player.setFavors(0);
                        for (int i = max; i > 0; i--) {
                            game.getFavorTable().useFavor(game, player, sc);
                        }
                        break;
                    default:
                        player.setFavors(player.getFavors() + this.scoreFavors + 2);
                        System.out.println(player.getColor() + " earns "
                                + (scoreFavors + 2) + " favors");
                        max = player.getFavors();
                        if (player.getFavors() > 4) {
                            max = 4;
                        }
                        player.setFavors(0);
                        for (int i = max; i > 0; i--) {
                            game.getFavorTable().useFavor(game, player, sc);
                        }
                }
            }
        }
        this.setScored(true);
    }
}
