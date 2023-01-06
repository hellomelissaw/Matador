package Translator;/*import java.io.BufferedReader;
import java.io.FileReader;*/
import java.io.*;
import Controllers.GuiController;
import gui_main.GUI;


public class Text {
    String file;
    GuiController guiController;
    GUI gui;

    int lineCount = 101;
    String[] messages = new String[lineCount];

    String[] labels = new String[lineCount];

    /***
     * Reads a text file and parses each line into labels and their corresponding messages
     * @param file file name for user-chosen language
     * @param //guiController
     */
    /*public Text(String file, GuiController guiController){
        this.file = file;
        this.guiController = guiController;
        BufferedReader reader;

        try {
            reader = new BufferedReader(new java.io.FileReader(file));
            String line;

            for (int i = 0; i < lineCount; i++) {

                line = reader.readLine();
                String[] labelsAndMessages = line.split(";");
                labels[i] = labelsAndMessages[0];
                messages[i] = labelsAndMessages[1];

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }*/
    public Text(String file, GuiController guiController){
        this.file = file;
        this.guiController = guiController;
       // this.gui=gui;
        BufferedReader reader;

        try {
            reader = new BufferedReader(new java.io.FileReader(file));
            String line;

            for (int i = 0; i < lineCount; i++) {

                line = reader.readLine();
                String[] labelsAndMessages = line.split(";");
                labels[i] = labelsAndMessages[0];
                messages[i] = labelsAndMessages[1];

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     * Takes the label given as parameter and finds corresponding message
     * @param label the label for a specific message (The label is the same for every language)
     * @return String contained message corresponding to given label in chosen language
     */
    public String getText(String label) {
        String message = "";
        for (int i = 0 ; i < lineCount ; i++) {
            if(label.equals(labels[i])) {
                message = messages[i];
                break;
            }
        }
        return message;
    }

    public void printText(String label, String otherMessage) {
        String message = "";
        for (int i = 0 ; i < lineCount ; i++) {
            if(label.equals(labels[i])) {
                message = messages[i];
                break;
            }
        }

        /*if(otherMessage == "na") {
            System.out.println(message);
            guiController.showMessage(message);
            //gui.showMessage(message);
        } else {
            System.out.println(otherMessage + message);
            guiController.showMessage(otherMessage + message);
            //gui.showMessage(otherMessage + message);
        }*/

    }
}

