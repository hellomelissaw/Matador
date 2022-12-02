package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardDeedTest {

    GuiController guiController = new GuiController();
    Player testPlayer1 = new Player("TestPlayer 1");

    Text msg = new Text("src/main/java/Translator/EnglishText");

    GUI_Player testGuiPlayer1 = new GUI_Player("TestPlayer 1");

    @Test
    public void Chance8GoToSkateParkFromSquare22() {
        ChanceCard goToSkatePark = new CardDeed("chance8", guiController, "na", "na");
        testPlayer1.updatePosition(22);
        goToSkatePark.setCardLang(msg);
        goToSkatePark.playCard(testPlayer1,testGuiPlayer1);
        assertEquals(10, testPlayer1.getPosition());
    }


    @Test
    public void Chance9GoToFreeCyanSquareFromSquare15() {
        Square[] board = new Square[24];
        for (int i = 0 ; i < 24 ; i++) {
            board[i] = new DeedSquare("Test Deed", 5, guiController);
        }


        String[] colors = {"lightgrey", "cyan", "pink", "orange", "red", "yellow", "green", "darkblue"};
        board[1].setColor(colors[0]);
        board[2].setColor(colors[0]);

        board[4].setColor(colors[1]);
        board[5].setColor(colors[1]);

        board[7].setColor(colors[2]);
        board[8].setColor(colors[2]);

        board[10].setColor(colors[3]);
        board[11].setColor(colors[3]);

        board[13].setColor(colors[4]);
        board[14].setColor(colors[4]);

        board[16].setColor(colors[5]);
        board[17].setColor(colors[5]);

        board[19].setColor(colors[6]);
        board[20].setColor(colors[6]);

        board[22].setColor(colors[7]);
        board[23].setColor(colors[7]);
        /*for(int i = 1 ; i < board.length ; i+=3) {
            for (int j = 0 ; j < colors.length ; j++) {
                board[i].setColor(colors[j]);
                board[i+1].setColor(colors[j]);
            }
        }*/

        ChanceCard[] chanceCards = new ChanceCard[8];

        chanceCards[0] = new CardDeed("chance8", guiController, "na","na");
        chanceCards[1] = new CardDeed("chance9", guiController, "cyan", "red");
        chanceCards[2] = new CardDeed("chance10", guiController, "lightgrey", "yellow");
        chanceCards[3] = new CardDeed("chance11", guiController, "orange", "na");
        chanceCards[4] = new CardDeed("chance12", guiController, "cyan", "na");
        chanceCards[5] = new CardDeed("chance13", guiController, "red", "na");
        chanceCards[6] = new CardDeed("chance14", guiController, "orange", "green");
        chanceCards[7] = new CardDeed("chance15", guiController, "pink", "darkblue");



        for(int i = 0 ; i < chanceCards.length ; i++) {
            ((CardDeed)chanceCards[i]).setBoard(board);
            chanceCards[i].setCardLang(msg);

        }
        testPlayer1.updatePosition(15);
        chanceCards[0].playCard(testPlayer1,testGuiPlayer1);
       assertEquals(10, testPlayer1.getPosition());

        chanceCards[1].playCard(testPlayer1,testGuiPlayer1);
        assertEquals(4, testPlayer1.getPosition());

        chanceCards[2].playCard(testPlayer1,testGuiPlayer1);
        assertEquals(1, testPlayer1.getPosition());

        chanceCards[3].playCard(testPlayer1,testGuiPlayer1);
        assertEquals(10, testPlayer1.getPosition());

        chanceCards[4].playCard(testPlayer1,testGuiPlayer1);
        assertEquals(5, testPlayer1.getPosition());

        chanceCards[5].playCard(testPlayer1,testGuiPlayer1);
        assertEquals(13, testPlayer1.getPosition());

        chanceCards[6].playCard(testPlayer1,testGuiPlayer1);
        assertEquals(11, testPlayer1.getPosition());

        chanceCards[7].playCard(testPlayer1,testGuiPlayer1);
        assertEquals(7, testPlayer1.getPosition());

    }

    @Test
    public void playerGetsDeedForFree() {

    }
}