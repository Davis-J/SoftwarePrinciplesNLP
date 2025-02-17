import java.util.ArrayList;

public class ResultMenu { //class which displays all the final results of the sentiment analysis
    private static int lineLength = 27; // Line length in order to display UI with proper center alignment and borders
    public static void resultMenu(ArrayList<String> inputs, ArrayList<String> results) {
        InputValidation inv = new InputValidation(); // Creating object for Input Validation for taking string and integer input
        int choice;
        do {
            //Displaying Result Menu which follows the options in the flowchart of the report
            System.out.println("\n\n");
            printBorder(lineLength);
            printCentered("Result Menu:", lineLength);
            printBorder(lineLength);
            System.out.println("1. Show results in console");
            System.out.println("2. Show statistics");
            System.out.println("3. Download Results");
            System.out.println("4. Share Results"); //Not Available due to GUI restrictions
            System.out.println("5. Go Back");
            printBorder(lineLength);
            System.out.print("Enter your choice: ");

            // Getting user choice
            choice = inv.takeInt();

            // Processing user choice
            switch (choice) {
                case 1: // Printing results in console with both Inputs and Sentiment results
                    //For loop to traverse both Inputs and Sentiment Results in order to display them side by side
                    for (int i = 0; i < inputs.size(); i++) {
                        System.out.println("\nEntry no."+(i+1)+": ");
                        System.out.println(inputs.get(i));
                        System.out.print("Sentiment Result: ");
                        if (results.get(i)=="Overall Negative") {
                            //Using ANSI escape codes in order to print coloured text and then resetting back to normal colour, Here, it's red for negative
                            System.out.println("\u001B[31m"+results.get(i)+"\u001B[0m");
                        } else if (results.get(i)=="Overall Positive") {
                            //Using ANSI escape codes in order to print coloured text, Here, it's green for positive
                            System.out.println("\u001B[32m"+results.get(i)+"\u001B[0m");
                        } else {
                            //Here, the result is neutral, so the normal colour is printed
                            System.out.println(results.get(i));
                        }
                    }
                    break;
                case 2: // Printing number of positive, negative and neutral sentiments
                    //Initializing variables to keep track of sentiment counts
                    short negativeCount = 0;
                    short positiveCount = 0;
                    short neutralCount = 0;
                    for (String result : results) {
                        switch(result) {
                            case "Overall Negative":
                                negativeCount++;
                                break;
                            case "Overall Positive":
                                positiveCount++;
                                break;
                            case "Overall Neutral":
                                neutralCount++;
                                break;
                            default:
                                System.out.println("ERROR. Invalid result.");
                        }
                    }
                    System.out.println("\nNegative Sentiments: "+negativeCount);
                    System.out.println("\nPositive Sentiments: "+positiveCount);
                    System.out.println("\nNeutral Sentiments: "+neutralCount);
                    break;
                case 3: //Writing the results of the sentiment analysis to a file in the project directory
                    FileHandler.fileWriter(inputs, results);
                    System.out.println("\nFile Downloaded! Please check project directory for output.txt\n");
                    break;
                case 4: //Share function, in order to share the results of the sentiment analysis to other social platforms
                    System.out.println("\n\nNot available due to GUI restrictions.");
                    break;
                case 5: //To go back the main menu
                    System.out.println("\n\n\n");
                    break;
                default: //Invalid Input error message
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 5.\n");
            }
        } while (choice != 5);
    }
    // Utility method to print borders with given length
    public static void printBorder(int length) {
        System.out.print("|");
        for (int i = 0; i < length; i++) {
            System.out.print("*");
        }
        System.out.println("|");
    }

    // Utility method to print text as centered
    public static void printCentered(String text, int length) {
        int padding = (length - text.length()) / 2;
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.println(text);
    }
}
