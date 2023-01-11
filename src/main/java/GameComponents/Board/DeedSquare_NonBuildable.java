package GameComponents.Board;

import GameComponents.Player;

public class DeedSquare_NonBuildable extends DeedSquare {
    Deed_NonBuildable deed;
    /**
     * Constructs a Square of type DeedSquare which cannot be built on
     *
     * @param deedName      name of the Deed for the Square (for example "The Skate Park").
     * @param deedPrice     the price it costs to buy the deed to a lot
     * @param rent          an array of rent amounts player can receive according to buildings on lot
     */
    public DeedSquare_NonBuildable(String deedName, int deedPrice, int[] rent) {
        super(deedName, deedPrice, rent);
        deed = new Deed_NonBuildable(deedPrice, rent, deedName);
        deed.setDeedGroup(color, groupSize);


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
                    System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());
                    sellDeed = false;
                    freeDeed = false;
                    deed.setOwner(currentPlayer);
                    currentPlayer.takeNonBuildableDeed(deed);
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

                msg.printText("payRent", deedOwner.getPlayerName());
                int rentPrice;

                currentPlayer.withdrawMoney(rent[0]); //TO DO: CHECK IF PLAYER OWNS OTHER LOTS IN GROUP TO CHANGE RENT
                deedOwner.depositMoney(deedPrice);
                System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());

            }

            System.out.println("");
        }

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
