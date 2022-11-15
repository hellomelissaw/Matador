package Controllers;

import GameComponents.Board.*;
import GameComponents.Board.Square;
import GameComponents.Cup;
import GameComponents.Player;
import gui_fields.GUI_Player;
import gui_main.GUI;
import org.w3c.dom.Text;

import java.util.Scanner;

public class GameController {
    GuiController guiController = new GuiController();
    //Text gameInstruction = new Text("src/DanskTekst.csv");
    public int playerCount = 0;
    String userInput;
    int balance = 0;
    Player[] player;
    Square[] square;
    public void init() {
        BoardInit board = new BoardInit();
        square = board.getSquareArr();
        //String userInput = new Scanner(System.in);
        String userInput;


        //INITIALIZING PLAYERS
        System.out.println("Enter number of player (2-4):");
        guiController.showMessage("Enter number of player (2-4):");
        boolean playerCountInvalid = true;
        while (playerCountInvalid) {
           // playerCount = userInput.nextInt();
            System.out.println(playerCount);
            playerCount = guiController.getUserInteger();
            if (playerCount >= 2 && playerCount <= 4) {
                playerCountInvalid = false;

            } else {
                System.out.println("Invalid player number, please enter an integer between 2 and 4 inclusively.");
            }
        }
        balance = 20-(playerCount-2)*2; //SETS START BALANCE ACCORDING TO AMOUNT OF PLAYERS INPUT


        player = new Player[playerCount];

        for (int i = 0 ; i < playerCount ; i++) {
            //System.out.println("There are " + playerCount + "players.");
            int playerNumber = i + 1;
            System.out.println("Player " + playerNumber + " enter your name:");
            String guiMessage = "Player " + playerNumber + " enter your name:";
            guiController.showMessage(guiMessage);
            userInput = guiController.getUserString();
            player[i] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
            player[i].depositMoney(balance); // DEPOSIT INITIAL BALANCE
            //GUI_Player player = new GUI_Player(userInput,balance);
            guiController.addPlayer(userInput,balance);
        }

    }

    public void run() {
        Cup cup = new Cup();
        //guiController.setDice(guiController.die1, guiController.die2);
        int[] diceArr;
        int newPosition = 0;

        int testInt = 0;
        while(testInt < 25) {

            for (int i = 0; i < playerCount; i++) { //THROWS DICE AND UPDATES PLAYER'S POSITION
                testInt++;
                //THROWS THE DICE AND MOVES THE PLAYER
                diceArr = cup.getSum();
                int sum = diceArr[2];
                int playerIndex = i + 1;

                System.out.println(player[i].getPlayerName() + ", you have rolled a " + diceArr[0] + " and a " + diceArr[1] + ". You move " + sum + " squares.");
                newPosition = player[i].updatePosition(sum);

                System.out.println(player[i].getPlayerName() + " you are on square " + square[newPosition].toString());

                //HANDLES THE PROCESS OF LANDING ON A SQUARE AND CALLS METHOD FOR SUBSEQUENT ACTIONS
                LandOnSquare playerTurn = new LandOnSquare(square, player);

                if(square[newPosition] instanceof DeedSquare) {
                    playerTurn.landOnDeedSquare(newPosition,i);

                } else if (square[newPosition] instanceof ChanceSquare) {
                    playerTurn.landOnChanceSquare(newPosition,i);

                } else if (square[newPosition] instanceof JailSquare) {
                    playerTurn.landOnJailSquare(newPosition,i);

                } else if (square[newPosition] instanceof ParkingSquare) {
                    playerTurn.landOnParkingSquare(newPosition,i);

                } else {
                    playerTurn.landOnStartSquare(newPosition,i);
                }

            }
        }
    }
    public int getPlayerCount() {
        return playerCount;
    }
}
