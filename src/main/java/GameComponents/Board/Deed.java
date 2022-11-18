package GameComponents.Board;

import GameComponents.player;

public class Deed {


    player owner;
    // int[] squarePrice; // i Matador mangler vi en array med alle priser på et skød
    int squarePrice;
    String deedName;

    public Deed(int squarePrice,String deedName){
        this.squarePrice = squarePrice;
        this.deedName = deedName;
    }

    public player getOwner() {
        return owner;
    }
    public void setOwner(player ownerName){

        this.owner = ownerName;

    }

}
