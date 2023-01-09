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

    public JailSquare(String jailSquare, GuiController guiController) {
        super(jailSquare);
        this.guiController = guiController;

    }

    public void landOn(Player currentPlayer) {
        int currentPosition = currentPlayer.getPosition();
        int fine = 1000;
        Scanner scan = new Scanner(System.in);


        //The player get moved to jail square and gets instructions on how to leave. If the player didn't land on jail square, then they're just visiting.
        if(currentPosition==31)
        {
        msg.printText("moveToJail","\"Du skal i fængsel og har kun følgende muligheder for at komme ud af fængsel! Valg en valgmulighed: Betal bøde på 1000 kr med det samme? eller Prøv heldet med terningekast!\"");
        currentPlayer.updatePosition(20);
            String[] jailOptions = {"Betal bøde?","Kast terninger?"};
            String name;
            name = guiController.getUserSelection("Betal bøde på 1000 kr med det samme? eller Prøv heldet med terningekast!",jailOptions);

            if(name == "Betal bøde?")
            {
                System.out.println("Du har nu betalt bøden, du kan nu forlade fængsel!");
                currentPlayer.withdrawMoney(fine);
                int currentBalance = currentPlayer.getCurrentBalance();
                System.out.println(msg.getText("newBalance")+ currentBalance);
                msg.printText("moveToSquare","na");
            }else if(name == "Kast terninger?")
            {
                System.out.println("Du har valgt at prøve dit held ved terningekast!");


        }else{
            System.out.println(msg.getText("visitJail"));
       }


        }

    }

}