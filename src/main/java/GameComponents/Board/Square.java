package GameComponents.Board;
import GameComponents.Player;
import Controllers.GuiController;
import Translator.*;
import gui_fields.GUI_Player;

public abstract class Square {

    private String squareName;

    Text msg;

    String color;

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

}

