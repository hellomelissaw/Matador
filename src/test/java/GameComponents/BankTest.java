package GameComponents;

import GameComponents.Board.DeedSquare_Buildable;
import Translator.Text;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {
    private Player[] testPlayers = new Player[1];
    private int startBalance = 100;
    private int deedPrice = 5;
    private int[] rent = {2,3,4,5,6};
    private int buildingPrice = 5;

    private Text msg = new Text("src/main/java/Translator/DanskTekst", null);


    DeedSquare_Buildable[] testSquare = new DeedSquare_Buildable[1];
    public BankTest() {
        msg.setGuiIsOn(false);
        testPlayers[0] = new Player("Test Player 1");
        testPlayers[0].guiIsOn(false);
        testPlayers[0].setStartBalance(startBalance);

        testSquare[0] = new DeedSquare_Buildable("Test Square", deedPrice, rent, buildingPrice);
        testSquare[0].setGuiOn(false);
        testSquare[0].setGroup("blue", 1);
        testSquare[0].setLang(msg);

    }
   @Test
   public void startBalanceIsWithdrawnFromBank(){
        assertEquals(150000-startBalance, testPlayers[0].getBankDetails("balance"));
   }

    @Test
    public void playerReceives10FromBank() {
        testPlayers[0].depositMoney(10);
        testPlayers[0].updateBank(10, "deposit");

        assertEquals(startBalance+10, testPlayers[0].getCurrentBalance());
        assertEquals(150000-startBalance-10, testPlayers[0].getBankDetails("balance"));

    }

    @Test
    public void playerPays10toTheBank() {
        testPlayers[0].withdrawMoney(10);
        testPlayers[0].updateBank(10, "withdraw");

        assertEquals(startBalance-10, testPlayers[0].getCurrentBalance());
        assertEquals(150000-startBalance+10, testPlayers[0].getBankDetails("balance"));
    }

    @Test
    public void priceCalculator() {
    }

    @Test
    public void playerSells1HouseToBankForHalfPrice() {
        testSquare[0].testing(true,"ja");
        testSquare[0].landOn(testPlayers[0]);
        testPlayers[0].buyHouse(testSquare,1);
        int balanceBeforeSale = testPlayers[0].getCurrentBalance();
        testPlayers[0].sellHouseToBank(testSquare,1);
        assertEquals(balanceBeforeSale+buildingPrice/2, testPlayers[0].getCurrentBalance());
        assertEquals(32, testPlayers[0].getBankDetails("houseCount"));
        assertEquals(150000-startBalance+deedPrice+buildingPrice-Math.round((buildingPrice/2)), testPlayers[0].getBankDetails("balance"));
    }

    @Test
    public void playerBuys4housesFromBank(){
        testSquare[0].testing(true,"ja");
        testSquare[0].landOn(testPlayers[0]);
        testPlayers[0].buyHouse(testSquare,4);

        int balanceBeforeSale = testPlayers[0].getCurrentBalance();
        assertEquals(150000-startBalance+deedPrice+buildingPrice*4,testPlayers[0].getBankDetails("balance"));
    }

    @Test
    public void sellHotelToBank() {
        testSquare[0].testing(true,"ja");
        testSquare[0].landOn(testPlayers[0]);
        testPlayers[0].buyHouse(testSquare,4);
        testPlayers[0].buyHotel(testSquare);
        int balanceBeforeSale = testPlayers[0].getCurrentBalance();
        System.out.println("Currentbank balance" + testPlayers[0].getBankDetails("balance"));
        testPlayers[0].sellHotelToBank(testSquare);
        assertEquals(balanceBeforeSale+buildingPrice/2, testPlayers[0].getCurrentBalance());
        assertEquals(12, testPlayers[0].getBankDetails("hotelCount"));
        assertEquals(150000-startBalance+deedPrice+buildingPrice*5-Math.round((buildingPrice/2)), testPlayers[0].getBankDetails("balance"));
    }

    @Test
    public void playerBuys1HouseSo1LessHouseInBank() {
        testSquare[0].testing(true,"ja");
        testSquare[0].landOn(testPlayers[0]);
        testPlayers[0].buyHouse(testSquare,1);

        assertEquals(31, testPlayers[0].getBankDetails("houseCount"));
    }

    @Test
    public void buyHotelFromBank() {
        testSquare[0].testing(true,"ja");
        testSquare[0].landOn(testPlayers[0]);
        testPlayers[0].buyHouse(testSquare,4);
        testPlayers[0].buyHotel(testSquare);

        assertEquals(11, testPlayers[0].getBankDetails("hotelCount"));
    }
}