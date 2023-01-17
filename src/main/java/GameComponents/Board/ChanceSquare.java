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

// definition af chancecards
        chanceCards[0] = new CardMoney("chance1", guiController, "withdraw", 500, 2000); //1. Oliepriserne er steget, og De skal betale kr 500 pr hus og kr 2000 pr hotel.
        chanceCards[1] = new CardMoney("chance2", guiController, "withdraw", 800, 2300); //2. Ejendomsskatten er steget. Ekstraudgifterne er: 800 kr pr hus, 2300 kr pr hotel.
        chanceCards[2] = new CardMoney("chance3", guiController, "withdraw", 1000); //3. De har kørt frem for “fuldt stop”, Betal 1000 kroner i bøde.
        chanceCards[3] = new CardMoney("chance4", guiController, "withdraw", 300); // 4. Betal for vognvask og smøring kr 300.
        chanceCards[4] = new CardMoney("chance5", guiController, "withdraw", 200); // 5. Betal kr 200 for levering af 2 kasser øl.
        chanceCards[5] = new CardMoney("chance6", guiController, "withdraw", 3000); // 6. Betal 3000 for reparation af deres vogn.
        chanceCards[6] = new CardMoney("chance7", guiController, "withdraw", 3000); // 7. Betal 3000 for reparation af deres vogn.
        chanceCards[7] = new CardMoney("chance8", guiController, "withdraw", 1000); // 8. De har købt 4 nye dæk til Deres vogn, betal kr 1000.
        chanceCards[8] = new CardMoney("chance9", guiController, "withdraw", 200); // 9. De har fået en parkeringsbøde, betal kr 200 i bøde.
        chanceCards[9] = new CardMoney("chance10", guiController, "withdraw", 1000); // 10. Betal deres bilforsikring, kr 1000.
        chanceCards[10] = new CardMoney("chance11", guiController, "withdraw", 200); // 11. De har været udenlands og købt for mange smøger, betal kr 200 i told.
        chanceCards[11] = new CardMoney("chance12", guiController, "withdraw", 2000); // 12. Tandlægeregning, betal kr 2000.
        chanceCards[12] = new CardMoney("chance13", guiController, "deposit", 500); // 13. De har vundet i klasselotteriet. Modtag 500 kr.
        chanceCards[13] = new CardMoney("chance14", guiController, "deposit", 500); // 14. De har vundet i klasselotteriet. Modtag 500 kr.
        chanceCards[14] = new CardMoney("chance15", guiController, "deposit", 1000); // 15. De modtager Deres aktieudbytte. Modtag kr 1000 af banken.
        chanceCards[15] = new CardMoney("chance16", guiController, "deposit", 1000); // 16. De modtager Deres aktieudbytte. Modtag kr 1000 af banken.
        chanceCards[16] = new CardMoney("chance17", guiController, "deposit", 1000); // 17. De modtager Deres aktieudbytte. Modtag kr 1000 af banken.
        chanceCards[17] = new CardMoney("chance18", guiController, "deposit", 3000); // 18. Kommunen har eftergivet et kvartals skat. Hæv i banken 3000 kr.
        chanceCards[18] = new CardMoney("chance19", guiController, "deposit", 1000); // 19. De have en række med elleve rigtige i tipning, modtag kl 1000
        chanceCards[19] = new CardMoney("chance20", guiController, "deposit", 1000); // 20. Grundet dyrtiden har De fået gageforhøjelse, modtag kr 1000.
        chanceCards[20] = new CardMoney("chance21", guiController, "deposit", 1000); // 21. Deres præmieobligation er udtrykket. De modtager 1000 kr af banken.
        chanceCards[21] = new CardMoney("chance22", guiController, "deposit", 1000); // 22. Deres præmieobligation er udtrykket. De modtager 1000 kr af banken.
        chanceCards[22] = new CardMoney("chance23", guiController, "deposit", 1000); // 23. De har solgt nogle gamle møbler på auktion. Modtag 1000 kr af banken.
        chanceCards[23] = new CardMoney("chance24", guiController, "deposit", 200); // 24. Værdien af egen avl fra nyttehaven udgør 200 som de modtager af banken
        chanceCards[24] = new CardMoney("chance25", guiController, "deposit", 40000); // 25. modtag fra banken et matador legat på 40.000 kun hvis værdier ikke overstiger 15.000 (spørger indtil de 15000)
        chanceCards[25] = new CardMoney("chance26", guiController, "hybrid", 200); // 26. Det er deres fødselsdag. Modtag af hver medspiller 200 kr.
        chanceCards[26] = new CardMoney("chance27", guiController, "hybrid", 500); // 27. De har lagt penge ud til et sammenskudsgilde. Mærkværdigvis betaler alle straks. Modtag fra hver medspiller 500 kr.
        chanceCards[27] = new CardMoney("chance28", guiController, "hybrid", 500); // 28. De skal holde familiefest og får et tilskud fra hver medspiller på 500 kr.
        chanceCards[28] = new CardMove("chance29", guiController, 0,"index"); // 29. Ryk frem til START
        chanceCards[29] = new CardMove("chance30", guiController, 0,"index"); // 30. Ryk frem til START
        chanceCards[30] = new CardMove("chance31", guiController,3, "distance"); // 31. Ryk tre felter frem
        chanceCards[31] = new CardMove("chance32", guiController,-3, "distance"); // 32. Ryk tre felter tilbage
        chanceCards[32] = new CardMove("chance33", guiController,-3, "distance"); // 33. Ryk tre felter tilbage
        chanceCards[33] = new CardMove("chance34", guiController, 11,"index"); // 34. Ryk frem til Frederiksberg Allé. Hvis De passere START, indkasser da 4000 kr.
        chanceCards[34] = new CardDeed("chance35", guiController);// 35. Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De selv købe det.
        chanceCards[35] = new CardDeed("chance36", guiController); // 36. Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De selv købe det.
        chanceCards[36] = new CardMove("chance37", guiController, 15,"index"); // 37. Tag med Mols-Linjen, flyt brikken frem og hvis De passerer START indkassér da kr 4000. (test)
        chanceCards[37] = new CardMove("chance38", guiController, 24,"index"); // 38. Ryk frem til Grønningen, hvis De passerer start indkasser da kr 4000
        chanceCards[38] = new CardMove("chance39", guiController, 32,"index"); // 39. Ryk frem til Vimmelskaftet, hvis de passerer start indkasser da kr 4000
        chanceCards[39] = new CardDeed("chance40", guiController); // 40. Tag med den nærmeste færge, hvis de passerer start indkasser da kr 4000 (felter 5, 25, 35) (waiting on the ferry square)
        chanceCards[40] = new CardMove("chance41", guiController, 19,"index"); // 41. Ryk frem til Strandvejen. Hvis De passere START, indkasser da 4000 kr.
        chanceCards[41] = new CardMove("chance42", guiController, 39,"index"); // 42. Tag til Rådhuspladsen
        chanceCards[42] = new CardMove("chance43", guiController, 30,"index"); // 43. I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det. (waiting on updated jailsquare)
        chanceCards[43] = new CardMove("chance44", guiController, 30,"index"); // 44. I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det. (waiting on updated jailsquare)
        chanceCards[44] = new CardMove("chance45", guiController, 30,"index"); // 45. Gå i fængsel, De indkasserer ikke 4000 kr for at passere start. (test)
        chanceCards[45] = new CardMove("chance46", guiController, 30,"index"); // 46. Gå i fængsel, De indkasserer ikke 4000 kr for at passere start. (test)

    }

    public void setChanceCards(Square[] board) {

        for (int i = 0 ; i < chanceCards.length ; i++) { //SETS PLAYERS ARRAY FOR EACH CARD
            chanceCards[i].setBoard(board);
            chanceCards[i].setPlayers(players);

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
                //(int) (Math.random() * (45 - 1));
                cardIndex = 35;
                chanceCards[cardIndex].printMessage(cardIndex);
            }

            System.out.println("Card picked: " + cardIndex);
            chanceCards[cardIndex].playCard(currentPlayer);
            pickAgain = chanceCards[cardIndex].checkPickAgain();
        }
    }
}
