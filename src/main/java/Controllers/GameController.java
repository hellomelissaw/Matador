package Controllers;
import GameComponents.Board.*;
import GameComponents.Board.Square;
import GameComponents.Cup;
import GameComponents.Player;
import gui_fields.GUI_Player;
import Translator.*;

public class GameController {
    GuiController guiController = new GuiController();
    public int playerCount = 0;
    String userInput;
    int balance = 0;
    Player[] players;
    Square[] square;
    GUI_Player[] guiPlayers;
    Text msg;

    public void initTest() {
        msg = new Text("src/main/java/Translator/EnglishText");
        guiController.initFieldTitles(msg);
        BoardInit board = new BoardInit(guiController,msg);
        square = board.getSquareArr();
        playerCount = 3;
        balance = 30000;
        players = new Player[playerCount];
        guiPlayers = new GUI_Player[playerCount];


        players[0] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
        players[0].depositMoney(balance); // DEPOSIT INITIAL BALANCE

        players[1] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
        players[1].depositMoney(balance); // DEPOSIT INITIAL BALANCE

        players[2] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
        players[2].depositMoney(balance); // DEPOSIT INITIAL BALANCE

        if (playerCount > 3) {
            players[3] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
            players[3].depositMoney(balance); // DEPOSIT INITIAL BALANCE

            players[4] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
            players[4].depositMoney(balance); // DEPOSIT INITIAL BALANCE


            if (playerCount == 6) {
                players[5] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
                players[5].depositMoney(balance); // DEPOSIT INITIAL BALANCE
            }
        }
        guiPlayers = guiController.addPlayerOnBoard(players);
    }

    public void init() {
        String[] lang = {"EnglishText","DanskTekst"};
        int langIndex = guiController.getUserLang(); //GETS USER TO CHOOSE LANGUAGE
        String langFile = "src/main/java/Translator/"+lang[langIndex-1];
        msg = new Text(langFile);
        guiController.initFieldTitles(msg);

        BoardInit board = new BoardInit(guiController,msg);
        square = board.getSquareArr();
        //String userInput = new Scanner(System.in);
        String userInput;

        //INITIALIZING PLAYERS
        System.out.println(msg.getText("enterPlayerCount"));
        boolean playerCountInvalid = true;
        while (playerCountInvalid) {
           // playerCount = userInput.nextInt();
            //System.out.println(playerCount);
            playerCount = guiController.getUserIntegerPlayerCount();
            if (playerCount >= 3 && playerCount <= 6) {
                playerCountInvalid = false;

            } else {
                System.out.println(msg.getText("invalidCount"));
                guiController.showMessage(msg.getText("invalidCount"));
            }
        }
        balance = 30000; //SETS START BALANCE ACCORDING TO AMOUNT OF PLAYERS INPUT


        players = new Player[playerCount];
        guiPlayers = new GUI_Player[playerCount];

        for (int i = 0 ; i < playerCount ; i++) {
            int playerNumber = i + 1;
            System.out.println(msg.getText("enterName") + " " + playerNumber);
            userInput = guiController.getUserString(playerNumber);
            players[i] = new Player(userInput); // INSTANTIATE EACH PLAYER WITH NAME
            players[i].depositMoney(balance); // DEPOSIT INITIAL BALANCE

        }

      guiPlayers = guiController.addPlayerOnBoard(players);
        guiController.showMessage(msg.getText("startGame"));

    }

    public void run() {
        Cup cup = new Cup(guiController);
        int[] diceArr;
        int newPosition = 0;

        boolean gameOver = false;
        while(gameOver == false) {

            for (int i = 0; i < playerCount; i++) { //THROWS DICE AND UPDATES PLAYER'S POSITION
                String rollMessage = players[i].getPlayerName() + msg.getText("rollDice");
                guiController.showMessage(rollMessage);
                diceArr = cup.getSum();
                int sum = diceArr[2];
                int playerIndex = i + 1;
                int oldPosition = players[i].getPosition();

                System.out.println(players[i].getPlayerName() + " rolled a " + diceArr[0] + " and a " + diceArr[1] + ". Got moved " + sum + " squares.");

                //TO TEST LANDING ON SPECIFIC SQUARE, COMMENT OUT WHEN NOT IN USE
                /*int testDie = 1;
                newPosition = players[i].updatePosition(testDie);*/

                // UNCOMMENT THE FOLLOWING LINE WHEN NOT USING TEST DIE
                newPosition = players[i].updatePosition(sum);
                //guiController.showMessage(players[i].getPlayerName() + ", you have rolled a " + diceArr[0] + " and a " + diceArr[1] + ". You move " + sum + " squares.");
                guiController.move(guiPlayers[i], oldPosition, newPosition);


                // hvis newPosition er mindre end oldPosition, betyder det at man har passeret start
                if (newPosition<oldPosition && oldPosition != 18) {
                    players[i].depositMoney(4000);
                    int currentBalance = players[i].getCurrentBalance();
                    guiController.updateBalance(guiPlayers[i], currentBalance);
                    String passStart = players[i].getPlayerName()+ msg.getText("passStart") + currentBalance;
                    System.out.println(passStart);
                    guiController.showMessage(passStart);

                System.out.println(players[i].getPlayerName() + msg.getText("position") + square[newPosition].toString());
                }
                System.out.println(players[i].getPlayerName() + msg.getText("position") + square[newPosition].getSquareName() +  msg.getText("squareNum") + players[i].getPosition());


                //HANDLES THE PROCESS OF LANDING ON A SQUARE AND CALLS METHOD FOR SUBSEQUENT ACTIONS
                LandOnSquare playerTurn = new LandOnSquare(square, players, guiController, guiPlayers, playerCount);
                playerTurn.setLang(msg);
                if(square[newPosition] instanceof DeedSquare) {
                    playerTurn.landOnDeedSquare(newPosition,i);

                } else if (square[newPosition] instanceof ChanceSquare) {
                    playerTurn.landOnChanceSquare(i, newPosition, square, players, guiController, guiPlayers, playerCount);

                } else if (square[newPosition] instanceof JailSquare) {
                    playerTurn.landOnJailSquare(newPosition,i);

                } else if (square[newPosition] instanceof ParkingSquare) {
                    playerTurn.landOnParkingSquare(newPosition);

                } else {
                    playerTurn.landOnStartSquare(newPosition,i);
                }

                    if(players[i].isBankrupt() == true) {
                        gameOver = true;
                        String winnerName = players[i].winner(players);
                        String gameOverMsg = msg.getText("gameOver") + " " + winnerName;
                        System.out.println(gameOverMsg);
                        guiController.showMessage(gameOverMsg);

                        break;
                    }

                System.out.println("");
                System.out.println("");

                }

            }



    }
}
