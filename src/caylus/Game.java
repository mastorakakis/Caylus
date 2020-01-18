package caylus;

import entities.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    // creates a list of players in random order
    public List<Player> createPlayerList(Scanner sc) {
        players = new ArrayList();
        int numberOfComPlayers = 0;
        int numberOfUserPlayers = SetUpGame.numberOfUserPlayers(sc);
        if (numberOfUserPlayers != SetUpGame.MAX_PLAYERS) {
            numberOfComPlayers = SetUpGame.numberOfComPlayers(sc, numberOfUserPlayers);
        }
        SetUpGame.addUserPlayers(numberOfUserPlayers, players);
        if (numberOfComPlayers != 0) {
            SetUpGame.addComPlayers(numberOfComPlayers, players);
        }
        SetUpGame.randomOrderList(players);
        return players;
    }
}
