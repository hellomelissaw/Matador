package GameComponents.Board;

public abstract class Square {
    int pointsValue;
    private String squareName;

    Square(String squareName){

        this.squareName = squareName;
    }

    public String getSquareName() {
        return squareName;
    }
}

