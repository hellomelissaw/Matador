package GameComponents.Board;

import GameComponents.Player;

public class Deed {

    Player ownerName;
    // int[] squarePrice; // i Matador mangler vi en array med alle priser på et skød
    int squarePrice;
    String name;

    public Deed(int squarePrice,String name){
        //this.owner = owner;
        this.squarePrice = squarePrice;
        this.name=name;
    }

    public int getSquarePrice() {
        return squarePrice ;
    }

    public Player getOwner() {
        return ownerName;
    }
    public void setOwner(Player ownerName){ // Not sure where we will use setOwner method yet

        this.ownerName = ownerName;

    }
}
