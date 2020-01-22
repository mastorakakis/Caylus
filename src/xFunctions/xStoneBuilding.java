//package xFunctions;
//
//import entities.buildings.Building;
//import entities.players.Player;
//import java.util.Scanner;
//import otherClasses.Resources;
//import otherClasses.SelectAction;
//import utilities.Functions;
//
//public class xStoneBuilding {
//    public enum StoneBuilding implements Building {
//    FARM(3, 0, 0, new Resources(1, 0, 1, 0, 0)),
//    PARK(3, 0, 0, new Resources(1, 0, 1, 0, 0)),
//    WORKSHOP(3, 0, 0, new Resources(1, 0, 1, 0, 0)),
//    BANK(6, 0, 0, new Resources(0, 1, 1, 0, 0)),
//    ALCHEMIST(6, 0, 0, new Resources(1, 0, 1, 0, 0)),
//    JEWELER(6, 0, 0, new Resources(0, 0, 1, 1, 0)),
//    CHURCH(3, 1, 0, new Resources(0, 0, 1, 1, 0)),
//    TAILOR(6, 0, 0, new Resources(0, 1, 1, 0, 0)),
//    ARCHITECT_A(6, 0, 0, new Resources(1, 0, 1, 0, 0)),
//    ARCHITECT_B(6, 0, 0, new Resources(1, 0, 1, 0, 0));
//
//    private int points;
//    private int favors;
//    private int productionMoney;
//    private Resources resources;
//    private Resources productionResources;
//    private Player player;
//    private Player house;
//
//    private StoneBuilding(int points, int favors, int productionMoney, Resources resources) {
//        this.points = points;
//        this.favors = favors;
//        this.productionMoney = productionMoney;
//        this.resources = resources;
//    }
//
//    @Override
//    public int getPoints() {
//        return points;
//    }
//
//    public int getFavors() {
//        return favors;
//    }
//
//    @Override
//    public int getProductionMoney() {
//        return productionMoney;
//    }
//
//    @Override
//    public Resources getResources() {
//        return resources;
//    }
//
//    @Override
//    public Resources getProductionResources() {
//        return productionResources;
//    }
//
//    public void clearProductionMoney() {
//        this.productionMoney = 0;
//    }
//
//    public void clearProductionResources() {
//        this.productionResources = new Resources(0, 0, 0, 0, 0);
//    }
//
//    @Override
//    public void activate(Player player, Scanner sc) {
//        if (this == FARM) {
//            activateFARM_A(sc);
//        } else if (this == PARK) {
//            activatePark(sc);
//        } else if (this == WORKSHOP) {
//            activateWorkshop(sc);
//        } else if (this == BANK) {
//            activateBank(player, sc);
//        } else if (this == ALCHEMIST) {
//            activateAlchemist(player, sc);
//        }
//        player.selectFromBuilding(this.productionResources, productionMoney);
//    }
//
//    public void activateFARM_A(Scanner sc) {
//
//    }
//
//    public void activatePark(Scanner sc) {
//
//    }
//
//    public void activateWorkshop(Scanner sc) {
//
//    }
//
//    public void activateBank(Player player, Scanner sc) {
//        clearProductionMoney();
//        clearProductionResources();
//        int choice = Functions.inputValidation(1, 2, "Select amount of money to trade\n"
//                + "1)1 denier 2)5 deniers", "Invalid input.", sc);
//        if (choice == 1) {
//            this.productionMoney = 2;
//            this.getResources().setGold(1);
//        } else if (choice == 2) {
//            this.productionMoney = 5;
//        }
//        if (player.getMoney() < this.productionMoney) {
//            System.out.println("Not enough money to trade.");
//        } else {
//            this.productionResources.modifyResources(SelectAction.ADD, 5, sc);
//            this.productionResources.modifyResources(SelectAction.ADD, 5, sc);
//            player.setMoney(player.getMoney() - this.productionMoney);
//        }
//        productionMoney = 0;
//    }
//
//    public void activateAlchemist(Player player, Scanner sc) {
//        clearProductionResources();
//        int choice = Functions.inputValidation(1, 2, "Select amount of resources to trade\n"
//                + "1)2 resources 2)4 resources", "Invalid input.", sc);
//        if (choice == 1) {
//            int choice2 = Functions.inputValidation(1, 4, "Select first resource to trade\n"
//                    + "1)Food\n2)Wood\n3)Stone\n4)Cloth", "Invalid input.", sc);
//            this.productionResources.modifyResources(SelectAction.ADD, choice2, sc);
//            choice2 = Functions.inputValidation(1, 4, "Select second resource to trade\n"
//                    + "1)Food\n2)Wood\n3)Stone\n4)Cloth", "Invalid input.", sc);
//            this.productionResources.modifyResources(SelectAction.ADD, choice2, sc);
//            player.getResources().setGold(player.getResources().getGold() + 1); // player gold +1
//        } else if (choice == 2) {
//            int choice2 = Functions.inputValidation(1, 4, "Select first resource to trade\n"
//                    + "1)Food\n2)Wood\n3)Stone\n4)Cloth", "Invalid input.", sc);
//            this.productionResources.modifyResources(SelectAction.ADD, choice2, sc);
//            choice2 = Functions.inputValidation(1, 4, "Select second resource to trade\n"
//                    + "1)Food\n2)Wood\n3)Stone\n4)Cloth", "Invalid input.", sc);
//            this.productionResources.modifyResources(SelectAction.ADD, choice2, sc);
//            choice2 = Functions.inputValidation(1, 4, "Select third resource to trade\n"
//                    + "1)Food\n2)Wood\n3)Stone\n4)Cloth", "Invalid input.", sc);
//            this.productionResources.modifyResources(SelectAction.ADD, choice2, sc);
//            choice2 = Functions.inputValidation(1, 4, "Select forth resource to trade\n"
//                    + "1)Food\n2)Wood\n3)Stone\n4)Cloth", "Invalid input.", sc);
//            this.productionResources.modifyResources(SelectAction.ADD, choice2, sc);
//            player.getResources().setGold(player.getResources().getGold() + 2); // player gold +1
//        }
//        if (player.getResources().compareTo(this.productionResources) < 0) {
//            System.out.println("Not enough resources to build.");
//        } else {
//            player.payResources(this.productionResources);
//        }
//        clearProductionResources();
//    }
//}
//}
