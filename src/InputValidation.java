import java.util.Scanner;

public class InputValidation { //Class for input validation
    Scanner scanner;
    public InputValidation() {
        scanner = new Scanner(System.in);
    } //Constructor to initialize scanner
    public int takeInt() { //Function to take integer inputs with validation
        int newInput = 0;
        boolean valid = false;
        do {
            try {
                String input = scanner.nextLine();
                newInput = Integer.parseInt(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Valid Integer not detected. Please try again.");
                System.out.print(": ");
            }
        }while(!valid);
        return newInput;
    }

    public String takeString() { //Function to take string inptus with validation
        String input;
        do {
            input = scanner.nextLine().trim(); // In built method to remove leading and trailing whitespace
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty. Please try again.");
                System.out.print(": ");
            }
        } while (true);
    }
}