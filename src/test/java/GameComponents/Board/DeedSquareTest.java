package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Bank;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeedSquareTest {
    GuiController guiController = null;

    Player[] testPlayers = new Player[2];
    GUI_Player[] testGuiPlayers = new GUI_Player[2];
    DeedSquare_Buildable[] testStreetSquare = new DeedSquare_Buildable[3];

    DeedSquare_NonBuildable[] testShippingSquare = new DeedSquare_NonBuildable[4];

    DeedSquare_NonBuildable[] testBrewerySquare = new DeedSquare_NonBuildable[2];
    Text msg = new Text("src/main/java/Translator/DanskTekst");

    Bank bank = new Bank();

    int startBalance = 30000;

    int streetDeedPrice = 1200;
    int[] rentStreet = {50,250,750,2250,4000,6000};

    int buildingPrice = 1000;

    int shippingDeedPrice = 4000;
    int[] rentShipping = {500, 1000, 2000, 4000};

    int breweryDeedPrice = 3000;

    int[] breweryRent ={100,200};

    public DeedSquareTest() {
        msg.setGuiIsOn(false);
        for(int i = 0; i < testStreetSquare.length - 1 ; i++) {
            testStreetSquare[i] = new DeedSquare_Buildable("TestDeedSquare" + i, streetDeedPrice, rentStreet, buildingPrice);
            testStreetSquare[i].setLang(msg);
            testStreetSquare[i].setGuiController(guiController);
            testStreetSquare[i].setGroup("blue", 2);
            testStreetSquare[i].setGuiIsOn(false);
        }

        testStreetSquare[2] = new DeedSquare_Buildable("TestDeedSquare 3", streetDeedPrice, rentStreet, buildingPrice);
        testStreetSquare[2].setLang(msg);
        testStreetSquare[2].setGroup("red",1);
        testStreetSquare[2].setGuiController(guiController);
        testStreetSquare[2].setGuiIsOn(false);

        testPlayers[0] = new Player("TestPlayer 1");
        testPlayers[1] = new Player("TestPlayer 2");


        testGuiPlayers[0] = new GUI_Player("TestPlayer 1");
        testGuiPlayers[1] = new GUI_Player("TestPlayer 2");

        for(int i = 0; i < testShippingSquare.length; i++) {
            testShippingSquare[i] = new DeedSquare_NonBuildable("TestShipping", shippingDeedPrice, rentShipping);
            testShippingSquare[i].setLang(msg);
            testShippingSquare[i].setGroup("white", 4);
            testShippingSquare[i].setGuiController(guiController);
            testShippingSquare[i].setGuiIsOn(false);

        }

        for(int i = 0; i < testBrewerySquare.length; i++) {
            testBrewerySquare[i] = new DeedSquare_NonBuildable("TestBrewery", breweryDeedPrice, breweryRent);
            testBrewerySquare[i].setLang(msg);
            testBrewerySquare[i].setGroup("lavender", 2);
            testBrewerySquare[i].setGuiController(guiController);
            testBrewerySquare[i].setGuiIsOn(false);

        }

        for(int i = 0 ; i < testPlayers.length ; i++) {
            testPlayers[i].setGui(testGuiPlayers[i], guiController, msg);
            testPlayers[i].depositMoney(startBalance,false);
            testPlayers[i].setBank(bank);
            testPlayers[i].setGuiIsOn(false);
        }
    }


    @Test   // User story K7 , accepttest : Givet at spilleren ejer flere huse eller hotel, så tjener vedkommende flere penge svarende til bygningernes værdi.
    public void cannotBuyHouseBecauseNotEnoughMoney() {
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        testPlayers[0].withdrawMoney(startBalance-1200,false);

        Deed_Buildable[] deedsToBuildOn = {testStreetSquare[2].getDeed()};
        testPlayers[0].buyHouse(deedsToBuildOn, 1);
        assertEquals(0, testStreetSquare[0].getDeed().getHouseCount());
    }
    @Test
    public void cannotBuyHouseBecauseNotOwnerOfLotGroup() {
        Deed_Buildable[] deedsToBuildOn = {testStreetSquare[0].getDeed()};
        testPlayers[0].buyHouse(deedsToBuildOn, 1);

        assertEquals(0, testStreetSquare[0].getDeed().getHouseCount());
    }


    @Test  // User story K20, accepttest: Givet at spilleren allerede har købt 4 huse på samme grund først, så kan de efterfølgende købe et hotel
    public void buyHotelForDeedSquare() {
        testStreetSquare[2].testing(true,"ja");
        System.out.println("Group size is: " + testStreetSquare[2].getDeed().getGroupSize());
        testStreetSquare[2].landOn(testPlayers[0]);
        Deed_Buildable[] deedsToBuildOn = {testStreetSquare[2].getDeed()};
        testPlayers[0].buyHouse(deedsToBuildOn, 4);
        testPlayers[0].buyHotel(deedsToBuildOn);

        assertTrue(testStreetSquare[2].getDeed().hasHotel());
    }

    @Test  // User story K20, accepttest: Givet at spilleren allerede har købt 4 huse på samme grund først, så kan de efterfølgende købe et hotel
    public void errorMsgCannotBuyHotel() {
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        Deed_Buildable[] deedsToBuildOn = {testStreetSquare[2].getDeed()};
        testPlayers[0].buyHotel(deedsToBuildOn);

        assertFalse(testStreetSquare[2].getDeed().hasHotel());
    }

    @Test
    public void cannotBuyHotelBecauseLackOfFunds() {
        int amount = startBalance-streetDeedPrice-(buildingPrice*4);
        testPlayers[0].withdrawMoney(amount,false);
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        Deed_Buildable[] deedsToBuildOn = {testStreetSquare[2].getDeed()};
        testPlayers[0].buyHouse(deedsToBuildOn, 4);
        testPlayers[0].buyHotel(deedsToBuildOn);

        assertFalse(testStreetSquare[2].getDeed().hasHotel());
    }

    @Test // User story K4, accepttest : Givet at en anden spiller lander på et spillers grund, så kan ejeren opkræve lejemål svarende til ejendommens værdi
    public void ownerHasNoHousesAndNotOwnerOfLotGroupReceives50InRent() {
        testStreetSquare[0].testing(true,"ja");
        testStreetSquare[0].landOn(testPlayers[0]);

        testStreetSquare[0].landOn(testPlayers[1]);
        assertEquals(startBalance-50,testPlayers[1].getCurrentBalance());
    }


    @Test // User story K7 , accepttest : Givet at spilleren ejer flere huse eller hotel, så tjener vedkommende flere penge svarende til bygningernes værdi.
    public void ownerHasNoHousesButOwnerOfLotGroupReceives100InRent() {
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);

        testStreetSquare[2].landOn(testPlayers[1]);

        assertEquals(startBalance-50*2,testPlayers[1].getCurrentBalance());
    }


    @Test // User story K7 , accepttest : Givet at spilleren ejer flere huse eller hotel, så tjener vedkommende flere penge svarende til bygningernes værdi.
    public void ownerHas1HouseReceives250InRent() {

        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        Deed_Buildable[] deedsToBuildOn = {testStreetSquare[2].getDeed()};
        testPlayers[0].buyHouse(deedsToBuildOn, 1);

        testStreetSquare[2].landOn(testPlayers[1]);

        assertEquals(startBalance-250,testPlayers[1].getCurrentBalance());
    }

    @Test  // User story K7 , accepttest : Givet at spilleren ejer flere huse eller hotel, så tjener vedkommende flere penge svarende til bygningernes værdi.
    public void ownerHas2HousesReceives750InRent() {
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        Deed_Buildable[] deedsToBuildOn = {testStreetSquare[2].getDeed()};
        testPlayers[0].buyHouse(deedsToBuildOn, 2);

        testStreetSquare[2].landOn(testPlayers[1]);

        assertEquals(startBalance-750,testPlayers[1].getCurrentBalance());
    }

    @Test // User story K7 , accepttest : Givet at spilleren ejer flere huse eller hotel, så tjener vedkommende flere penge svarende til bygningernes værdi.
    public void ownerHas3HousesReceives2250InRent() {
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        Deed_Buildable[] deedsToBuildOn = {testStreetSquare[2].getDeed()};
        testPlayers[0].buyHouse(deedsToBuildOn, 3);

        testStreetSquare[2].landOn(testPlayers[1]);

        assertEquals(startBalance-2250,testPlayers[1].getCurrentBalance());
    }

    @Test // User story K7 , accepttest : Givet at spilleren ejer flere huse eller hotel, så tjener vedkommende flere penge svarende til bygningernes værdi.
    public void ownerHas4HousesReceives4000InRent() {
        testStreetSquare[2].testing(true,"ja");
        testStreetSquare[2].landOn(testPlayers[0]);
        Deed_Buildable[] deedsToBuildOn = {testStreetSquare[2].getDeed()};
        testPlayers[0].buyHouse(deedsToBuildOn, 4);

        testStreetSquare[2].landOn(testPlayers[1]);

        assertEquals(startBalance-4000,testPlayers[1].getCurrentBalance());
    }

    @Test // User story K3, accepttest : Givet at spilleren har købt en grund, så modtager spilleren rettigheder til at opkræve leje og bygge ejendom
    public void playerChoosesToBuyLot(){
        testStreetSquare[0].testing(true,"ja");
        testStreetSquare[0].landOn(testPlayers[0]);
        assertFalse(testStreetSquare[0].hasDeed());
        assertEquals(testPlayers[0], testStreetSquare[0].getDeedOwner());
    }

    @Test // User story K3, acceptest : Givet at spilleren har købt en grund, så modtager spilleren rettigheder til at opkræve leje og bygge ejendom
    public void playerChoosesNotToBuyLot(){
        testStreetSquare[0].testing(true,"nej");
        testStreetSquare[0].landOn(testPlayers[0]);
        assertTrue(testStreetSquare[0].hasDeed());
        assertNull(testStreetSquare[0].getDeedOwner());
    }

    @Test // User story K3, acceptest : Givet at spilleren har købt en grund, så modtager spilleren rettigheder til at opkræve leje og bygge ejendom
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
    @Test // User story K23, accepttest : Givet at en spiller ejer grunde i samme farve, så kan de opkræve dobbeltleje, når en anden spiller lander på dem.
    public void playerHasAllLotsOfSameColour(){
        testPlayers[0].takeBuildableDeed(testStreetSquare[0].getDeed());
        testPlayers[0].takeBuildableDeed(testStreetSquare[0].getDeed());

        assertTrue(testStreetSquare[0].ownsGroup(testPlayers[0]));
    }

    @Test // User story K23, accepttest : Givet at en spiller ejer grunde i samme farve, så kan de opkræve dobbeltleje, når en anden spiller lander på dem.
    public void playerDoesNotHaveAllLotsOfSameColour(){
        testPlayers[0].takeBuildableDeed(testStreetSquare[0].getDeed());

        assertFalse(testStreetSquare[0].ownsGroup(testPlayers[0]));
    }

    @Test // user story K24, accepttest:  Givet at spilleren har bygget et hus på hver grund i samme gruppe, så vedkommende kan bygge videre ovenpå
    public void playerCannotBuildSecondHouseBecauseNoHouseOnOtherLotInGroup() {
        testStreetSquare[0].testing(true,"ja");
        testStreetSquare[0].landOn(testPlayers[0]);
        testStreetSquare[1].testing(true,"ja");
        testStreetSquare[1].landOn(testPlayers[0]);

        Deed_Buildable[] deedsToBuildOn = {testStreetSquare[0].getDeed()};
        testPlayers[0].buyHouse(deedsToBuildOn,2);

        assertEquals(1, testStreetSquare[0].getDeed().getHouseCount());
    }

    @Test  // User story K3, accepttest : Givet at spilleren har købt en grund, så modtager spilleren rettigheder til at opkræve leje og bygge ejendom
    public void playerBuilds1HouseOnTestDeedSquare0And1() {
        testStreetSquare[0].testing(true,"ja");
        testStreetSquare[0].landOn(testPlayers[0]);
        testStreetSquare[1].testing(true,"ja");
        testStreetSquare[1].landOn(testPlayers[0]);

        Deed_Buildable[] deedsToBuildOn = {testStreetSquare[0].getDeed(), testStreetSquare[1].getDeed()};
        testPlayers[0].buyHouse(deedsToBuildOn,1);

        assertEquals(1, testStreetSquare[0].getDeed().getHouseCount());
        assertEquals(1, testStreetSquare[1].getDeed().getHouseCount());
    }

    @Test // User story K3, accepttest : Givet at spilleren har købt en grund, så modtager spilleren rettigheder til at opkræve leje og bygge ejendom
    public void playerBuilds2HousesOnTestDeedSquare0And1() {
        testPlayers[0].depositMoney(2000,false);
        testStreetSquare[0].testing(true,"ja");
        testStreetSquare[0].landOn(testPlayers[0]);
        testStreetSquare[1].testing(true,"ja");
        testStreetSquare[1].landOn(testPlayers[0]);

        Deed_Buildable[] deedsToBuildOn = {testStreetSquare[0].getDeed(), testStreetSquare[1].getDeed()};
        testPlayers[0].buyHouse(deedsToBuildOn,2);

        assertEquals(2, testStreetSquare[0].getDeed().getHouseCount());
        assertEquals(2, testStreetSquare[1].getDeed().getHouseCount());
    }

    @Test // User story K3, accepttest : Givet at spilleren har købt en grund, så modtager spilleren rettigheder til at opkræve leje og bygge ejendom
    public void playerOwns1ShippingLotAndRentIs500() {
        testShippingSquare[0].testing(true,"ja");
        testShippingSquare[0].landOn(testPlayers[0]);

        testShippingSquare[0].landOn(testPlayers[1]);

        assertEquals(startBalance-500, testPlayers[1].getCurrentBalance());
        assertEquals(startBalance-shippingDeedPrice+500, testPlayers[0].getCurrentBalance());

    }

    @Test // User story K3, accepttest : Givet at spilleren har købt en grund, så modtager spilleren rettigheder til at opkræve leje og bygge ejendom
    public void playerOwns2ShippingLotsAndRentIs1000() {
        testShippingSquare[0].testing(true,"ja");
        testShippingSquare[0].landOn(testPlayers[0]);
        testShippingSquare[1].testing(true,"ja");
        testShippingSquare[1].landOn(testPlayers[0]);

        testShippingSquare[0].landOn(testPlayers[1]);

        assertEquals(startBalance-1000, testPlayers[1].getCurrentBalance());
        assertEquals(startBalance-shippingDeedPrice*2+1000, testPlayers[0].getCurrentBalance());

    }

    @Test // User story K3, accepttest : Givet at spilleren har købt en grund, så modtager spilleren rettigheder til at opkræve leje og bygge ejendom
    public void playerOwns3ShippingLotsAndRentIs2000() {
        testShippingSquare[0].testing(true,"ja");
        testShippingSquare[0].landOn(testPlayers[0]);
        testShippingSquare[1].testing(true,"ja");
        testShippingSquare[1].landOn(testPlayers[0]);
        testShippingSquare[2].testing(true,"ja");
        testShippingSquare[2].landOn(testPlayers[0]);

        testShippingSquare[0].landOn(testPlayers[1]);

        assertEquals(startBalance-2000, testPlayers[1].getCurrentBalance());
        assertEquals(startBalance-shippingDeedPrice*3+2000, testPlayers[0].getCurrentBalance());

    }

    @Test // User story K3, accepttest : Givet at spilleren har købt en grund, så modtager spilleren rettigheder til at opkræve leje og bygge ejendom
    public void playerOwns4ShippingLotsAndRentIs4000() {
        testShippingSquare[0].testing(true,"ja");
        testShippingSquare[0].landOn(testPlayers[0]);
        testShippingSquare[1].testing(true,"ja");
        testShippingSquare[1].landOn(testPlayers[0]);
        testShippingSquare[2].testing(true,"ja");
        testShippingSquare[2].landOn(testPlayers[0]);
        testShippingSquare[3].testing(true,"ja");
        testShippingSquare[3].landOn(testPlayers[0]);

        testShippingSquare[0].landOn(testPlayers[1]);

        assertEquals(startBalance-4000, testPlayers[1].getCurrentBalance());
        assertEquals(startBalance-shippingDeedPrice*4+4000, testPlayers[0].getCurrentBalance());

    }

    @Test // User story K3, accepttest : Givet at spilleren har købt en grund, så modtager spilleren rettigheder til at opkræve leje og bygge ejendom
    public void playerOwns1breweryRentIs100() {
        testBrewerySquare[0].testing(true,"ja");
        testBrewerySquare[0].landOn(testPlayers[0]);

        testBrewerySquare[0].landOn(testPlayers[1]);

        assertEquals(startBalance-100, testPlayers[1].getCurrentBalance());
        assertEquals(startBalance-breweryDeedPrice+100, testPlayers[0].getCurrentBalance());

    }
    @Test // User story K3, accepttest : Givet at spilleren har købt en grund, så modtager spilleren rettigheder til at opkræve leje og bygge ejendom
    public void playerOwns2breweryRentIs200() {
        testBrewerySquare[0].testing(true,"ja");
        testBrewerySquare[0].landOn(testPlayers[0]);
        testBrewerySquare[1].testing(true,"ja");
        testBrewerySquare[1].landOn(testPlayers[0]);


        testBrewerySquare[0].landOn(testPlayers[1]);

        assertEquals(startBalance-200, testPlayers[1].getCurrentBalance());
        assertEquals(startBalance-breweryDeedPrice*2+200, testPlayers[0].getCurrentBalance());

    }


}