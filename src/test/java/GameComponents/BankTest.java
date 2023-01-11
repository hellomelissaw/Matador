package GameComponents;


import Controllers.GuiController;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;
import static org.junit.Assert.*;


public class BankTest {


    Bank bank = new Bank();
    Player testPlayer = new Player("Test Player");
    GUI_Player testGuiPlayer = new GUI_Player("Test Player");
    GuiController guiController = new GuiController();
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);
    public BankTest() {
        testPlayer.setGui(testGuiPlayer, guiController, msg);
    }

    //The bank is set to have
    //gameBalance = 374500;
    //houses = 32;
    //hotels = 12;
    @Test
    public void testGiveMoneyToBank(){
        int deposit = 1000;
        bank.giveMoneyToBank(deposit);
        assertEquals(bank.getBankBalance(),375500);

    }

    @Test
    public void testBankKeepsTheRestOfTheMoney(){
        int startBalance = 30000;

        testPlayer.setBank(bank);
        testPlayer.depositMoney(startBalance,true);
        assertEquals(bank.getBankBalance(),344500);

    }

    @Test
    public void testBankWillOnlyGiveTheMoneyItHas(){
        int withdrawAmount = 374600;
        testPlayer.setBank(bank);
        testPlayer.depositMoney(withdrawAmount,true);
        assertEquals(bank.getBankBalance(),0);
    }

    @Test
    public void testBankOnlyHas32Houses(){
        //The two lines under will buy out all houses
        int boughtHouses = 32;
        bank.buyHouseFromBank(boughtHouses,0);

        //Given that the player attempts to buy a certain amount of houses, the bank will check if there are enough.
        //Since all houses are bought out, it should be false
        int buyHouses = 3;
        assertFalse(bank.areThereEnoughHouses(buyHouses));
    }

    @Test
    public void testBankOnlyHas12Hotels(){

        int boughtHotels = 12;
        bank.buyHotelFromBank(boughtHotels,0);

        assertFalse(bank.areThereStillHotels());
    }

}


