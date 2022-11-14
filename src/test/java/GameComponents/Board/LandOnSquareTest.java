package GameComponents.Board;

import GameComponents.Player;

import org.junit.Test;

import static org.junit.Assert.*;

public class LandOnSquareTest {


    @Test
    public void player1BuysAvailableDeedSquareAndPlayer2PaysRent() {
        Square[] testBoard = new Square[1];
        Player[] testPlayer = new Player[2];
        LandOnSquare playerTurnTest = new LandOnSquare(testBoard, testPlayer);

        testPlayer[0] = new Player("Player 1");
        testPlayer[0].depositMoney(20);

        testPlayer[1] = new Player("Player 2");
        testPlayer[1].depositMoney(20);


        testBoard[0] = new DeedSquare("Test Property",5);

        //PLAYER 1 BUYS TEST PROPERTY
        playerTurnTest.landOnDeedSquare(0,0);
        assertEquals(15,testPlayer[0].getCurrentBalance());

        //PLAYER 2 PAYS RENT TO PLAYER 1 AFTER LANDING ON TEST PROPERTY OWNED BY PLAYER 1
        playerTurnTest.landOnDeedSquare(0,1);
        assertEquals(15,testPlayer[1].getCurrentBalance());
        assertEquals(20,testPlayer[0].getCurrentBalance());

    }

    @Test
    public void landOnJailSquareAndPay1M() {
        Square[] testBoard = new Square[19];
        Player[] testPlayer = new Player[1];
        LandOnSquare playerTurnTest = new LandOnSquare(testBoard, testPlayer);

        testPlayer[0] = new Player("Player 1");
        testPlayer[0].depositMoney(20);

        testBoard[18] = new JailSquare("Go to Jail");

        playerTurnTest.landOnJailSquare(18,0);
        assertEquals(19, testPlayer[0].getCurrentBalance());

    }
}