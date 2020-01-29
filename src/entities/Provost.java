package entities;

import java.io.Serializable;

public class Provost implements Serializable {

    private static Provost provostInstance = new Provost();
    private static int position = 12;

    // constructor private
    private Provost() {
    }

    // getters setters
    public static Provost getProvostInstance() {
        return provostInstance;
    }

    public static int getPosition() {
        return position;
    }

    public static void setPosition(int position) {
        if (position < 7 || position > 34) {
            throw new IllegalArgumentException("Provost's movement is out of limits");
        }
        Provost.position = position;
    } // end of getters setters

}
