package Controllers;

import GameComponents.Bank;
import GameComponents.Board.Deed_Buildable;
import GameComponents.Board.Deed_NonBuildable;
import GameComponents.Player;
import Translator.Text;
import org.junit.Test;

import static org.junit.Assert.*;

public class SellControllerTest {

    GuiController guiController = new GuiController();
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);
    //SellController sellController = new SellController(guiController,msg);
    Player testPlayer = new Player("TestPlayer");
    Player buyerPlayer = new Player("BuyerPlayer");
    int[] arrayBuildable = {1000,50,250,750,2250,4000,6000};
    int[] arrayNonBuildable = {3000,100,200};
    Bank bank = new Bank();
    Deed_Buildable deedBuildable = new Deed_Buildable(1200 , arrayBuildable,"Rødorvrevej",500);
    Deed_NonBuildable deedNonBuildable = new Deed_NonBuildable(3000,arrayNonBuildable,"Tuborg Squash");
    //GUI_Player testGuiPlayer = new GUI_Player("Test Player");


    @Test
    public void testPlayerTakeNonBuildableDeed() {

        Deed_NonBuildable deed = new Deed_NonBuildable(100, arrayBuildable,"Shipping");
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
        testPlayer.guiIsOn(false);
        buyerPlayer.guiIsOn(false);
        testPlayer.depositMoney(2000,false);
        buyerPlayer.depositMoney(2000, false);
        assertEquals(2000, testPlayer.getCurrentBalance());
        assertEquals(2000,buyerPlayer.getCurrentBalance());
        testPlayer.addToOwnedFields(deedBuildable);
    }
}