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
    private int squareIndex = 0;
    private String playerName;
    private Account playerAccount = new Account();

    private Cardholder cardholder = new Cardholder();
    private String winnerName;
    Deed deed;

    Bank bank;

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

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setStartBalance(int startBalance, boolean transactionToBankParameter) {
        playerAccount.deposit(startBalance);
        if (guiOn) {
            guiPlayer.setBalance(startBalance);
        }

        if (transactionToBankParameter) {
            bank.takeMoneyFromBank(startBalance);
        }

    }

    /**
     * Deposits money in Player's Account
     *
     * @param newPoints                  amount of Monopoly money to deposit
     * @param transactionToBankParameter is true if the transaction is with the bank and not other players
     */
    public void withdrawMoney(int newPoints, boolean transactionToBankParameter) {

        if (transactionToBankParameter) {
            playerAccount.withDraw(newPoints);
            bank.giveMoneyToBank(newPoints);
        } else if (!transactionToBankParameter) {
            playerAccount.withDraw(newPoints);
        }
    }

    /**
     * Withdraws money from Player's Account
     *
     * @param newPoints                  amount of Monopoly Money to withdraw
     * @param transactionToBankParameter true if the transaction is with the bank and not other players
     */
    public void depositMoney(int newPoints, boolean transactionToBankParameter) {
        if (transactionToBankParameter) {
            int cashedOutMoney = bank.takeMoneyFromBank(newPoints);
            playerAccount.deposit(cashedOutMoney);
        } else if (!transactionToBankParameter) {
            playerAccount.deposit(newPoints);
        }
    }

    public int getCurrentBalance() {
        return (playerAccount.getBalance());
    }

    public String getPlayerName() {
        return playerName;
    }

    /**
     * Updates the position of the Player according to the sum of the dice in rings from square 0 to 23
     *
     * @param distance amount of squares to move player's car
     * @return index of the Square that the Player is moved to after throwing dice
     */
    public void updatePosition(int distance) {
        boolean getStartMoney = false;
        int currentPos = squareIndex;
        if (currentPos == 30 || currentPos == 0) {
            getStartMoney = false;
        }

        for (int i = 0; i < Math.abs(distance); i++) {
            if (distance < 0) {
                if (squareIndex > 0) {
                    squareIndex--;
                } else if (squareIndex == 0) {
                    squareIndex = 39;
                }

            } else {
                if (squareIndex < 39) {
                    squareIndex++;

                } else {
                    squareIndex = 0;
                    if (!(currentPos == 30)) { // solution broche-a-foin... to be fixed
                        getStartMoney = true;
                    }
                }
            }
        }

        if (getStartMoney) {
            msg.printText("passStart", "na");
            playerAccount.deposit(4000);
        }
        if (!testing) {
            if (guiOn) {
                guiController.move(guiPlayer, currentPos, squareIndex);
            }
        }
    }


    public int getPosition() {
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

    public void moveToJail() {
        inJail = true;

    }

    public void moveOutJail() {
        inJail = false;
        counter = 0;
    }

    public boolean checkInJail() {
        return inJail;
    }

    public int jailCounter() {
        return counter;
    }

    public void jailIncrement() {
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


    public void buyHouse(Deed_Buildable[] deedsToBuildOn, int housesToBuy) {

        boolean enoughHouses = bank.areThereEnoughHouses(housesToBuy);
        boolean abortMission = false;
        if (enoughHouses) {

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
                                bank.buyHouseFromBank(1, buildingPrice);
                                int count = deedsToBuildOn[i].getHouseCount();
                                count++;
                                deedsToBuildOn[i].setHouseCount(count);
                                if (guiOn) {
                                    guiController.setHouseCount(deedsToBuildOn[i].getIndex(), count);
                                }
                                //lotsToBuildOn[i].setHouseCount(count);
                                System.out.println("There is now " + count + " houses on Square " + deedsToBuildOn[i].getDeedName());
                                System.out.println("Player's new balance is " + playerAccount.getBalance());

                            } else {
                                msg.printText("insufficientFunds", "na");
                                abortMission = true;
                                break;

                            }
                        } else {
                            msg.printText("houseCountUnequal", "na");
                            abortMission = true;
                            break;

                        }

                    } else {
                        msg.printText("notGroupOwner", "na");

                    }
                }
                if (abortMission) {
                    break;
                }
            }
        }

    }


    public void buyHotel(Deed_Buildable[] deedsToBuildOn) {
        for (int i = 0; i < deedsToBuildOn.length; i++) {
            // Deed_Buildable deed = lotsToBuildOn[i].getDeed();
            int houseCount = deedsToBuildOn[i].getHouseCount();
            boolean availableHotels = bank.areThereStillHotels();
            if (houseCount == 4 && availableHotels) {
                int currentBalance = playerAccount.getBalance();
                int buildingPrice = deedsToBuildOn[i].getBuildingPrice();
                if (currentBalance > 0 && currentBalance - buildingPrice >= 0) {
                    playerAccount.withDraw(buildingPrice);

                    int index = deedsToBuildOn[i].getIndex();

                    deedsToBuildOn[i].setHouseCount(0);
                    bank.sellHouseToBank(4, 0);

                    bank.buyHotelFromBank(1, buildingPrice);
                    deedsToBuildOn[i].setHasHotel(true);

                    if (guiOn) {
                        guiController.setHouseCount(index, 0);
                        guiController.setHasHotel(index, true);

                    }

                } else {
                    msg.printText("insufficientFunds", "na");

                }

            } else if (!availableHotels) {
                msg.printText("insufficientHotelsInBank", "na");

            } else {
                msg.printText("insufficientHouses", "na");

            }
        }
    }


    public void sellHotelToBank(Deed_Buildable[] deedsToSellFrom) { // in gui make sure there is not the option to sell houses one does not own

        for (int i = 0; i < deedsToSellFrom.length; i++) {

            deedsToSellFrom[i].setHasHotel(false);
            int halfPrice = Math.round(deedsToSellFrom[i].getBuildingPrice() / 2);
            playerAccount.deposit(halfPrice);
            bank.sellHotelToBank(1, halfPrice);

            if (guiOn) {
                int index = deedsToSellFrom[i].getIndex();
                guiController.setHasHotel(index, false);
            }
        }
    }

    public void sellHouseToBank(Deed_Buildable[] deedsToSellFrom, int houseCount) { // in gui make sure there is not the option to sell houses one does not own

        for (int i = 0; i < deedsToSellFrom.length; i++) {
            int currentHouseCount = deedsToSellFrom[i].getHouseCount();
            if (houseCount > currentHouseCount) {
                msg.printText("toFewHouses", "na");
            } else {
                deedsToSellFrom[i].setHouseCount(currentHouseCount - houseCount);
            }

            int halfPrice = Math.round(deedsToSellFrom[i].getBuildingPrice() / 2);
            playerAccount.deposit(halfPrice);
            bank.sellHouseToBank(houseCount, halfPrice);

            if (guiOn) {
                int index = deedsToSellFrom[i].getIndex();
                guiController.setHouseCount(index, currentHouseCount - houseCount);
            }
        }
    }



    public void sellLot(Player[] players) {
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
                       String[] playersName = new String[players.length];
                        for (int k = 0; k < playersName.length; k++) {
                            if (!players[k].getPlayerName().equals(playersName)){
                                playersName[k] = new String(players[k].getPlayerName());
                            }
                        }

                    if(userSelection.equals(ownedBuildableDeeds[i].getDeedName()))
                    deedPrice = ownedBuildableDeeds[i].getDeedPrice(userSelection);
                    if (userSelection.equals(ownedNonBuildableDeeds[i].getDeedName()))
                        deedPrice = ownedNonBuildableDeeds[i].getDeedPrice(userSelection);

                }

            }

        }}

    }
    }



