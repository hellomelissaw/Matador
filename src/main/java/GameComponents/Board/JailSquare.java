package GameComponents.Board;

import GameComponents.Player;
import gui_fields.GUI_Player;
import Controllers.GuiController;

public class JailSquare extends Square{
    GuiController guiController;
    public JailSquare(String jailSquare, GuiController guiController) {
        super(jailSquare);
        this.guiController = guiController;

    }

    public void landOn(Player currentPlayer, GUI_Player currentGuiPlayer) {
        int currentPosition = currentPlayer.getPosition();
        if (currentPosition==18){
            String jailMessage = msg.getText("movedToJail");
            System.out.println(jailMessage);

            currentPlayer.withdrawMoney(1);
            int currentBalance = currentPlayer.getCurrentBalance();
            guiController.updateBalance(currentGuiPlayer, currentBalance);
            System.out.println(msg.getText("newBalance")+ currentBalance);
            guiController.showMessage(jailMessage);
            currentPlayer.updatePosition(12);
            guiController.move(currentGuiPlayer,18,6);
            System.out.println(msg.getText("movedToSquare") + currentPlayer.getPosition() );

        }  else  {
            System.out.println(msg.getText("visitJail"));
        }
    }

}


