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

    public void setGuiOn(boolean guiIsOn) {
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

    public abstract void landOn(Player currentPlayer);


    public boolean ownsGroup(Player currentPlayer) {
        ownsGroup = currentPlayer.IsGroupOwner(color);
        return ownsGroup;
    }


}
