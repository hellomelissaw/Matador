package GameComponents.Board;
import java.util.Scanner;
import GameComponents.Player;
import Controllers.GuiController;
import GameComponents.Cup;
import GameComponents.Die;
import GameComponents.Player;
public class JailSquare extends Square{
    GuiController guiController;

    Die  die1 = new Die();
    Die die2 = new Die();
    int counter = 0;


    public JailSquare(String jailSquare, GuiController guiController) {
        super(jailSquare);
        this.guiController = guiController;

    }

    public void landOn(Player currentPlayer) {
        int currentPosition = currentPlayer.getPosition();

        //The player get moved to jail square and gets instructions on how to leave. If the player didn't land on jail square, then they're just visiting.
        if(currentPosition==30)
        {
            currentPlayer.updatePosition(20);

            }else{
            System.out.println(msg.getText("visitJail"));
       }


        }

    }

