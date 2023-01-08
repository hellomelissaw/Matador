package GameComponents.Board;

public class Deed_Buildable extends Deed{
    int buildingPrice;

    /**
     * Constructs a Deed which can be owned by Player
     *
     * @param deedPrice     price of the Deed
     * @param buildingPrice
     * @param rent
     * @param deedName      name of the Deed (For example "The Skate Park")
     */
    public Deed_Buildable(int deedPrice, int[] rent, String deedName, int buildingPrice) {
        super(deedPrice, rent, deedName);
        this.buildingPrice = buildingPrice;
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

    public void setHasHotel(boolean hasHotel) {
        this.hasHotel = hasHotel;
    }
}
