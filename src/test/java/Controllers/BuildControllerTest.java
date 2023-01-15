package Controllers;

import GameComponents.Board.DeedSquare_Buildable;
import GameComponents.Player;
import Translator.Text;

import static org.junit.Assert.*;

public class BuildControllerTest {
    int deedPrice = 1000;
    int[] rent = {1000,50,250,750,2250,4000,6000};
    int buildingPrice = 500;
    int startBalance = 7000;
    GuiController guiController = new GuiController();
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);
    Player testPlayer = new Player("Test Player 0");
    DeedSquare_Buildable ponyFarm = new DeedSquare_Buildable("Pony Farm", deedPrice, rent, buildingPrice);

    public BuildControllerTest() {
        testPlayer.setStartBalance(startBalance, false);
        testPlayer.setLang(msg);
        ponyFarm.setGroup("pink", 1);
        ponyFarm.setOwnerForTesting(testPlayer);

    }



}