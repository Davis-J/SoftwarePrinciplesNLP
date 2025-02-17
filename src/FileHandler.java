import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public static void fileWriter(ArrayList<String> inputs, ArrayList<String> results) {
        // Method to write the results of sentiment analysis to a text file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            for (int i = 0; i < inputs.size(); i++) {
                writer.write("Entry no."+(i+1)+": ");
                writer.write(inputs.get(i));
                writer.write("\nSentiment Result: ");
                writer.write(results.get(i));
                writer.write("\n\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> fileReader(String filePath) {
        //Method to read a text file in order to process for sentiment analysis
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            ArrayList<String> textFile= new ArrayList<>();
            while((line = reader.readLine()) != null) {
                textFile.add(line);
            }
            reader.close();
            return textFile;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Please run the program again and input the path of the text file correctly.");
            return null;
        }
    }
}
