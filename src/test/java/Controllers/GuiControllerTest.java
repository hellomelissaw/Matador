package Controllers;

import GameComponents.Bank;
import GameComponents.Board.DeedSquare_Buildable;
import GameComponents.Player;
import Translator.Text;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuiControllerTest {

    GuiController guiController = new GuiController();

    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);

    Player[] testPlayers = new Player[1];

    int startBalance = 10000;

    DeedSquare_Buildable[] testStreetSquare = new DeedSquare_Buildable[3];

    int[] rent = {5,10,15,20,21,22};

    Bank bank = new Bank();

    public GuiControllerTest() {
        testPlayers[0] = new Player("Brigit");
        testPlayers[0].guiIsOn(false);
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
        testStreetSquare[0].landOn(testPlayers[0],testPlayers);
        testStreetSquare[1].testing(true,"ja");
        testStreetSquare[1].landOn(testPlayers[0],testPlayers);
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0],testPlayers);

    }
}