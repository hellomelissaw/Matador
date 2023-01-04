package GameComponents.Board;

import GameComponents.Player;

public class Deed {
    Player owner;
    int[] rent;
    int deedPrice;
    int housePrice;
    String deedName;

    /**
     * Constructs a Deed which can be owned by Player
     * @param deedPrice price of the Deed
     * @param deedName name of the Deed (For example "The Skate Park")
     */
    public Deed(int deedPrice, int housePrice, int[] rent, String deedName){

        this.deedPrice = deedPrice;
        this.housePrice = housePrice;
        this.rent = rent;
        this.deedName = deedName;
    }

    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player ownerName){
        this.owner = ownerName;

    }

    public String getDeedName() {
        return deedName;
    }

}
