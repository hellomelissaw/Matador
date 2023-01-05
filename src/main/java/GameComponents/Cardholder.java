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
        return ownerStatus;
    }

    public Deed[] getDeedList() {
        Deed[] deedCardsArray = new Deed[deedCards.size()];
        deedCardsArray = deedCards.toArray(deedCardsArray);
        return deedCardsArray;
    }
}
