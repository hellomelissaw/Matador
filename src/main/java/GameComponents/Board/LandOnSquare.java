package GameComponents.Board;

public class LandOnSquare {
    Square[] square;
    public LandOnSquare(Square[] squares) {
        this.square = squares;
    }
    public void landOnDeedSquare(int newPosition) {

        if(((DeedSquare) square[newPosition]).hasDeed()) {
                System.out.println("This property is available for purchase.");
                ((DeedSquare) square[newPosition]).sellDeed();
                //player[i].withdrawMoney();

            } else if (!((DeedSquare) square[newPosition]).hasDeed()) {
                System.out.println("Sorry but u gotta pay rent.");

        }
    }

    public void landOnChanceSquare(int newPosition) {

    }

    public void landOnStartSquare (int newPosition) {

    }

    public void landOnJailSquare(int newPosition) {

    }

    public void landOnParkingSquare(int newPosition) {

    }
}
