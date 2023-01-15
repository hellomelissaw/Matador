package Controllers;
import GameComponents.Board.*;
import GameComponents.Board.Square;
import GameComponents.Cup;
import GameComponents.Cup_stub;
import GameComponents.Player;
import Translator.*;
import GameComponents.Bank;
import gui_fields.GUI_Player;

import java.awt.*;


public class GameController {
    boolean useCupStub = false;
    boolean testingInit = false;
    boolean testingBuildButton = false;
    boolean testStartBalance = false;
    GuiController guiController = new GuiController();
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);

    BuildController buildController = new BuildController(guiController, msg);
    SellController sellController = new SellController(guiController,msg);
    String userInput;
    int balance = 0;
    Player[] players;
    Square[] squares;


    int playerCount = 0;

    int jailCounter = 0;

    int counter = 0;

    Bank bank = new Bank();


    public void init() {
        guiController.setLang(msg);
        if (testingInit){
            msg = new Text("src/main/java/Translator/DanskTekst", guiController);
            //msg = new Text("src/main/java/Translator/EnglishText", guiController);
            //guiController.initFieldTitles(msg);
            playerCount = 6;
            if(testStartBalance){
                balance = 4000;

            } else { balance = 30000; }


            players = new Player[playerCount];

            players[0] = new Player("Marc"); // INITIALISE EACH PLAYER WITH NAME
            players[0].setGui(guiController.createGuiPlayer(players[0]), guiController, msg);
            players[0].setBank(bank); //INITIALISE BANK WITHIN PLAYER
            players[0].setStartBalance(balance,false); // DEPOSIT INITIAL BALANCE
            players[0].setCarColor(Color.red);


            players[1] = new Player("Germaine"); // INITIALISE EACH PLAYER WITH NAME
            players[1].setGui(guiController.createGuiPlayer(players[1]), guiController, msg);
            players[1].setBank(bank); //INITIALISE BANK WITHIN PLAYER
            players[1].setCarColor(Color.white);
            players[1].setStartBalance(balance, true); // DEPOSIT INITIAL BALANCE

            players[2] = new Player("Harry"); // INITIALISE EACH PLAYER WITH NAME
            players[2].setGui(guiController.createGuiPlayer(players[2]), guiController, msg);
            players[2].setBank(bank); //INITIALISE BANK WITHIN PLAYER
            players[2].setCarColor(Color.orange);
            players[2].setStartBalance(balance, true); // DEPOSIT INITIAL BALANCE

            if (playerCount > 3) {
                players[3] = new Player("Sara"); // INITIALISE EACH PLAYER WITH NAME
                players[3].setGui(guiController.createGuiPlayer(players[3]), guiController, msg);
                players[3].setCarColor(Color.pink);
                players[3].setBank(bank); //INITIALISE BANK WITHIN PLAYER
                players[3].setStartBalance(balance, true); // DEPOSIT INITIAL BALANCE

                if (playerCount > 4){
                players[4] = new Player("Megan"); // INITIALISE EACH PLAYER WITH NAME
                players[4].setGui(guiController.createGuiPlayer(players[4]),guiController,msg);
                players[4].setBank(bank); //INITIALISE BANK WITHIN PLAYER
                players[4].setStartBalance(balance, true); // DEPOSIT INITIAL BALANCE
                players[4].setCarColor(Color.yellow);
            }}

            if (playerCount == 6) {
                players[5] = new Player("Adam"); // INITIALISE EACH PLAYER WITH NAME
                players[5].setGui(guiController.createGuiPlayer(players[5]), guiController, msg);
                players[5].setBank(bank);
                players[5].setStartBalance(balance, true); // DEPOSIT INITIAL BALANCE
                players[5].setCarColor(Color.blue);

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

            Color[] COLORSset = {Color.red, Color.white, Color.blue, Color.yellow, Color.pink, Color.black};//COLOR ARRAY FOR THE CAR
            players = new Player[playerCount];

            for (int i = 0; i < playerCount; i++) {
                int playerNumber = i + 1;
                boolean duplicateName = true;
                while(duplicateName) {
                    userInput = guiController.getUserString(playerNumber);

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
                players[i].setBank(bank); //INITIALISE BANK WITHIN PLAYER
                players[i].setStartBalance(balance, true); // DEPOSIT INITIAL BALANCE
                players[i].setCarColor(COLORSset[i]);// SET CAR COLOR FOR EACH PLAYER


            }
        }

        BoardInit board = new BoardInit(guiController, msg, players);
        squares = board.getSquareArr();
        board.initChanceSquare(squares);
        msg.printText("startGame", "na");

    }

    public void run() {

        Cup cup;
        if (useCupStub) {
            cup = new Cup_stub(guiController);
        } else {
            cup = new Cup(guiController);
        }

        int newPosition;
        int fine = 1000;

        boolean gameOver = false;

        while (gameOver == false) {



            for (int i = 0; i < playerCount; i++) { //THROWS DICE AND UPDATES PLAYER'S POSITION
                int sum;
                boolean isInJail = players[i].checkInJail();


                if (isInJail) {

                    msg.printText("fængsel", "na");
                    String[] jailOptions = {"Betal bøde?", "Kast terninger?"};
                    String name;
                    name = guiController.getUserSelection("Betal bøde på 1000 kr med det samme? eller Prøv heldet med terningekast!", jailOptions);

                    if (players[i].jailCounter() < 3) {

                        if (name.equals("Betal bøde?")) {
                            players[i].withdrawMoney(fine, true);
                            int currentBalance = players[i].getCurrentBalance();
                            System.out.println(msg.getText("newBalance") + currentBalance);
                            msg.printText("forladFængsel", "Du har nu betalt bøden, du kan nu forlade fængsel!");
                            players[i].moveOutJail();

                        } else if (name == "Kast terninger?") {

                            msg.printText("rollDice", players[i].getPlayerName());

                            boolean sameValue = cup.rollAndCheckEqualValueOfDice();
                            players[i].jailIncrement();

                            if (sameValue) {
                                players[i].moveOutJail();
                                msg.printText("kastOgForladFængsel", "na");
                                //Extra turn when leaving jail missing! maybe have done to final product!

                                msg.printText("rollDice", players[i].getPlayerName());
                                sum = cup.getSum();
                                players[i].updatePosition(sum);
                                newPosition = players[i].getPosition();
                                squares[newPosition].landOn(players[i]);

                            } else {
                                System.out.println("Vent til næste runde");
                            }

                        }
                    } else if (players[i].jailCounter() == 3) {
                        msg.printText("spildt3Runder", "na");
                        players[i].withdrawMoney(fine, true);
                        int currentBalance = players[i].getCurrentBalance();
                        System.out.println(msg.getText("newBalance") + currentBalance);
                        msg.printText("forladFængsel", "na");
                        players[i].moveOutJail();
                    }
                }

                if (!isInJail){
                    if(testingBuildButton){setOwnerForTesting();}
                        boolean rollDice = false;
                        while (!rollDice) {
                            String[] userActionButtons = setActionButtons(i);
                            String userChoice = guiController.getUserAction(players[i].getPlayerName(), userActionButtons);

                            if (userChoice.equals("Byg")) {
                                buildController.setCurrentPlayer(players[i]);
                                buildController.build();

                            } else if (userChoice.equals("Sælg")) {
                                System.out.println("player chose saelg");
                                sellController.sellLot(players[i], players);

                            } else {
                                rollDice = true;
                            }
                        }

                    msg.printText("rollDice", players[i].getPlayerName());
                    sum = cup.getSum();
                    players[i].updatePosition(sum);
                    newPosition = players[i].getPosition();
                    squares[newPosition].landOn(players[i]);
                }

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

        private String[] setActionButtons(int i) {
            String[] actionButtons;

            if(players[i].getOwnedFields().length > 0) {
                if(players[i].getBuildableDeeds().length > 0) {
                    actionButtons = new String[3];
                    actionButtons[0] = "Byg";
                    actionButtons[1] = "Sælg";
                    actionButtons[2] = "Kast terningerne";
                }  else {
                    actionButtons = new String[2];
                    actionButtons[0] = "Sælg";
                    actionButtons[1] = "Kast terningerne";
                }

            } else {
                actionButtons = new String[1];
                actionButtons[0] = "Kast terningerne";

            }
            return actionButtons;
        }


        private void setOwnerForTesting() {

            ((DeedSquare_Buildable)squares[6]).setOwnerForTesting(players[0]);
            ((DeedSquare_Buildable)squares[8]).setOwnerForTesting(players[0]);
            ((DeedSquare_Buildable)squares[9]).setOwnerForTesting(players[0]);
            ((DeedSquare_Buildable)squares[11]).setOwnerForTesting(players[1]);
            ((DeedSquare_Buildable)squares[13]).setOwnerForTesting(players[1]);
            ((DeedSquare_Buildable)squares[14]).setOwnerForTesting(players[2]);


        }


    }




