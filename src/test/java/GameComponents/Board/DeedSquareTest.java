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

    int startBalance = 6000;
    int[] rent = {50,250,750,2250,4000,6000};
    public DeedSquareTest() {
        for(int i = 0 ; i < testDeedSquare.length ; i++) {
            testDeedSquare[i] = new DeedSquare("TestDeedSquare" + i, 1200, 1000, rent, guiController);
            testDeedSquare[i].setLang(msg);
            testDeedSquare[i].setColor("red");
        }

        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[1] = new Player("TestPlayer 2");

        testGuiPlayers[0] = new GUI_Player("TestPlayer 1");
        testGuiPlayers[1] = new GUI_Player("TestPlayer 2");

        for(int i = 0 ; i < testPlayers.length ; i++) {
            testPlayers[i].setGui(testGuiPlayers[i], guiController, msg);
            testPlayers[i].depositMoney(startBalance);
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
    public void buy1HouseForDeedSquareWhenPlayerOwnsLotGroup() {
        testDeedSquare[0].setOwnsGroup(true);
        testDeedSquare[0].addHouse(1, testPlayers[0]);
        assertEquals(1, testDeedSquare[0].getHouseCount());

    }

    @Test
    public void playerBuys1HouseFor1000() {
        testDeedSquare[0].setOwnsGroup(true);
        testDeedSquare[0].addHouse(1, testPlayers[0]);
        assertEquals(startBalance - 1000, testPlayers[0].getCurrentBalance());

    }

    @Test
    public void playerBuys1HouseThenAnotherFor1000Each() {
        testDeedSquare[0].setOwnsGroup(true);
        testDeedSquare[0].addHouse(1, testPlayers[0]);
        assertEquals(startBalance - 1000, testPlayers[0].getCurrentBalance());
        testDeedSquare[0].addHouse(1, testPlayers[0]);
        assertEquals(startBalance - 2000, testPlayers[0].getCurrentBalance());

    }

    @Test
    public void cannotBuyHouseBecauseNotEnoughMoney() {
        testPlayers[0].withdrawMoney(startBalance);
        testDeedSquare[0].setOwnsGroup(true);
        testDeedSquare[0].addHouse(1, testPlayers[0]);
        assertEquals(0, testDeedSquare[0].getHouseCount());
    }
    @Test
    public void cannotBuyHouseBecauseNotOwnerOfLotGroup() {
        testDeedSquare[0].addHouse(1, testPlayers[0]);
        assertEquals(0, testDeedSquare[0].getHouseCount());
    }

    @Test
    public void cannotBuyHouseBecauseMissingHouseOnOtherGround() {
        testDeedSquare[0].setOwnsGroup(true);
        testDeedSquare[0].addHouse(1, testPlayers[0]);
        testDeedSquare[0].addHouse(1, testPlayers[0]);
        assertEquals(1, testDeedSquare[0].getHouseCount());
    }

    @Test
    public void buyHotelForDeedSquare() {
        testDeedSquare[0].setOwnsGroup(true);
        testDeedSquare[0].addHouse(4, testPlayers[0]);
        testDeedSquare[0].addHotel();
        assertEquals(true,testDeedSquare[0].hasHotel());
    }

    @Test
    public void errorMsgCannotBuyHotel() {
        testDeedSquare[0].addHotel();
        assertEquals(false, testDeedSquare[0].hasHotel);
    }

    @Test
    public void ownerHasNoHousesReceives50InRent() {
        testDeedSquare[0].setDeedOwner(testPlayers[0],0);
        testDeedSquare[0].setOwnsGroup(true);
        testDeedSquare[0].landOn(testPlayers[1]);
        assertEquals(startBalance-50,testPlayers[1].getCurrentBalance());
    }

    @Test
    public void ownerHas1HouseReceives250InRent() {
        testDeedSquare[0].setDeedOwner(testPlayers[0],0);
        testDeedSquare[0].setOwnsGroup(true);
        testDeedSquare[0].addHouse(1, testPlayers[0]);
        testDeedSquare[0].landOn(testPlayers[1]);
        assertEquals(startBalance-250,testPlayers[1].getCurrentBalance());
    }

    @Test
    public void ownerHas2HousesReceives750InRent() {
        testDeedSquare[0].setDeedOwner(testPlayers[0],0);
        testDeedSquare[0].setOwnsGroup(true);
        testDeedSquare[0].addHouse(2, testPlayers[0]);
        testDeedSquare[0].landOn(testPlayers[1]);
        assertEquals(startBalance-750,testPlayers[1].getCurrentBalance());
    }

    @Test
    public void ownerHas3HousesReceives2250InRent() {
        testDeedSquare[0].setDeedOwner(testPlayers[0],0);
        testDeedSquare[0].setOwnsGroup(true);
        testDeedSquare[0].addHouse(3, testPlayers[0]);
        testDeedSquare[0].landOn(testPlayers[1]);
        assertEquals(startBalance-2250,testPlayers[1].getCurrentBalance());
    }

    @Test
    public void ownerHas4HousesReceives4000InRent() {
        testDeedSquare[0].setDeedOwner(testPlayers[0],0);
        testDeedSquare[0].setOwnsGroup(true);
        testDeedSquare[0].addHouse(4, testPlayers[0]);
        testDeedSquare[0].landOn(testPlayers[1]);
        assertEquals(startBalance-4000,testPlayers[1].getCurrentBalance());
    }

}