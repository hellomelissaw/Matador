package GameComponents.Board;

import GameComponents.Player;
import Controllers.GuiController;
import GameComponents.Cup;
import GameComponents.Die;
import GameComponents.Player;
public class JailSquare extends Square{
    GuiController guiController;
    Die  die1 = new Die();
    Die die2 = new Die();

    public JailSquare(String jailSquare, GuiController guiController) {
        super(jailSquare);
        this.guiController = guiController;

    }

    public void landOn(Player currentPlayer) {
        int currentPosition = currentPlayer.getPosition();
        int d1 = die1.roll();
        int d2 = die2.roll();
        int i = 0;

        if(currentPosition==31);
        msg.printText("moveToJail", "na");
        currentPlayer.updatePosition(20);

        //If the player while in prison rolls the dice of the same value, they get an extra as well as leaving prison
        while(currentPosition==31);
        if (d1==d2){
            System.out.println("Lucky shark, you get to leave jail and get an extra turn!");
            i = i + 1;
            System.out.println(msg.getText("movedToSquare") + currentPlayer.getPosition() );
            int currentBalance = currentPlayer.getCurrentBalance();
            System.out.println(msg.getText("newBalance")+ currentBalance);
            System.out.println();

            //note: husk at skrive at spilleren får ekstra tur
        //The player has the chance to leave prison over the course of three rounds where they have to roll the dice of the same value at least once or they have to pay 1000 to leave
        }else{
            System.out.println("Oh you failed..");



        //If they land on visit jail, the player gets a round where they have a break
        }else{
            System.out.println(msg.getText("visitJail"));
        }
    }

}