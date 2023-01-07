package GameComponents.Board;
import GameComponents.Player;

public class DeedSquare_Buildable extends DeedSquare {
    int buildingPrice;
    int houseCount = 0;
    boolean hasHotel = false;
    Deed_Buildable deed;
    /**
     * Constructs a Square of type DeedSquare (ownable Square)
     *
     * @param deedName      name of the Deed for the Square (for example "The Skate Park").
     * @param deedPrice     the price of the deed, both for buying and amount of rent to pay once bought.
     * @param rent
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

        if(sellDeed == true) {

            if (guiOn) {
                String[] choices = {"ja", "nej"};
                if(!testing) {
                    buying = guiController.getUserSelection("buyLot", choices);
                }
                /*String guiMessage = currentPlayer.getPlayerName() + msg.getText("haveBought") + deed.getDeedName();
                guiController.showMessage(guiMessage); // CAN BE DELETED ONCE IMPLEMENT BORDER AROUND SQUARE*/
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
                    System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());
                    sellDeed = false;
                    freeDeed = false;
                    deed.setOwner(currentPlayer);
                    currentPlayer.takeBuildableDeed(deed);
                    if (guiOn) {
                        guiController.setOwnerName(currentPlayer, currentPlayer.getPosition());
                    }

                } else if (!(buying.equals("ja") || buying.equals("nej"))) {
                    System.out.println("Ugyldigt svar. Indtast venligst ja eller nej");

                } else {
                    valid = true;
                    System.out.println("Spilleren køber ikke grunden.");
                }
            }

        } else {
            Player deedOwner = deed.getOwner();
            if (currentPlayer==deedOwner) {
                msg.printText("ownerOfDeed", "na");

            } else {

                msg.printText("payRent", "na");
                currentPlayer.withdrawMoney(rent[houseCount]);
                deedOwner.depositMoney(deedPrice);
                System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());

            }

            System.out.println("");
        }

    }


    public int getHouseCount(){
        return houseCount;

    }

    public boolean hasHotel() {
        return hasHotel;
    }

    public void setHouseCount(int count) {
        houseCount = count;
        deed.setHouseCount(count);
    }

    public void setHasHotel(boolean hasHotel) {
        this.hasHotel = hasHotel;
        deed.setHasHotel(hasHotel);
    }

    public Player getDeedOwner() {
        return deed.getOwner();
    }

    public void setGroup(String color, int groupSize) {
        this.color = color;
        this.groupSize = groupSize;
        deed.setDeedGroup(color, groupSize);
    }

}
