package GameComponents;

import GameComponents.Board.Deed;
import GameComponents.Board.Deed_Buildable;
import GameComponents.Board.Deed_NonBuildable;

import java.util.ArrayList;

public class Cardholder {

    ArrayList<Deed_Buildable> deedCards_Buildable = new ArrayList<Deed_Buildable>();
    ArrayList<Deed_NonBuildable> deedCards_NonBuildable = new ArrayList<Deed_NonBuildable>();
    ArrayList<Deed> deedCards_Properties = new ArrayList<Deed>();
    boolean isOwner = false;

    public void addDeed_Buildable(Deed_Buildable deed) {
        deedCards_Buildable.add(deed);
    }

    public void removeDeed_Buildable(Deed_Buildable deed) {
        deedCards_Buildable.remove(deed);
    }

    public void addDeedNon_Buildable(Deed_NonBuildable deed) {
        deedCards_NonBuildable.add(deed);
    }

    public void removeDeed_NonBuildable(Deed_NonBuildable deed) {
        deedCards_NonBuildable.remove(deed);

    }
    public void addProperties (Deed_Buildable deedBuildable , Deed_NonBuildable deedNonBuildable){
         deedCards_Properties.add(deedBuildable) ;
         deedCards_Properties.add(deedNonBuildable);

    }
    public boolean getOwnerStatus(String color) {
        int groupSize = 0;
        int cardCount = 0;

        for(Deed_Buildable deed : deedCards_Buildable) {
            if(deed.getColor().equals(color)) {
                groupSize = deed.getGroupSize();
                cardCount++;
            }
        }

        if (cardCount == groupSize) {
            isOwner = true;
        }

        return isOwner;

    }

    public void setOwnerOfGroup() {
        isOwner = true;
    }

    public Deed_NonBuildable[] getNonBuildable() {
        Deed_NonBuildable[] deedCardsArray = new  Deed_NonBuildable[deedCards_NonBuildable.size()];
        deedCardsArray = deedCards_NonBuildable.toArray(deedCardsArray);
        return deedCardsArray;
    }

    public Deed_Buildable[] getBuildable() {
        Deed_Buildable[] deedCardsArray = new  Deed_Buildable[deedCards_Buildable.size()];
        deedCardsArray = deedCards_Buildable.toArray(deedCardsArray);
        return deedCardsArray;
    }
    public Deed[] getProperties(){
        Deed[] deedCardsArray = new Deed[deedCards_Buildable.size()+deedCards_NonBuildable.size()];
        deedCardsArray = deedCards_Properties.toArray(deedCardsArray);
        return deedCardsArray;
    }

    public int getPropertyCount () {
        return deedCards_Buildable.size()+deedCards_NonBuildable.size();
    }

    public boolean houseCountIsLevel(String color, Deed_Buildable deed) {
        boolean countIsLevel = false;
        int newCount = deed.getHouseCount() + 1;

        for(int i = 0 ; i < deedCards_Buildable.size() ; i++) {
            if(deedCards_Buildable.get(i).getColor().equals(color)) {
                if ((newCount - deedCards_Buildable.get(i).getHouseCount()) <= 1) {
                    countIsLevel = true;

                } else {
                    countIsLevel = false;
                    break;
                }
            }
        }
        return countIsLevel;
    }


}
