package GameComponents.Board;
import Controllers.GuiController;
import GameComponents.Player;

public class ChanceSquare extends Square{
    boolean testing = false;
    private int cardIndex;
    private GuiController guiController;
    Player[] players;

    private ChanceCard[] chanceCards = new ChanceCard[46];

    public ChanceSquare(String squareName, GuiController guiController, Player[] players) { //INITIALISES DECK OF CHANCE CARDS

        super(squareName);
        this.guiController = guiController;
        this.players = players;


        chanceCards[0] = new CardMoney("chance1", guiController, "withdraw", 500); //betal 500 kr pr hus og 2000 pr hotel (venter på mels)
        chanceCards[1] = new CardMoney("chance2", guiController, "withdraw", 800); //betal 800 kr pr hus og 2300 pr hotel (venter på mels)
        chanceCards[2] = new CardMoney("chance3", guiController, "withdraw", 1000);
        chanceCards[3] = new CardMoney("chance4", guiController, "withdraw", 300);
        chanceCards[4] = new CardMoney("chance5", guiController, "withdraw", 200);
        chanceCards[5] = new CardMoney("chance6", guiController, "withdraw", 3000);
        chanceCards[6] = new CardMoney("chance7", guiController, "withdraw", 3000);
        chanceCards[7] = new CardMoney("chance8", guiController, "withdraw", 1000);
        chanceCards[8] = new CardMoney("chance9", guiController, "withdraw", 200);
        chanceCards[9] = new CardMoney("chance10", guiController, "withdraw", 1000);
        chanceCards[10] = new CardMoney("chance11", guiController, "withdraw", 200);
        chanceCards[11] = new CardMoney("chance12", guiController, "withdraw", 2000);
        chanceCards[12] = new CardMoney("chance13", guiController, "deposit", 500);
        chanceCards[13] = new CardMoney("chance14", guiController, "deposit", 500);
        chanceCards[14] = new CardMoney("chance15", guiController, "deposit", 1000);
        chanceCards[15] = new CardMoney("chance16", guiController, "deposit", 1000);
        chanceCards[16] = new CardMoney("chance17", guiController, "deposit", 1000);
        chanceCards[17] = new CardMoney("chance18", guiController, "deposit", 3000);
        chanceCards[18] = new CardMoney("chance19", guiController, "deposit", 1000);
        chanceCards[19] = new CardMoney("chance20", guiController, "deposit", 1000);
        chanceCards[20] = new CardMoney("chance21", guiController, "deposit", 1000);
        chanceCards[21] = new CardMoney("chance22", guiController, "deposit", 1000);
        chanceCards[22] = new CardMoney("chance23", guiController, "deposit", 1000);
        chanceCards[23] = new CardMoney("chance24", guiController, "deposit", 200);
        chanceCards[24] = new CardMoney("chance25", guiController, "deposit", 40000); //modtag fra banken et matador legat på 40.000 kun hvis værdier ikke overstiger 15.000 (spørger indtil de 15000)
        chanceCards[25] = new CardMoney("chance26", guiController, "hybrid", 200);
        chanceCards[26] = new CardMoney("chance27", guiController, "hybrid", 500);
        chanceCards[27] = new CardMoney("chance28", guiController, "hybrid", 500);
        chanceCards[28] = new CardMove("chance29", guiController, 0,"index");
        chanceCards[29] = new CardMove("chance30", guiController, 0,"index");
        chanceCards[30] = new CardMove("chance31", guiController,3, "distance");
        chanceCards[31] = new CardMove("chance32", guiController,-3, "distance");
        chanceCards[32] = new CardMove("chance33", guiController,-3, "distance");
        chanceCards[33] = new CardMove("chance34", guiController, 11,"index");
        chanceCards[34] = new CardDeed("chance35", guiController);
        chanceCards[35] = new CardDeed("chance36", guiController);
        chanceCards[36] = new CardMove("chance37", guiController, 15,"index"); //Tag med Mols-Linjen, flyt brikken frem og hvis De passerer START indkassér da kr 4000. (test)
        chanceCards[37] = new CardMove("chance38", guiController, 24,"index");
        chanceCards[38] = new CardMove("chance39", guiController, 32,"index");
        chanceCards[39] = new CardMove("chance40", guiController, 5,"index"); // Tag med den nærmeste færge, hvis de passerer start indkasser da kr 4000 (felter 5, 25, 35) (waiting on the ferry square)
        chanceCards[40] = new CardMove("chance41", guiController, 19,"index");
        chanceCards[41] = new CardMove("chance42", guiController, 39,"index");
        chanceCards[42] = new CardMove("chance43", guiController, 30,"index"); // I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det. (waiting on updated jailsquare)
        chanceCards[43] = new CardMove("chance44", guiController, 30,"index"); // I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det. (waiting on updated jailsquare)
        chanceCards[44] = new CardMove("chance45", guiController, 30,"index"); // Gå i fængsel, De indkasserer ikke 4000 kr for at passere start. (test)
        chanceCards[45] = new CardMove("chance46", guiController, 30,"index"); // Gå i fængsel, De indkasserer ikke 4000 kr for at passere start. (test)

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
