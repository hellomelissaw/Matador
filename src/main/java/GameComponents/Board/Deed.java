package GameComponents.Board;

import GameComponents.Player;

public class Deed {


    Player owner;
    // int[] squarePrice; // i Matador mangler vi en array med alle priser på et skød
    int squarePrice;
    String deedName;

    public Deed(int squarePrice,String deedName){
        //this.owner = owner;
        this.squarePrice = squarePrice;
        this.deedName = deedName;
    }

    public int getSquarePrice() {
        return squarePrice ;
    }

    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player ownerName){ // Not sure where we will use setOwner method yet

        this.owner = ownerName;

    }

}
