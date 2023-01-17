package GameComponents.Board;

import GameComponents.Player;

public class ParkingSquare extends Square{
    Player[] players;


    public ParkingSquare(String freeParkingSquare) {
        super(freeParkingSquare);


    }
    public void landOn(Player currentPlayer, Player[] players) {
        int currentPosition = currentPlayer.getPosition();
        if (currentPosition == 20) {
           msg.printText("freeParking","na");
        }
    }
}

