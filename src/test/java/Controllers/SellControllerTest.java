package Controllers;

import GameComponents.Board.*;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import static org.junit.Assert.*;

public class SellControllerTest {

    GuiController guiController = new GuiController();
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);
    SellController sellController = new SellController(guiController,msg);
    Player testPlayer = new Player("TestPlayer");
    Player buyerPlayer = new Player("BuyerPlayer");

    Player[] players = new Player[3];
    int[] array = {1000,50,250,750,2250,4000,6000};
    Deed_Buildable deedBuildable = new Deed_Buildable(1200 ,array,"Rødorvrevej",500);
    //Deed_NonBuildable bunnyPalace = new Deed_NonBuildable(1000, array, "Bunny Palace");
    //Deed_Buildable ratKingdom = new Deed_Buildable(1000, array, "Rat Kingdom", 500);
    DeedSquare_Buildable bunnyPalace = new DeedSquare_Buildable("Bunny Palace", 1200, array, 500);
    DeedSquare_NonBuildable ratKingdom = new DeedSquare_NonBuildable("Rat Kingdom", 1000, array);
    GUI_Player testGuiPlayer = new GUI_Player("Test Player");

    int startBalance = 7000;
    public SellControllerTest() {
        testPlayer.guiIsOn(false);
        buyerPlayer.guiIsOn(false);

        for(int i = 0 ; i < players.length ; i++) {
            players[i] = new Player("TestPlayer"+i);
            players[i].guiIsOn(false);
            players[i].setStartBalance(startBalance, false);
        }

        bunnyPalace.setOwnerForTesting(players[1]);
        ratKingdom.setOwnerForTesting(players[2]);

    }

    @Test
    public void testPlayerTakeNonBuildableDeed() {

        Deed_NonBuildable deed = new Deed_NonBuildable(100,array,"Shipping");
        testPlayer.takeNonBuildableDeed(deed);
        assertEquals("Shipping" , testPlayer.getNonBuildableDeeds()[0].getDeedName());
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
        String[] expected = {"Bunny Palace", "Rat Kingdom"};
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
        Deed[] deeds = players[1].getOwnedFields();
        String[] player1OwnedFields = new String[1];

        for (int i = 0 ; i < deeds.length ; i++) {
            //player0OwnedFields[i] = players[0].getOwnedFields()[i].getDeedName();
            player1OwnedFields[i] = deeds[i].getDeedName();
        }

        System.out.println("Player 1 owned fields: " + player1OwnedFields[0]);

        sellController.setTestingBuyLot(true,"Bunny Palace", 1000, true);
        sellController.buyLot(players[0], players);

        assertEquals(startBalance-1000,players[0].getCurrentBalance());
        assertEquals(startBalance+1000, players[1].getCurrentBalance());
        assertEquals(players[0], bunnyPalace.getDeedOwner());


    }
}