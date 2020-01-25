package caylussetup;

import caylus.Game;
import entities.Block;
import entities.buildings.Building;
import entities.players.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SetUpGame {

    public static final int MIN_PLAYERS = 2;
    public static final int MIN_USER_PLAYERS = 0;
    public static final int MAX_PLAYERS = 5;

    // creates a list of playerList in random order
    public static List<Player> players(Scanner sc) {
        List<Player> playerList = new ArrayList();
        int numberOfComPlayers = 0;
        // selecy number of user players
        int numberOfUserPlayers = 3; // CreatePlayers.numberOfUserPlayers(sc);
        // if number of players is not max ask for com players
        if (numberOfUserPlayers != SetUpGame.MAX_PLAYERS) {
            numberOfComPlayers = 0;// CreatePlayers.numberOfComPlayers(sc, numberOfUserPlayers);
        }// add user players to the list if there are any
        if (numberOfUserPlayers != 0) {
            CreatePlayers.addUserPlayers(numberOfUserPlayers, playerList);
        }// add com players to the list if there are any
        if (numberOfComPlayers != 0) {
            CreatePlayers.addComPlayers(numberOfComPlayers, playerList);
        }
        CreatePlayers.randomOrderList(playerList); // randomize order
        return playerList;
    }

    public static List<Block> road() {

        List<Block> road = new ArrayList(34);
        //Neutral buildings
        List<Building> neutralBuildings = new ArrayList();
        neutralBuildings.add(BuildingObjects.neutralQuarry);
        neutralBuildings.add(BuildingObjects.neutralSawmill);
        neutralBuildings.add(BuildingObjects.neutralFarm);
        neutralBuildings.add(BuildingObjects.forest);
        neutralBuildings.add(BuildingObjects.neutralMarketPlace);
        neutralBuildings.add(BuildingObjects.neutralCarpenter);
        Collections.shuffle(neutralBuildings); // Shuffle neutral buildings
        // Add special buildings to road
        road.add(new Block(BuildingObjects.gate));
        road.add(new Block(BuildingObjects.tradingPost));
        road.add(new Block(BuildingObjects.merchantsGuild));
        road.add(new Block(BuildingObjects.joustField));
        road.add(new Block(BuildingObjects.stables));
        road.add(new Block(Game.inn));
        // Add neutral buildings to road
        for (Building neutralBuilding : neutralBuildings) {
            road.add(new Block(neutralBuilding));
        }// Add 2 fixed buildings
        road.add(new Block(BuildingObjects.fixedPeddler));
        road.add(new Block(BuildingObjects.fixedCarpenter));
        // add 7 empty blocks
        for (int i = 0; i < 7; i++) {
            road.add(new Block());
        }// Add gold mine
        road.add(new Block(BuildingObjects.goldMine));
        // Add 12 empty blocks
        for (int i = 0; i < 12; i++) {
            road.add(new Block());
        }
        return road;
    }
}
// delete number of user players 5 and com players 3
