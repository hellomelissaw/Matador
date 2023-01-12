package GameComponents.Board;

public class Deed_Buildable extends Deed{
    int buildingPrice;

    int index;

    /**
     * Constructs a Deed which can be owned by Player
     *
     * @param deedPrice     price of the Deed
     * @param buildingPrice the cost of building one house or hotel
     * @param rent          an array of rent amounts player can receive according to buildings on lot
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

    public boolean hasHotel() {
        return hasHotel;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int i) {
        index = i;
    }
}
