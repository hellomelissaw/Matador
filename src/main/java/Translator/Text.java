package Translator;/*import java.io.BufferedReader;
import java.io.FileReader;*/
import java.io.*;


public class Text {
    String file;

    int lineCount = 32;
    String[] messages = new String[lineCount];

    String[] labels = new String[lineCount];
    public Text(String file){
        this.file = file;
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

    /*public void printText(int index) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new java.io.FileReader(file));

            String line = "";

            for(int i = 0 ; i < lineCount ; i++) {

                line = reader.readLine();
                String[] labelsAndMessages = line.split(",");
                labels[i] = labelsAndMessages[0];
                messages[i] = labelsAndMessages[1];

            }
            System.out.println("First label: " + labels[0]);
            System.out.println("Second label: " + labels[1]);
            System.out.println("Third label: " + labels[2]);

            System.out.println("First msg: " + messages[0]);
            System.out.println("Second msg: " + messages[1]);
            System.out.println("Third msg: " + messages[2]);


        } catch (Exception e) {
            e.printStackTrace();

        }

    }
*/

}

