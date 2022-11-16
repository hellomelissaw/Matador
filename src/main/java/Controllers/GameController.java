package Controllers;
import GameComponents.Board.*;
import GameComponents.Board.Square;
import GameComponents.Cup;
import GameComponents.Player;
import gui_fields.GUI_Player;

public class GameController {
    GuiController guiController = new GuiController();
    //Text gameInstruction = new Text("src/DanskTekst.csv");
    public int playerCount = 0;
    String userInput;
    int balance = 0;
    Player[] players;
    Square[] square;

    GUI_Player[] guiPlayers;
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
                guiController.showMessage("Invalid player number, please enter an integer between 2 and 4 inclusively.");
            }
        }
        balance = 20-(playerCount-2)*2; //SETS START BALANCE ACCORDING TO AMOUNT OF PLAYERS INPUT


        players = new Player[playerCount];
        guiPlayers = new GUI_Player[playerCount];

        for (int i = 0 ; i < playerCount ; i++) {
            //System.out.println("There are " + playerCount + "players.");
            guiController.showMessage("There are " + playerCount + " players.");
            int playerNumber = i + 1;
            System.out.println("Player " + playerNumber + " enter your name:");
            String guiMessage = "Player " + playerNumber + " enter your name:";
            guiController.showMessage(guiMessage);
            userInput = guiController.getUserString();
            players[i] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
            players[i].depositMoney(balance); // DEPOSIT INITIAL BALANCE

        }

      guiPlayers = guiController.addPlayerOnBoard(players);
        guiController.showMessage("Press Ok to start the game!");

    }

    public void run() {
        Cup cup = new Cup(guiController);
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
                int oldPosition = players[i].getPosition();

                System.out.println(players[i].getPlayerName() + ", you have rolled a " + diceArr[0] + " and a " + diceArr[1] + ". You move " + sum + " squares.");

                //TO TEST LANDING ON SPECIFIC SQUARE, COMMENT OUT WHEN NOT IN USE
                int testDie = 18;
                newPosition = players[i].updatePosition(testDie);

                // UNCOMMENT THE FOLLOWING LINE WHEN NOT USING TEST DIE
                // newPosition = players[i].updatePosition(sum);
                guiController.showMessage(players[i].getPlayerName() + ", you have rolled a " + diceArr[0] + " and a " + diceArr[1] + ". You move " + sum + " squares.");
                newPosition = players[i].updatePosition(sum);
                guiController.move(guiPlayers[i], oldPosition, newPosition);


                // hvis newPosition er mindre end oldPosition, betyder det at man har passeret start
                if (newPosition<oldPosition && oldPosition != 18) {
                    players[i].depositMoney(2);
                    System.out.println(players[i].getPlayerName()+ " passed the start square, and will now recieve M2. New balance: " + players[i].getCurrentBalance());

                System.out.println(players[i].getPlayerName() + " you are on square " + square[newPosition].toString());
                }
                System.out.println(players[i].getPlayerName() + " you are on square " + square[newPosition].getSquareName() + "(square #" + players[i].getPosition() + ")");


                //HANDLES THE PROCESS OF LANDING ON A SQUARE AND CALLS METHOD FOR SUBSEQUENT ACTIONS
                LandOnSquare playerTurn = new LandOnSquare(square, players, guiController, guiPlayers);

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

                System.out.println("");
                System.out.println("");

            }
        }
    }
    public int getPlayerCount() {
        return playerCount;
    }
}
