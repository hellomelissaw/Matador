package Controllers;
import GameComponents.Board.*;
import GameComponents.Board.Square;
import GameComponents.Cup;
import GameComponents.Cup_stub;
import GameComponents.Player;
import Translator.*;

import java.awt.*;

public class GameController {
    GuiController guiController = new GuiController();
    //private int playerCount = 0;
    String userInput;
    int balance = 0;
    Player[] players;
    Square[] squares;
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);
    String carColor;

    int playerCount = 0;

    String[] color = {"Cyan" , "Lyserød" , "Gul" , "Grøn" ,"Rød" ,"Blå","Hvid"};

    public void init() {
        guiController.setLang(msg);
        boolean testingInit = false;
        if (testingInit){
            msg = new Text("src/main/java/Translator/DanskTekst", guiController);
            msg.printText("startGame", "na");
            //msg = new Text("src/main/java/Translator/EnglishText", guiController);
            //guiController.initFieldTitles(msg);
            playerCount = 3;
            balance = 30000;

            players = new Player[playerCount];

            players[0] = new Player("Marc"); // INITIALISE EACH PLAYER WITH NAME
            players[0].setGui(guiController.createGuiPlayer(players[0]),guiController,msg);
            players[0].setCarColor(Color.red);
            players[0].setStartBalance(balance); // DEPOSIT INITIAL BALANCE



            players[1] = new Player("Germaine"); // INITIALISE EACH PLAYER WITH NAME
            players[1].setGui(guiController.createGuiPlayer(players[1]),guiController,msg);
            players[1].setStartBalance(balance); // DEPOSIT INITIAL BALANCE
            players[1].setCarColor(Color.yellow);

            players[2] = new Player("Harry"); // INITIALISE EACH PLAYER WITH NAME
            players[2].setGui(guiController.createGuiPlayer(players[2]),guiController,msg);
            players[2].setStartBalance(balance); // DEPOSIT INITIAL BALANCE
            players[2].setCarColor(Color.pink);

            if (playerCount > 3) {
                players[3] = new Player("Sara"); // INITIALISE EACH PLAYER WITH NAME
                players[3].setGui(guiController.createGuiPlayer(players[3]),guiController,msg);
                players[3].setStartBalance(balance); // DEPOSIT INITIAL BALANCE

                players[4] = new Player("Megan"); // INITIALISE EACH PLAYER WITH NAME
                players[4].setGui(guiController.createGuiPlayer(players[4]),guiController,msg);
                players[4].setStartBalance(balance); // DEPOSIT INITIAL BALANCE


                if (playerCount == 6) {
                    players[5] = new Player("Adam"); // INITIALISE EACH PLAYER WITH NAME
                    players[5].setGui(guiController.createGuiPlayer(players[5]),guiController,msg);
                    players[5].setStartBalance(balance); // DEPOSIT INITIAL BALANCE
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
            msg.printText("welcomeMessage", "na");
            boolean playerCountInvalid = true;
            while (playerCountInvalid) {
                // playerCount = userInput.nextInt();
                //System.out.println(playerCount);
                playerCount = guiController.getUserInteger(msg.getText("enterPlayerCount"));
                if (playerCount >= 3 && playerCount <= 6) {
                    playerCountInvalid = false;

                } else {
                    msg.printText("invalidCount", "na");

                }
            }
            balance = 30000;//SETS START BALANCE ACCORDING TO AMOUNT OF PLAYERS INPUT

            players = new Player[playerCount];

            for (int i = 0; i < playerCount; i++) {
                int playerNumber = i + 1;
                boolean duplicateName = true;

                while(duplicateName) {
                    userInput = guiController.getUserString(playerNumber);
                    carColor = guiController.getUserSelection("Hvilke farve skal være din bil?", color);
                    for (int j = 0; j < color.length; j++) {// check color duplicated, and remove the color from the color array, and the next player is not allowed to choos the same color
                        if (color[j] != null && color[j].equals(carColor)) {
                            color[j] = null;
                            String[] tempColor = new String[color.length-1];
                            boolean foundColor = false;
                            for (int k = 0; k < color.length-1; k++) {
                                if (color[k] == null || foundColor) {
                                    foundColor = true;
                                    tempColor[k] = color[k+1];
                                }
                                else tempColor[k] = color[k];
                            }
                            color = tempColor;
                        }
                    }

                    if (i == 0) {
                        duplicateName = false;
                        System.out.println("First Player");

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
                setCarColor(carColor, players[i]);

                }

            }

        BoardInit board = new BoardInit(guiController, msg, players);
        squares = board.getSquareArr();
        board.initChanceSquare(squares);
        msg.printText("startGame", "na");

    }


    public void run() {
        boolean testing = false; // SET TO TRUE WHEN TESTING LANDING ON SPECIFIC SQUARE (SET SUM IN Cup_stub)
        Cup cup;
        if(testing){
           cup = new Cup_stub(guiController);
        } else {
        cup = new Cup(guiController);}

        int newPosition;

        boolean gameOver = false;
        while(gameOver == false) {

            for (int i = 0; i < playerCount; i++) { //THROWS DICE AND UPDATES PLAYER'S POSITION

                msg.printText("rollDice", players[i].getPlayerName());
                int sum = cup.getSum();

                players[i].updatePosition(sum);
                newPosition = players[i].getPosition();

                squares[newPosition].landOn(players[i]);


                   if(players[i].isBankrupt()) {
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


    public void setCarColor(String color, Player player) {
        switch (carColor) {

            case "Lyserød":
                player.setCarColor(Color.pink);
                break;
            case "Gul":
                player.setCarColor(Color.yellow);
                break;
            case "Grøn":
                player.setCarColor(Color.green);
                break;
            case "Blå":
                player.setCarColor(Color.blue);
                break;
            case "Hvid":
                player.setCarColor(Color.white);
                break;
            case "Cyan":
                player.setCarColor(Color.cyan);
                break;

    }
}}
