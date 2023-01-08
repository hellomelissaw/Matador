package GameComponents.Board;

public class Deed_NonBuildable extends Deed {
    /**
     * Constructs a Deed which can be owned by Player
     *
     * @param deedPrice price of the Deed
     * @param rent
     * @param deedName  name of the Deed (For example "The Skate Park")
     */
    public Deed_NonBuildable(int deedPrice, int[] rent, String deedName) {
        super(deedPrice, rent, deedName);
    }
}
