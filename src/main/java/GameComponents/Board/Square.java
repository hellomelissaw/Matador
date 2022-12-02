package GameComponents.Board;
import GameComponents.Player;
import Controllers.GuiController;
import Translator.*;
import gui_fields.GUI_Player;

public abstract class Square {
    int pointsValue;
    private String squareName;

    protected String lang = "";

    Text msg;

    String color;

    Square(String squareName){
        this.squareName = squareName;

    }

    public abstract void landOn(Player currentPlayer, GUI_Player currentGuiPlayer);

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

}

