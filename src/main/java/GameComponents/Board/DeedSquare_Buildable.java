package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;

public class DeedSquare_Buildable extends DeedSquare {
    int buildingPrice;
    int houseCount = 0;
    boolean hasHotel = false;

    DeedBuildable deed;
    /**
     * Constructs a Square of type DeedSquare (ownable Square)
     *
     * @param deedName      name of the Deed for the Square (for example "The Skate Park").
     * @param deedPrice     the price of the deed, both for buying and amount of rent to pay once bought.
     * @param rent
     * @param guiController The GuiController used throughout the classes.
     */
    public DeedSquare_Buildable(String deedName, int deedPrice, int[] rent, GuiController guiController, int buildingPrice) {
        super(deedName, deedPrice, rent, guiController);
        this.deed = new DeedBuildable(deedPrice, rent, deedName, buildingPrice);
        this.buildingPrice = buildingPrice;
    }

    public void landOn(Player currentPlayer) {

        if(sellDeed == true) {

            if (guiOn) {
                String guiMessage = currentPlayer.getPlayerName() + msg.getText("haveBought") + deed.getDeedName();
                guiController.showMessage(guiMessage); // CAN BE DELETED ONCE IMPLEMENT BORDER AROUND SQUARE
            } else {
                System.out.println("Vil du købe denne grund?");
            }

            boolean valid = false;
            while(!valid) {

                if(!testing){
                    buying = userInput.nextLine();
                }

                if (buying.equals("ja")) {
                    valid = true;
                    currentPlayer.withdrawMoney(deedPrice);
                    System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());
                    sellDeed = false;
                    freeDeed = false;
                    deed.setOwner(currentPlayer);
                    currentPlayer.takeCard("deed", deed);
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


    public Deed getDeed() {
        return deed;
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
}
