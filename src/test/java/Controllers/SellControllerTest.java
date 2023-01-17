package Controllers;

import GameComponents.Bank;
import GameComponents.Board.*;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class SellControllerTest {

    Text msg = new Text("src/main/java/Translator/DanskTekst");
    SellController sellController = new SellController(msg);
    SellController_loadable sellControllerSequ = new SellController_loadable(msg);
    Player testPlayer = new Player("TestPlayer");
    Player buyerPlayer = new Player("BuyerPlayer");

    Bank bank = new Bank();
    Player[] players = new Player[3];
    int[] array = {1000,50,250,750,2250,4000,6000};
    int[] arrayBuildable = {1000,50,250,750,2250,4000,6000};
    Deed_Buildable deedBuildable = new Deed_Buildable(1200 ,array,"Rødorvrevej",500);
    //Deed_NonBuildable bunnyPalace = new Deed_NonBuildable(1000, array, "Bunny Palace");
    //Deed_Buildable ratKingdom = new Deed_Buildable(1000, array, "Rat Kingdom", 500);
    DeedSquare_Buildable bunnyPalace = new DeedSquare_Buildable("Bunny Palace", 1200, array, 500);
    DeedSquare_NonBuildable ratKingdom = new DeedSquare_NonBuildable("Rat Kingdom", 1000, array);
    DeedSquare_Buildable boulevardOfBrokenDreams = new DeedSquare_Buildable("Boulevard of Broken Dreams", 1000, array, 500);
    GUI_Player testGuiPlayer = new GUI_Player("Test Player");

    DeedSquare_NonBuildable capriciousCarport = new DeedSquare_NonBuildable("Capricious Carport", 1000, array);

    int startBalance = 7000;
    public SellControllerTest() {
        testPlayer.setGuiIsOn(false);
        buyerPlayer.setGuiIsOn(false);
        msg.setGuiIsOn(false);

        for(int i = 0 ; i < players.length ; i++) {
            players[i] = new Player("TestPlayer"+i);
            players[i].setGuiIsOn(false);
            players[i].setBank(bank);
            players[i].setStartBalance(startBalance, false);
        }

        bunnyPalace.setOwnerForTesting(players[1]);
        bunnyPalace.setGuiOn(false);

        ratKingdom.setOwnerForTesting(players[2]);
        ratKingdom.setGuiOn(false);

        boulevardOfBrokenDreams.setOwnerForTesting(players[2]);
        capriciousCarport.setGuiOn(false);

    }

    @Test
    public void testPlayerTakeNonBuildableDeed() {

        Deed_NonBuildable deed = new Deed_NonBuildable(100,array,"Shipping");
        testPlayer.takeNonBuildableDeed(deed);
        assertEquals("Shipping" , testPlayer.getNonBuildableDeeds()[0].getDeedName());
    }
    @Test
    public void testPlayerTakeBuildableDeed(){
        testPlayer.takeBuildableDeed(deedBuildable);
        assertEquals("Rødorvrevej",testPlayer.getBuildableDeeds()[0].getDeedName());
    }
    @Test
    public void testGetOwnedFields(){
        testPlayer.addToOwnedFields(deedBuildable);
        (testPlayer.getOwnedFields()[0]).getDeedName();
        String expected = "Rødorvrevej";
        assertEquals(expected , (testPlayer.getOwnedFields()[0]).getDeedName());
    }
    @Test
    public void testChangeOwner(){

        testPlayer.depositMoney(2000, false);
        buyerPlayer.depositMoney(2000, false);
        assertEquals(2000, testPlayer.getCurrentBalance());
        assertEquals(2000,buyerPlayer.getCurrentBalance());

        testPlayer.addToOwnedFields(deedBuildable);
    }

    @Test
    public void player0WantsToBuyAndPlayer1And2LotsShownInArray() {
        sellController.setTestingBuyLot(true,"Bunny Palace", 1000, true);
        sellController.buyLot(players[0], players);
        String[] expected = {"Bunny Palace", "Boulevard of Broken Dreams", "Rat Kingdom"};
        assertEquals(expected, sellController.getLotOptions());

    }

    public void player1BuysBunnyPalaceFromPlayer2For500() {

        sellController.buyLot(players[0], players);

        assertEquals(bunnyPalace.getDeedOwner(), players[0]);

    }

    @Test
    public void deedIsOfTypeDeed_Buildable() {
       Deed_Buildable deed = bunnyPalace.getDeed();
        System.out.println(bunnyPalace.getDeedType(deed));
    }

    @Test
    public void player0BuysBunnyPalaceFromPlayer1For1000(){
        sellController.setTestingBuyLot(true,"Bunny Palace", 1000, true);
        sellController.buyLot(players[0], players);

        assertEquals(startBalance-1000,players[0].getCurrentBalance());
        assertEquals(startBalance+1000, players[1].getCurrentBalance());
        assertEquals(players[0], bunnyPalace.getDeedOwner());


    }

    @Test
    public void player0TriesToBuyBunnyPalaceFor1000ButOfferDeclined(){
        sellController.setTestingBuyLot(true,"Bunny Palace", 1000, false);
        sellController.buyLot(players[0], players);

        assertEquals(startBalance, players[0].getCurrentBalance());
        assertEquals(startBalance, players[1].getCurrentBalance());
        assertEquals(players[1], bunnyPalace.getDeedOwner());


    }

    @Test
    public void player1TradesBunnyPalaceWithPlayer2RatKingdom(){
        sellController.setTestingTradeLot(true,"Bunny Palace", "Rat Kingdom", true);
        sellController.tradeLot(players[1], players);

        assertEquals(startBalance, players[1].getCurrentBalance());
        assertEquals(startBalance, players[2].getCurrentBalance());
        assertEquals(players[1], ratKingdom.getDeedOwner());
        assertEquals(players[2], bunnyPalace.getDeedOwner());


    }

    @Test
    public void player1AsksTradesBunnyPalaceWithPlayer2RatKingdomOfferRefused(){
        sellController.setTestingTradeLot(true,"Bunny Palace", "Rat Kingdom", false);
        sellController.tradeLot(players[1], players);

        assertEquals(startBalance, players[1].getCurrentBalance());
        assertEquals(startBalance, players[2].getCurrentBalance());
        assertEquals(players[2], ratKingdom.getDeedOwner());
        assertEquals(players[1], bunnyPalace.getDeedOwner());

    }
@Test
    public void player1BidsOnCapriciousCarportAndGetIt(){
        capriciousCarport.testing(true, "nej");
        capriciousCarport.landOn(players[0]);

        sellController.setTestingAuctionLot(true,"TestPlayer1", 1000, false);
        sellController.auctionLot(players[0],players,capriciousCarport.getAuctionedDeed());
        assertEquals(players[1], capriciousCarport.getDeedOwner());
}

@Test
    public void player1Bids1000Player2Bids1100Player1Bids1200AndGetCapriciousCarport() {
        capriciousCarport.testing(true,"nej");
        capriciousCarport.landOn(players[0]);
        String[] bidderNames= {"TestPlayer1", "TestPlayer2", "TestPlayer1"};
        int[] offers = {1000, 1100, 1200};
        boolean[] higherBidders = {true, true, false};
        sellControllerSequ.setTestingAuctionLot(bidderNames, offers, higherBidders);
        sellControllerSequ.auctionLot(players[0], players, capriciousCarport.getAuctionedDeed());
        assertEquals(players[1], capriciousCarport.getDeedOwner());
        assertEquals(startBalance-1200, players[1].getCurrentBalance());
        assertEquals(startBalance, players[2].getCurrentBalance());
}
}