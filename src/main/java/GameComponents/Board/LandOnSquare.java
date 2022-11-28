package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;
import gui_fields.GUI_Player;
import Translator.*;

public class LandOnSquare {
    Square[] square;
    Player[] player;

    GUI_Player[] guiPlayers;
    GuiController guiController;
    String guiMessage;
    Text msg;
    int playerCount;

    /**
     * Constructs a new LandOnSquare with parameters initialized in {@link Controllers.GameController}.
     * @param square Array of squares instantiated from {@link BoardInit}.
     * @param player Array of {@link Player} objects instantiated in run() method.
     * @param guiController The GuiController used throughout the classes
     * @param guiPlayers The array of GUI_Player objects instantiated in {@link GuiController}
     */

    public LandOnSquare(Square[] square, Player[] player, GuiController guiController, GUI_Player[] guiPlayers, int playerCount) {
        this.square = square;
        this.player = player;
        this.guiController = guiController;
        this.guiPlayers = guiPlayers;
        this.playerCount = playerCount;
    }

    /**
     * Sets language according to user input at game start
     * @param msg The Text object used to set user-selected language throughout the program.
     */
    public void setLang(Text msg) {
        this.msg = msg;
    }

    /**
     * Handles three possible scenarios when landing on a Deed Square (buy deed, land on own property or pay rent)
     * @param newPosition gives the index of the Square that Player is on
     * @param currentPlayer gives the current Player of the Player Array whose turn it is
     */
    public void landOnDeedSquare(int newPosition, int currentPlayer) {
        int i = currentPlayer;
        int deedPrice = ((DeedSquare) square[newPosition]).getDeedPrice();

        if(((DeedSquare) square[newPosition]).hasDeed()) {
            /*System.out.println("This property is available for purchase for" +((DeedSquare) square[newPosition]).getDeedPrice()  + "M.");
            guiMessage = "This property is available for purchase.";*/
            guiMessage = player[i].getPlayerName() + msg.getText("haveBought") + square[newPosition].getSquareName();
            guiController.showMessage(guiMessage);

            player[i].withdrawMoney(deedPrice); // TO DO: must check if player has enough money to buy
            int currentBalance = player[i].getCurrentBalance();
            guiController.updateBalance(guiPlayers[i], currentBalance);
            System.out.println(msg.getText("newBalance") + currentBalance);

            ((DeedSquare) square[newPosition]).sellDeed(player[i], newPosition); // SETS sellDeed TO FALSE AND UPDATES OWNERSHIP

            } else if (((DeedSquare) square[newPosition]).hasDeed()==false) {
                Player deedOwner = ((DeedSquare) square[newPosition]).getDeedOwner();

                if (player[i]==deedOwner) {
                    String ownerMessage = msg.getText("ownerOfDeed");
                    System.out.println(ownerMessage);
                    guiController.showMessage(ownerMessage);

                } else {
                    String payRent = deedOwner.toString() + msg.getText("payRent");
                    System.out.println(payRent + ((DeedSquare) square[newPosition]).getDeedPrice());
                    player[i].withdrawMoney(deedPrice);
                    int currentBalance = player[i].getCurrentBalance();
                    guiController.updateBalance(guiPlayers[i], currentBalance);

                    System.out.println(msg.getText("newBalance") + currentBalance);
                    guiController.showMessage(payRent);

                    deedOwner.depositMoney(deedPrice);
                    currentBalance = deedOwner.getCurrentBalance();
                    guiController.receiveRent(deedOwner.getPlayerName(),currentBalance);


                }


                 System.out.println("");

        }
    }

    public void landOnStartSquare (int newPosition, int currentPlayer) {


    }

    /**
     * Handles landing on Go to Jail and Visiting Jail square
     *@param newPosition gives the index of the Square that Player is on
     *@param currentPlayer gives the current Player of the Player Array whose turn it is
     */
    public void landOnJailSquare(int newPosition, int currentPlayer){

       int i = currentPlayer;
        if (newPosition==18){
            String jailMessage = msg.getText("movedToJail");
            System.out.println(jailMessage);

            player[i].withdrawMoney(1);
            int currentBalance = player[i].getCurrentBalance();
            guiController.updateBalance(guiPlayers[i], currentBalance);
            System.out.println(msg.getText("newBalance")+ currentBalance);
            guiController.showMessage(jailMessage);
            player[i].updatePosition(12);
            guiController.move(guiPlayers[i],18,6);
            System.out.println(msg.getText("movedToSquare") + player[i].getPosition() );

        }  else if (newPosition==6) {
            System.out.println(msg.getText("visitJail"));
        }
    }

    public void landOnParkingSquare(int newPosition) {

         if (newPosition==12)
             System.out.println(msg.getText("freeParking"));
    }

    public void landOnChanceSquare(int currentPlayer, int currentPosition,Square[] square, Player[] player, GuiController guiController, GUI_Player[] guiPlayers, int playerCount) {
        ChanceSquare chanceSquare = new ChanceSquare(square[currentPosition].getSquareName());
        chanceSquare.initializer(square, player, guiController, guiPlayers, playerCount, msg);
        chanceSquare.Roll(currentPlayer,currentPosition);
    }

}

