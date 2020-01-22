//package xFunctions;
//
//import entities.buildings.Building;
//import entities.players.Player;
//import java.util.Scanner;
//import otherClasses.Resources;
//import otherClasses.SelectAction;
//import utilities.Functions;
//
//public class xWoodBuilding {

//    public enum WoodBuilding implements Building {
//        FARM_A(2, 0, new Resources(1, 1, 0, 0, 0), new Resources(0, 0, 0, 0, 0)), // 2 food or 1 cloth production
//        FARM_B(2, 0, new Resources(1, 1, 0, 0, 0), new Resources(0, 0, 0, 0, 0)), // 2 cloth or 1 food production
//        MARKET_PLACE(4, 6, new Resources(0, 1, 0, 0, 0), new Resources(0, 0, 0, 0, 0)), // plus 1 resource of choice build cost
//        PEDDLER(4, 0, new Resources(0, 1, 0, 0, 0), new Resources(0, 0, 0, 0, 0)), // plus 1 resource of choice build cost
//        QUARRY(2, 0, new Resources(1, 1, 0, 0, 0), new Resources(0, 0, 2, 0, 0)),
//        SAWMILL(2, 0, new Resources(1, 1, 0, 0, 0), new Resources(0, 2, 0, 0, 0)),
//        MASON(4, 0, new Resources(1, 1, 0, 0, 0), new Resources(0, 0, 0, 0, 0)),
//        LAWYER(4, 0, new Resources(0, 1, 0, 1, 0), new Resources(0, 0, 0, 0, 0));
//
//        private int points;
//        private int productionMoney;
//        private Resources resources;
//        private Resources productionResources;
//        private Player player;
//        private Player house;
//
//        // constructor
//        private WoodBuilding(int points, int productionMoney, Resources resources, Resources productedResources) {
//            this.points = points;
//            this.productionMoney = productionMoney;
//            this.resources = resources;
//            this.productionResources = productedResources;
//        }
//
//        @Override
//        public int getPoints() {
//            return points;
//        }
//
//        @Override
//        public Resources getResources() {
//            return resources;
//        }
//
//        @Override
//        public int getProductionMoney() {
//            return productionMoney;
//        }
//
//        @Override
//        public Resources getProductionResources() {
//            return productionResources;
//        }
//
//        public void clearResources() {
//            this.resources = new Resources(0, 0, 0, 0, 0);
//        }
//
//        public void clearProductionResources() {
//            this.productionResources = new Resources(0, 0, 0, 0, 0);
//        }
//
//        public void clearProductionMoney() {
//            this.productionMoney = 0;
//        }
//
//        @Override
//        public void activate(Player player, Scanner sc) {
//            if (this == FARM_A) {
//                activateFARM_A(sc);
//            } else if (this == FARM_B) {
//                activateFARM_B(sc);
//            } else if (this == MARKET_PLACE) {
//                activateMARKET_PLACE(player, sc);
//            } else if (this == PEDDLER) {
//                activatePEDDLER(player, sc);
//            } else {
//                if (player.getResources().compareTo(this.productionResources) < 0) {
//                    System.out.println("Not enough resources to build.");
//                    return;
//                } else {
//                    player.payResources(this.productionResources);
//                }
//            }
//            player.selectFromBuilding(this.productionResources, productionMoney);
//        }
//
//        // resource production for farms
//        public void activateFARM_A(Scanner sc) {
//            clearProductionResources();
//            int choice = Functions.inputValidation(1, 2, "Select production\n"
//                    + "1. 1 Cloth 2. 2 Food ", "Invalid input.", sc);
//            switch (choice) {
//                case 1:
//                    this.productionResources.setCloth(1);
//                    break;
//                case 2:
//                    this.productionResources.setFood(2);
//                    break;
//            }
//        }
//
//        public void activateFARM_B(Scanner sc) {
//            clearProductionResources();
//            int choice = Functions.inputValidation(1, 2, "Select production\n"
//                    + "1)1 Food 2)2 Cloth ", "Invalid input.", sc);
//            switch (choice) {
//                case 1:
//                    this.productionResources.setFood(1);
//                    break;
//                case 2:
//                    this.productionResources.setCloth(2);
//                    break;
//            }
//        }
//
//        public void activateMARKET_PLACE(Player player, Scanner sc) {
//            clearProductionResources();
//            int choice = Functions.inputValidation(1, 4, "Select one resource to trade\n"
//                    + "1)Food\n2)Wood\n3)Stone\n4)Cloth", "Invalid input.", sc);
//            this.productionResources.modifyResources(SelectAction.ADD, choice, sc);
//            if (player.getResources().compareTo(this.productionResources) < 0) {
//                System.out.println("Not enough resources to build.");
//            } else {
//                player.payResources(this.productionResources);
//            }
//            clearProductionResources();
//        }
//
//        public void activatePEDDLER(Player player, Scanner sc) {
//            clearProductionMoney();
//            clearProductionResources();
//            int choice = Functions.inputValidation(1, 2, "Select amount of money to trade\n"
//                    + "1)1 denier 2)2 deniers", "Invalid input.", sc);
//            if (choice == 1) {
//                this.productionMoney = 1;
//                int choice2 = Functions.inputValidation(1, 4, "Select one resource to trade\n"
//                        + "1)Food\n2)Wood\n3)Stone\n4)Cloth", "Invalid input.", sc);
//                this.productionResources.modifyResources(SelectAction.ADD, choice2, sc);
//            } else if (choice == 2) {
//                this.productionMoney = 2;
//                int choice2 = Functions.inputValidation(1, 4, "Select first resource to trade\n"
//                        + "1)Food\n2)Wood\n3)Stone\n4)Cloth", "Invalid input.", sc);
//                this.productionResources.modifyResources(SelectAction.ADD, choice2, sc);
//                int choice3 = Functions.inputValidation(1, 4, "Select second resource to trade\n"
//                        + "1)Food\n2)Wood\n3)Stone\n4)Cloth", "Invalid input.", sc);
//                this.productionResources.modifyResources(SelectAction.ADD, choice3, sc);
//            }
//            if (player.getMoney() < this.productionMoney) {
//                System.out.println("Not enough money to trade.");
//            } else {
//                player.setMoney(player.getMoney() - this.productionMoney);
//                productionMoney = 0;
//            }
//        }
//    } //    TODO  construction method, getters setters, comments, check building values with cards
//
//}
