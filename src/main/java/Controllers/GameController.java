package Controllers;

import GameComponents.Bank;
import GameComponents.Board.BoardInit;
import GameComponents.Board.DeedSquare_Buildable;
import GameComponents.Board.Deed_Buildable;
import GameComponents.Board.Square;
import GameComponents.Cup2;
import GameComponents.Player;
import Translator.Text;

import java.awt.*;
import java.util.Objects;


public class GameController {
    boolean useCupStub = false;
    boolean testingInit = true;
    boolean testingBuildButton = false;
    boolean testStartBalance = true;
    GuiController guiController = new GuiController();
    Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);

    BuildController buildController = new BuildController(guiController, msg);
    SellController sellController = new SellController(guiController, msg);
    String userInput;
    int balance = 0;
    Player[] players;
    Square[] squares;

    int bankCreditorID = 99;
    int playerCount = 0;


    Bank bank = new Bank();

    int creditorId = 10;


    public void init() {
        guiController.setLang(msg);
        if (testingInit) {
            msg = new Text("src/main/java/Translator/DanskTekst", guiController);
            //msg = new Text("src/main/java/Translator/EnglishText", guiController);
            //guiController.initFieldTitles(msg);
            playerCount = 6;
            if (testStartBalance) {
                balance = 4000;

            } else {
                balance = 30000;
            }


            players = new Player[playerCount];

            players[0] = new Player("Marc"); // INITIALISE EACH PLAYER WITH NAME
            players[0].setGui(guiController.createGuiPlayer(players[0]), guiController, msg);
            players[0].setBank(bank); //INITIALISE BANK WITHIN PLAYER
            players[0].setStartBalance(balance, false); // DEPOSIT INITIAL BALANCE
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

                if (playerCount > 4) {
                    players[4] = new Player("Megan"); // INITIALISE EACH PLAYER WITH NAME
                    players[4].setGui(guiController.createGuiPlayer(players[4]), guiController, msg);
                    players[4].setBank(bank); //INITIALISE BANK WITHIN PLAYER
                    players[4].setStartBalance(balance, true); // DEPOSIT INITIAL BALANCE
                    players[4].setCarColor(Color.yellow);
                }
            }

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
                while (duplicateName) {
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

                            } else {
                                duplicateName = false;
                            }
                        }
                    }
                }


                players[i] = new Player(userInput); // INITIALISE EACH PLAYER WITH NAME
                players[i].setGui(guiController.createGuiPlayer(players[i]), guiController, msg);
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

    public void run(Cup2 cup) {

        int newPosition;
        int fine = 1000;

        boolean gameOver = false;

        while (!gameOver) {


            for (int i = 0; i < playerCount; i++) { //THROWS DICE AND UPDATES PLAYER'S POSITION
                int sum;
                boolean isInJail = players[i].checkInJail();

                if (players[i].isBankrupt()){
                    continue;
                }else{

                    if (isInJail) {

                        guiController.showMessage(players[i].getPlayerName() + " Du er i fængsel og har kun følgende muligheder for at komme ud af fængsel! Vælg en valgmulighed: Betal bøde på 1000 kr med det samme? eller Prøv dit held ved terningekast! Du kan også bruge et kom ud af fængsel kort, hvis du har et");
                        String name;
                        if (players[i].getJailPass() > 0) {
                            guiController.showMessage("Du har " + players[i].getJailPass() + " kom ud af fængsel kort.");
                            String[] jailOptions = {"Betal bøde?", "Kast terninger?", "Brug et kom ud af fængsel kort"};
                            name = guiController.getUserSelection("Betal bøde på 1000 kr med det samme?, Prøv heldet med terningekast? eller Kom ud af fængslet med det samme ", jailOptions);
                        } else {
                            String[] jailOptions = {"Betal bøde?", "Kast terninger?"};
                            name = guiController.getUserSelection("Betal bøde på 1000 kr med det samme? eller Prøv heldet med terningekast!", jailOptions);
                        }

                        if (players[i].jailCounter() < 3) {

                            if (name.equals("Betal bøde?")) {
                                players[i].withdrawMoney(fine, true,bankCreditorID);
                                int currentBalance = players[i].getCurrentBalance();
                                System.out.println(msg.getText("newBalance") + currentBalance);
                                msg.printText("forladFængsel", "Du har nu betalt bøden, du kan nu forlade fængsel!");
                                players[i].moveOutJail();

                            } else if (name.equals("Kast terninger?")) {

                                msg.printText("rollDice", players[i].getPlayerName());

                                boolean sameValue = cup.roll().isSame();
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

                            } else if (Objects.equals(name, "Brug et kom ud af fængsel kort")) {
                                int jailPasses = players[i].getJailPass();

                                if (jailPasses > 0) {
                                    players[i].useJailPass();
                                    guiController.showMessage(players[i].getPlayerName() + " Du er nu ude af fængslet");
                                    players[i].moveOutJail();
                                }

                            }
                        } else if (players[i].jailCounter() == 3) {
                            msg.printText("spildt3Runder", "na");
                            players[i].withdrawMoney(fine, true,bankCreditorID);

                            if (players[i].doesPlayerHaveCreditor()){
                                creditorId = players[i].getCreditor();

                                if (creditorId == bankCreditorID){

                                }
                            }
                            int currentBalance = players[i].getCurrentBalance();
                            System.out.println(msg.getText("newBalance") + currentBalance);
                            msg.printText("forladFængsel", "na");
                            players[i].moveOutJail();
                        }
                    }
                }

                if (!isInJail) {
                    if (testingBuildButton) {
                        setOwnerForTesting();
                    }
                    boolean rollDice = false;
                    while (!rollDice) {
                        String[] userActionButtons = setActionButtons(i);
                        String userChoice = guiController.getUserAction(players[i].getPlayerName(), userActionButtons);

                        if (userChoice.equals("Bygninger")) {
                            //buildController.setCurrentPlayer(players[i]);
                            String[] constructionOptions = setConstructionButtons(i);
                            String constructChoice = guiController.getUserSelection(msg.getText("buildOrDemo"), constructionOptions);

                            if (constructChoice.equals("Byg")) {
                                buildController.build(players[i]);

                            } else {
                                buildController.demolish(players[i]);

                            }


                        } else if (userChoice.equals("Handle")) {
                            System.out.println("player chose handle");
                            String[] userDealButtons = setDealButtons(i);
                            String dealChoice = guiController.getUserAction(players[i].getPlayerName(), userDealButtons);


                            if (dealChoice.equals("Køb")) {
                                sellController.buyLot(players[i], players);

                            } else if (dealChoice.equals("Sælg")) {
                                sellController.sellLot(players[i], players);

                            } else {
                                System.out.println("Player wants to trade");/*sellController.tradeLot(players[i], players);*/
                            }

                        } else {
                            rollDice = true;
                        }
                    }

                    msg.printText("rollDice", players[i].getPlayerName());
                    sum = cup.roll().getSum();
                    players[i].updatePosition(sum);
                    newPosition = players[i].getPosition();
                    squares[newPosition].landOn(players[i]);

                    if(players[i].doesPlayerHaveCreditor()) {
                        int creditor;
                        creditor = players[i].getCreditor();

                        if (creditor == bankCreditorID) {
                            guiController.showMessage("Du skylder penge til banken, men du har mulighed for at sælge dine ejendomme før du går bankerot");
                            String[] userActionButtons = setActionButtons(i);
                            String userChoice = guiController.getUserAction(players[i].getPlayerName(), userActionButtons);

                            if (userChoice.equals("Erklær Bankerot")) {

                                buildController.demolishEverything(players[i]);
                                sellController.takeLots(players[i]);
                                players[i].setBalanceToZero();
                                players[i].isBankrupt();


                            } else if (userChoice.equals("Handle")) {
                            System.out.println("player chose handle");
                            String[] userDealButtons = setDealButtons(i);
                            String dealChoice = guiController.getUserAction(players[i].getPlayerName(), userDealButtons);


                            if (dealChoice.equals("Køb")) {
                                sellController.buyLot(players[i], players);

                            } else if (dealChoice.equals("Sælg")) {
                                sellController.sellLot(players[i], players);

                            } else {
                                System.out.println("Player wants to trade");/*sellController.tradeLot(players[i], players);*/
                            }
                            }

                        }else if (creditor == 10) {
                            continue;

                        } else {

                            sellController.giveLotsToCreditor(players[i],players,creditor);
                            players[i].setBalanceToZero();
                            guiController.showMessage(players[i].getPlayerName() + " Du skylder " + players[creditor].getPlayerName() + " penge, men nu er det for sent. Du er nu gået bankerot og er ude af spillet. Alt hvad du ejer går til din  kreditor");

                        }



                    }

                }

                if (players[i].isBankrupt()) {

                    int bankruptPlayers = 0;
                    int winner = 0;
                    for (int j = 0; j < playerCount; j++) {  //Checks for bankrupt players and declares winner if only one is standing


                        if (players[j].isBankrupt()) {
                            bankruptPlayers = bankruptPlayers + 1;
                        } else if (!players[j].isBankrupt()) {
                            winner = j;
                        }

                    }
                    if (bankruptPlayers == playerCount - 1) {
                        gameOver = true;
                        String winnerName = players[winner].getPlayerName();
                        guiController.showMessage(msg.getText("gameOver") +" "+ winnerName);
                        break;
                    } else {
                        winner = 0;
                    }

                }

                System.out.println();
                System.out.println();
            }

        }

    }

    private String[] setActionButtons(int i) {
        String[] actionButtons;
        boolean anyPlayerHasDeeds = false;
        for (int k = 0; k < players.length; k++) {
            if (players[k].getPropertyCount() > 0) {
                anyPlayerHasDeeds = true;
                break;
            }
        }

        if (players[i].getOwnedFields().length > 0) {
            if (players[i].getBuildableDeeds().length > 0) {
                actionButtons = new String[3];
                actionButtons[0] = "Bygninger";
                actionButtons[1] = "Handle";
                actionButtons[2] = "Kast terningerne";
            } else {
                actionButtons = new String[2];
                actionButtons[0] = "Handle";
                actionButtons[1] = "Kast terningerne";
            }

        } else if (anyPlayerHasDeeds) {
            actionButtons = new String[2];
            actionButtons[0] = "Handle";
            actionButtons[1] = "Kast terningerne";
        } else {
            actionButtons = new String[1];
            actionButtons[0] = "Kast terningerne";

        }
        return actionButtons;
    }

    private String[] setConstructionButtons(int i) {
        Deed_Buildable[] deeds = players[i].getBuildableDeeds();
        String[] constructButtons;
        boolean hasLots = false;
        boolean hasBuildings = false;

        for (int j = 0; j < deeds.length; j++) {
            if (deeds.length > 0) {
                hasLots = true;
                break;
            }
        }

        for (int j = 0; j < deeds.length; j++) {
            if (deeds[j].getHouseCount() > 0 || deeds[j].hasHotel()) {

                hasBuildings = true;
                break;
            }
        }

        if (hasLots && !hasBuildings) { // JUST BUILD
            constructButtons = new String[1];
            constructButtons[0] = "Byg";

        } else { //BUILD + DEMOLISH
            constructButtons = new String[2];
            constructButtons[0] = "Byg";
            constructButtons[1] = "Sælg tilbage til banken";
        }
        return constructButtons;
    }

    private String[] setDealButtons(int i) {
        String[] dealButtons;
        boolean currentPlayerHasDeeds = players[i].getBuildableDeeds().length > 0 || players[i].getNonBuildableDeeds().length > 0;

        if (currentPlayerHasDeeds) {
            dealButtons = new String[3];
            dealButtons[0] = "Køb";
            dealButtons[1] = "Sælg";
            dealButtons[2] = "Bytte";

        } else {
            dealButtons = new String[1];
            dealButtons[0] = "Køb";

        }
        return dealButtons;
    }


    private void setOwnerForTesting() {

        ((DeedSquare_Buildable) squares[6]).setOwnerForTesting(players[0]);
        ((DeedSquare_Buildable) squares[8]).setOwnerForTesting(players[0]);
        ((DeedSquare_Buildable) squares[9]).setOwnerForTesting(players[0]);
        ((DeedSquare_Buildable) squares[11]).setOwnerForTesting(players[1]);
        ((DeedSquare_Buildable) squares[13]).setOwnerForTesting(players[1]);
        ((DeedSquare_Buildable) squares[14]).setOwnerForTesting(players[2]);


    }


}




