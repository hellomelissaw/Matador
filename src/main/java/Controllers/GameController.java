package Controllers;
import GameComponents.Board.*;
import GameComponents.Board.Square;
import GameComponents.Cup;
import GameComponents.Cup_stub;
import GameComponents.Board.JailSquare;
import GameComponents.Player;
import Translator.*;
public class GameController {
    GuiController guiController = new GuiController();
    //private int playerCount = 0;
    String userInput;
    int balance = 0;
    Player[] players;
    Square[] squares;
    Text msg = new Text("src/main/java/Translator/Dansktekst", guiController);

    int playerCount = 0;

    int jailCounter = 0;

    int counter = 0;


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
            players[0].setStartBalance(balance); // DEPOSIT INITIAL BALANCE


            players[1] = new Player("Germaine"); // INITIALISE EACH PLAYER WITH NAME
            players[1].setGui(guiController.createGuiPlayer(players[1]),guiController,msg);
            players[1].setStartBalance(balance); // DEPOSIT INITIAL BALANCE

            players[2] = new Player("Harry"); // INITIALISE EACH PLAYER WITH NAME
            players[2].setGui(guiController.createGuiPlayer(players[2]),guiController,msg);
            players[2].setStartBalance(balance); // DEPOSIT INITIAL BALANCE

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
        } else {
        cup = new Cup(guiController);}

        int newPosition;
        int fine = 1000;

        boolean gameOver = false;
        while(gameOver == false) {


            for (int i = 0; i < playerCount; i++) { //THROWS DICE AND UPDATES PLAYER'S POSITION
                int sum;
                boolean isInJail = players[i].checkInJail();


                if(isInJail)  {

                    msg.printText("fængsel","na");
                    String[] jailOptions = {"Betal bøde?","Kast terninger?"};
                    String name;
                    name = guiController.getUserSelection("Betal bøde på 1000 kr med det samme? eller Prøv heldet med terningekast!",jailOptions);

                if(players[i].jailCounter() < 3)
                {

                    if(name == "Betal bøde?")
                    {
                        players[i].withdrawMoney(fine);
                        int currentBalance = players[i].getCurrentBalance();
                        System.out.println(msg.getText("newBalance")+ currentBalance);
                        msg.printText("forladFængsel","Du har nu betalt bøden, du kan nu forlade fængsel!");
                        players[i].moveOutJail();
                    }else if(name == "Kast terninger?"){

                        msg.printText("rollDice", players[i].getPlayerName());
                        cup.getSum();

                        boolean sameValue = cup.checkEqualValueOfDice();


                        if(sameValue == true){
                            players[i].moveOutJail();
                            msg.printText("kastOgForladFængsel","na");
                            //Extra turn when leaving jail missing! maybe have done to final product!
                        }else{
                            players[i].jailIncrement();
                        }

                    }else{
                        msg.printText("spildt3Runder","na");
                        players[i].withdrawMoney(fine);
                        int currentBalance = players[i].getCurrentBalance();
                        System.out.println(msg.getText("newBalance")+ currentBalance);
                        msg.printText("forladFængsel", "na");
                        players[i].moveOutJail();

                    }
                }
                }

                    msg.printText("rollDice", players[i].getPlayerName());
                    sum = cup.getSum();
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

    }

