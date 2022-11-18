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
    Player[] Players;
    Square[] square;

    private boolean bankrupt=false;
    GUI_Player[] guiPlayers;

    public void initTest() {
        BoardInit board = new BoardInit(guiController);
        square = board.getSquareArr();
        playerCount = 2;
        balance = 20 - (playerCount - 2) * 2;
        Players = new Player[playerCount];
        guiPlayers = new GUI_Player[playerCount];


        Players[0] = new Player("Marc"); // INITIALISE EACH PLAYER WITH NAME
        Players[0].depositMoney(balance); // DEPOSIT INITIAL BALANCE

        Players[1] = new Player("Germaine"); // INITIALISE EACH PLAYER WITH NAME
        Players[1].depositMoney(balance); // DEPOSIT INITIAL BALANCE

        if (playerCount > 2) {
            Players[2] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
            Players[2].depositMoney(balance); // DEPOSIT INITIAL BALANCE
            if (playerCount == 4) {
                Players[3] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
                Players[3].depositMoney(balance); // DEPOSIT INITIAL BALANCE
            }
        }
        guiPlayers = guiController.addPlayerOnBoard(Players);
    }

    public void init() {
        BoardInit board = new BoardInit(guiController);
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


        Players = new Player[playerCount];
        guiPlayers = new GUI_Player[playerCount];

        for (int i = 0 ; i < playerCount ; i++) {
            guiController.showMessage("There are " + playerCount + " players.");
            int playerNumber = i + 1;
            System.out.println("Player " + playerNumber + " enter your name:");
            String guiMessage = "Player " + playerNumber + " enter your name:";
            guiController.showMessage(guiMessage);
            userInput = guiController.getUserString();
            Players[i] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
            Players[i].depositMoney(balance); // DEPOSIT INITIAL BALANCE

        }

      guiPlayers = guiController.addPlayerOnBoard(Players);
        guiController.showMessage("Press Ok to start the game!");

    }

    public void run() {
        Cup cup = new Cup(guiController);
        int[] diceArr;
        int newPosition = 0;


        while(bankrupt==false) {

            for (int i = 0; i < playerCount; i++) { //THROWS DICE AND UPDATES PLAYER'S POSITION

                //THROWS THE DICE AND MOVES THE PLAYER
                String rollMessage = Players[i].getPlayerName() + ", press OK to roll the dice.";
                guiController.showMessage(rollMessage);
                diceArr = cup.getSum();
                int sum = diceArr[2];
                int playerIndex = i + 1;
                int oldPosition = Players[i].getPosition();

                System.out.println(Players[i].getPlayerName() + ", you have rolled a " + diceArr[0] + " and a " + diceArr[1] + ". You move " + sum + " squares.");

                //TO TEST LANDING ON SPECIFIC SQUARE, COMMENT OUT WHEN NOT IN USE
                /*int testDie = 1;
                newPosition = players[i].updatePosition(testDie);*/

                // UNCOMMENT THE FOLLOWING LINE WHEN NOT USING TEST DIE
                newPosition = Players[i].updatePosition(sum);
                guiController.showMessage(Players[i].getPlayerName() + ", you have rolled a " + diceArr[0] + " and a " + diceArr[1] + ". You move " + sum + " squares.");
                guiController.move(guiPlayers[i], oldPosition, newPosition);


                // hvis newPosition er mindre end oldPosition, betyder det at man har passeret start
                if (newPosition<oldPosition && oldPosition != 18) {
                    Players[i].depositMoney(2);
                    int currentBalance = Players[i].getCurrentBalance();
                    guiController.updateBalance(guiPlayers[i], currentBalance);
                    System.out.println(Players[i].getPlayerName()+ " passed the start square, and will now recieve M2. New balance: " + currentBalance);

                System.out.println(Players[i].getPlayerName() + " you are on square " + square[newPosition].toString());
                }
                System.out.println(Players[i].getPlayerName() + " you are on square " + square[newPosition].getSquareName() + "(square #" + Players[i].getPosition() + ")");


                //HANDLES THE PROCESS OF LANDING ON A SQUARE AND CALLS METHOD FOR SUBSEQUENT ACTIONS
                LandOnSquare playerTurn = new LandOnSquare(square, Players, guiController, guiPlayers);

                if(square[newPosition] instanceof DeedSquare) {
                    playerTurn.landOnDeedSquare(newPosition,i);

                } else if (square[newPosition] instanceof ChanceSquare) {
                    playerTurn.landOnChanceSquare(newPosition,i);

                } else if (square[newPosition] instanceof JailSquare) {
                    playerTurn.landOnJailSquare(newPosition,i);

                } else if (square[newPosition] instanceof ParkingSquare) {
                    playerTurn.landOnParkingSquare(newPosition);

                } else {
                    playerTurn.landOnStartSquare(newPosition,i);
                }

                System.out.println("");
                System.out.println("");

            }
        }
    }
}
