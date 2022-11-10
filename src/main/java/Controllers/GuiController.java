package Controllers;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
public class GuiController {

    public GuiController() {
        GUI_Field[] fields = {
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


        GUI gui = new GUI(fields);

        gui.getFields()[0].setTitle("Start");
        gui.getFields()[0].setBackGroundColor(Color.yellow);
        gui.getFields()[0].setSubText("M1");

        gui.getFields()[1].setTitle("Burgerbaren");
        gui.getFields()[1].setBackGroundColor(Color.PINK);
        gui.getFields()[1].setSubText("M1");

        gui.getFields()[2].setTitle("Pizzahuset");
        gui.getFields()[2].setBackGroundColor(Color.red);
        gui.getFields()[2].setSubText("M1");

        gui.getFields()[3].setBackGroundColor(Color.MAGENTA);

        gui.getFields()[4].setTitle("Slikbuttiken");
        gui.getFields()[4].setBackGroundColor(Color.ORANGE);
        gui.getFields()[4].setSubText("M1");

        gui.getFields()[5].setTitle("Iskiosken");
        gui.getFields()[5].setBackGroundColor(Color.blue);
        gui.getFields()[5].setSubText("M1");

        gui.getFields()[6].setTitle("I fengsel");
        gui.getFields()[6].setBackGroundColor(Color.gray);
        gui.getFields()[6].setSubText("På besøg!");

        gui.getFields()[7].setTitle("Museet");
        gui.getFields()[7].setBackGroundColor(Color.PINK);
        gui.getFields()[7].setSubText("M2");

        gui.getFields()[8].setTitle("Biblioteket");
        gui.getFields()[8].setBackGroundColor(Color.orange);
        gui.getFields()[8].setSubText("M2");


        gui.getFields()[9].setBackGroundColor(Color.magenta);

        gui.getFields()[10].setTitle("Skateparken");
        gui.getFields()[10].setBackGroundColor(Color.CYAN);
        gui.getFields()[10].setSubText("M2");

        gui.getFields()[11].setTitle("Swimmingpoolen");
        gui.getFields()[11].setBackGroundColor(Color.orange);
        gui.getFields()[11].setSubText("M2");

        gui.getFields()[12].setTitle("Parkering");
        gui.getFields()[12].setBackGroundColor(Color.darkGray);
        gui.getFields()[12].setSubText("Gratis");

        gui.getFields()[13].setTitle("Spillehallen");
        gui.getFields()[13].setBackGroundColor(Color.orange);
        gui.getFields()[13].setSubText("M3");

        gui.getFields()[14].setTitle("Biografen");
        gui.getFields()[14].setBackGroundColor(Color.pink);
        gui.getFields()[14].setSubText("M3");

        gui.getFields()[15].setBackGroundColor(Color.magenta);

        gui.getFields()[16].setTitle("Legetøjsbutikken");
        gui.getFields()[16].setBackGroundColor(Color.orange);
        gui.getFields()[16].setSubText("M3");

        gui.getFields()[17].setTitle("Dyrebutikken");
        gui.getFields()[17].setBackGroundColor(Color.pink);
        gui.getFields()[17].setSubText("M3");

        gui.getFields()[18].setTitle("I fengsel");
        gui.getFields()[18].setBackGroundColor(Color.gray);
        gui.getFields()[18].setSubText("Gå i fengsel!");

        gui.getFields()[19].setTitle("Bowlinghallen");
        gui.getFields()[19].setBackGroundColor(Color.blue);
        gui.getFields()[19].setSubText("M4");

        gui.getFields()[20].setTitle("Zoologisk Have");
        gui.getFields()[20].setBackGroundColor(Color.PINK);
        gui.getFields()[20].setSubText("M4");


        gui.getFields()[21].setBackGroundColor(Color.MAGENTA);

        gui.getFields()[22].setTitle("Vandlandet");
        gui.getFields()[22].setBackGroundColor(Color.CYAN);
        gui.getFields()[22].setSubText("M5");

        gui.getFields()[23].setTitle("Strand");
        gui.getFields()[23].setBackGroundColor(Color.orange);
        gui.getFields()[23].setSubText("M5");

    }
}
