package caylussetup;

import entities.buildings.FixedBuilding;
import entities.buildings.SpecialBuilding;
import entities.Resources;
import entities.buildings.PrestigeBuilding;
import entities.buildings.WoodBuilding;

public class CreateBuildings {

    // Special Buildings
    public static SpecialBuilding gate = new SpecialBuilding(0,
            new Resources(0, 0, 0, 0, 0), 0, 0, "Gate");
    public static SpecialBuilding tradingPost = new SpecialBuilding(3,
            new Resources(0, 0, 0, 0, 0), 0, 0, "Trading Post");
    public static SpecialBuilding merchantsGuild = new SpecialBuilding(0,
            new Resources(0, 0, 0, 0, 0), 0, 0, "Merchant's Guild");
    public static SpecialBuilding joustField = new SpecialBuilding(1,
            new Resources(0, 0, 0, 1, 0), 0, 0, "Joust Field");
    public static SpecialBuilding stables = new SpecialBuilding(0,
            new Resources(0, 0, 0, 0, 0), 0, 0, "Stables");
    public static SpecialBuilding inn = new SpecialBuilding(1,
            new Resources(0, 0, 0, 0, 0), 0, 0, "Inn");

    // Fixed Buildings
    public static FixedBuilding fixedPeddler = new FixedBuilding(2,
            new Resources(0, 0, 0, 0, 0), "Peddler");
    public static FixedBuilding carpenter = new FixedBuilding(0,
            new Resources(0, 0, 0, 0, 0), "Carpenter");
    public static FixedBuilding goldMine = new FixedBuilding(0,
            new Resources(0, 0, 0, 0, 1), "Gold Mine");
//    public static FixedBuilding bridge = new FixedBuilding(1,
//            new Resources(0, 0, 0, 0, 0), "Bridge");

    // Wood Buildings
    public static WoodBuilding farmA = new WoodBuilding(2, new Resources(1, 1, 0, 0, 0),
            0, new Resources(0, 0, 0, 0, 0), "Farm A");
    public static WoodBuilding farmB = new WoodBuilding(2, new Resources(1, 1, 0, 0, 0),
            0, new Resources(0, 0, 0, 0, 0), "Farm B");
    public static WoodBuilding marketPlace = new WoodBuilding(4, new Resources(0, 1, 0, 0, 0),
            6, new Resources(0, 0, 0, 0, 0), "Market Place");
    public static WoodBuilding peddler = new WoodBuilding(4, new Resources(0, 4, 0, 0, 0),
            1, new Resources(0, 0, 0, 0, 0), "Peddler");
    public static WoodBuilding quarry = new WoodBuilding(2, new Resources(1, 1, 0, 0, 0),
            0, new Resources(0, 0, 2, 0, 0), "Quarry");
    public static WoodBuilding sawmill = new WoodBuilding(2, new Resources(1, 1, 0, 0, 0),
            0, new Resources(0, 2, 0, 0, 0), "Sawmill");
    public static WoodBuilding mason = new WoodBuilding(4, new Resources(1, 1, 0, 0, 0),
            0, new Resources(0, 0, 0, 0, 0), "Mason");
    public static WoodBuilding lawyer = new WoodBuilding(4, new Resources(0, 1, 0, 1, 0),
            0, new Resources(0, 0, 0, 0, 0), "Lawyer");

    // Prestige Buildings
    public static PrestigeBuilding statue = new PrestigeBuilding(7,
            new Resources(0, 0, 2, 0, 1), 1, "Statue");
    public static PrestigeBuilding library = new PrestigeBuilding(10,
            new Resources(0, 3, 0, 0, 1), 0, "Library");
    public static PrestigeBuilding hotel = new PrestigeBuilding(16, new Resources(0, 0, 3, 0, 1), 0, "Hotel");
}
// TODO add prestige buildings
