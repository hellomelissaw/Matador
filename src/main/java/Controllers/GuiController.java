package Controllers;
import GameComponents.Board.Square;
import GameComponents.Player;
import Translator.Text;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

import static java.awt.Color.blue;

public class GuiController {
     private GUI gui;
     Text msg;
     private GUI_Field[] fields   ;
     GUI_Ownable[] ownable = new GUI_Ownable[40];

    private GUI_Player[] guiPlayers;
    private int squareIndex = 0;

   public GuiController() {

       fields = new GUI_Field[]{
               new GUI_Start(),
               new GUI_Street(),
               new GUI_Chance(),
               new GUI_Street(),
               new GUI_Tax(),
               new GUI_Shipping(),
               new GUI_Street(),
               new GUI_Chance(),
               new GUI_Street(),
               new GUI_Street(),
               new GUI_Jail(),
               new GUI_Street(),
               new GUI_Brewery(),
               new GUI_Street(),
               new GUI_Street(),
               new GUI_Shipping(),
               new GUI_Street(),
               new GUI_Chance(),
               new GUI_Street(),
               new GUI_Street(),
               new GUI_Refuge(),
               new GUI_Street(),
               new GUI_Chance(),
               new GUI_Street(),
               new GUI_Street(),
               new GUI_Shipping(),
               new GUI_Street(),
               new GUI_Street(),
               new GUI_Brewery(),
               new GUI_Street(),
               new GUI_Jail(),
               new GUI_Street(),
               new GUI_Street(),
               new GUI_Chance(),
               new GUI_Street(),
               new GUI_Shipping(),
               new GUI_Chance(),
               new GUI_Street(),
               new GUI_Tax(),
               new GUI_Street()

       };
        Color back = new Color(64, 153, 255);
       this.gui = new GUI(fields,back);

       gui.getFields()[0].setTitle("START");
       gui.getFields()[0].setBackGroundColor(Color.red);
       gui.getFields()[0].setSubText(" ");

       gui.getFields()[1].setTitle("Rødorvrevej");
       gui.getFields()[1].setBackGroundColor(Color.cyan);
       ownable[1] = (GUI_Ownable)fields[1];
       ownable[1].setSubText("1200");



       gui.getFields()[2].setBackGroundColor(Color.green);
       gui.getFields()[2].setSubText("Prøv Lykken");


       gui.getFields()[3].setTitle("Hvidovrevej");
       gui.getFields()[3].setBackGroundColor(Color.cyan);
       gui.getFields()[3].setSubText("1200");
       ownable[3] = (GUI_Ownable)fields[3];
       //ownable[3].setSubText("1200");

       gui.getFields()[4].setTitle("Betale indkomst skat:");
       gui.getFields()[4].setBackGroundColor(Color.gray);
       gui.getFields()[4].setSubText("10% eller 4000 kr.");

       gui.getFields()[5].setTitle("Scandlines");
       gui.getFields()[5].setBackGroundColor(Color.white);
       gui.getFields()[5].setSubText("4000 kr");

       gui.getFields()[6].setTitle("Roskildevej");
       gui.getFields()[6].setBackGroundColor(Color.orange);
       ownable[6] = (GUI_Ownable)fields[6];
       ownable[6].setSubText("2000 kr");


       gui.getFields()[7].setBackGroundColor(Color.green);
       gui.getFields()[7].setSubText("Prøv Lykken");

       gui.getFields()[8].setTitle("Valby Langgade");
       gui.getFields()[8].setBackGroundColor(Color.orange);
       ownable[8] = (GUI_Ownable)fields[8];
       ownable[8].setSubText("2000 kr");

       gui.getFields()[9].setTitle("Allegade");
       gui.getFields()[9].setBackGroundColor(Color.orange);
       ownable[9] = (GUI_Ownable)fields[9];
       ownable[9].setSubText("2400 kr");

       gui.getFields()[10].setTitle("I fængsel");
       gui.getFields()[10].setSubText("På besøg");

       gui.getFields()[11].setTitle("Fredriksberg Alle");
       gui.getFields()[11].setBackGroundColor(Color.yellow);
       ownable[11] = (GUI_Ownable)fields[11];
       ownable[11].setSubText("2800 kr");

       gui.getFields()[12].setTitle("Sqush");
       gui.getFields()[12].setBackGroundColor(Color.orange);
       gui.getFields()[12].setSubText("3000 kr");

       gui.getFields()[13].setTitle("Bullowsvej");
       gui.getFields()[13].setBackGroundColor(Color.yellow);
       ownable[13] = (GUI_Ownable)fields[13];
       ownable[13].setSubText("2800 kr");

       gui.getFields()[14].setTitle("Gl. Kongevej");
       gui.getFields()[14].setBackGroundColor(Color.yellow);
       ownable[14] = (GUI_Ownable)fields[14];
       ownable[14].setSubText("3200 kr");

       gui.getFields()[15].setTitle("Mols-Linien");
       gui.getFields()[15].setBackGroundColor(Color.white);
       gui.getFields()[15].setSubText("4000 kr");

       gui.getFields()[16].setTitle("Bernstorffsvej");
       gui.getFields()[16].setBackGroundColor(Color.gray);
       ownable[16] = (GUI_Ownable)fields[16];
       ownable[16].setSubText("3600 kr");


       gui.getFields()[17].setBackGroundColor(Color.green);
       gui.getFields()[17].setSubText("Prøv Lykken");

       gui.getFields()[18].setTitle("Hellerupvej");
       gui.getFields()[18].setBackGroundColor(Color.gray);
       ownable[18] = (GUI_Ownable)fields[18];
       ownable[18].setSubText("3600 kr");

       gui.getFields()[19].setTitle("Strandvejen");
       gui.getFields()[19].setBackGroundColor(Color.gray);
       ownable[19] = (GUI_Ownable)fields[19];
       ownable[19].setSubText("4000 kr");


       gui.getFields()[20].setSubText("Parkering!");
       gui.getFields()[20].setBackGroundColor(Color.white);

       gui.getFields()[21].setTitle("Trianglen");
       gui.getFields()[21].setBackGroundColor(Color.red);
       ownable[21] = (GUI_Ownable)fields[21];
       ownable[21].setSubText("4400 kr");

       gui.getFields()[22].setBackGroundColor(Color.green);
       gui.getFields()[22].setSubText("Prøv Lykken");

       gui.getFields()[23].setTitle("Østerbrogade");
       gui.getFields()[23].setBackGroundColor(Color.red);
       ownable[23] = (GUI_Ownable)fields[23];
       ownable[23].setSubText("4400 kr");

       gui.getFields()[24].setTitle("Grønningen");
       gui.getFields()[24].setBackGroundColor(Color.red);
       ownable[24] = (GUI_Ownable)fields[24];
       ownable[24].setSubText("4800 kr");

       gui.getFields()[25].setTitle("Scandlines");
       gui.getFields()[25].setBackGroundColor(Color.white);
       gui.getFields()[25].setSubText("4000 kr");



       gui.getFields()[26].setTitle("Bredgade");
       gui.getFields()[26].setBackGroundColor(Color.white);
       ownable[26] = (GUI_Ownable)fields[26];
       ownable[26].setSubText("5200 kr");

       gui.getFields()[27].setTitle("Kgs. Nytorv");
       gui.getFields()[27].setBackGroundColor(Color.white);
       ownable[27] = (GUI_Ownable)fields[27];
       ownable[27].setSubText("5200 kr");

       gui.getFields()[28].setTitle("Coca Cola");
       gui.getFields()[28].setBackGroundColor(Color.orange);
       gui.getFields()[28].setSubText("3000 kr");

       gui.getFields()[29].setTitle("Østergade");
       gui.getFields()[29].setBackGroundColor(Color.white);
       ownable[29] = (GUI_Ownable)fields[29];
       ownable[29].setSubText("5600 kr");

       gui.getFields()[30].setTitle("");
       gui.getFields()[30].setSubText("Gå i fængsel");

       gui.getFields()[31].setTitle("Amagertorv");
       gui.getFields()[31].setBackGroundColor(Color.yellow);
       ownable[31] = (GUI_Ownable)fields[31];
       ownable[31].setSubText("6000 kr");

       gui.getFields()[32].setTitle("vimmelskaffet");
       gui.getFields()[32].setBackGroundColor(Color.yellow);
       ownable[32] = (GUI_Ownable)fields[32];
       ownable[32].setSubText("6000 kr");

       gui.getFields()[33].setBackGroundColor(Color.green);
       gui.getFields()[33].setSubText("Prøv Lykken");

       gui.getFields()[34].setTitle("Nygade");
       gui.getFields()[34].setBackGroundColor(Color.yellow);
       ownable[34] = (GUI_Ownable)fields[34];
       ownable[34].setSubText("6400 kr");

       gui.getFields()[35].setTitle("Scandlines");
       gui.getFields()[35].setBackGroundColor(Color.white);
       gui.getFields()[35].setSubText("4000 kr");

       gui.getFields()[36].setBackGroundColor(Color.green);
       gui.getFields()[36].setSubText("Prøv Lykken");

       gui.getFields()[37].setTitle("Frederiksberggade");
       gui.getFields()[37].setBackGroundColor(Color.pink);
       ownable[37] = (GUI_Ownable) fields[37];
       ownable[37].setSubText("7000 kr");

       gui.getFields()[38].setTitle("Ekstra ordinær statskat:");
       gui.getFields()[38].setBackGroundColor(Color.gray);
       gui.getFields()[38].setSubText("Betal 2000 kr indkomst skat.");

       gui.getFields()[39].setTitle("Rådhuspladsen");
       gui.getFields()[39].setBackGroundColor(Color.pink);
       ownable[39] = (GUI_Ownable)fields[39];
       ownable[39].setSubText("8000 kr");


   }

     public void setLang(Text msg) {
         this.msg = msg;
     }

     public void showMessage(String message){
         gui.showMessage(message);
     }

     /**
      * Sets the titles on the Squares according to language chosen by user
      * @param //msg The Text object used to set user-selected language throughout the program
      */

    public GUI_Player createGuiPlayer(Player player) {
         GUI_Player guiPlayer = new GUI_Player(player.getPlayerName());
         fields[0].setCar(guiPlayer,true);
         gui.addPlayer(guiPlayer);
         System.out.println("Gui Player is set");
         return guiPlayer;
    }

     public GUI_Player[] getGuiPlayersArr() {
         return guiPlayers;
     }

     /**
      * Moves the GUI_car object to a new square for current player
      * @param currentPlayer current GUI_Player to be moved
      * @param currentPositionIndex index of Square GUI_Player is located on, so their car can stop being displayed there
      * @param newPosition new index of Square to display car on after removing from previous square
      */
     public void move(GUI_Player currentPlayer, int currentPositionIndex, int newPosition){

        fields[currentPositionIndex].setCar(currentPlayer,false);
        fields[newPosition].setCar(currentPlayer,true);

     }

     /**
      * Sets name of owner of a deed when buying it and displays name in center square when clicking on DeedSquare
      * @param currentPlayer current Player buying the deed
      * @param currentPositionIndex index of the Deed Square being bought
      */
     public void setOwnerName(Player currentPlayer, int currentPositionIndex){
         if (fields[currentPositionIndex] instanceof GUI_Ownable ){
             ((GUI_Ownable)fields[currentPositionIndex]).setOwnerName(currentPlayer.getPlayerName());
         }

     }

     public int getUserInteger(String message) {
         return gui.getUserInteger(message);

     }
    /* String userSelection;
     Square selectedSquare;
     public Square getSelectedSquare(Square[] currentColorsArr) {

         String[] currentColorSqrName = new String[currentColorsArr.length];
         for(int i = 0 ; i < currentColorsArr.length ; i++){
             currentColorSqrName[i] = currentColorsArr[i].getSquareName();

         }
        userSelection = gui.getUserSelection(msg.getText("prompt"), currentColorSqrName);

        for (int i = 0; i < currentColorsArr.length ; i++) {
            if (userSelection == currentColorsArr[i].getSquareName()) {
                selectedSquare = currentColorsArr[i];

            }
        }
        return selectedSquare;
     }*/

     /*public int getSquareIndex(Square[] board) {
         int index = 0;
         for(int i = 0; i < board.length ; i++) {
             if(selectedSquare.getSquareName() == board[i].getSquareName()){
                 index = i;
             }
         }
         return index;
     }*/
     public String getUserSelection(String message, String[] buttons) {
         return gui.getUserSelection(message, buttons);
     }

     public String getUserString( String message , int currentPlayer){
        return gui.getUserString(msg.getText(message) + " " + currentPlayer);

     }

    public void setDice(int die1, int die2){
        gui.setDice(die1,die2);
    }

    public  String getUserButtonPressed(){
        return gui.getUserButtonPressed("Vil du salge noget?" , "Ja" , "Nej");
    }

    public String getUserSelection(String userSelection){
         return gui.getUserSelection("Hvilken ground vil du salge?");
    }

 }
