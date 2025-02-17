import java.util.ArrayList;
//STUDENT ID: 2299426
public class Main {
    private static int lineLength = 27; // Line length in order to display UI with proper center alignment and borders
    public static void main(String[] args) {
        InputValidation inv = new InputValidation(); // Creating object for Input Validation for taking string and integer input
        int choice;
        ArrayList<String> Inputs = new ArrayList<>(); // ArrayList in order to take multiple inputs from the user
        do {
            //Menu which follows the options in the flowchart of the report
            ResultMenu.printBorder(lineLength);
            ResultMenu.printCentered("Textual Feedback Analysis", lineLength);
            ResultMenu.printBorder(lineLength);
            System.out.println("1. Enter Text");
            System.out.println("2. Upload a file");
            System.out.println("3. Exit");
            ResultMenu.printBorder(lineLength);
            System.out.print("\nEnter your choice: ");

            // Getting user choice
            choice = inv.takeInt();

            // Processing user choice
            switch (choice) {
                case 1: //Manually Entering subjective text for analysis
                    System.out.print("Enter the number of lines of text you want to enter: ");
                    int numberOfLines = inv.takeInt();
                    for (int i = 1; i <= numberOfLines; i++) { //Looping statement in order to take multiple inputs
                        System.out.println("Enter your text(Entry no."+i+"): ");
                        String text = inv.takeString();
                        Inputs.add(text);
                    }
                    FeedbackAnalysis.analysis(Inputs); //Taking the ArrayList of Inputs and performing sentiment analysis on it, then going to the ResultMenu
                    break;
                case 2: //File Upload for analysis
                    System.out.print("Enter your file path."); //Enter a file path
                    String path = inv.takeString();
                    // Example file path for windows: C:\Users\[ENTER USERNAME]\Desktop\Input.txt
                    Inputs = FileHandler.fileReader(path); // Getting all the inputs from the file
                    FeedbackAnalysis.analysis(Inputs); // Running it through sentiment analysis and later going to the ResultMenu
                    break;
                case 3: // Program Exit
                    System.out.println("Exiting...");
                    break;
                default: //Invalid Input error message
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        } while (choice != 3);
    }
}

