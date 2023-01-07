package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;

public class DeedSquare_NonBuildable extends DeedSquare {
    Deed_NonBuildable deed;
    String deedName;
    int deedPrice;
    int[] rent;
    GuiController guiController;

    public DeedSquare_NonBuildable(String deedName, int deedPrice, int[] rent, GuiController guiController) {
        super(deedName, deedPrice, rent, guiController);
        deed = new Deed_NonBuildable(deedPrice, rent, deedName);
        this.deedName = deedName;
        this.deedPrice = deedPrice;
        this.guiController = guiController;
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
                int rentPrice;

                currentPlayer.withdrawMoney(rent[0]); //TO DO: CHECK IF PLAYER OWNS OTHER LOTS IN GROUP TO CHANGE RENT
                deedOwner.depositMoney(deedPrice);
                System.out.println(msg.getText("newBalance") + currentPlayer.getCurrentBalance());

            }

            System.out.println("");
        }

    }

    public void setGroup(boolean testing) {
      if(deedName.equals"")
    }

}
