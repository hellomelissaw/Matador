package GameComponents.Board;
import GameComponents.Player;

public class DeedSquare_Buildable extends DeedSquare {
    int buildingPrice;
    int houseCount = 0;
    boolean hasHotel = false;
    Deed_Buildable deed;
    /**
     * Constructs a Square of type DeedSquare which one can build on
     *
     * @param deedName      name of the Deed for the Square (for example "The Skate Park").
     * @param deedPrice     the price it costs to buy the deed to a lot
     * @param rent          an array of rent amounts player can receive according to buildings on lot
     * @param buildingPrice the cost of building one house or hotel
     */
    public DeedSquare_Buildable(String deedName, int deedPrice, int[] rent, int buildingPrice) {
        super(deedName, deedPrice, rent);
        this.buildingPrice = buildingPrice;
        deed = new Deed_Buildable(deedPrice, rent, deedName, buildingPrice);

    }

    public Deed_Buildable getDeed() {
        return deed;
    }


    public void landOn(Player currentPlayer) {

        if(sellDeed == true) { // IF DEED IS AVAILABLE TO BUY

            if (guiIsOn) {
                String[] choices = {"ja", "nej"};
                if(!testing) {
                    buying = guiController.getUserSelection(msg.getText("buyLot") + " " + deed.getDeedName() + "?", choices);
                }

            } else {
                System.out.println("Vil du købe denne grund?");
                if(!testing){
                    buying = userInput.nextLine();
                }
            }

            boolean valid = false;
            while(!valid) {

                if (buying.equals("ja")) {
                    valid = true;
                    currentPlayer.withdrawMoney(deedPrice);
                    currentPlayer.updateBank(deedPrice,"withdraw");
                    System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());
                    sellDeed = false;
                    freeDeed = false;
                    deed.setOwner(currentPlayer);
                    currentPlayer.takeBuildableDeed(deed);
                    if (guiIsOn) {
                        guiController.setOwnerName(currentPlayer, currentPlayer.getPosition());
                    }

                } else if (!(buying.equals("ja") || buying.equals("nej"))) {
                    System.out.println("Ugyldigt svar. Indtast venligst ja eller nej");

                } else {
                    valid = true;
                    System.out.println("Spilleren køber ikke grunden.");
                }
            }

        } else { // IF DEED IS ALREADY OWNED
            Player deedOwner = deed.getOwner();
            if (currentPlayer==deedOwner) { // IF PLAYER HAS LANDED ON A LOT THAT THEY OWN
                msg.printText("ownerOfDeed", "na");

            } else { // IF A PLAYER LANDS ON A LOT THAT ANOTHER PLAYER OWNS

                msg.printText("payRent",  deedOwner.getPlayerName());
                currentPlayer.withdrawMoney(rent[houseCount]);
                deedOwner.depositMoney(rent[houseCount]);
                System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());

            }

            System.out.println("");
        }

    }


    public int getHouseCount(){
        return houseCount;

    }

    public void setHouseCount(int count) {
        houseCount = count;
        deed.setHouseCount(count);
    }

    public boolean hasHotel() {
        return hasHotel;
    }

    public void setHasHotel(boolean hasHotel) {
        this.hasHotel = hasHotel;
        deed.setHasHotel(hasHotel);
    }

    public Player getDeedOwner() {
        return deed.getOwner();
    }

    /**
     * Sets the group which this square belongs to according to its color and the group's size
     * @param color the square's color
     * @param groupSize amount of squares which have same color
     */
    public void setGroup(String color, int groupSize) {
        this.color = color;
        this.groupSize = groupSize;
        deed.setDeedGroup(color, groupSize);
    }

}
