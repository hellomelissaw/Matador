package GameComponents.Board;
import GameComponents.Player;
import Controllers.GuiController;
import Translator.*;
import gui_fields.GUI_Player;

public abstract class Square {

    private String squareName;

    Text msg;

    String color;

    int groupSize;

    GuiController guiController;

    Square(String squareName){
        this.squareName = squareName;

    }

    public abstract void landOn(Player currentPlayer);

    public String getSquareName() {
        return squareName;
    }

    public void setLang(Text msg) {
       this.msg = msg;

    }

    public void setColor(String color) {
        this.color = color;

    }

    public String getColor() { return color;}

    public void setGroup(String color, int groupSize) {
        this.color = color;
        this.groupSize = groupSize;
    }


    public void setGuiController(GuiController guiController) {
        this.guiController = guiController;
    }


}

