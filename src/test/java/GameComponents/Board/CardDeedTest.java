package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardDeedTest {
    GuiController guiController = new GuiController();

    String[] colors = {"lightgrey", "cyan", "pink", "orange", "red", "yellow", "green", "darkblue"};
    Player[] testPlayers = new Player[2];
    Text msg = new Text("src/main/java/Translator/EnglishText", guiController);

    Square[] board = new Square[24];

    GUI_Player[] testGuiPlayers = new GUI_Player[2];

    ChanceCard[] chanceCards = new ChanceCard[8];
    public CardDeedTest() {

        for (int i = 0 ; i < board.length ; i++) {
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

        }

        testGuiPlayers[0] = new GUI_Player("Test Player 1");
        testGuiPlayers[1] = new GUI_Player("Test Player 2");

        testPlayers[0] = new Player("Test Player1");
        testPlayers[0].setGui(testGuiPlayers[0], guiController);

        testPlayers[1] = new Player("Test Player2");
        testPlayers[1].setGui(testGuiPlayers[0], guiController);

        chanceCards[0] = new CardDeed("chance8", guiController, "na","na");
        chanceCards[1] = new CardDeed("chance9", guiController, "cyan", "red");
        chanceCards[2] = new CardDeed("chance10", guiController, "lightgrey", "yellow");
        chanceCards[3] = new CardDeed("chance11", guiController, "orange", "na");
        chanceCards[4] = new CardDeed("chance12", guiController, "cyan", "na");
        chanceCards[5] = new CardDeed("chance13", guiController, "red", "na");
        chanceCards[6] = new CardDeed("chance14", guiController, "orange", "green");
        chanceCards[7] = new CardDeed("chance15", guiController, "pink", "darkblue");

        for (int i = 0 ; i < board.length ; i++) {
            board[i].setLang(msg);
            if(board[i] instanceof ChanceSquare){
                ((ChanceSquare)board[i]).setCardLang();
                ((ChanceSquare)board[i]).setBoard(board);
            }
        }

        for(int i = 0 ; i < chanceCards.length ; i++) {
            ((CardDeed)chanceCards[i]).setBoard(board);
            chanceCards[i].setCardLang(msg);

        }
    }



    @Test
    public void setColorForLoop(){

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
    public void Chance8GoToSkateParkFromSquare22() {

        ChanceCard goToSkatePark = new CardDeed("chance8", guiController, "na", "na");
        testPlayers[0].updatePosition(22);
        goToSkatePark.setCardLang(msg);
        ((CardDeed)goToSkatePark).setBoard(board);
        goToSkatePark.playCard(testPlayers[0]);
        assertEquals(10, testPlayers[0].getPosition());
    }

    @Test
    public void Chance8GoToSkateParkAndPayRent() {

        board[10].setColor("na");
        ((DeedSquare)board[10]).setDeedOwner(testPlayers[0],10);
        board[10].setLang(msg);
        ChanceCard goToSkatePark = new CardDeed("chance8", guiController, "na", "na");
        goToSkatePark.setCardLang(msg);
        ((CardDeed)goToSkatePark).setBoard(board);
        goToSkatePark.playCard(testPlayers[1]);
        assertEquals(-5, testPlayers[1].getCurrentBalance());
    }


    @Test
    public void Chance9GoToFreeCyanSquareFromSquare15() {

        testPlayers[0].updatePosition(15);
        chanceCards[0].playCard(testPlayers[0]);
        assertEquals(10, testPlayers[0].getPosition());

        chanceCards[1].playCard(testPlayers[0]);
        assertEquals(4, testPlayers[0].getPosition());

        chanceCards[2].playCard(testPlayers[0]);
        assertEquals(1, testPlayers[0].getPosition());

        chanceCards[3].playCard(testPlayers[0]);
        assertEquals(11, testPlayers[0].getPosition());

        chanceCards[4].playCard(testPlayers[0]);
        assertEquals(5, testPlayers[0].getPosition());

        chanceCards[5].playCard(testPlayers[0]);
        assertEquals(13, testPlayers[0].getPosition());

        chanceCards[6].playCard(testPlayers[0]);
        assertEquals(19, testPlayers[0].getPosition());

        chanceCards[7].playCard(testPlayers[0]);
        assertEquals(7, testPlayers[0].getPosition());

    }

    @Test
    public void playerGetsSkateParkAndCyanSquareDeedForFree() {

        testPlayers[0].depositMoney(3);

        chanceCards[0].playCard(testPlayers[0]);
        assertEquals(3,testPlayers[0].getCurrentBalance());

        chanceCards[1].playCard(testPlayers[0]);
        assertEquals(3, testPlayers[0].getCurrentBalance());

    }

    @Test
    public void payRentIfNoAvailableSquare() {

        ((DeedSquare)board[10]).setDeedOwner(testPlayers[0], 10);
        ((DeedSquare)board[11]).setDeedOwner(testPlayers[0], 11);

        chanceCards[3].playCard(testPlayers[1]);

        assertEquals(-5, testPlayers[1].getCurrentBalance());

    }
}