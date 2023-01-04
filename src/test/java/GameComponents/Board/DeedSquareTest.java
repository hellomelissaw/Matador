package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeedSquareTest {
    GuiController guiController = new GuiController();

    Player[] testPlayers = new Player[2];
    GUI_Player[] testGuiPlayers = new GUI_Player[2];
    DeedSquare[] testDeedSquare = new DeedSquare[2];
    Text msg = new Text("src/main/java/Translator/EnglishText", guiController);

    public DeedSquareTest() {
        for(int i = 0 ; i < testDeedSquare.length ; i++) {
            testDeedSquare[i] = new DeedSquare("TestDeedSquare" + i, 1200, guiController);
            testDeedSquare[i].setLang(msg);
            testDeedSquare[i].setColor("red");
        }

        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[1] = new Player("TestPlayer 2");

        testGuiPlayers[0] = new GUI_Player("TestPlayer 1");
        testGuiPlayers[1] = new GUI_Player("TestPlayer 2");

        for(int i = 0 ; i < testPlayers.length ; i++) {
            testPlayers[i].setGui(testGuiPlayers[i], guiController, msg);
        }
    }

    @Test
    public void deedOwnerIsTestPlayer1AfterLandingOnIt() {

        testPlayers[0].updatePosition(1);
        testDeedSquare[0].landOn(testPlayers[0]);
        assertEquals(testPlayers[0], testDeedSquare[0].getDeedOwner());
    }
    @Test
    public void testPlayer2PaysRentToDeedOwner() {

        testPlayers[0].depositMoney(20);
        testPlayers[1].depositMoney(20);
        testPlayers[0].updatePosition(1);
        testDeedSquare[0].landOn(testPlayers[0]);
        testPlayers[1].updatePosition(1);
        testDeedSquare[0].landOn(testPlayers[1]);
        assertEquals(20, testPlayers[0].getCurrentBalance());
        assertEquals(15, testPlayers[1].getCurrentBalance());
    }

    @Test
    public void playerGetsDeedForFree() {

        testDeedSquare[0].setDeedToFree();
        testDeedSquare[0].landOn(testPlayers[0]);

        assertEquals(0, testPlayers[0].getCurrentBalance());
    }

    @Test
    public void playerOwnsGroup() {
        testDeedSquare[0].setDeedOwner(testPlayers[0],0);
        testDeedSquare[1].setDeedOwner(testPlayers[0],1);

    }

    @Test
    public void buy1HouseForDeedSquare() {
        testDeedSquare[0].ownsGroup();
        testDeedSquare[0].buyHouse(1);
        assertEquals(1, testDeedSquare[0].getHouseCount());

    }
    @Test
    public void cannotBuyHouseBecauseNotOwnerOfLotGroup() {
        testDeedSquare[0].buyHouse(1);
        assertEquals(0, testDeedSquare[0].getHouseCount());
    }

    @Test
    public void cannotBuyHouseBecauseMissingHouseOnOtherGround() {
        testDeedSquare[0].ownsGroup();
        testDeedSquare[0].buyHouse(2);
        assertEquals(0, testDeedSquare[0].getHouseCount());
    }

    @Test
    public void buyHotelForDeedSquare() {
        testDeedSquare[0].buyHouse(4);
        testDeedSquare[0].buyHotel();
        assertEquals(true,testDeedSquare[0].hasHotel());
    }

    @Test
    public void errorMsgCannotBuyHotel() {
        testDeedSquare[0].buyHotel();
        assertEquals(false, testDeedSquare[0].hasHotel);
    }

}