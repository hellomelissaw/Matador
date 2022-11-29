package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;

import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class LandOnSquareTest {
    Player[] testPlayers;
    GuiController guiController = new GuiController();

    Square[] testBoard;
    GUI_Player[] guiPlayers;

    Text testMsg = new Text("src/main/java/Translator/EnglishText");

    @Test
    public void player1BuysAvailableDeedSquareAndPlayer2PaysRent() {
        testPlayers = new Player[2];
        testPlayers[0] = new Player("Player 1");
        testPlayers[0].depositMoney(20);

        testPlayers[1] = new Player("Player 2");
        testPlayers[1].depositMoney(20);

        testBoard = new Square[2];
        testBoard[1] = new DeedSquare("Test Property",5,guiController);

        guiPlayers = guiController.addPlayerOnBoard(testPlayers);

        LandOnSquare playerTurnTest = new LandOnSquare(testBoard, testPlayers,guiController, guiPlayers,1);

        playerTurnTest.setLang(testMsg);

        //PLAYER 1 BUYS TEST PROPERTY
        playerTurnTest.landOnDeedSquare(1,0);
        assertEquals(15,testPlayers[0].getCurrentBalance());

        //PLAYER 2 PAYS RENT TO PLAYER 1 AFTER LANDING ON TEST PROPERTY OWNED BY PLAYER 1
        playerTurnTest.landOnDeedSquare(1,1);
        assertEquals(15,testPlayers[1].getCurrentBalance());
        assertEquals(20,testPlayers[0].getCurrentBalance());

    }

    @Test
    public void landOnJailSquareAndPay1M() {

        testPlayers = new Player[1];
        testPlayers[0] = new Player("Player 1");
        testPlayers[0].depositMoney(20);

        GuiController guiController = new GuiController();
        guiPlayers = guiController.addPlayerOnBoard(this.testPlayers);

        testBoard = new Square[19];
        testBoard[18] = new JailSquare("Go to Jail", guiController);

        LandOnSquare playerTurnTest = new LandOnSquare(testBoard, testPlayers, guiController, guiPlayers,1);
        playerTurnTest.setLang(testMsg);


        playerTurnTest.landOnJailSquare(18,0);
        System.out.println(testPlayers[0].getPosition());
        assertEquals(19, testPlayers[0].getCurrentBalance());

    }

    @Test
    public void updatePlayerPositionToIndex6AfterJail(){

        testPlayers = new Player[1];
        testPlayers[0] = new Player("Player 1");
        testPlayers[0].depositMoney(20);

        GuiController guiController = new GuiController();
        guiPlayers = guiController.addPlayerOnBoard(testPlayers);

        testBoard = new Square[24];
        testBoard[18] = new JailSquare("Go to Jail", guiController);

        LandOnSquare playerTurnTest = new LandOnSquare(testBoard, testPlayers, guiController, guiPlayers,1);
        playerTurnTest.setLang(testMsg);

        testPlayers[0].updatePosition(18);

        playerTurnTest.landOnJailSquare(18,0);
        System.out.println(testPlayers[0].getPosition());
        assertEquals(6,testPlayers[0].getPosition());

    }

    @Test
    public void landOnVisitJailAndDontPay() {
        testPlayers = new Player[1];
        testPlayers[0] = new Player("Player 1");
        testPlayers[0].depositMoney(20);

        GuiController guiController = new GuiController();
        guiPlayers = guiController.addPlayerOnBoard(testPlayers);

        testBoard = new Square[24];
        testBoard[6] = new JailSquare("Visit Jail", guiController);

        LandOnSquare playerTurnTest = new LandOnSquare(testBoard, testPlayers, guiController, guiPlayers,1);
        playerTurnTest.setLang(testMsg);

        testPlayers[0].updatePosition(6);

        playerTurnTest.landOnJailSquare(6,0);
        System.out.println(testPlayers[0].getPosition());
        assertEquals(20,testPlayers[0].getCurrentBalance());

    }

    @Test
    public void landOnStartSquare() {
    }
}