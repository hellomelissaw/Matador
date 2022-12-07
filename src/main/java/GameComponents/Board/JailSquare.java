package GameComponents.Board;

import GameComponents.Player;
import Controllers.GuiController;

public class JailSquare extends Square{
    GuiController guiController;
    public JailSquare(String jailSquare, GuiController guiController) {
        super(jailSquare);
        this.guiController = guiController;

    }

    public void landOn(Player currentPlayer) {
        int currentPosition = currentPlayer.getPosition();
        if (currentPosition==18){
            msg.printText("moveToJail", "na");


            currentPlayer.withdrawMoney(1);
            int currentBalance = currentPlayer.getCurrentBalance();
            System.out.println(msg.getText("newBalance")+ currentBalance);

            currentPlayer.updatePosition(12);

            System.out.println(msg.getText("movedToSquare") + currentPlayer.getPosition() );

        }  else  {
            System.out.println(msg.getText("visitJail"));
        }
    }

}


