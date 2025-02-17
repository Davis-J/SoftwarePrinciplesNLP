import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.*;

import java.util.*;


public class FeedbackAnalysis {
    //Initializing hashmaps to convert sentiments to their numeric values and back
    private static final HashMap<Integer, String> integerToSentiment = new HashMap<>();
    private static final HashMap<String, Integer> sentimentToInteger = new HashMap<>();

    static { //Static block for initialization
        integerToSentiment.put(-2, "Overall Negative");
        integerToSentiment.put(-1, "Overall Negative");
        integerToSentiment.put(0, "Overall Neutral");
        integerToSentiment.put(1, "Overall Positive");
        integerToSentiment.put(2, "Overall Positive");

        // Initialize the reverse HashMap for cardinal to number mappings
        sentimentToInteger.put("Very negative", -2);
        sentimentToInteger.put("Negative", -1);
        sentimentToInteger.put("Neutral", 0);
        sentimentToInteger.put("Positive", 1);
        sentimentToInteger.put("Very positive", 2);
    }
    public static void analysis(ArrayList<String> inputs) {
        // Result ArrayList in order to store sentiment analysis results
        ArrayList<String> results = new ArrayList<>();
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline(); // Getting the Pipeline for NLP
        for (String input : inputs) {
            CoreDocument coreDocument = new CoreDocument(input.toLowerCase());
            stanfordCoreNLP.annotate(coreDocument);
            List<CoreSentence> sentences = coreDocument.sentences();
            // Creating an ArrayList that stores the sentiments in the form of their corresponding numeric values
            // This is so that average sentiment of the input can be calculated
            ArrayList<Integer> numericSentiments = new ArrayList<>();
            for (CoreSentence sentence : sentences) {
                String sentiment = sentence.sentiment();
                // Converting sentiments to their corresponding integers and storing them
                numericSentiments.add(conversion(sentiment));
            }
            // Calculating the average/overall sentiments of the inputs by calculating average of their numeric counterpart
            int sum = 0;
            for (int num : numericSentiments) {
                sum += num;
            }
            int averageNumericSentiment = Math.round((float) sum/ (float) numericSentiments.size());
            //After calculating their average, the average is converted to the corresponding sentiment they correspond to
            String averageSentiment = conversion(averageNumericSentiment);
            results.add(averageSentiment);
        }
        //These average sentiments are put through the Result Menu in order to display them
        ResultMenu.resultMenu(inputs, results);
    }


    // Function to convert integers to their corresponding sentiments
    public static String conversion(int averageNumericSentiment) {
        return integerToSentiment.getOrDefault(averageNumericSentiment, "Unknown");
    }

    // Function to convert sentiments to their corresponding integers
    public static int conversion(String sentiment) {
        return sentimentToInteger.getOrDefault(sentiment, 0);
    }
}
