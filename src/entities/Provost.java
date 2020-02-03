package entities;

import java.io.Serializable;

public class Provost implements Serializable {

    private int position = 12;

    // constructor private
    public Provost() {
    }

    // getters setters
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        if (position < 7 || position > 34) {
            throw new IllegalArgumentException("Provost's movement is out of limits");
        }
        this.position = position;
    } // end of getters setters

}
