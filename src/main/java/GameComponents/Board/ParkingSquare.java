package GameComponents.Board;

import GameComponents.Player;
import gui_fields.GUI_Player;

public class ParkingSquare extends Square{
    public ParkingSquare(String freeParkingSquare) {
        super(freeParkingSquare);


    }
    public void landOn(Player currentPlayer) {
        System.out.println(msg.getText("freeParking"));
    }
}

