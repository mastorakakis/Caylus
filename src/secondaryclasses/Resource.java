package secondaryclasses;

public class Resource {

    private int food;
    private int wood;
    private int stone;
    private int cloth;
    private int gold;

    public Resource(int food, int wood, int stone, int cloth, int gold) {
        this.food = food;
        this.wood = wood;
        this.stone = stone;
        this.cloth = cloth;
        this.gold = gold;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getStone() {
        return stone;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public int getCloth() {
        return cloth;
    }

    public void setCloth(int cloth) {
        this.cloth = cloth;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return "Resource{" + "food=" + food + ", wood=" + wood + ", stone="
                + stone + ", cloth=" + cloth + ", gold=" + gold + '}';
    }
}
