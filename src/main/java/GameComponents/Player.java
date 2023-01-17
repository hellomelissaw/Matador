package GameComponents;

import Controllers.GuiController;
import GameComponents.Board.*;
import gui_fields.GUI_Player;
import Translator.Text;
import gui_fields.GUI_Car;
import java.util.ArrayList;

import java.awt.*;

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

    private GUI_Car guiCar = new GUI_Car();
    Text msg;
    private int squareIndex = 0;
    private String playerName;
    private Account playerAccount = new Account();

    private Cardholder cardholder = new Cardholder();
    private String winnerName;

    private int creditor;

    private int ignoreCreditor = 10;
    private boolean hasCreditor = false;


    public GUI_Car getGuiCar() {

        return guiCar;
    }

    ArrayList<Deed> ownedFields = new ArrayList<>();
    //SellController sellController = new SellController();

    Bank bank;

    public Player(String playerName) {
        this.playerName = playerName;

    }

    public void guiIsOn(boolean guiIsOn) {
        guiOn = guiIsOn;
        playerAccount.guiIsOn(guiIsOn);

    }

    public void setCarColor (Color color){
        guiPlayer.getCar().setPrimaryColor(color);
    }//SETS COLOR FOR THE CARS


    public void setLang(Text msg) {
        this.msg = msg;
    }

    public void setGui(GUI_Player guiPlayer, GuiController guiController, Text msg){
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

    public void setBalanceToZero (){
        playerAccount.setBalance(0);
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

    public String setPlayerName(String playerName) {
        this.playerName = playerName;
        return playerName;
    }

    /**
     * Deposits money in Player's Account
     *
     * @param newPoints                  amount of Monopoly money to deposit
     * @param transactionToBankParameter is true if the transaction is with the bank and not other players
     */
    public void withdrawMoney(int newPoints, boolean transactionToBankParameter, int creditor) {

            if (transactionToBankParameter) {
                playerAccount.withDraw(newPoints);
                bank.giveMoneyToBank(newPoints);
            } else {
                playerAccount.withDraw(newPoints);
            }

            if (playerAccount.getAccountStatus()) {
            if (creditor != ignoreCreditor) {
            this.creditor = creditor;
            hasCreditor = true;
            }

        }

    }

    public int getCreditor(){
        return creditor;
    }

    public void setCreditorFalse(){
        hasCreditor = false;
    }

    public boolean doesPlayerHaveCreditor(){
        return hasCreditor;
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
        } else {
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
        cardholder.addDeed_Buildable(deed);

    }

    public void takeNonBuildableDeed(Deed_NonBuildable deed) {
        cardholder.addDeedNon_Buildable(deed);
    }

    public Deed_Buildable[] getBuildableDeeds() {
        Deed_Buildable[] deedList = cardholder.getBuildable();
        return deedList;
    }

    public Deed_NonBuildable[] getNonBuildableDeeds() {
        Deed_NonBuildable[] deedList = cardholder.getNonBuildable();
        return deedList;
    }

    public void addToOwnedFields(Deed deed) {

        ownedFields.add(deed);
    }


    public void removeFromOwnedFields(Deed deed) {

        ownedFields.remove(deed);
    }


    public Deed[] getOwnedFields() {
        Deed[] deeds = new Deed[ownedFields.size()];
        for (int i = 0; i < ownedFields.size(); i++) {
            deeds[i] = ownedFields.get(i);
        }
        return deeds;
    }

    public Deed[] getPropertiesDeed() {
        return cardholder.getProperties();
    }

    public int getPropertyCount() {
        return cardholder.getPropertyCount();
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
                                Deed_Buildable[] deed_buildables = cardholder.getBuildable();
                                for (int k = 0; k < deed_buildables.length; k++) {
                                    System.out.println(playerName + " owns " + deed_buildables[k].getDeedName() + "after buying house.");
                                }

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
                    playerAccount.withDraw(buildingPrice*5);

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

    public int getJailPass(){
        return playerAccount.getJailPasses();
    }

    public void giveJailPass(){
        playerAccount.giveJailPass();
    }

    public Boolean useJailPass(){
        return playerAccount.useJailPass();
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

    public void giveHousesAndHotelsToBank(Deed_Buildable[] deedsToSellFrom) {
        int houseCounter = 0;
        for (int i = 0; i < deedsToSellFrom.length; i++) {
            int currentHouseCount = deedsToSellFrom[i].getHouseCount();
            houseCounter = houseCounter + currentHouseCount;
            deedsToSellFrom[i].setHouseCount(0);

            if (deedsToSellFrom[i].hasHotel()) {
                deedsToSellFrom[i].setHasHotel(false);
                bank.sellHotelToBank(1, 0);
            }
        }

        bank.sellHouseToBank(houseCounter,0);

    }

    public void addToCardholder(Deed boughtDeed) {
        if (boughtDeed instanceof Deed_Buildable) {
            cardholder.addDeed_Buildable((Deed_Buildable) boughtDeed);
        } else if (boughtDeed instanceof Deed_NonBuildable) {
            cardholder.addDeedNon_Buildable((Deed_NonBuildable) boughtDeed);
        } else {
            System.out.println("Card type not recognised");
        }
    }

    public void removeFromCardholder(Deed boughtDeed) {
        if (boughtDeed instanceof Deed_Buildable) {
            cardholder.removeDeed_Buildable((Deed_Buildable) boughtDeed);
        } else if (boughtDeed instanceof Deed_NonBuildable) {
            cardholder.removeDeed_NonBuildable((Deed_NonBuildable) boughtDeed);
        }
    }
}
