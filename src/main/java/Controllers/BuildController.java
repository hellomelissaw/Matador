package Controllers;

import GameComponents.Board.Deed_Buildable;
import GameComponents.Player;
import Translator.Text;

import java.util.ArrayList;

public class BuildController {

    GuiController guiController;
    Text msg;
    Player currentPlayer;
    boolean testingHouseCount = false;
    String buildingType;
    int houseCount = 0;
    boolean testing = false;

    public BuildController(GuiController guiController, Text msg) {
        this.guiController = guiController;
        this.msg = msg;

    }

    /*public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }*/

    public void build(Player currentPlayer) {
        Deed_Buildable[] selectedLotsArr = selectLots(currentPlayer, "build");

        String[] buildOptions = {"Hus", "Hotel"};

        if(!testing)
        buildingType = guiController.getUserSelection(msg.getText("houseOrHotel"), buildOptions);

        if (buildingType.equals("Hus")) {

            if(!testing){
                houseCount = getHouseCount("build");
            }
            currentPlayer.buyHouse(selectedLotsArr, houseCount);

        } else {
            if (testingHouseCount) {
                setHouseCountForTesting(currentPlayer, 4);
            }

            currentPlayer.buyHotel(selectedLotsArr);

        }
    }

    public void demolish(Player currentPlayer) {
        Deed_Buildable[] selectedLotsArr = selectLots(currentPlayer,"demolish");

        String[] demolishOptions = {"Hus", "Hotel"};

        if(!testing) {
            buildingType = guiController.getUserSelection(msg.getText("houseOrHotelDemo"), demolishOptions);
        }

        if (buildingType.equals("Hus")) {
            if(!testing){
                houseCount = getHouseCount("demolish");
            }
            currentPlayer.sellHouseToBank(selectedLotsArr, houseCount);

        } else {
            if (testingHouseCount) {
                setHouseCountForTesting(currentPlayer, 4);
            }

            currentPlayer.sellHotelToBank(selectedLotsArr);

        }
    }

    public void demolishEverything(Player currentPlayer) {

        Deed_Buildable[] playerDeeds = currentPlayer.getBuildableDeeds();
        currentPlayer.giveHousesAndHotelsToBank(playerDeeds);

    }




    public void testing(boolean testing, String buildingType, int houseCount) {
        this.testing = testing;
        this.buildingType = buildingType;
        this.houseCount = houseCount;
    }


    private Deed_Buildable getDeedFromName(String deedName, Player currentPlayer) {
        int deedToBuildOnIndex = 0;
        Deed_Buildable[] deeds = currentPlayer.getBuildableDeeds();
        for(int k = 0 ; k < deeds.length ; k++) {
            if(deedName.equals(deeds[k].getDeedName())){
                deedToBuildOnIndex = k;
            }
        }
        Deed_Buildable deedToBuildOn = deeds[deedToBuildOnIndex];
        return deedToBuildOn;
    }

    private Deed_Buildable[] selectLots(Player currentPlayer, String actionType) {
        ArrayList<Deed_Buildable> updatedDeedList = new ArrayList<Deed_Buildable>();
        Deed_Buildable[] playerDeeds = currentPlayer.getBuildableDeeds();
        for (int j = 0; j < currentPlayer.getBuildableDeeds().length; j++) {
            updatedDeedList.add(playerDeeds[j]);
        }

        ArrayList<Deed_Buildable> selectedLots = new ArrayList<Deed_Buildable>();

        boolean selectingMoreLots = true;
        while (selectingMoreLots) {

            String userLot = guiController.getUserLot(currentPlayer, updatedDeedList, actionType);
            selectedLots.add(getDeedFromName(userLot, currentPlayer));
            updatedDeedList.remove(getDeedFromName(userLot, currentPlayer));
            if (updatedDeedList.size() == 0) {
                selectingMoreLots = false;
            } else {
                selectingMoreLots = guiController.getUserBoolean(msg.getText("selectMoreLots"));
            }
        }

        Deed_Buildable[] selected = new Deed_Buildable[selectedLots.size()];
        return selectedLots.toArray(selected);
    }

    private int getHouseCount(String action) {
        String[] countOptions = {"1", "2", "3", "4"};
        String userHouseCount;
        if(action.equals("build")) {
            userHouseCount = guiController.getUserSelection(msg.getText("howManyBuildings"), countOptions);
        } else { userHouseCount = guiController.getUserSelection(msg.getText("howManyBuildingsDemo"),countOptions);
        }
        int houseCount = Integer.parseInt(userHouseCount);
        System.out.println("House count: " + houseCount);

        return houseCount;
    }

    private void setHouseCountForTesting(Player currentPlayer, int houseCountForTesting) {

        Deed_Buildable[] deeds = currentPlayer.getBuildableDeeds();
        currentPlayer.buyHouse(deeds,houseCountForTesting);
    }

    private String[] setDemolishButtons(int i) {
        String[] demolishButtons;
        boolean hasHouses = false;
        boolean hasHotels = false;
        Deed_Buildable[] deeds = currentPlayer.getBuildableDeeds();
        for (int j = 0; j < deeds.length; j++) {
            if (deeds[j].getHouseCount() > 0) {
                hasHouses = true;
                break;
            } else if (deeds[j].hasHotel()) {
                hasHotels = true;
            }
        }
        if(hasHouses && hasHotels) {
            demolishButtons = new String[2];
            demolishButtons[0] = "Hus";
            demolishButtons[1] = "Hotel";

        } else if (hasHouses && !hasHotels) {
            demolishButtons = new String[1];
            demolishButtons[0] = "Hus";
        } else if (!hasHouses && hasHotels) {
            demolishButtons = new String[1];
            demolishButtons[0] = "Hotel";
        } else {
            System.out.println("Player has no buildings");
            demolishButtons = new String[0];}

        return demolishButtons;
    }

    private String[] setBuildButtons(int i) {
        String[] buildButtons;
        boolean hasFourHouses = false;
        Deed_Buildable[] deeds = currentPlayer.getBuildableDeeds();
        for (int j = 0; j < deeds.length; j++) {

            if (deeds[j].getHouseCount() == 4) {
                hasFourHouses = true;
            }
        }

        if(deeds.length > 0) {
            if(hasFourHouses) {
                buildButtons = new String[2];
                buildButtons[0] = "Hus";
                buildButtons[1] = "Hotel";
            } else {
                buildButtons = new String[1];
                buildButtons[0] = "Hus";
            }
        } else {buildButtons = new String[0];
            System.out.println("Player does not owns lots");}
        return buildButtons;

    }
}
