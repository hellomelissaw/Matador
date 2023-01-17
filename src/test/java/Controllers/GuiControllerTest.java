package Controllers;

import GameComponents.Bank;
import GameComponents.Board.DeedSquare_Buildable;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class GuiControllerTest {

    GuiController guiController = new GuiController();

    Text msg = new Text("src/main/java/Translator/DanskTekst");

    Player[] testPlayers = new Player[1];

    int startBalance = 10000;

    DeedSquare_Buildable[] testStreetSquare = new DeedSquare_Buildable[3];

    int[] rent = {5,10,15,20,21,22};

    Bank bank = new Bank();
    Player testPlayer_1 = new Player("Test Player 1");

    GUI_Player testGuiPlayer_1 = new GUI_Player("TestGui Player 1");


    String carColor;
    String userSelection;
    String[] colorArray = new String[3];
    Color BLÅ = new Color(0, 0, 255);
    Color GUL = new Color(255, 255, 0);
    Color GRØN = new Color(0, 255, 0);


    public GuiControllerTest() {
        msg.setGuiController(guiController);
        testPlayers[0] = new Player("Brigit");
        testPlayers[0].setGuiIsOn(false);
        testPlayers[0].setBank(bank);
        testPlayers[0].setStartBalance(startBalance,true);

        for(int i = 0; i < testStreetSquare.length; i++) {
            testStreetSquare[i] = new DeedSquare_Buildable("TestDeedSquare" + i, 1200, rent, 1000);
            testStreetSquare[i].setLang(msg);
            testStreetSquare[i].setGuiController(guiController);
            testStreetSquare[i].setGroup("blue", 3);
        }
    }


    @Test
    public void getUserLot() {
        testStreetSquare[0].testing(true,"ja");
        testStreetSquare[0].landOn(testPlayers[0]);
        testStreetSquare[1].testing(true,"ja");
        testStreetSquare[1].landOn(testPlayers[0]);
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);

    }


    @Test
    public void chooseCarColor() {
        testPlayer_1.setGui(testGuiPlayer_1, guiController, msg);
        colorArray[0] = "Blå";
        colorArray[1] = "Gul";
        colorArray[2] = "Grøn";

        userSelection = guiController.getUserSelection("Valg et farve", colorArray);

        if (userSelection.equals("Blå")) {
            testPlayer_1.setCarColor(Color.blue);
            assertEquals(BLÅ, testGuiPlayer_1.getPrimaryColor());
        }
        if (userSelection.equals("Gul")) {
            testPlayer_1.setCarColor(Color.yellow);
            assertEquals(GUL, testGuiPlayer_1.getPrimaryColor());
        }
        if (userSelection.equals("Grøn")) {
            testPlayer_1.setCarColor(Color.green);
            assertEquals(GRØN, testGuiPlayer_1.getPrimaryColor());
        }

    }
}