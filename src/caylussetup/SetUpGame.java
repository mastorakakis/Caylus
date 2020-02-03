package caylussetup;

import entities.Block;
import entities.Resources;
import entities.buildings.Building;
import entities.buildings.Castle;
import entities.buildings.FixedBuilding;
import entities.buildings.Gate;
import entities.buildings.Inn;
import entities.buildings.NeutralBuilding;
import entities.buildings.PrestigeBuilding;
import entities.buildings.SpecialBuilding;
import entities.buildings.StoneBuilding;
import entities.buildings.WoodBuilding;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetUpGame {

    public List<Building> getBuildingList() {

        // buildings list available for building
        List<Building> buildingsList = new ArrayList();
        buildingsList.add(new WoodBuilding(2, new Resources(1, 1, 0, 0, 0),
                0, new Resources(0, 0, 0, 0, 0), "Wood Farm A"));
        buildingsList.add(new WoodBuilding(2, new Resources(1, 1, 0, 0, 0),
                0, new Resources(0, 0, 0, 0, 0), "Wood Farm B"));
        buildingsList.add(new WoodBuilding(4, new Resources(0, 1, 0, 0, 0),
                6, new Resources(0, 0, 0, 0, 0), "Wood Market Place"));
        buildingsList.add(new WoodBuilding(4, new Resources(0, 1, 0, 0, 0),
                0, new Resources(0, 0, 0, 0, 0), "Wood Peddler"));
        buildingsList.add(new WoodBuilding(2, new Resources(1, 1, 0, 0, 0),
                0, new Resources(0, 0, 2, 0, 0), "Wood Quarry"));
        buildingsList.add(new WoodBuilding(2, new Resources(1, 1, 0, 0, 0),
                0, new Resources(0, 2, 0, 0, 0), "Wood Sawmill"));
        buildingsList.add(new WoodBuilding(4, new Resources(1, 1, 0, 0, 0),
                0, new Resources(0, 0, 0, 0, 0), "Mason"));
        buildingsList.add(new WoodBuilding(4, new Resources(0, 1, 0, 1, 0),
                0, new Resources(0, 0, 0, 0, 0), "Lawyer"));
        buildingsList.add(new StoneBuilding(3, 0,
                new Resources(1, 0, 1, 0, 0), 0, 0, new Resources(1, 2, 0, 0, 0),
                new Resources(0, 0, 0, 0, 0), "Park"));
        buildingsList.add(new StoneBuilding(3, 0,
                new Resources(1, 0, 1, 0, 0), 0, 0, new Resources(2, 0, 0, 1, 0),
                new Resources(0, 0, 0, 0, 0), "Stone Farm"));
        buildingsList.add(new StoneBuilding(3, 0,
                new Resources(1, 0, 1, 0, 0), 0, 0, new Resources(0, 0, 2, 1, 0),
                new Resources(0, 0, 0, 0, 0), "Workshop"));
        buildingsList.add(new StoneBuilding(6, 0,
                new Resources(0, 1, 1, 0, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
                new Resources(0, 0, 0, 0, 0), "Bank"));
        buildingsList.add(new StoneBuilding(3, 1,
                new Resources(0, 0, 1, 1, 0), 1, 0, new Resources(0, 0, 0, 0, 0),
                new Resources(0, 0, 0, 0, 0), "Church"));
        buildingsList.add(new StoneBuilding(6, 0,
                new Resources(1, 0, 1, 0, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
                new Resources(0, 0, 0, 0, 0), "Alchemist"));
        buildingsList.add(new StoneBuilding(6, 0,
                new Resources(0, 0, 1, 1, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
                new Resources(0, 0, 0, 0, 0), "Jeweler"));
        buildingsList.add(new StoneBuilding(6, 0,
                new Resources(0, 1, 1, 0, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
                new Resources(0, 0, 0, 0, 0), "Tailor"));
        buildingsList.add(new StoneBuilding(6, 0,
                new Resources(1, 0, 1, 0, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
                new Resources(0, 0, 0, 0, 0), "Architect A"));
        buildingsList.add(new StoneBuilding(6, 0,
                new Resources(1, 0, 1, 0, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
                new Resources(0, 0, 0, 0, 0), "Architect B"));
        buildingsList.add(new PrestigeBuilding(7, 1,
                new Resources(0, 0, 2, 0, 1), 0, "Statue"));
        buildingsList.add(new PrestigeBuilding(10, 0,
                new Resources(0, 3, 0, 0, 1), 1, "Library"));
        buildingsList.add(new PrestigeBuilding(16, 0,
                new Resources(0, 0, 3, 0, 2), 2, "Hotel"));
        buildingsList.add(new PrestigeBuilding(14, 1,
                new Resources(0, 3, 0, 0, 2), 0, "Theater"));
        buildingsList.add(new PrestigeBuilding(10, 0,
                new Resources(3, 0, 0, 0, 1), 0, "Granary"));
        buildingsList.add(new PrestigeBuilding(12, 0,
                new Resources(0, 0, 0, 3, 1), 0, "Weaver"));
        buildingsList.add(new PrestigeBuilding(14, 1,
                new Resources(0, 0, 3, 0, 2), 0, "College"));
        buildingsList.add(new PrestigeBuilding(25, 0,
                new Resources(0, 0, 5, 0, 3), 0, "Cathredal"));
        buildingsList.add(new PrestigeBuilding(14, 2,
                new Resources(0, 0, 4, 0, 2), 0, "Monument"));
        return buildingsList;
    }

    public List<Block> getRoad() {

        List<Block> road = new ArrayList(34);
        //neutral buildings
        List<Building> neutralBuildings = new ArrayList();
        neutralBuildings.add(new NeutralBuilding(0, new Resources(0, 0, 1, 0, 0),
                "Neutral Quarry"));
        neutralBuildings.add(new NeutralBuilding(0, new Resources(0, 1, 0, 0, 0),
                "Neutral Sawmill"));
        neutralBuildings.add(new NeutralBuilding(0, new Resources(0, 0, 0, 0, 0),
                "Neutral Farm"));
        neutralBuildings.add(new NeutralBuilding(0, new Resources(0, 0, 0, 0, 0),
                "Forest"));
        neutralBuildings.add(new NeutralBuilding(4, new Resources(0, 0, 0, 0, 0),
                "Neutral Market Place"));
        neutralBuildings.add(new NeutralBuilding(0, new Resources(0, 0, 0, 0, 0),
                "Neutral Carpenter"));
        Collections.shuffle(neutralBuildings); // Shuffle neutral buildings
        // add castle
        road.add(new Block(new Castle("Castle")));
        // add special buildings to getRoad
        road.add(new Block(new Gate("Gate")));
        road.add(new Block(new SpecialBuilding(3, new Resources(0, 0, 0, 0, 0), 0,
                "Trading Post")));
        road.add(new Block(new SpecialBuilding(0, new Resources(0, 0, 0, 0, 0), 0,
                "Merchant's Guild")));
        road.add(new Block(new SpecialBuilding(1, new Resources(0, 0, 0, 1, 0), 1,
                "Joust Field")));
        road.add(new Block(new SpecialBuilding(0, new Resources(0, 0, 0, 0, 0), 0,
                "Stables")));
        road.add(new Block(new Inn("Inn")));
        // Add neutral buildings to getRoad
        for (Building neutralBuilding : neutralBuildings) {
            road.add(new Block(neutralBuilding));
        }// Add 2 fixed buildings
        road.add(new Block(new FixedBuilding(2, new Resources(0, 0, 0, 0, 0),
                "Fixed Peddler")));
        road.add(new Block(new FixedBuilding(0, new Resources(0, 0, 0, 0, 0),
                "Fixed Carpenter")));
        // add 7 empty blocks
        for (int i = 0; i < 7; i++) {
            road.add(new Block());
        }// Add gold mine
        road.add(new Block(new FixedBuilding(0, new Resources(0, 0, 0, 0, 1),
                "Gold Mine")));
        // Add 12 empty blocks
        for (int i = 0; i < 12; i++) {
            road.add(new Block());
        }
        return road;
    }
}
// TODO delete number of user players 5 and com players 3
