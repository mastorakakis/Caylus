package caylussetup;

import entities.buildings.FixedBuilding;
import entities.buildings.SpecialBuilding;
import entities.Resources;
import entities.buildings.Castle;
import entities.buildings.Gate;
import entities.buildings.Inn;
import entities.buildings.NeutralBuilding;
import entities.buildings.PrestigeBuilding;
import entities.buildings.StoneBuilding;
import entities.buildings.WoodBuilding;

public class BuildingObjects {

    // Unique Buildings
    protected static Castle castle = Castle.getCastleInstance();
    protected static Gate gate = Gate.getGateInstance();
    protected static Inn inn = Inn.getInnInstance();

    // Special Buildings
    protected static SpecialBuilding tradingPost = new SpecialBuilding(3,
            new Resources(0, 0, 0, 0, 0), 0, "Trading Post");
    protected static SpecialBuilding merchantsGuild = new SpecialBuilding(0,
            new Resources(0, 0, 0, 0, 0), 0, "Merchant's Guild");
    protected static SpecialBuilding joustField = new SpecialBuilding(1,
            new Resources(0, 0, 0, 1, 0), 1, "Joust Field");
    protected static SpecialBuilding stables = new SpecialBuilding(0,
            new Resources(0, 0, 0, 0, 0), 0, "Stables");

    // Fixed Buildings
    protected static FixedBuilding fixedPeddler = new FixedBuilding(2,
            new Resources(0, 0, 0, 0, 0), "Fixed Peddler");
    protected static FixedBuilding fixedCarpenter = new FixedBuilding(0,
            new Resources(0, 0, 0, 0, 0), "Fixed Carpenter");
    protected static FixedBuilding goldMine = new FixedBuilding(0,
            new Resources(0, 0, 0, 0, 1), "Gold Mine");

    // Neutral Buildings
    protected static NeutralBuilding neutralQuarry = new NeutralBuilding(0,
            new Resources(0, 0, 1, 0, 0), "Neutral Quarry");
    protected static NeutralBuilding neutralSawmill = new NeutralBuilding(0,
            new Resources(0, 1, 0, 0, 0), "Neutral Sawmill");
    protected static NeutralBuilding neutralFarm = new NeutralBuilding(0,
            new Resources(0, 0, 0, 0, 0), "Neutral Farm");
    protected static NeutralBuilding forest = new NeutralBuilding(0,
            new Resources(0, 0, 0, 0, 0), "Forest");
    protected static NeutralBuilding neutralMarketPlace = new NeutralBuilding(4,
            new Resources(0, 0, 0, 0, 0), "Neutral Market Place");
    protected static NeutralBuilding neutralCarpenter = new NeutralBuilding(0,
            new Resources(0, 0, 0, 0, 0), "Neutral Carpenter");

    // Wood Buildings //TODO make private
    public static WoodBuilding woodFarmA = new WoodBuilding(2, new Resources(1, 1, 0, 0, 0),
            0, new Resources(0, 0, 0, 0, 0), "Wood Farm A");
    public static WoodBuilding woodFarmB = new WoodBuilding(2, new Resources(1, 1, 0, 0, 0),
            0, new Resources(0, 0, 0, 0, 0), "Wood Farm B");
    public static WoodBuilding woodMarketPlace = new WoodBuilding(4, new Resources(0, 1, 0, 0, 0),
            6, new Resources(0, 0, 0, 0, 0), "Wood Market Place");
    public static WoodBuilding peddler = new WoodBuilding(4, new Resources(0, 1, 0, 0, 0),
            0, new Resources(0, 0, 0, 0, 0), "Wood Peddler");
    public static WoodBuilding woodQuarry = new WoodBuilding(2, new Resources(1, 1, 0, 0, 0),
            0, new Resources(0, 0, 2, 0, 0), "Wood Quarry");
    public static WoodBuilding woodSawmill = new WoodBuilding(2, new Resources(1, 1, 0, 0, 0),
            0, new Resources(0, 2, 0, 0, 0), "Wood Sawmill");
    public static WoodBuilding mason = new WoodBuilding(4, new Resources(1, 1, 0, 0, 0),
            0, new Resources(0, 0, 0, 0, 0), "Mason");
    public static WoodBuilding lawyer = new WoodBuilding(4, new Resources(0, 1, 0, 1, 0),
            0, new Resources(0, 0, 0, 0, 0), "Lawyer");

    // Stone Buildings
    public static StoneBuilding park = new StoneBuilding(3, 0,
            new Resources(1, 0, 1, 0, 0), 0, 0, new Resources(1, 2, 0, 0, 0),
            new Resources(0, 0, 0, 0, 0), "Park");
    public static StoneBuilding stoneFarm = new StoneBuilding(3, 0,
            new Resources(1, 0, 1, 0, 0), 0, 0, new Resources(2, 0, 0, 1, 0),
            new Resources(0, 0, 0, 0, 0), "Stone Farm");
    public static StoneBuilding workshop = new StoneBuilding(3, 0,
            new Resources(1, 0, 1, 0, 0), 0, 0, new Resources(0, 0, 2, 1, 0),
            new Resources(0, 0, 0, 0, 0), "Workshop");
    public static StoneBuilding bank = new StoneBuilding(6, 0,
            new Resources(0, 1, 1, 0, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
            new Resources(0, 0, 0, 0, 0), "Bank");
    public static StoneBuilding church = new StoneBuilding(3, 1,
            new Resources(0, 0, 1, 1, 0), 1, 0, new Resources(0, 0, 0, 0, 0),
            new Resources(0, 0, 0, 0, 0), "Church");
    public static StoneBuilding alchemisth = new StoneBuilding(6, 0,
            new Resources(1, 0, 1, 0, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
            new Resources(0, 0, 0, 0, 0), "Alchemist");
    public static StoneBuilding jeweler = new StoneBuilding(6, 0,
            new Resources(0, 0, 1, 1, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
            new Resources(0, 0, 0, 0, 0), "Jeweler");
    public static StoneBuilding tailor = new StoneBuilding(6, 0,
            new Resources(0, 1, 1, 0, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
            new Resources(0, 0, 0, 0, 0), "Tailor");
    public static StoneBuilding architectA = new StoneBuilding(6, 0,
            new Resources(1, 0, 1, 0, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
            new Resources(0, 0, 0, 0, 0), "Architect A");
    public static StoneBuilding architectB = new StoneBuilding(6, 0,
            new Resources(1, 0, 1, 0, 0), 0, 0, new Resources(0, 0, 0, 0, 0),
            new Resources(0, 0, 0, 0, 0), "Architect B");

    // Residential buildings are not limited and all have same attributes
    //
    // Prestige Buildings
    protected static PrestigeBuilding statue = new PrestigeBuilding(7, 1,
            new Resources(0, 0, 2, 0, 1), 0, "Statue");
    protected static PrestigeBuilding library = new PrestigeBuilding(10, 0,
            new Resources(0, 3, 0, 0, 1), 1, "Library");
    protected static PrestigeBuilding hotel = new PrestigeBuilding(16, 0,
            new Resources(0, 0, 3, 0, 2), 2, "Hotel");
    protected static PrestigeBuilding theater = new PrestigeBuilding(14, 1,
            new Resources(0, 3, 0, 0, 2), 0, "Theater");
    protected static PrestigeBuilding granary = new PrestigeBuilding(10, 0,
            new Resources(3, 0, 0, 0, 1), 0, "Granary");
    protected static PrestigeBuilding weaver = new PrestigeBuilding(12, 0,
            new Resources(0, 0, 0, 3, 1), 0, "Weaver");
    protected static PrestigeBuilding college = new PrestigeBuilding(14, 1,
            new Resources(0, 0, 3, 0, 2), 0, "College");
    protected static PrestigeBuilding cathredal = new PrestigeBuilding(25, 0,
            new Resources(0, 0, 5, 0, 3), 0, "Cathredal");
    protected static PrestigeBuilding monument = new PrestigeBuilding(14, 2,
            new Resources(0, 0, 4, 0, 2), 0, "Monument");

}
