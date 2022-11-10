package Controllers;
import GameComponents.Board.*;
import GameComponents.Cup;
import GameComponents.Player;

import java.util.Scanner;

public class GameController {
    int playerCount = 0;
    int balance = 0;
    Player[] player;
    Square[] square;
    public void init() {
        BoardInit board = new BoardInit();
        square = board.getSquareArr();
        Scanner userInput = new Scanner(System.in);

        //INITIALIZING PLAYERS
        System.out.println("Enter number of player (2-4):");
        boolean playerCountInvalid = true;
        while (playerCountInvalid) {
            playerCount = userInput.nextInt();
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
            Scanner input = new Scanner(System.in);
            String playerName = input.nextLine();
            player[i] = new Player(playerName); // INITIALISE EACH PLAYER WITH NAME
            player[i].depositMoney(balance); // DEPOSIT INITIAL BALANCE
        }

    }

    public void run() {
        Cup cup = new Cup();
        int[] diceArr;
        int newPosition = 0;

        while(newPosition < 20) { //ARBITRARY NEWPOSITION VALUE TO TEST FOR-LOOP

            for (int i = 0; i < playerCount; i++) { //THROWS DICE AND UPDATES PLAYER'S POSITION
                diceArr = cup.getSum();
                int sum = diceArr[2];
                int playerIndex = i + 1;

                System.out.println("Player " + playerIndex + ", you have rolled a " + diceArr[0] + " and a " + diceArr[1] + ". You move " + sum + " squares.");
                newPosition = player[i].updatePosition(sum);

                System.out.println("Player " + playerIndex + " you are on square " + square[newPosition].toString());

                if(square[newPosition] instanceof DeedSquare) {
                    boolean deed = ((DeedSquare) square[newPosition]).hasDeed();
                    if(deed) {
                        System.out.println("This property is available for purchase.");
                    }

                    System.out.println();

                }
            }
        }
    }
}
