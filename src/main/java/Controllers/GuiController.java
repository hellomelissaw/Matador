package Controllers;
import GameComponents.Die;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
 public class GuiController {

     GUI gui;
     //TextReader reader = new TextReader(DanskTekst);
     GUI_Field[] fields = new GUI_Field[24];


     Die die1 = new Die();
     Die die2 = new Die();
     int diceSum = 0;

    String message;
    int[] player;
    GUI_Player gui_player;
    GUI_Player[] gui_players;
    private String playerName;

    private String ownerName = "Available";
    private int balance;
    GUI_Ownable[] ownable = new GUI_Ownable[24];

    GUI_Player owner;

    public GuiController() {
         fields = new GUI_Field[]{
                 new GUI_Start(),
                 new GUI_Street(),
                 new GUI_Street(),
                 new GUI_Chance(),
                 new GUI_Street(),
                 new GUI_Street(),
                 new GUI_Jail(),
                 new GUI_Street(),
                 new GUI_Street(),
                 new GUI_Chance(),
                 new GUI_Street(),
                 new GUI_Street(),
                 new GUI_Street(),
                 new GUI_Street(),
                 new GUI_Street(),
                 new GUI_Chance(),
                 new GUI_Street(),
                 new GUI_Street(),
                 new GUI_Street(),
                 new GUI_Street(),
                 new GUI_Street(),
                 new GUI_Chance(),
                 new GUI_Street(),
                 new GUI_Street(),
         };

        this.gui = new GUI(fields);

        gui.getFields()[0].setTitle("Start");
        gui.getFields()[0].setBackGroundColor(Color.yellow);

        gui.getFields()[1].setTitle("Burgerbaren");
        gui.getFields()[1].setBackGroundColor(Color.PINK);
        ownable[1] = (GUI_Ownable)fields[1];
        ownable[1].setSubText("M1");



        gui.getFields()[2].setTitle("Pizzahuset");
        gui.getFields()[2].setBackGroundColor(Color.red);
        ownable[2] = (GUI_Ownable)fields[2];
        ownable[2].setSubText("M1");

        gui.getFields()[3].setBackGroundColor(Color.MAGENTA);

        gui.getFields()[4].setTitle("Slikbuttiken");
        gui.getFields()[4].setBackGroundColor(Color.ORANGE);
        ownable[4] = (GUI_Ownable)fields[4];
        ownable[4].setSubText("M1");

        gui.getFields()[5].setTitle("Iskiosken");
        gui.getFields()[5].setBackGroundColor(Color.blue);
        ownable[5] = (GUI_Ownable)fields[5];
        ownable[5].setSubText("M1");

        gui.getFields()[6].setTitle("I fengsel");
        gui.getFields()[6].setBackGroundColor(Color.gray);
        gui.getFields()[6].setSubText("På besøg!");

        gui.getFields()[7].setTitle("Museet");
        gui.getFields()[7].setBackGroundColor(Color.PINK);
        ownable[7] = (GUI_Ownable)fields[7];
        ownable[7].setSubText("M2");

        gui.getFields()[8].setTitle("Biblioteket");
        gui.getFields()[8].setBackGroundColor(Color.orange);
        ownable[8] = (GUI_Ownable)fields[8];
        ownable[8].setSubText("M2");

        gui.getFields()[9].setBackGroundColor(Color.magenta);

        gui.getFields()[10].setTitle("Skateparken");
        gui.getFields()[10].setBackGroundColor(Color.CYAN);
        ownable[10] = (GUI_Ownable)fields[10];
        ownable[10].setSubText("M2");

        gui.getFields()[11].setTitle("Swimmingpoolen");
        gui.getFields()[11].setBackGroundColor(Color.orange);
        ownable[11] = (GUI_Ownable)fields[11];
        ownable[11].setSubText("M2");

        gui.getFields()[12].setTitle("Parkering");
        gui.getFields()[12].setBackGroundColor(Color.darkGray);
        gui.getFields()[12].setSubText("Gratis");

        gui.getFields()[13].setTitle("Spillehallen");
        gui.getFields()[13].setBackGroundColor(Color.orange);
        ownable[13] = (GUI_Ownable)fields[13];
        ownable[13].setSubText("M3");

        gui.getFields()[14].setTitle("Biografen");
        gui.getFields()[14].setBackGroundColor(Color.pink);
        ownable[14] = (GUI_Ownable)fields[14];
        ownable[14].setSubText("M3");

        gui.getFields()[15].setBackGroundColor(Color.magenta);

        gui.getFields()[16].setTitle("Legetøjsbutikken");
        gui.getFields()[16].setBackGroundColor(Color.orange);
        ownable[16] = (GUI_Ownable)fields[16];
        ownable[16].setSubText("M3");

        gui.getFields()[17].setTitle("Dyrebutikken");
        gui.getFields()[17].setBackGroundColor(Color.pink);
        ownable[17] = (GUI_Ownable)fields[17];
        ownable[17].setSubText("M3");

        gui.getFields()[18].setTitle("I fengsel");
        gui.getFields()[18].setBackGroundColor(Color.gray);
        gui.getFields()[18].setSubText("Gå i fengsel!");

        gui.getFields()[19].setTitle("Bowlinghallen");
        gui.getFields()[19].setBackGroundColor(Color.blue);
        ownable[19] = (GUI_Ownable)fields[19];
        ownable[19].setSubText("M4");

        gui.getFields()[20].setTitle("Zoologisk Have");
        gui.getFields()[20].setBackGroundColor(Color.PINK);
        ownable[20] = (GUI_Ownable)fields[20];
        ownable[20].setSubText("M4");


        gui.getFields()[21].setBackGroundColor(Color.MAGENTA);

        gui.getFields()[22].setTitle("Vandlandet");
        gui.getFields()[22].setBackGroundColor(Color.CYAN);
        ownable[22] = (GUI_Ownable)fields[22];
        ownable[22].setSubText("M5");

        gui.getFields()[23].setTitle("Strand");
        gui.getFields()[23].setBackGroundColor(Color.orange);
        ownable[23] = (GUI_Ownable)fields[23];
        ownable[23].setSubText("M5");

        gui.getUserButtonPressed("Press Ok to start!");
        gui.showMessage("Welcome to Monopoly Junior game.");


    }

     public void showMessage(String message){
        gui.showMessage(message);
    }

     public GUI_Player[] addPlayerOnBoard(GameComponents.player[] list){
         gui_players = new GUI_Player[list.length];
         for (int i = 0; i < list.length; i++) {
             gui_players[i] = new GUI_Player(list[i].getPlayerName(),list[i].getCurrentBalance());
             fields[0].setCar(gui_players[i],true);
             gui.addPlayer(gui_players[i]);
         }
         return gui_players;
     }

     public void move(GUI_Player currentPlayer, int currentPositionIndex, int newPosition){

        fields[currentPositionIndex].setCar(currentPlayer,false);
        fields[newPosition].setCar(currentPlayer,true);

     }

     public void displayOwnerName(GameComponents.player currentPlayer, int currentPositionIndex){
         ownable[currentPositionIndex].setOwnerName(currentPlayer.getPlayerName());

     }
     public void updateBalance(GUI_Player currentPlayer, int newBalance){
         currentPlayer.setBalance(newBalance);
     }
     public void receiveRent(String ownerName, int newBalance) {
         for(int i = 0; i < gui_players.length; i++){
             if(ownerName == gui_players[i].getName()) {
                 gui_players[i].setBalance(newBalance);
                 break;
             }
         }
     }
     public int getUserInteger() {
        return gui.getUserInteger("Enter players count:");
     }
     public String getUserString(){
        return gui.getUserString("");
     }
    public void setDice(int die1, int die2){
        gui.setDice(die1,die2);
    }

 }
