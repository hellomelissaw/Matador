/* Copying from our CDIO 2 */
import java.io.BufferedReader;
import java.io.FileReader;


    public class TextReader {
        String file;
        public TextReader(String file){
            this.file = file;
        }
        public void printText(int index) {
           String[] messages = new String[51];
            BufferedReader csvReader;
            String line = "";

            try {
                csvReader = new BufferedReader(new FileReader(file));
                String[] reader = new String[2];
                while((line = csvReader.readLine()) != null) {
                    reader = line.split(",");
                }
                String[] labels = new String[51];
                labels = reader[0].split("\n");

                messages = reader[1].split("\n");
                /*
                for (int i = 0; i < row.length; i++) {
                    row[i] = csvReader.readLine();

                }*/

            } catch (Exception e) {
                e.printStackTrace();

            }
            System.out.println(messages[index]);
        }

        public String returnText(int index) {
            String[] messages = new String[51];
            BufferedReader csvReader;
            String line = "";

            try {
                csvReader = new BufferedReader(new FileReader(file));
                String[] reader = new String[2];
                while((line = csvReader.readLine()) != null) {
                    reader = line.split(",");
                }
                String[] labels = new String[51];
                labels = reader[0].split("\n");

                messages = reader[1].split("\n");
                /*
                for (int i = 0; i < row.length; i++) {
                    row[i] = csvReader.readLine();

                }*/

            } catch (Exception e) {
                e.printStackTrace();

            }
            return messages[index];
        }


    }

