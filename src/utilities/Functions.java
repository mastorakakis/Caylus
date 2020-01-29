package utilities;

import entities.Block;
import entities.buildings.Building;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.Scanner;

public class Functions {

    // validate and return user input
    // min: minimum choice
    // max: maximum choice
    // message: prompt message to user
    // warning: warning message in case of invalid input
    public static int inputValidation(int min, int max, String message,
            String warning, Scanner sc) {
        String input = String.valueOf(min - 1);
        do {
            try {
                System.out.printf("%s (Select %d-%d): ", message, min, max);
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
// create a string with available options (from a list) for the user

    public static String printIndexedOptions(List<Integer> index, List<Block> road) {
        StringBuilder message = new StringBuilder();
        int i;
        for (i = 0; i < index.size(); i++) { // for every index
            // get the building according to index
            Building building = road.get(index.get(i)).getBuilding();
            if (building != null) { // if building is not empty
                message.append(String.format("%02d)%-20s\t", i + 1, building.getName()));
                if ((i + 1) % 4 == 0) {
                    message.append("\n");
                }
            }
        }
        message.append(String.format("%d)%s", ++i, "-Pass-"));
        return message.toString();
    }

    // string of available options
    public static <T extends Building> String printOptions(List<T> buildingList) {
        StringBuilder message = new StringBuilder();
        int i;
        T building = null;
        for (i = 0; i < buildingList.size(); i++) {
            building = buildingList.get(i);
            message.append(String.format("%02d)%-20s\t", i + 1, building.getName()));
            if ((i + 1) % 4 == 0) {
                message.append("\n");
            }
        }
        message.append(String.format("%d)%s", ++i, "-Pass-"));
        return message.toString();
    }
}
