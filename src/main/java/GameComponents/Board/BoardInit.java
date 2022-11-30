package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;

public class BoardInit {
    Square[] board = new Square[24];
    GuiController guiController;
    Text msg;
    /**
     * Constructs a BoardInit where all the Squares on the board are instantiated with name and evt. price.
     * @param guiController The GuiController used throughout the classes.
     * @param msg The Text object used throughout the classes.
     */
    public BoardInit(GuiController guiController, Text msg, Player[] players) {
        this.msg = msg;
        this.guiController = guiController;

        board[0] = new StartSquare(msg.getText("start"));
        board[1] = new DeedSquare(msg.getText("burgerBar"), 1,guiController);
        board[2] = new DeedSquare(msg.getText("pizzeria"), 1,guiController);
        board[3] = new ChanceSquare(msg.getText("chance"),guiController, players);
        board[4] = new DeedSquare(msg.getText("candyShop"),1,guiController);
        board[5] = new DeedSquare(msg.getText("iceCreamShop"), 1,guiController);
        board[6] = new JailSquare(msg.getText("visitJail"),guiController);
        board[7] = new DeedSquare(msg.getText("museum"),2,guiController);
        board[8] = new DeedSquare(msg.getText("library"),2,guiController);
        board[9] = new ChanceSquare(msg.getText("Chance"),guiController, players);
        board[10] = new DeedSquare(msg.getText("skatePark"),2,guiController);
        board[11] = new DeedSquare(msg.getText("pool"),2,guiController);
        board[12] = new ParkingSquare(msg.getText("freeParking"));
        board[13] = new DeedSquare(msg.getText("playingHall"), 3,guiController);
        board[14] = new DeedSquare(msg.getText("cinema"),3,guiController);
        board[15] = new ChanceSquare(msg.getText("chance"),guiController, players);
        board[16] = new DeedSquare(msg.getText("toyStore"),3,guiController);
        board[17] = new DeedSquare(msg.getText("petShop"),3,guiController);
        board[18] = new JailSquare(msg.getText("jail"),guiController);
        board[19] = new DeedSquare(msg.getText("bowlingAlley"),4,guiController);
        board[20] = new DeedSquare(msg.getText("zoo"),4,guiController);
        board[21] = new ChanceSquare(msg.getText("chance"),guiController, players);
        board[22] = new DeedSquare(msg.getText("waterPark"),5,guiController);
        board[23] = new DeedSquare(msg.getText("promenade"),5,guiController);

    }
    public Square[] getSquareArr() {
        return board;
    }

}
