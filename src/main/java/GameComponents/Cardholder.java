package GameComponents;

import GameComponents.Board.Deed;

import java.util.ArrayList;

public class Cardholder {
    boolean ownerStatus;

    ArrayList<Deed> deedCards = new ArrayList<Deed>();

    public void addCard(String cardType, Deed deed) {
        if(cardType.equals("deed")) {
            deedCards.add(deed);
        }

    }
    public boolean getOwnerStatus(String color) {
        int groupSize = 0;
        boolean ownerStatus = false;
        int cardCount = 0;

        for(Deed deed : deedCards) {
            System.out.println(deed.getDeedName());
            if(deed.getColor().equals(color)) {
                groupSize = deed.getGroupSize();
                cardCount++;
            }
        }

        if (cardCount == groupSize) {
            ownerStatus = true;
        }

        return ownerStatus;
    }

    public Deed[] getDeedList() {
        Deed[] deedCardsArray = new Deed[deedCards.size()];
        deedCardsArray = deedCards.toArray(deedCardsArray);
        return deedCardsArray;
    }

    public boolean ownsGroup(String color){
        int groupSize = 0;
        boolean ownsGroup = false;
        int cardCount = 0;
        for(Deed deed : deedCards) {
            if(deed.getColor().equals(color)) {
                groupSize = deed.getGroupSize();
                cardCount++;
            }
        }

        if (cardCount == groupSize) {
            ownsGroup = true;
        }
        return ownsGroup;
    }

    public boolean houseCountIsLevel(String color) {
        boolean countIsLevel = false;
        ArrayList<Deed> deedsInGroup = new ArrayList<Deed>();
        for(Deed deed: deedCards) {
            if (deed.getColor().equals(color)){
                deedsInGroup.add(deed);
            }
        }

        for(Deed deed: deedsInGroup) {

        }
        return countIsLevel;
    }
}
