package GameComponents.Board;

import GameComponents.Player;
import gui_fields.GUI_Player;

public class Deed {
    Player owner;
    // int[] squarePrice; // i Matador mangler vi en array med alle priser på et skød
    int squarePrice;
    String deedName;

    public Deed(int squarePrice,String deedName){
        this.squarePrice = squarePrice;
        this.deedName = deedName;
    }

    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player ownerName){

        this.owner = ownerName;

    }

}
