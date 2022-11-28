package GameComponents.Board;

import GameComponents.Player;

public class Deed {
    Player owner;
    // int[] squarePrice; // i Matador mangler vi en array med alle priser på et skød
    int deedPrice;
    String deedName;

    /**
     * Constructs a Deed which can be owned by Player
     * @param deedPrice price of the Deed
     * @param deedName name of the Deed (For example "The Skate Park")
     */
    public Deed(int deedPrice,String deedName){
        this.deedPrice = deedPrice;
        this.deedName = deedName;
    }

    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player ownerName){

        this.owner = ownerName;

    }

    public int getDeedPrice() {
        return deedPrice;
    }

    public String getDeedName() {
        return deedName;
    }

}
