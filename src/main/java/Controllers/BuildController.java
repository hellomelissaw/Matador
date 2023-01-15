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

    public BuildController(GuiController guiController, Text msg) {
        this.guiController = guiController;
        this.msg = msg;

    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void build() {
        Deed_Buildable[] selectedLotsArr = selectLots();

        String[] buildOptions = {"Hus", "Hotel"};
        String buildingType = guiController.getUserSelection(msg.getText("houseOrHotel"), buildOptions);

        if (buildingType.equals("Hus")) {
            int houseCount = getHouseCount("build");
            currentPlayer.buyHouse(selectedLotsArr, houseCount);

        } else {
            if (testingHouseCount) {
                setHouseCountForTesting(currentPlayer, 4);
            }

            currentPlayer.buyHotel(selectedLotsArr);

        }
    }

    public void demolish() {
        Deed_Buildable[] selectedLotsArr = selectLots();

        String[] demolishOptions = {"Hus", "Hotel"};
        String buildingType = guiController.getUserSelection(msg.getText("houseOrHotelDemo"), demolishOptions);

        if (buildingType.equals("Hus")) {
            int houseCount = getHouseCount("demolish");
            currentPlayer.sellHouseToBank(selectedLotsArr, houseCount);

        } else {
            if (testingHouseCount) {
                setHouseCountForTesting(currentPlayer, 4);
            }

            currentPlayer.sellHotelToBank(selectedLotsArr);

        }
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

    private Deed_Buildable[] selectLots() {
        ArrayList<Deed_Buildable> updatedDeedList = new ArrayList<Deed_Buildable>();
        Deed_Buildable[] playerDeeds = currentPlayer.getBuildableDeeds();
        for (int j = 0; j < currentPlayer.getBuildableDeeds().length; j++) {
            updatedDeedList.add(playerDeeds[j]);
        }

        ArrayList<Deed_Buildable> selectedLots = new ArrayList<Deed_Buildable>();

        boolean selectingMoreLots = true;
        while (selectingMoreLots) {

            String userLot = guiController.getUserLot(currentPlayer, updatedDeedList);
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
}
