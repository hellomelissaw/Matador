/* Copying from our CDIO 2 */
import java.io.BufferedReader;
import java.io.FileReader;


    public class TextReader {
        String file;
        public TextReader(String file){
            this.file = file;
        }
        public void printText(int index) {
            String[] row = new String[49];
            BufferedReader csvReader;
            String line = "";

            try {
                csvReader = new BufferedReader(new FileReader(file));
                while((line = csvReader.readLine()) != null) {
                    String[] messages = line.split(",");
                    System.out.println(messages);
                }
                /*
                for (int i = 0; i < row.length; i++) {
                    row[i] = csvReader.readLine();

                }*/

            } catch (Exception e) {
                e.printStackTrace();

            }
            System.out.println(row[index]);
        }

        public String returnText(int index) {
            String[] row = new String[49];
            BufferedReader csvReader;

            try {
                csvReader = new BufferedReader(new FileReader(file));
                for (int i = 0; i < 49; i++) {
                    row[i] = csvReader.readLine();

                }

            } catch (Exception e) {
                e.printStackTrace();

            }
            return row[index];
        }


    }

