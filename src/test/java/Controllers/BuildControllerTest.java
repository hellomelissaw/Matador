package Controllers;

import GameComponents.Bank;
import GameComponents.Board.Deed;
import GameComponents.Board.DeedSquare_Buildable;
import GameComponents.Board.Deed_Buildable;
import GameComponents.Player;
import Translator.Text;
import org.apache.maven.model.Build;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuildControllerTest {
    int deedPrice = 1000;
    int[] rent = {1000,50,250,750,2250,4000,6000};
    int buildingPrice = 500;
    int startBalance = 7000;
    GuiController guiController = new GuiController();
    Text msg = new Text("src/main/java/Translator/DanskTekst");
    BuildController buildController = new BuildController(msg);
    Player testPlayer = new Player("Test Player 0");

    Bank bank = new Bank();
    DeedSquare_Buildable ponyFarm = new DeedSquare_Buildable("Pony Farm", deedPrice, rent, buildingPrice);

    public BuildControllerTest() {
        guiController.setLang(msg);
        //buildController.setCurrentPlayer(testPlayer);
        testPlayer.setLang(msg);
        testPlayer.guiIsOn(false);
        testPlayer.setBank(bank);
        testPlayer.setStartBalance(startBalance, false);

        ponyFarm.setLang(msg);
        ponyFarm.setGuiController(guiController);
        ponyFarm.setGuiOn(false);
        ponyFarm.setGroup("pink", 1);
        ponyFarm.setOwnerForTesting(testPlayer);

    }

    @Test
    public void playerHas2HousesOnPonyFarmAndSells1HouseBackToBank() {
        Deed_Buildable[] deeds = new Deed_Buildable[1];
        deeds[0] = ponyFarm.getDeed();

        buildController.testing(true,"Hus",2,deeds);

        buildController.build(testPlayer);
        assertEquals(2, ponyFarm.getDeed().getHouseCount());

        buildController.testing(true,"Hus",1, deeds);
        buildController.demolish(testPlayer);
        assertEquals(1, ponyFarm.getDeed().getHouseCount());
    }

    @Test
    public void playerHas1HotelOnPonyFarmAndSellsItBackToBank() {
        Deed_Buildable[] deeds = new Deed_Buildable[1];
        deeds[0] = ponyFarm.getDeed();
        buildController.testing(true,"Hus",4, deeds);

        buildController.build(testPlayer);
        assertEquals(4,ponyFarm.getDeed().getHouseCount());

        buildController.testing(true,"Hotel",0, deeds);
        buildController.build(testPlayer);
        assertEquals(0,ponyFarm.getDeed().getHouseCount());
        assertTrue(ponyFarm.getDeed().hasHotel());

        buildController.testing(true,"Hotel",0, deeds);
        buildController.demolish(testPlayer);
        assertFalse(ponyFarm.getDeed().hasHotel());
    }

}