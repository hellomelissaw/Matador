package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

import java.util.Scanner;

public class DeedSquare extends Square{
    boolean guiOn = false;
    boolean testing = false;
    String buying;
    Deed deed;
    boolean sellDeed = true;
    boolean freeDeed = false;
    int deedPrice;

    int buildingPrice;
    GuiController guiController;

    boolean ownsGroup = false;
    int houseCount;
    boolean hasHotel = false;
    int[] rent;
    Scanner userInput = new Scanner(System.in);

    /**
     * Constructs a Square of type DeedSquare (ownable Square)
     * @param deedName name of the Deed for the Square (for example "The Skate Park").
     * @param deedPrice the price of the deed, both for buying and amount of rent to pay once bought.
     * @param guiController The GuiController used throughout the classes.
     */
    public DeedSquare(String deedName, int deedPrice, int buildingPrice, int[] rent, GuiController guiController) {
        super(deedName);
        this.deed = new Deed(deedPrice, buildingPrice, rent, deedName);
        this.deedPrice = deedPrice;
        this.buildingPrice = buildingPrice;
        this.rent = rent;
        if(guiOn){
            this.guiController = guiController;
        } else { this.guiController = null;}
    }

    public void testing(boolean testing, String answer) {
        this.testing = testing;
        buying = answer;
    }

   public boolean hasDeed(){ // Checks if the square has a deed available to buy or if it's already sold
        return sellDeed;
    }

   public void setDeedOwner(Player currentPlayer, int deedIndex){
        sellDeed = false ;
        deed.setOwner(currentPlayer);
        if (guiOn) {guiController.setOwnerName(currentPlayer, deedIndex); }

    }

    public Player getDeedOwner() {
        return deed.getOwner();
    }

    public void setDeedToFree() {
       freeDeed = true;
    }

    @Override
    public String toString() {
        String priceString = Integer.toString(deedPrice);
        return priceString;
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

    public void setOwnsGroup(boolean ownsGroup) {
        this.ownsGroup = ownsGroup;
    }

    public void addHouse(int houseCount, Player currentPlayer) {
        if(ownsGroup) {
            int balanceToPay = houseCount * buildingPrice;
            int currentBalance = currentPlayer.getCurrentBalance();
            if(currentBalance > 0 && currentBalance - balanceToPay >= 0){
                currentPlayer.withdrawMoney(balanceToPay);
                this.houseCount += houseCount;
            } else {
                System.out.println("Du har ikke nok penge til at købe dette hus.");
            }

        } else {
            System.out.println("Du ejer ikke alle grunde i gruppen, derfor kan du ikke bygge endnu.");}
    }


    public int getHouseCount(){
        return houseCount;

    }

    public boolean hasHotel() {
        return hasHotel;
    }

    public void addHotel(Player currentPlayer) {
        if(houseCount == 4) {
            int currentBalance = currentPlayer.getCurrentBalance();
            if(currentBalance > 0 && currentBalance - buildingPrice >= 0){
                currentPlayer.withdrawMoney(buildingPrice);
                hasHotel = true;
                houseCount = 0;
            } else {
                System.out.println("Du har ikke nok penge til at købe dette hotel.");
            }

        } else {
            System.out.println("Du har ikke nok huse til at bygge et hotel.");
        }
    }
}
