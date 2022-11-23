package Controllers;
import GameComponents.Die;
import GameComponents.Player;
import Translator.Text;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
 public class GuiController {
     private GUI gui;

     Text msg;
     private GUI_Field[] fields = new GUI_Field[24];

    private GUI_Player[] gui_players;

    private GUI_Ownable[] ownable = new GUI_Ownable[24];

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

        gui.getFields()[0].setTitle(" ");
        gui.getFields()[0].setBackGroundColor(Color.white);
        gui.getFields()[0].setSubText(" ");

        gui.getFields()[1].setTitle("");
        gui.getFields()[1].setBackGroundColor(Color.lightGray);
        ownable[1] = (GUI_Ownable)fields[1];
        ownable[1].setSubText("M1");

        gui.getFields()[2].setTitle(" ");
        gui.getFields()[2].setBackGroundColor(Color.lightGray);
        ownable[2] = (GUI_Ownable)fields[2];
        ownable[2].setSubText("M1");

        gui.getFields()[3].setTitle("?");
        gui.getFields()[3].setBackGroundColor(Color.orange);
        gui.getFields()[3].setSubText(" ");

        gui.getFields()[4].setTitle("");
        gui.getFields()[4].setBackGroundColor(Color.cyan);
        ownable[4] = (GUI_Ownable)fields[4];
        ownable[4].setSubText("M1");

        gui.getFields()[5].setTitle("");
        gui.getFields()[5].setBackGroundColor(Color.cyan);
        ownable[5] = (GUI_Ownable)fields[5];
        ownable[5].setSubText("M1");

        gui.getFields()[6].setTitle("");
        gui.getFields()[6].setBackGroundColor(Color.white);
        gui.getFields()[6].setSubText("");

        gui.getFields()[7].setTitle("");
        gui.getFields()[7].setBackGroundColor(Color.pink);
        ownable[7] = (GUI_Ownable)fields[7];
        ownable[7].setSubText("M2");

        gui.getFields()[8].setTitle("");
        gui.getFields()[8].setBackGroundColor(Color.pink);
        ownable[8] = (GUI_Ownable)fields[8];
        ownable[8].setSubText("M2");

        gui.getFields()[9].setTitle("?");
        gui.getFields()[9].setBackGroundColor(Color.orange);
        gui.getFields()[9].setSubText(" ");

        gui.getFields()[10].setTitle("");
        gui.getFields()[10].setBackGroundColor(Color.orange);
        ownable[10] = (GUI_Ownable)fields[10];
        ownable[10].setSubText("M2");

        gui.getFields()[11].setTitle("");
        gui.getFields()[11].setBackGroundColor(Color.orange);
        ownable[11] = (GUI_Ownable)fields[11];
        ownable[11].setSubText("M2");

        gui.getFields()[12].setTitle("");
        gui.getFields()[12].setBackGroundColor(Color.white);
        gui.getFields()[12].setSubText("");

        gui.getFields()[13].setTitle("");
        gui.getFields()[13].setBackGroundColor(Color.red);
        ownable[13] = (GUI_Ownable)fields[13];
        ownable[13].setSubText("M3");

        gui.getFields()[14].setTitle("");
        gui.getFields()[14].setBackGroundColor(Color.red);
        ownable[14] = (GUI_Ownable)fields[14];
        ownable[14].setSubText("M3");

        gui.getFields()[15].setTitle("?");
        gui.getFields()[15].setBackGroundColor(Color.orange);
        gui.getFields()[15].setSubText(" ");

        gui.getFields()[16].setTitle("");
        gui.getFields()[16].setBackGroundColor(Color.yellow);
        ownable[16] = (GUI_Ownable)fields[16];
        ownable[16].setSubText("M3");

        gui.getFields()[17].setTitle("");
        gui.getFields()[17].setBackGroundColor(Color.yellow);
        ownable[17] = (GUI_Ownable)fields[17];
        ownable[17].setSubText("M3");

        gui.getFields()[18].setTitle("");
        gui.getFields()[18].setBackGroundColor(Color.white);
        gui.getFields()[18].setSubText("");

        gui.getFields()[19].setTitle("");
        gui.getFields()[19].setBackGroundColor(Color.green);
        ownable[19] = (GUI_Ownable)fields[19];
        ownable[19].setSubText("M4");

        gui.getFields()[20].setTitle("");
        gui.getFields()[20].setBackGroundColor(Color.green);
        ownable[20] = (GUI_Ownable)fields[20];
        ownable[20].setSubText("M4");

        gui.getFields()[21].setTitle("?");
        gui.getFields()[21].setBackGroundColor(Color.orange);
        gui.getFields()[21].setSubText(" ");

        gui.getFields()[22].setTitle("");
        gui.getFields()[22].setBackGroundColor(Color.blue);
        ownable[22] = (GUI_Ownable)fields[22];
        ownable[22].setSubText("M5");

        gui.getFields()[23].setTitle("");
        gui.getFields()[23].setBackGroundColor(Color.blue);
        ownable[23] = (GUI_Ownable)fields[23];
        ownable[23].setSubText("M5");

    }

     public void initFieldTitles(String langFile){
         msg = new Text(langFile);

         gui.getFields()[0].setTitle(msg.getText("start"));
         gui.getFields()[1].setTitle(msg.getText("burgerBar"));
         gui.getFields()[2].setTitle(msg.getText("pizzeria"));
         gui.getFields()[3].setSubText(msg.getText("chance"));
         gui.getFields()[4].setTitle(msg.getText("candyShop"));
         gui.getFields()[5].setTitle(msg.getText("iceCreamShop"));
         gui.getFields()[6].setTitle(msg.getText("inJail"));
         gui.getFields()[6].setSubText(msg.getText("onVisit"));
         gui.getFields()[7].setTitle(msg.getText("museum"));
         gui.getFields()[8].setTitle(msg.getText("library"));
         gui.getFields()[9].setSubText(msg.getText("chance"));
         gui.getFields()[10].setTitle(msg.getText("skatePark"));
         gui.getFields()[11].setTitle(msg.getText("pool"));
         gui.getFields()[12].setTitle(msg.getText("parking"));
         gui.getFields()[12].setSubText(msg.getText("free"));
         gui.getFields()[13].setTitle(msg.getText("playingHall"));
         gui.getFields()[14].setTitle(msg.getText("cinema"));
         gui.getFields()[15].setSubText(msg.getText("chance"));
         gui.getFields()[16].setTitle(msg.getText("toyStore"));
         gui.getFields()[17].setTitle(msg.getText("petShop"));
         gui.getFields()[18].setTitle(msg.getText("jail"));
         gui.getFields()[19].setTitle(msg.getText("bowlingAlley"));
         gui.getFields()[20].setTitle(msg.getText("zoo"));
         gui.getFields()[21].setSubText(msg.getText("chance"));
         gui.getFields()[22].setTitle(msg.getText("waterPark"));
         gui.getFields()[23].setTitle(msg.getText("boardWalk"));
         gui.getUserButtonPressed(msg.getText("startGame"));
         gui.showMessage(msg.getText("welcomeMessage"));
     }

     public void showMessage(String message){
        gui.showMessage(message);
    }

     public GUI_Player[] addPlayerOnBoard(Player[] list){
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

     public void displayOwnerName(Player currentPlayer, int currentPositionIndex){
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
        return gui.getUserInteger(msg.getText("enterPlayerCount"));
     }

     public int getUserLang() {
        return gui.getUserInteger("You are in English mode. Enter 1 to keep English or enter 2 to switch to Danish.");
     }
     public String getUserString(int currentPlayer){

        return gui.getUserString(msg.getText("enterName") + " " + currentPlayer);
     }
    public void setDice(int die1, int die2){
        gui.setDice(die1,die2);
    }
    public String winner(){

        return winner();
    }

 }
