package Controllers;
import GameComponents.Board.*;
import GameComponents.Board.Square;
import GameComponents.Cup;
import GameComponents.Cup_stub;
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
    Text msg;

    public void init() {
        boolean testingInit = true;
        if (testingInit){
            msg = new Text("src/main/java/Translator/EnglishText", guiController);
            guiController.initFieldTitles(msg);
            playerCount = 2;
            balance = 20 - (playerCount - 2) * 2;
            //balance = 1; //TEST BALANCE, COMMENT OUT FOR NORMAL RUNNING OF GAME
            players = new Player[playerCount];

            players[0] = new Player("Marc"); // INITIALISE EACH PLAYER WITH NAME
            players[0].setStartBalance(balance); // DEPOSIT INITIAL BALANCE

            players[1] = new Player("Germaine"); // INITIALISE EACH PLAYER WITH NAME
            players[1].setStartBalance(balance); // DEPOSIT INITIAL BALANCE

            if (playerCount > 2) {
                players[2] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
                players[2].setStartBalance(balance); // DEPOSIT INITIAL BALANCE
                if (playerCount == 4) {
                    players[3] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
                    players[3].setStartBalance(balance); // DEPOSIT INITIAL BALANCE
                }
            }

        } else {
            String[] lang = {"EnglishText", "DanskTekst"};
            int langIndex = guiController.getUserInteger("You are in English mode. Enter 1 to keep English or enter 2 to switch to Danish."); //GETS USER TO CHOOSE LANGUAGE
            String langFile = "src/main/java/Translator/" + lang[langIndex - 1];
            msg = new Text(langFile, guiController);
            guiController.initFieldTitles(msg);

            String userInput;

            //INITIALIZING PLAYERS
            //System.out.println(msg.getText("enterPlayerCount"));
            boolean playerCountInvalid = true;
            while (playerCountInvalid) {
                // playerCount = userInput.nextInt();
                //System.out.println(playerCount);
                playerCount = guiController.getUserInteger(msg.getText("playerCount"));
                if (playerCount >= 2 && playerCount <= 4) {
                    playerCountInvalid = false;

                } else {
                    msg.printText("invalidCount", "na");

                }
            }
            balance = 20 - (playerCount - 2) * 2; //SETS START BALANCE ACCORDING TO AMOUNT OF PLAYERS INPUT

            players = new Player[playerCount];

            for (int i = 0; i < playerCount; i++) {
                int playerNumber = i + 1;
                msg.printText("enterName", String.valueOf(playerNumber));
                System.out.println(msg.getText("enterName") + " " + playerNumber);
                userInput = guiController.getUserString(playerNumber);
                players[i] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
                players[i].setStartBalance(balance); // DEPOSIT INITIAL BALANCE

            }
        }

        BoardInit board = new BoardInit(guiController, msg, players);
        square = board.getSquareArr();

        guiController.addPlayerOnBoard(players);
        GUI_Player[] guiPlayers = guiController.getGuiPlayersArr();
        for(int i = 0 ; i < players.length ; i++) {
            players[i].setGui(guiPlayers[i],guiController);
            System.out.println("Gui Players are set");
        }

        msg.printText("startGame", "na");

    }

    public void run() {
        boolean testing = true; // SET TO TRUE WHEN TESTING LANDING ON SPECIFIC SQUARE (SET SUM IN Cup_stub)
        Cup cup;
        if(testing){
           cup = new Cup_stub(guiController);

        } else {
        cup = new Cup(guiController);}

        int[] diceArr;
        int newPosition;

        boolean gameOver = false;
        while(gameOver == false) {

            for (int i = 0; i < playerCount; i++) { //THROWS DICE AND UPDATES PLAYER'S POSITION
                msg.printText("rollDice", players[i].getPlayerName());

                diceArr = cup.getSum();
                int sum = diceArr[2];
                int oldPosition = players[i].getPosition();

                System.out.println(players[i].getPlayerName() + " rolled a " + diceArr[0] + " and a " + diceArr[1] + ". Got moved " + sum + " squares.");

                players[i].updatePosition(sum);
                newPosition = players[i].getPosition();


                // hvis newPosition er mindre end oldPosition, betyder det at man har passeret start
                if (newPosition<oldPosition && oldPosition != 18) {
                    players[i].depositMoney(2);
                    System.out.println("New balance after passing start is: " + players[i].getCurrentBalance());
                    msg.printText("passStart", players[i].getPlayerName());

                }
                System.out.println(players[i].getPlayerName() + "has landed on" + square[newPosition].getSquareName() +  ", this is square #" + players[i].getPosition());

                square[newPosition].landOn(players[i]);

                    if(players[i].isBankrupt() == true) {
                        gameOver = true;
                        String winnerName = players[i].winner(players) + " ";
                        msg.printText("gameOver", winnerName);
                        break;
                    }

                System.out.println("");
                System.out.println("");

                }

            }

    }
}
