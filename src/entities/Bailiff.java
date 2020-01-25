package entities;

public class Bailiff {

    private static Bailiff bailiffInstance = new Bailiff();
    private static int position = 11;

    // constructor private
    public Bailiff() {
    }

    // getters setters
    public static Bailiff getBailiffInstance() {
        return bailiffInstance;
    }

    public static int getPosition() {
        return position;
    }

    public static void setPosition(int position) {
        if (position < 6 || position > 33) {
            throw new IllegalArgumentException("Bailiff can't move any further");
        }
        Bailiff.position = position;
    } // end of getters setters
}
