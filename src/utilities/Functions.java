package utilities;

import static java.lang.Integer.parseInt;
import java.util.Scanner;

public class Functions {

    // validates user input
    // min: minimum choice
    // max: maximum choice
    // message: prompt message to user
    // warning: warning message in case of invalid input
    public static int inputValidation(int min, int max, String message,
            String warning, Scanner sc) {
        String input = String.valueOf(min - 1);
        do {
            try {
                System.out.printf("%s (%d-%d): ", message, min, max);
                input = sc.nextLine();
                if (parseInt(input) < min || parseInt(input) > max) {
                    System.out.println(warning);
                }
            } catch (NumberFormatException e) {
                System.out.println(warning);
                input = String.valueOf(min - 1);
            }
        } while (parseInt(input) < min || parseInt(input) > max);
        return parseInt(input);
    }
    // transfer inputValidation elsewhere
}
