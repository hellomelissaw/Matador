package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;

public class DeedSquare extends Square{
    Deed deed;
    boolean sellDeed = true;
    int deedPrice;
    GuiController guiController;

    /**
     * Constructs a Square of type DeedSquare (ownable Square)
     * @param deedName name of the Deed for the Square (for example "The Skate Park").
     * @param deedPrice the price of the deed, both for buying and amount of rent to pay once bought.
     * @param guiController The GuiController used throughout the classes.
     */
    public DeedSquare(String deedName, int deedPrice, GuiController guiController) {
        super(deedName);
        this.deed = new Deed(deedPrice, deedName);
        this.deedPrice = deedPrice;
        this.guiController = guiController;
    }
    public Deed getDeed(){
        return deed;
    }
   public boolean hasDeed(){ // Checks if the square has a deed available to buy or if it's already sold
        return sellDeed;
    }

    /*
     * Sells the deed by setting sellDeed to false and setting owner name for the deed.
     * @param player gives the current player whose turn it is
     * @param currentSquareIndex gives the index of the Square that the current player is on
     */
   public void sellDeed(Player currentPlayer, int currentSquareIndex){
        sellDeed = false ;
        deed.setOwner(currentPlayer);
        guiController.displayOwnerName(currentPlayer, currentSquareIndex);

    }

    public Player getDeedOwner() {
        return deed.getOwner();
    }

    //public void setDeedOwner(Player currentPlayer) { deed.setOwner(currentPlayer); }


    @Override
    public String toString() {
        String priceString = Integer.toString(deedPrice);
        return priceString;
    }

    public void landOn(Player currentPlayer, GUI_Player currentGuiPlayer) {
        //int i = currentPlayer;
        //int deedPrice = ((DeedSquare) square[newPosition]).getDeedPrice();
        currentPlayer.withdrawMoney(deedPrice);
        int currentBalance = currentPlayer.getCurrentBalance();
        guiController.updateBalance(currentGuiPlayer, currentBalance);
        System.out.println(msg.getText("newBalance") + currentBalance);

        if(sellDeed == true) {
            /*System.out.println("This property is available for purchase for" +((DeedSquare) square[newPosition]).getDeedPrice()  + "M.");
            guiMessage = "This property is available for purchase.";*/

            String guiMessage = currentPlayer.getPlayerName() + msg.getText("haveBought") + deed.getDeedName();
            guiController.showMessage(guiMessage);

            sellDeed = false ;
            deed.setOwner(currentPlayer);
            guiController.displayOwnerName(currentPlayer, currentPlayer.getPosition());
            //((DeedSquare) square[newPosition]).sellDeed(player[i], newPosition); // SETS sellDeed TO FALSE AND UPDATES OWNERSHIP

        } else {
           Player deedOwner = deed.getOwner();
            if (currentPlayer==deedOwner) {
                String ownerMessage = msg.getText("ownerOfDeed");
                System.out.println(ownerMessage);
                guiController.showMessage(ownerMessage);

            } else {
                String payRent = deedOwner.toString() + msg.getText("payRent");
                System.out.println(payRent + deed.getDeedPrice());

                guiController.showMessage(payRent);

                deedOwner.depositMoney(deedPrice);
                currentBalance = deedOwner.getCurrentBalance();
                guiController.updateBalance(currentGuiPlayer,currentBalance);


            }


            System.out.println("");

        }

    }

}
