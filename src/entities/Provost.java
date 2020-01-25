package entities;

public class Provost {

    private static Provost provostInstance = new Provost();
    private static int position = 11;

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
        if (position < 6 || position > 33) {
            throw new IllegalArgumentException("Provost can't move any further");
        }
        Provost.position = position;
    } // end of getters setters

}
