package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class ChanceSquare extends Square{
    boolean testing = false;
    private int cardIndex;
    private GuiController guiController;
    Player[] players;

    private ChanceCard[] chanceCards = new ChanceCard[15];

    public ChanceSquare(String squareName, GuiController guiController, Player[] players) { //INITIALISES DECK OF CHANCE CARDS

        super(squareName);
        this.guiController = guiController;
        this.players = players;
        chanceCards[0] = new CardMove("chance1", guiController, 0,"index");
        chanceCards[1] = new CardMove("chance2", guiController,5, "distance");
        chanceCards[2] = new CardMove("chance3", guiController,1, "distance");
        chanceCards[3] = new CardMove("chance4", guiController,23, "index");
        chanceCards[4] = new CardMoney("chance5", guiController, players);
        chanceCards[5] = new CardMoney("chance6", guiController, players);
        chanceCards[6] = new CardMoney("chance7", guiController, players);
        chanceCards[7] = new CardDeed("chance8", guiController, "na","na");
        chanceCards[8] = new CardDeed("chance9", guiController, "cyan", "red");
        chanceCards[9] = new CardDeed("chance10", guiController, "lightgrey", "yellow");
        chanceCards[10] = new CardDeed("chance11", guiController, "orange", "na");
        chanceCards[11] = new CardDeed("chance12", guiController, "cyan", "na");
        chanceCards[12] = new CardDeed("chance13", guiController, "red", "na");
        chanceCards[13] = new CardDeed("chance14", guiController, "orange", "green");
        chanceCards[14] = new CardDeed("chance15", guiController, "pink", "darkblue");


    }

    public void setBoard(Square[] board) {
        for (int i = 0; i < chanceCards.length; i++) {
                chanceCards[i].setBoard(board);
        }
    }

    public void setChanceCards(Square[] board) {

        for (int i = 0 ; i < chanceCards.length ; i++) { //SETS PLAYERS ARRAY FOR EACH CARD
            chanceCards[i].setBoard(board);
            chanceCards[i].setPlayers(players);
            chanceCards[i].setOptionsArr();

        }

    }

    public void setCardLang() {
        for (int i = 0 ; i < chanceCards.length ; i++) { //SETS LANGUAGE FOR EACH CARD
            chanceCards[i].setCardLang(msg);

        }
    }

    public void isTesting(boolean testing, int testIndex) {
        this.testing = testing;
        cardIndex = testIndex;
    }

    public void landOn(Player currentPlayer) {
        boolean pickAgain = true;
        while(pickAgain){

            if(!testing){
                cardIndex = (int) (Math.random() * (15 - 1));
            }
            chanceCards[cardIndex].printMessage(cardIndex);
            System.out.println("Card picked: " + cardIndex);
            chanceCards[cardIndex].playCard(currentPlayer);
            pickAgain = chanceCards[cardIndex].checkPickAgain();
        }
    }
}
