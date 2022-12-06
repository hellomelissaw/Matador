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
    GUI_Player[] guiPlayers;
    Text msg;

    public void initTest() {

    }

    public void init() {
        boolean testingInit = true;
        if (testingInit){
            msg = new Text("src/main/java/Translator/EnglishText");
            guiController.initFieldTitles(msg);
            playerCount = 2;
            balance = 20 - (playerCount - 2) * 2;
            //balance = 1; //TEST BALANCE, COMMENT OUT FOR NORMAL RUNNING OF GAME
            players = new Player[playerCount];
            guiPlayers = new GUI_Player[playerCount];

            players[0] = new Player("Marc"); // INITIALISE EACH PLAYER WITH NAME
            players[0].depositMoney(balance); // DEPOSIT INITIAL BALANCE

            players[1] = new Player("Germaine"); // INITIALISE EACH PLAYER WITH NAME
            players[1].depositMoney(balance); // DEPOSIT INITIAL BALANCE

            if (playerCount > 2) {
                players[2] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
                players[2].depositMoney(balance); // DEPOSIT INITIAL BALANCE
                if (playerCount == 4) {
                    players[3] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
                    players[3].depositMoney(balance); // DEPOSIT INITIAL BALANCE
                }
            }
            BoardInit board = new BoardInit(guiController, msg, players);
            square = board.getSquareArr();
            guiController.addPlayerOnBoard(players);
            guiPlayers = guiController.getGuiPlayersArr();

        } else {
            String[] lang = {"EnglishText", "DanskTekst"};
            int langIndex = guiController.getUserLang(); //GETS USER TO CHOOSE LANGUAGE
            String langFile = "src/main/java/Translator/" + lang[langIndex - 1];
            msg = new Text(langFile);
            guiController.initFieldTitles(msg);

            String userInput;

            //INITIALIZING PLAYERS
            System.out.println(msg.getText("enterPlayerCount"));
            boolean playerCountInvalid = true;
            while (playerCountInvalid) {
                // playerCount = userInput.nextInt();
                //System.out.println(playerCount);
                playerCount = guiController.getUserIntegerPlayerCount();
                if (playerCount >= 2 && playerCount <= 4) {
                    playerCountInvalid = false;

                } else {
                    System.out.println(msg.getText("invalidCount"));
                    guiController.showMessage(msg.getText("invalidCount"));
                }
            }
            balance = 20 - (playerCount - 2) * 2; //SETS START BALANCE ACCORDING TO AMOUNT OF PLAYERS INPUT


            players = new Player[playerCount];
            guiPlayers = new GUI_Player[playerCount];

            for (int i = 0; i < playerCount; i++) {
                int playerNumber = i + 1;
                System.out.println(msg.getText("enterName") + " " + playerNumber);
                userInput = guiController.getUserString(playerNumber);
                players[i] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
                players[i].depositMoney(balance); // DEPOSIT INITIAL BALANCE

            }

            BoardInit board = new BoardInit(guiController, msg, players);
            square = board.getSquareArr();
            //String userInput = new Scanner(System.in);
        /*for(int i = 0 ; i < square.length ; i++) { //SETS LANGUAGE FOR ALL SQUARES
            square[i].setLang(msg);
        }*/

            guiController.addPlayerOnBoard(players);
            guiPlayers = guiController.getGuiPlayersArr();
            guiController.showMessage(msg.getText("startGame"));
        }

    }

    public void run() {
        boolean testing = false; // SET TO TRUE WHEN TESTING LANDING ON SPECIFIC SQUARE (SET SUM IN Cup_stub)
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
                String rollMessage = players[i].getPlayerName() + msg.getText("rollDice");
                guiController.showMessage(rollMessage);
                diceArr = cup.getSum();
                int sum = diceArr[2];
                int oldPosition = players[i].getPosition();

                System.out.println(players[i].getPlayerName() + " rolled a " + diceArr[0] + " and a " + diceArr[1] + ". Got moved " + sum + " squares.");

                players[i].updatePosition(sum);
                newPosition = players[i].getPosition();

                guiController.move(guiPlayers[i], oldPosition, newPosition);


                // hvis newPosition er mindre end oldPosition, betyder det at man har passeret start
                if (newPosition<oldPosition && oldPosition != 18) {
                    players[i].depositMoney(2);
                    int currentBalance = players[i].getCurrentBalance();
                    guiController.updateBalance(guiPlayers[i], currentBalance);
                    String passStart = players[i].getPlayerName()+ msg.getText("passStart") + currentBalance;
                    System.out.println(passStart);
                    guiController.showMessage(passStart);

                }
                System.out.println(players[i].getPlayerName() + msg.getText("position") + square[newPosition].getSquareName() +  msg.getText("squareNum") + players[i].getPosition());

                square[newPosition].landOn(players[i], guiPlayers[i]);

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
