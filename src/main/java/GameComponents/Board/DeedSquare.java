package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

import java.util.Scanner;

public abstract class DeedSquare extends Square{
    boolean guiOn = true;
    boolean testing = false;
    String buying;
    boolean sellDeed = true;
    boolean freeDeed = false;
    int deedPrice;
    GuiController guiController;
    boolean ownsGroup = false;
    int[] rent;
    Scanner userInput = new Scanner(System.in);


    /**
     * Constructs a Square of type DeedSquare (ownable Square)
     * @param deedName name of the Deed for the Square (for example "The Skate Park").
     * @param deedPrice the price of the deed, both for buying and amount of rent to pay once bought.
     * @param guiController The GuiController used throughout the classes.
     */
    public DeedSquare(String deedName, int deedPrice, int[] rent, GuiController guiController) {
        super(deedName);
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
