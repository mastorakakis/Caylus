package entities;

import java.util.Scanner;
import enums.SelectAction;

public class Resources implements Comparable {

    private int food;
    private int wood;
    private int stone;
    private int cloth;
    private int gold;

    // constructor
    public Resources(int food, int wood, int stone, int cloth, int gold) {
        this.food = food;
        this.wood = wood;
        this.stone = stone;
        this.cloth = cloth;
        this.gold = gold;
    }

    // getter setters
    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        if (food < 0) {
            throw new IllegalArgumentException("Food cannot be negative");
        }
        this.food = food;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        if (wood < 0) {
            throw new IllegalArgumentException("Wood cannot be negative");
        }
        this.wood = wood;
    }

    public int getStone() {
        return stone;
    }

    public void setStone(int stone) {
        if (stone < 0) {
            throw new IllegalArgumentException("Stone cannot be negative");
        }
        this.stone = stone;
    }

    public int getCloth() {
        return cloth;
    }

    public void setCloth(int cloth) {
        if (cloth < 0) {
            throw new IllegalArgumentException("Cloth cannot be negative");
        }
        this.cloth = cloth;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        if (gold < 0) {
            throw new IllegalArgumentException("Gold cannot be negative");
        }
        this.gold = gold;
    }// end of getters setters

    // modifying Resourses +-1 depending on SelectAction used in build method
    public void modifyResources(SelectAction s, int selection, Scanner sc) {
        int modifier = (s == SelectAction.ADD ? 1 : -1);
        switch (selection) {
            case 1:
                this.setFood(food += modifier);
                break;
            case 2:
                this.setWood(wood += modifier);
                break;
            case 3:
                this.setStone(stone += modifier);
                break;
            case 4:
                this.setCloth(cloth += modifier);
                break;
            case 5:
                this.setGold(gold += modifier);
                break;
            default:
                break;
        }
    }

    // compare resources
    // if any of the resources is less, return -1
    @Override
    public int compareTo(Object o) {
        if (this.food < ((Resources) o).getFood() || this.wood < ((Resources) o).getWood()
                || this.stone < ((Resources) o).getStone() || this.cloth < ((Resources) o).getCloth()
                || this.gold < ((Resources) o).getGold()) {
            return -1;
        } else if (this.food == ((Resources) o).getFood() || this.wood == ((Resources) o).getWood()
                || this.stone == ((Resources) o).getStone() || this.cloth == ((Resources) o).getCloth()
                || this.gold == ((Resources) o).getGold()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Resources: Food=" + food + " Wood=" + wood + " Stone="
                + stone + " Cloth=" + cloth + " Gold=" + gold;
    }

}
