import java.util.*;

public class Knapsack_Problem {
    static Scanner objScanner = new Scanner(System.in);

    public static int funcKnapSackRecursive(int intCapacity, int[] intArrVal, int[] intArrWeight, int intArraySize) {
        if (intArraySize == 0 || intCapacity == 0) return 0;

        int intChosen = 0;

        // Pick nth item if it does not exceed the capacity of knapsack
        if (intArrWeight[intArraySize - 1] <= intCapacity)
            intChosen = intArrVal[intArraySize - 1] + funcKnapSackRecursive(intCapacity - intArrWeight[intArraySize - 1], intArrVal, intArrWeight, intArraySize - 1);
        
        // Don't pick the nth item
        int intNotChosen = funcKnapSackRecursive(intCapacity, intArrVal, intArrWeight, intArraySize - 1);
         
        return Math.max(intChosen, intNotChosen);
    }

    public static int funcKnapSack(int intCapacity, int[] intArrVal, int[] intArrWeight) {
        int intArraySize = intArrVal.length;
        return funcKnapSackRecursive(intCapacity, intArrVal, intArrWeight, intArraySize);
    }

    public static int funcIntOption(String prompt) {
        boolean boolValidOption = false;
        int intOption = 0;
        System.out.print(prompt);
        do {
            String input = objScanner.nextLine();
            try {
                intOption = Integer.parseInt(input);
                if (intOption <= 0) System.out.print("Option must be positive. Please enter an integer above 0: ");
                else boolValidOption = true;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter an integer: ");
            }
        } while (!boolValidOption);

        return intOption;
    }

    public static int funcIntOptionRange(String prompt, int min, int max) {
        boolean boolValidOption = false;
        int intOption = 0;
        System.out.print(prompt);
        do {
            String input = objScanner.nextLine();
            try {
                intOption = Integer.parseInt(input);
                if (intOption < min || intOption > max) System.out.printf("Option is out of bounds. Please enter an integer from %d-%d: ", min, max);
                else boolValidOption = true;
            } catch (NumberFormatException e) {
                System.out.printf("Invalid input. Please enter an integer from %d-%d: ", min, max);
            }
        } while (!boolValidOption);

        return intOption;
    }

    public static void funcShowKnapSack(int[] intArrVal, int[] intArrWeight, int intArraySize) {
        System.out.println("Value:");
        for (int j = 0; j < intArraySize; j++) System.out.print(intArrVal[j] + "\t");
        System.out.println("\nWeight:");
        for (int k = 0; k < intArraySize; k++) System.out.print(intArrWeight[k] + "\t");
        System.out.print("\n");
    }

    public static void main(String[] args) {
        int intArraySize;
        int[] intArrayVal;
        int[] intArrayWeight;
        int intKnapSackCapacity;

        intArraySize = funcIntOptionRange("Please set the amount of elements there is to be selected (Maximum of 10): ", 1, 10);
        intArrayVal = new int[intArraySize];
        intArrayWeight = new int[intArraySize];
        for (int i = 0; i < intArraySize; i++) {
            funcShowKnapSack(intArrayVal, intArrayWeight, intArraySize);

            intArrayVal[i] = funcIntOption("Enter index " + i + "'s value: ");
            intArrayWeight[i] = funcIntOption("Enter index " + i + "'s weight: ");
            System.out.print("\n");
        }

        funcShowKnapSack(intArrayVal, intArrayWeight, intArraySize);
        intKnapSackCapacity = funcIntOption("Please set the capacity of the knapsack: ");

        System.out.print("\n");

        System.out.print("Solving knapsack!");

        System.out.println("\n");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Knapsack's best value based from selection: " + funcKnapSack(intKnapSackCapacity, intArrayVal, intArrayWeight));
    }
}
