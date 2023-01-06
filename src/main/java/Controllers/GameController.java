package Controllers;
import GameComponents.Board.*;
import GameComponents.Board.Square;
import GameComponents.Cup;
import GameComponents.Cup_stub;
import GameComponents.Player;
import Translator.*;
import gui_main.GUI;

public class GameController {
    GuiController guiController = new GuiController();
    GUI gui ;
    //private int playerCount = 0;
    String userInput;
    int balance = 0;
    Player[] players;
    Square[] squares;
    Text msg = new Text("src/main/java/Translator/Dansktekst", guiController);

    int playerCount = 0;

    public void init() {
        guiController.setLang(msg);
        boolean testingInit = false;
        if (testingInit){
            msg = new Text("src/main/java/Translator/EnglishText", guiController);
            gui.showMessage(" Enter players count: ");
            //msg = new Text("src/main/java/Translator/EnglishText", guiController);
            //guiController.initFieldTitles(msg);
            int playerCount = 2;
            balance = 20 - (playerCount - 2) * 2;
            //balance = 1; //TEST BALANCE, COMMENT OUT FOR NORMAL RUNNING OF GAME
            players = new Player[playerCount];

            players[0] = new Player("Marc"); // INITIALISE EACH PLAYER WITH NAME
            players[0].setGui(guiController.createGuiPlayer(players[0]),guiController,msg);
            players[0].setStartBalance(balance); // DEPOSIT INITIAL BALANCE


            players[1] = new Player("Germaine"); // INITIALISE EACH PLAYER WITH NAME
            players[1].setGui(guiController.createGuiPlayer(players[1]),guiController,msg);
            players[1].setStartBalance(balance); // DEPOSIT INITIAL BALANCE

            if (playerCount > 2) {
                players[2] = new Player("Harald FitzGerald"); // INITIALISE EACH PLAYER WITH NAME
                players[2].setGui(guiController.createGuiPlayer(players[2]),guiController,msg);
                players[2].setStartBalance(balance); // DEPOSIT INITIAL BALANCE
                if (playerCount == 4) {
                    players[3] = new Player("Melanie"); // INITIALISE EACH PLAYER WITH NAME
                    players[3].setGui(guiController.createGuiPlayer(players[3]),guiController,msg);
                    players[3].setStartBalance(balance); // DEPOSIT INITIAL BALANCE
                }
            }

        } else {
            //String[] lang = {"DanskTekst"};
            //int langIndex = guiController.getUserInteger("You are in English mode. Enter 1 to keep English or enter 2 to switch to Danish."); //GETS USER TO CHOOSE LANGUAGE
           // String langFile = "src/main/java/Translator/" + lang[0];
            //msg = new Text(langFile, guiController);

            //guiController.initFieldTitles(msg);

            //String userInput;

            //INITIALIZING PLAYERS
            //System.out.println(msg.getText("enterPlayerCount"));

            boolean playerCountInvalid = true;
            while (playerCountInvalid) {
                // playerCount = userInput.nextInt();
                //System.out.println(playerCount);
                //gui.showMessage("enterPlayerCount");
                //playerCount = gui.getUserInteger("");
                playerCount = guiController.getUserInteger(msg.getText("enterPlayerCount"));
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
                boolean duplicateName = true;
                while(duplicateName) {
                    userInput = guiController.getUserString(playerNumber);
                    //userInput = guiController.getUserString(playerNumber);

                    if (i == 0) {
                        duplicateName = false;

                    } else {
                        for (int j = 0; j < i; j++) {
                            String name = players[j].getPlayerName();
                            if (name.equals(userInput)) {
                                duplicateName = true;
                                msg.printText("duplicateName", "na");
                                break;

                            } else {duplicateName = false;}
                        }
                    }
                }

                players[i] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
                players[i].setGui(guiController.createGuiPlayer(players[i]),guiController,msg);
                players[i].setStartBalance(balance); // DEPOSIT INITIAL BALANCE


            }
        }

        BoardInit board = new BoardInit(guiController, msg, players);
        squares = board.getSquareArr();
        board.initChanceSquare(squares);
        msg.printText("startGame", "na");

    }

    public void run() {
        boolean testing = true; // SET TO TRUE WHEN TESTING LANDING ON SPECIFIC SQUARE (SET SUM IN Cup_stub)
        Cup cup;
        if(testing){
           cup = new Cup_stub(guiController);
            //cup = new Cup_stub(gui);

        } else {
        cup = new Cup(guiController);
           // cup = new Cup(gui);
        }

        int newPosition;

        boolean gameOver = false;
        while(gameOver == false) {

            for (int i = 0; i < playerCount; i++) { //THROWS DICE AND UPDATES PLAYER'S POSITION

                msg.printText("rollDice", players[i].getPlayerName());
                int sum = cup.getSum();

                players[i].updatePosition(sum);
                newPosition = players[i].getPosition();

                squares[newPosition].landOn(players[i]);


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
