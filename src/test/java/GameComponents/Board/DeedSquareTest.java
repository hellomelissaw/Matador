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
    DeedSquare_Buildable[] testStreetSquare = new DeedSquare_Buildable[3];
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);

    int startBalance = 7000;
    int[] rent = {50,250,750,2250,4000,6000};
    public DeedSquareTest() {
        for(int i = 0; i < testStreetSquare.length - 1 ; i++) {
            testStreetSquare[i] = new DeedSquare_Buildable("TestDeedSquare" + i, 1200, rent, 1000);
            testStreetSquare[i].setLang(msg);
            testStreetSquare[i].setColor("purple");
            testStreetSquare[i].setGuiController(guiController);
            testStreetSquare[i].setGroup("blue", 2);
        }

        testStreetSquare[2] = new DeedSquare_Buildable("TestDeedSquare 3", 1200, rent, 1000);
        testStreetSquare[2].setLang(msg);
        testStreetSquare[2].setGroup("red",1);
        testStreetSquare[2].setGuiController(guiController);

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
    public void cannotBuyHouseBecauseNotEnoughMoney() {
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        testPlayers[0].withdrawMoney(startBalance-1200);

        DeedSquare_Buildable[] lotsToBuildOn = {testStreetSquare[2]};
        testPlayers[0].buyHouse(lotsToBuildOn, 1);
        assertEquals(0, testStreetSquare[0].getHouseCount());
    }
    @Test
    public void cannotBuyHouseBecauseNotOwnerOfLotGroup() {
        DeedSquare_Buildable[] lotsToBuildOn = {testStreetSquare[0]};
        testPlayers[0].buyHouse(lotsToBuildOn, 1);

        assertEquals(0, testStreetSquare[0].getHouseCount());
    }

    @Test
    public void buyHotelForDeedSquare() {
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        DeedSquare_Buildable[] lotsToBuildOn = {testStreetSquare[2]};
        testPlayers[0].buyHouse(lotsToBuildOn, 4);
        testPlayers[0].buyHotel(lotsToBuildOn);

        assertTrue(testStreetSquare[2].hasHotel());
    }

    @Test
    public void errorMsgCannotBuyHotel() {
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        DeedSquare_Buildable[] lotsToBuildOn = {testStreetSquare[2]};
        testPlayers[0].buyHotel(lotsToBuildOn);

        assertFalse(testStreetSquare[2].hasHotel);
    }

    @Test
    public void cannotBuyHotelBecauseLackOfFunds() {
        testPlayers[0].withdrawMoney(1000);
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        DeedSquare_Buildable[] lotsToBuildOn = {testStreetSquare[2]};
        testPlayers[0].buyHouse(lotsToBuildOn, 4);
        testPlayers[0].buyHotel(lotsToBuildOn);

        assertFalse(testStreetSquare[0].hasHotel);
    }

    @Test
    public void ownerHasNoHousesReceives50InRent() {
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);

        testStreetSquare[2].landOn(testPlayers[1]);

        assertEquals(startBalance-50,testPlayers[1].getCurrentBalance());
    }

    @Test
    public void ownerHas1HouseReceives250InRent() {

        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        DeedSquare_Buildable[] lotsToBuildOn = {testStreetSquare[2]};
        testPlayers[0].buyHouse(lotsToBuildOn, 1);

        testStreetSquare[2].landOn(testPlayers[1]);

        assertEquals(startBalance-250,testPlayers[1].getCurrentBalance());
    }

    @Test
    public void ownerHas2HousesReceives750InRent() {
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        DeedSquare_Buildable[] lotsToBuildOn = {testStreetSquare[2]};
        testPlayers[0].buyHouse(lotsToBuildOn, 2);

        testStreetSquare[2].landOn(testPlayers[1]);

        assertEquals(startBalance-750,testPlayers[1].getCurrentBalance());
    }

    @Test
    public void ownerHas3HousesReceives2250InRent() {
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        DeedSquare_Buildable[] lotsToBuildOn = {testStreetSquare[2]};
        testPlayers[0].buyHouse(lotsToBuildOn, 3);

        testStreetSquare[2].landOn(testPlayers[1]);

        assertEquals(startBalance-2250,testPlayers[1].getCurrentBalance());
    }

    @Test
    public void ownerHas4HousesReceives4000InRent() {
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        DeedSquare_Buildable[] lotsToBuildOn = {testStreetSquare[2]};
        testPlayers[0].buyHouse(lotsToBuildOn, 4);

        testStreetSquare[2].landOn(testPlayers[1]);

        assertEquals(startBalance-4000,testPlayers[1].getCurrentBalance());
    }

    @Test
    public void playerChoosesToBuyLot(){
        testStreetSquare[0].testing(true,"ja");
        testStreetSquare[0].landOn(testPlayers[0]);
        assertFalse(testStreetSquare[0].hasDeed());
        assertEquals(testPlayers[0], testStreetSquare[0].getDeedOwner());
    }

    @Test
    public void playerChoosesNotToBuyLot(){
        testStreetSquare[0].testing(true,"nej");
        testStreetSquare[0].landOn(testPlayers[0]);
        assertTrue(testStreetSquare[0].hasDeed());
        assertNull(testStreetSquare[0].getDeedOwner());
    }

    @Test
    public void playerHasTestDeedSquare1DeedInCardholder(){
        boolean hasDeed = false;
        testPlayers[0].takeBuildableDeed(testStreetSquare[0].getDeed());
        testPlayers[0].takeBuildableDeed(testStreetSquare[0].getDeed());
        Deed_Buildable[] deedList = testPlayers[0].getBuildableDeeds();
        for(int i = 0 ; i < deedList.length ; i++) {
            if (deedList[i].getDeedName().equals("TestDeedSquare0")) {
                hasDeed = true;
                break;
            }
        }

        assertTrue(hasDeed);
    }
    @Test
    public void playerHasAllLotsOfSameColour(){
        testPlayers[0].takeBuildableDeed(testStreetSquare[0].getDeed());
        testPlayers[0].takeBuildableDeed(testStreetSquare[0].getDeed());

        assertTrue(testStreetSquare[0].ownsGroup(testPlayers[0]));
    }

    @Test
    public void playerDoesNotHaveAllLotsOfSameColour(){
        testPlayers[0].takeBuildableDeed(testStreetSquare[0].getDeed());

        assertFalse(testStreetSquare[0].ownsGroup(testPlayers[0]));
    }

    @Test
    public void playerCannotBuildSecondHouseBecauseNoHouseOnOtherLotInGroup() {
        testStreetSquare[0].testing(true,"ja");
        testStreetSquare[0].landOn(testPlayers[0]);
        testStreetSquare[1].testing(true,"ja");
        testStreetSquare[1].landOn(testPlayers[0]);

        DeedSquare_Buildable[] lotToBuildOn = {testStreetSquare[0]};
        testPlayers[0].buyHouse(lotToBuildOn,2);

        assertEquals(1, testStreetSquare[0].getHouseCount());
    }

    @Test
    public void playerBuilds1HouseOnTestDeedSquare0And1() {
        testStreetSquare[0].testing(true,"ja");
        testStreetSquare[0].landOn(testPlayers[0]);
        testStreetSquare[1].testing(true,"ja");
        testStreetSquare[1].landOn(testPlayers[0]);

        DeedSquare_Buildable[] lotsToBuildOn = {testStreetSquare[0], testStreetSquare[1]};
        testPlayers[0].buyHouse(lotsToBuildOn,1);

        assertEquals(1, testStreetSquare[0].getHouseCount());
        assertEquals(1, testStreetSquare[1].getHouseCount());
    }

    @Test
    public void playerBuilds2HousesOnTestDeedSquare0And1() {
        testPlayers[0].depositMoney(2000);
        testStreetSquare[0].testing(true,"ja");
        testStreetSquare[0].landOn(testPlayers[0]);
        testStreetSquare[1].testing(true,"ja");
        testStreetSquare[1].landOn(testPlayers[0]);

        DeedSquare_Buildable[] lotsToBuildOn = {testStreetSquare[0], testStreetSquare[1]};
        testPlayers[0].buyHouse(lotsToBuildOn,2);

        assertEquals(2, testStreetSquare[0].getHouseCount());
        assertEquals(2, testStreetSquare[1].getHouseCount());
    }

}