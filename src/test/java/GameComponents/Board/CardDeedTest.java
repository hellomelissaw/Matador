package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import GameComponents.Board.BoardInit;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardDeedTest {
    GuiController guiController = new GuiController();

    String[] colors = {"lightgrey", "cyan", "pink", "orange", "red", "yellow", "green", "darkblue"};
    Player[] testPlayers = new Player[2];
    Text msg = new Text("src/main/java/Translator/EnglishText", guiController);

    ChanceCard[] chanceCards = new ChanceCard[8];
    Square[] board;

    GUI_Player[] testGuiPlayers = new GUI_Player[2];

    //ChanceCard[] chanceCards = new ChanceCard[8];

    public CardDeedTest() {

       /* for (int i = 0 ; i < board.length ; i++) {
            board[i] = new DeedSquare("Test Deed", 5, guiController);
        }
        for(int i = 0 ; i < board.length ; i+=3) {
            if(i % 2 == 0) {
                board[i].setColor("white");
            } else {
                board[i].setColor("magenta");
            }
            board[i+1].setColor(colors[i/3]);
            board[i+2].setColor(colors[i/3]);
        }*/

        testGuiPlayers[0] = new GUI_Player("Test Player 1");
        testGuiPlayers[1] = new GUI_Player("Test Player 2");

        testPlayers[0] = new Player("Test Player 1");
        testPlayers[0].setGui(testGuiPlayers[0], guiController, msg);

        testPlayers[1] = new Player("Test Player 2");
        testPlayers[1].setGui(testGuiPlayers[1], guiController, msg);


        BoardInit testBoard = new BoardInit(guiController, msg, testPlayers);
        //testBoard.initChanceSquare(true);
        board = testBoard.getSquareArr();

        chanceCards[0] = new CardDeed_stub("chance8", guiController, "na","na");
        chanceCards[1] = new CardDeed_stub("chance9", guiController, "cyan", "red");
        chanceCards[2] = new CardDeed_stub("chance10", guiController, "lightgrey", "yellow");
        chanceCards[3] = new CardDeed_stub("chance11", guiController, "orange", "na");
        chanceCards[4] = new CardDeed_stub("chance12", guiController, "cyan", "na");
        chanceCards[5] = new CardDeed_stub("chance13", guiController, "red", "na");
        chanceCards[6] = new CardDeed_stub("chance14", guiController, "orange", "green");
        chanceCards[7] = new CardDeed_stub("chance15", guiController, "pink", "darkblue");

        for(int i = 0 ; i < chanceCards.length ; i++) {
            chanceCards[i].setBoard(board);
            chanceCards[i].setCardLang(msg);
            chanceCards[i].setPlayers(testPlayers);
        }

       /* for (int i = 0 ; i < board.length ; i++) {
            board[i].setLang(msg);
            if(board[i] instanceof ChanceSquare){
                ((ChanceSquare)board[i]).setCardLang();
                ((ChanceSquare)board[i]).setBoard(board);
            }
        }
        for (int i = 0; i < chanceCards.length; i++) {
            ((CardDeed_stub) chanceCards[i]).setBoard(board);
            chanceCards[i].setCardLang(msg);
        }
             int arraySize = 0;
        Square[] currentColorsArr = new Square[0];
        for (int i = 0; i < board.length; i++) {
            if (board[i].getColor() == color1 || board[i].getColor() == color2) {
                arraySize++;
                Square[] updatedColorArr = new Square[arraySize];
                updatedColorArr[0] = board[i];
                System.arraycopy(currentColorsArr, 0, updatedColorArr, 1, currentColorsArr.length);
                currentColorsArr = updatedColorArr.clone();
            }
        }
        */
    }


    @Test
    public void setColorForLoop() {

        assertEquals("white", board[0].getColor());
        assertEquals("lightgrey", board[1].getColor());
        assertEquals("lightgrey", board[2].getColor());
        assertEquals("magenta", board[3].getColor());
        assertEquals("cyan", board[4].getColor());
        assertEquals("cyan", board[5].getColor());
        assertEquals("white", board[6].getColor());
        assertEquals("pink", board[7].getColor());
        assertEquals("pink", board[8].getColor());
        assertEquals("magenta", board[9].getColor());
        assertEquals("orange", board[10].getColor());
        assertEquals("orange", board[11].getColor());
        assertEquals("white", board[12].getColor());
        assertEquals("red", board[13].getColor());
        assertEquals("red", board[14].getColor());
        assertEquals("magenta", board[15].getColor());
        assertEquals("yellow", board[16].getColor());
        assertEquals("yellow", board[17].getColor());
        assertEquals("white", board[18].getColor());
        assertEquals("green", board[19].getColor());
        assertEquals("green", board[20].getColor());
        assertEquals("magenta", board[21].getColor());
        assertEquals("darkblue", board[22].getColor());
        assertEquals("darkblue", board[23].getColor());


    }

    @Test
    public void Chance8GoToSkateParkAndGetFree(){
        chanceCards[0].playCard(testPlayers[0]);
        assertEquals(0, testPlayers[0].getCurrentBalance());
    }

    @Test
    public void Chance8GoToSkateParkAndPayRent() {
        ((DeedSquare) board[10]).setDeedOwner(testPlayers[1], 10);
        //((CardDeed_stub) chanceCards[0]).setSelectedSquare(board[10]);
        chanceCards[0].playCard(testPlayers[0]);

        assertEquals(-2, testPlayers[0].getCurrentBalance());
    }


    //TESTS FOR CHANCE CARD 9
    //Player chooses cyan
    @Test
    public void playerGetsCandyShopFree() {
        ((CardDeed_stub) chanceCards[1]).setSelectedSquare(board[4]);
        chanceCards[1].playCard(testPlayers[0]);
        Player owner = ((DeedSquare) board[4]).getDeedOwner();

        assertEquals("Test Player 1", owner.getPlayerName());
        assertEquals(0, testPlayers[0].getCurrentBalance());
    }

    @Test
    public void playerGetsIceCreamShopFree() {

        ((CardDeed_stub) chanceCards[1]).setSelectedSquare(board[5]);
        chanceCards[1].playCard(testPlayers[0]);
        Player owner = ((DeedSquare) board[5]).getDeedOwner();

        assertEquals("Test Player 1", owner.getPlayerName());
        assertEquals(0, testPlayers[0].getCurrentBalance());
    }

    //Player chooses red
    @Test
    public void playerGetsPlayingHallFree() {

        ((CardDeed_stub) chanceCards[1]).setSelectedSquare(board[13]);
        chanceCards[1].playCard(testPlayers[0]);
        Player owner = ((DeedSquare) board[13]).getDeedOwner();

        assertEquals("Test Player 1", owner.getPlayerName());
        assertEquals(0, testPlayers[0].getCurrentBalance());
    }

    @Test
    public void playerGetsCinemaFree() {
        ((CardDeed_stub) chanceCards[1]).setSelectedSquare(board[14]);
        chanceCards[1].playCard(testPlayers[0]);
        Player owner = ((DeedSquare) board[14]).getDeedOwner();

        assertEquals("Test Player 1", owner.getPlayerName());
        assertEquals(0, testPlayers[0].getCurrentBalance());
    }

    //TESTS FOR CHANCE CARD 10
    //Player chooses lightgrey

    @Test
    public void playerGetsBurgerBarFree() {
        ((CardDeed_stub) chanceCards[2]).setSelectedSquare(board[1]);
        chanceCards[2].playCard(testPlayers[0]);
        Player owner = ((DeedSquare) board[1]).getDeedOwner();

        assertEquals("Test Player 1", owner.getPlayerName());
        assertEquals(0, testPlayers[0].getCurrentBalance());
    }

    @Test
    public void playerGetsPizzeriaFree() {
        ((CardDeed_stub) chanceCards[2]).setSelectedSquare(board[2]);
        chanceCards[2].playCard(testPlayers[0]);
        Player owner = ((DeedSquare) board[2]).getDeedOwner();

        assertEquals("Test Player 1", owner.getPlayerName());
        assertEquals(0, testPlayers[0].getCurrentBalance());
    }

    //Player chooses yellow
    @Test
    public void playerGetsToyStoreFree() {
        ((CardDeed_stub) chanceCards[2]).setSelectedSquare(board[16]);
        chanceCards[2].playCard(testPlayers[0]);
        Player owner = ((DeedSquare) board[16]).getDeedOwner();

        assertEquals("Test Player 1", owner.getPlayerName());
        assertEquals(0, testPlayers[0].getCurrentBalance());
    }

    @Test
    public void playerGetsPetShopFree() {
        // ((CardDeed_stub) chanceCards[2]).setSquareTest(msg.getText("petShop"));
        ((CardDeed_stub) chanceCards[2]).setSelectedSquare(board[17]);
        chanceCards[2].playCard(testPlayers[0]);
        Player owner = ((DeedSquare) board[17]).getDeedOwner();

        assertEquals("Test Player 1", owner.getPlayerName());
        assertEquals(0, testPlayers[0].getCurrentBalance());
    }

    //TESTS FOR CHANCE CARD 11
    //Player chooses Skate Park
    @Test
    public void playerGetsSkateParkFree() {
        ((CardDeed_stub) chanceCards[3]).setSelectedSquare(board[10]);
        chanceCards[2].playCard(testPlayers[0]);
        Player owner = ((DeedSquare) board[10]).getDeedOwner();

        assertEquals("Test Player 1", owner.getPlayerName());
        assertEquals(0, testPlayers[0].getCurrentBalance());
    }

    @Test
    public void playerGetsPoolFree() {
        ((CardDeed_stub) chanceCards[2]).setSelectedSquare(board[11]);
        chanceCards[2].playCard(testPlayers[0]);
        Player owner = ((DeedSquare) board[11]).getDeedOwner();

        assertEquals("Test Player 1", owner.getPlayerName());
        assertEquals(0, testPlayers[0].getCurrentBalance());
    }

}