package entities;

import java.io.Serializable;

public class Bailiff implements Serializable {

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
        if (position < 7 || position > 34) {
            throw new IllegalArgumentException("Bailiff's movement if out of limits");
        }
        Bailiff.position = position;
    } // end of getters setters
}
