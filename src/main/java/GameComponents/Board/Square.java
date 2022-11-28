package GameComponents.Board;
import Translator.*;

public abstract class Square {
    int pointsValue;
    private String squareName;

    Square(String squareName){

        this.squareName = squareName;
    }

    abstract void landOn();

    public String getSquareName() {
        return squareName;
    }

}

