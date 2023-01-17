package GameComponents.Board;
import GameComponents.Player;

import java.util.Scanner;

public abstract class DeedSquare extends Square{
    boolean guiIsOn = true;
    boolean testing = false;
    String buying;
    boolean sellDeed = true;
    boolean freeDeed = false;
    int deedPrice;
    boolean ownsGroup = false;
    int[] rent;
    Scanner userInput = new Scanner(System.in);

    int groupSize;


    /**
     * Constructs a Square of type DeedSquare (ownable Square)
     * @param deedName name of the Deed for the Square (for example "The Skate Park").
     * @param deedPrice the price of the deed, both for buying and amount of rent to pay once bought.
     */
    public DeedSquare(String deedName, int deedPrice, int[] rent) {
        super(deedName);
        this.deedPrice = deedPrice;
        this.rent = rent;

    }

    public void  setGuiOn(boolean guiIsOn) {
        this.guiIsOn = guiIsOn;
    }

    public void testing(boolean testing, String answer) {
        this.testing = testing;
        buying = answer;
    }


    public boolean hasDeed(){ // Checks if the square has a deed available to buy or if it's already sold
        return sellDeed;
    }

    @Override
    public String toString() {
        String priceString = Integer.toString(deedPrice);
        return priceString;
    }

    public void landOn(Player currentPlayer, Player[] players) {
        if(sellDeed == true) { // IF DEED IS AVAILABLE TO BUY

            if (guiIsOn) {
               buying = userInputBuying(testing);

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
                    buyingLot(currentPlayer);

                } else if (!(buying.equals("ja") || buying.equals("nej"))) {
                    System.out.println("Ugyldigt svar. Indtast venligst ja eller nej");

                } else {
                    valid = true;
                    System.out.println("Spilleren køber ikke grunden.");
                }
            }

        } else { // IF DEED IS ALREADY OWNED
            lotIsOwned(currentPlayer, players);
            System.out.println("");
        }


    }

    private abstract void lotIsOwned(Player currentPlayer, Player[] players) {
    }

    protected abstract String userInputBuying(boolean testing);
    protected abstract void buyingLot(Player currentPlayer);


    public boolean ownsGroup(Player currentPlayer) {
        ownsGroup = currentPlayer.IsGroupOwner(color);
        return ownsGroup;
    }


    public Class getDeedType(Deed deed){
        return deed.getClass();
    }
}
