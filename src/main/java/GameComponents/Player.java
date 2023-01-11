package GameComponents;

import Controllers.GuiController;
import GameComponents.Board.*;
import gui_fields.GUI_Player;
import Translator.Text;

/*
===================================================================================
This class is reused from our CDIO2 project and built upon.
===================================================================================
 */
public class Player {

    boolean guiOn = true;
    private boolean testing = false;
    GuiController guiController;
    GUI_Player guiPlayer;
    DeedSquare deedSquare;
    int squareCount = 40;

    private boolean inJail = false;

    int counter = 0;

    Text msg;


    private Bank bank = new Bank();
    private int squareIndex = 0;
    private String playerName;
    private Account playerAccount = new Account();

    private Cardholder cardholder = new Cardholder();
    private String winnerName;
    Deed deed;



    public Player(String playerName) {
        this.playerName = playerName;

    }

    public void guiIsOn(boolean guiIsOn) {
            guiOn = guiIsOn;
            playerAccount.guiIsOn(guiIsOn);

    }

    public void setLang(Text msg) {
        this.msg = msg;
    }

    public void setGui(GUI_Player guiPlayer, GuiController guiController, Text msg) {
        if (guiOn) {
            this.guiPlayer = guiPlayer;
            playerAccount.setGuiAccount(guiPlayer);
            this.guiController = guiController;
        }
        this.msg = msg;
        testing = false;
    }

    public void setStartBalance(int startBalance) {
        playerAccount.deposit(startBalance);
        bank.takeMoneyFromBank(startBalance);
        if (guiOn) {guiPlayer.setBalance(startBalance); }
    }

    public String setPlayerName(String playerName) {
        this.playerName = playerName;
        return playerName;
    }

    /**
     * Deposits money in Player's Account
     * @param newPoints amount of Monopoly money to deposit
     */
    public void withdrawMoney(int newPoints) {
        playerAccount.withDraw(newPoints);
    }

    /**
     * Withdraws money from Player's Account
     * @param newPoints amount of Monopoly Money to withdraw
     */
    public void depositMoney(int newPoints){
        playerAccount.deposit(newPoints);
    }
    public String[] playerNameList (Player[] players) {

        String[] playerNameArray = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            //playerNameArray = new String[players.length];
            playerNameArray[i] = players[i].getPlayerName();


        }
        return playerNameArray;
    }

    public void updateBank(int amount, String transactionType) {
        if(transactionType.equals("deposit")) {
            bank.takeMoneyFromBank(amount);
        } else if (transactionType.equals("withdraw")) {
            bank.giveMoneyToBank(amount);
        }
    }

    public int getBankDetails(String typeOfInfo) {
        int info = 0;
        if(typeOfInfo.equals("balance")){
            info = bank.getBalance();
        } else if (typeOfInfo.equals("houseCount")) {
            info = bank.getHouseCount();
        } else if(typeOfInfo.equals("hotelCount")) {
            info = bank.getHotelCount();
        } else {
            System.out.println("This type of information is not available. It might be top secret... or u might have just made a typo...");
        }
        return info;
    }

    public int getCurrentBalance(){
        return (playerAccount.getBalance());
    }

    public String getPlayerName (){
        return playerName;
    }

    /**
     * Updates the position of the Player according to the sum of the dice in rings from square 0 to 23
     * @param distance amount of squares to move player's car
     * @return index of the Square that the Player is moved to after throwing dice
     */
    public void updatePosition(int distance) {
        boolean getStartMoney = false;
        int currentPos = squareIndex;
        if(currentPos == 30 || currentPos == 0) {getStartMoney = false;}

        for(int i = 0; i < Math.abs(distance); i++) {
            if (distance < 0) {
                if(squareIndex > 0) {
                    squareIndex--;
                } else if (squareIndex == 0) {
                    squareIndex = 39;
                }

            } else {
                if (squareIndex < 39) {
                    squareIndex++;

                } else {
                    squareIndex = 0;
                    if(!(currentPos==30)) { // solution broche-a-foin... to be fixed
                        getStartMoney = true;
                    }
                }
            }
        }

        if (getStartMoney) {
            msg.printText("passStart", "na");
            playerAccount.deposit(4000);
        }
            if(!testing){ if(guiOn) { guiController.move(guiPlayer, currentPos, squareIndex); }}
        }


    public int getPosition(){
        return squareIndex;
    }

    public int getDistanceToMove(int newSquareIndex, int boardLength) {
        int distance;
        if (squareIndex > newSquareIndex) {
            distance = boardLength - squareIndex + newSquareIndex;
            System.out.println("CurrentPos > i, dist to move:" + distance);

        } else {
            distance = boardLength - squareIndex - (boardLength - newSquareIndex);
            System.out.println("CurrentPos < i, dist to move: " + distance);
        }
        return distance;
    }

    public boolean isBankrupt() {
        return playerAccount.getAccountStatus();

    }
    public void moveToJail(){
        inJail = true;

    }
    public void moveOutJail(){
        inJail = false;
        counter = 0;
    }

    public boolean checkInJail(){
        return inJail;
    }
    public int jailCounter(){
        return counter;
    }

    public void jailIncrement(){
        counter = counter + 1;

    }
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }


    @Override
    public String toString() {
        return playerName;
    }

    // Methode is inspired from internet https://www.geeksforgeeks.org/java-program-for-program-to-find-largest-element-in-an-array/
    public String winner(Player[] player) {
        int winner = player[0].getCurrentBalance();
        for (int i = 0; i < player.length; i++) {
            if (player[i].getCurrentBalance() > winner) {
                winner = player[i].getCurrentBalance();
                winnerName = player[i].getPlayerName();
            }
        }
        return winnerName;
    }

    public boolean IsGroupOwner(String color) {
        boolean ownerStatus = cardholder.getOwnerStatus(color);
        return ownerStatus;
    }

    public void setIsGroupOwner(){

    }

    public void takeBuildableDeed(Deed_Buildable deed) {
        cardholder.addDeedBuildable(deed);
    }

    public void takeNonBuildableDeed(Deed_NonBuildable deed) {
        cardholder.addDeedNonBuildable(deed);
    }

    public Deed_Buildable[] getBuildableDeeds() {
        Deed_Buildable[] deedList = cardholder.getBuildable();
        return deedList;
    }

    public void buyHouse(DeedSquare_Buildable[] lotsToBuildOn, int housesToBuy) {
        int housesAvailable = bank.getHouseCount();

        if(housesAvailable >= housesToBuy) {

            Deed_Buildable[] deedsToBuildOn = new Deed_Buildable[lotsToBuildOn.length];
            for (int i = 0; i < lotsToBuildOn.length; i++) {
                deedsToBuildOn[i] = lotsToBuildOn[i].getDeed();
            }

            for (int j = 0; j < housesToBuy; j++) {
                for (int i = 0; i < deedsToBuildOn.length; i++) {
                    String color = deedsToBuildOn[i].getColor();
                    boolean ownsGroup = cardholder.getOwnerStatus(color);

                    if (ownsGroup) {
                        boolean clearedForPurchase = cardholder.houseCountIsLevel(color, deedsToBuildOn[i]);
                        if (clearedForPurchase) {
                            int buildingPrice = deedsToBuildOn[i].getBuildingPrice();
                            int currentBalance = playerAccount.getBalance();
                            if (currentBalance > 0 && currentBalance - buildingPrice >= 0) {
                                playerAccount.withDraw(buildingPrice);
                                int count = deedsToBuildOn[i].getHouseCount();
                                count++;
                                deedsToBuildOn[i].setHouseCount(count); // Maybe set the house count for deed in the square's setHouseCount??
                                lotsToBuildOn[i].setHouseCount(count);
                                bank.buyHouseFromBank(count,buildingPrice);
                                System.out.println("There is now " + count + " houses on Square #" + i);
                                System.out.println("Player's new balance is " + playerAccount.getBalance());

                            } else {
                                System.out.println("Du har ikke nok penge til at købe dette hus.");
                            }
                        } else {
                            System.out.println("Du skal bygge en jævn mængde hus på alle grunde i gruppen før du kan bygge videre.");
                        }

                    } else {
                        System.out.println("Du ejer ikke alle grunde i gruppen, derfor kan du ikke bygge endnu.");
                    }
                }
            }
        } else {
            System.out.println("Banken har ikke nok huse til at opfylde din ordre.");
        }
    }

    public void buyHotel(DeedSquare_Buildable[] lotsToBuildOn) {
        int currentHotelCount = bank.getHotelCount();
        if (currentHotelCount > 0){
        for (int i = 0; i < lotsToBuildOn.length; i++) {
            Deed_Buildable deed = lotsToBuildOn[i].getDeed();
            int houseCount = lotsToBuildOn[i].getHouseCount();
            if (houseCount == 4) {
                int currentBalance = playerAccount.getBalance();
                int buildingPrice = deed.getBuildingPrice();
                if (currentBalance > 0 && currentBalance - buildingPrice >= 0) {
                    playerAccount.withDraw(buildingPrice);
                    lotsToBuildOn[i].setHouseCount(0);
                    lotsToBuildOn[i].setHasHotel(true);
                    bank.buyHotelFromBank(buildingPrice);

                } else {
                    System.out.println("Du har ikke nok penge til at købe dette hotel.");
                }

            } else {
                System.out.println("Du har ikke nok huse til at bygge et hotel.");
            }
        }
    } else {System.out.println("Der er ikke nok hoteller i banken til at opfylde din ordre");}
    }

    public void sellHouseToBank(DeedSquare_Buildable[] lotsToSellFrom, int housesToSell) { // in gui make sure there is not the option to sell houses one does not own

        for (int i = 0; i < lotsToSellFrom.length; i++) {
            int currentHouseCount = lotsToSellFrom[i].getDeed().getHouseCount();
            int newHouseCount = currentHouseCount - housesToSell;
            lotsToSellFrom[i].setHouseCount(newHouseCount);
            lotsToSellFrom[i].getDeed().setHouseCount(newHouseCount);
            int halfPrice = Math.round(lotsToSellFrom[i].getDeed().getBuildingPrice()/2);
            playerAccount.deposit(halfPrice);
            bank.sellHouseToBank(housesToSell,halfPrice);
        }
    }

    public void sellHotelToBank(DeedSquare_Buildable[] lotsToSellFrom) { // in gui make sure there is not the option to sell houses one does not own

        for (int i = 0; i < lotsToSellFrom.length; i++) {
            lotsToSellFrom[i].setHasHotel(false);
            lotsToSellFrom[i].getDeed().setHasHotel(false);
            int halfPrice = Math.round(lotsToSellFrom[i].getDeed().getBuildingPrice()/2);
            playerAccount.deposit(halfPrice);
            bank.sellHotelToBank(1,halfPrice);
        }
    }

    public void setHouseCount(int count){
        bank.setHouseCount(count);
    }
    public void setHotelCount(int count) {
        bank.setHotelCount(count);
    }



    public void selLot(Player[] players) {
        String choosenButton = guiController.getUserButtonPressed("salgeGround");
        if (choosenButton == "Ja") {

            Deed_Buildable[] ownedBuildableDeeds = cardholder.getBuildable();
            String[] buildablePropertyName = new String[ownedBuildableDeeds.length];
            for(int i = 0 ; i < ownedBuildableDeeds.length ; i++) {
                buildablePropertyName[i] = ownedBuildableDeeds[i].getDeedName();
            }
                Deed_NonBuildable[] ownedNonBuildableDeeds = cardholder.getNonBuildable();
                String[] nonBuildablePropertyName = new String[ownedNonBuildableDeeds.length];
                for (int j = 0; j < ownedNonBuildableDeeds.length; j++) {
                    nonBuildablePropertyName[j] = ownedNonBuildableDeeds[j].getDeedName();
                }
            for (int i = 0; i < ownedBuildableDeeds.length+ownedNonBuildableDeeds.length; i++) {
                String[] propertiesName = new String[ownedBuildableDeeds.length+ownedNonBuildableDeeds.length];
                for (int j = 0; j < ownedBuildableDeeds.length; j++) {
                    for (int k = ownedBuildableDeeds.length+1  ; k <ownedNonBuildableDeeds.length ; k++) {
                        propertiesName[i] = buildablePropertyName[i] + nonBuildablePropertyName[k];
                    }

                }
    int deedPrice =0 ;
                    String userSelection = guiController.getUserSelection(propertiesName[i]);
                for (int j = 0; j <propertiesName.length ; j++) {
                    String choosenButton_1 = guiController.getUserButtonPressed("erDerEnKøber");
                    if(choosenButton_1 == "Ja" ){
                        //String buyerName = guiController.getUserSelection();
                        //String buyerName = guiController.getUserString("Indtast dit navn",players);
                       String[] playersName = new String[players.length];
                        for (int k = 0; k < playersName.length; k++) {
                            if (!players[k].getPlayerName().equals(playersName)){
                                playersName[k] = new String(players[k].getPlayerName());
                                String buyerName = guiController.getUserSelection("Valge dit navn", playersName);
                                if(players[k].getPlayerName() ==buyerName){
                                    if(userSelection.equals(ownedBuildableDeeds[i].getDeedName()))
                                        deedPrice = ownedBuildableDeeds[i].getDeedPrice(userSelection);
                                    if (userSelection.equals(ownedNonBuildableDeeds[i].getDeedName()))
                                        deedPrice = ownedNonBuildableDeeds[i].getDeedPrice(userSelection);
                                    players[k].withdrawMoney(deedPrice);
                                    //players[players].depositMoney(deedPrice);
                                }
                            }
                        }



                }

            }

        }}

    }
    }



