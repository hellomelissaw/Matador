package Controllers;

import GameComponents.Board.Deed;
import GameComponents.Board.Deed_Buildable;
import GameComponents.Board.Deed_NonBuildable;
import GameComponents.Player;
import Translator.Text;
import org.junit.Test;

import static org.junit.Assert.*;

public class SellControllerTest {

    GuiController guiController = new GuiController();
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);
    SellController sellController = new SellController(guiController,msg);
    Player player = new Player("TestPlayer");
    Player buyerPlayer = new Player("BuyerPlayer");
    int[] array = {1000,50,250,750,2250,4000,6000};
    Deed_Buildable deedBuildable = new Deed_Buildable(1200 ,array,"Rødorvrevej",500);
    boolean guiIsOn = true;

    @Test
    public void testPlayerTakeNonBuildableDeed() {

        Deed_NonBuildable deed = new Deed_NonBuildable(100,array,"Shipping");
        player.takeNonBuildableDeed(deed);
        assertEquals("Shipping" ,player.getNonBuildableDeeds()[0].getDeedName());
    }
    @Test
    public void testGetOwnedFields(){
        player.addToOwnedFields(deedBuildable);
        (player.getOwnedFields()[0]).getDeedName();
        String expected = "Rødorvrevej";
        assertEquals(expected , (player.getOwnedFields()[0]).getDeedName());
    }
    @Test
    public void testChangeOwner(){

        //player.depositMoney(2000);
        //buyerPlayer.depositMoney(2000);
    }
}