package GameComponents.Board;

import GameComponents.Player;

public class Deed {
    Player owner;
    int[] rent;
    int deedPrice;
    int buildingPrice;
    String deedName;
    String color;
    int groupSize;

    int houseCount;

    /**
     * Constructs a Deed which can be owned by Player
     *
     * @param deedPrice price of the Deed
     * @param deedName  name of the Deed (For example "The Skate Park")
     * @param color
     */
    public Deed(int deedPrice, int buildingPrice, int[] rent, String deedName){

        this.deedPrice = deedPrice;
        this.buildingPrice = buildingPrice;
        this.rent = rent;
        this.deedName = deedName;
        //this.color = color;
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

    public String getColor() {
        return color;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;

    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setHouseCount(int houseCount) {
        this.houseCount = houseCount;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public int getBuildingPrice() {
        return buildingPrice;
    }
}
