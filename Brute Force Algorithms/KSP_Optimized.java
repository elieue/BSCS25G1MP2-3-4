import java.util.*; //TODO: MAKE OPTIMIZATION HOLD ON KUMAKAIN 

public class KSP_Optimized {
    static Scanner objScanner = new Scanner(System.in);

    public static int funcKnapSackRecursive(int intCapacity, int[] intArrVal, int[] intArrWeight, int intArraySize) {
        if (intArraySize == 0 || intCapacity == 0) return 0; // If there's nothing else to grab or if the knapsack is full, return

        int intChosen = 0; // Chosen Item

        // Picks the n'th item if it does not exceed the capacity of knapsack, recursively checks again with reduced space and selection
        if (intArrWeight[intArraySize - 1] <= intCapacity) intChosen = intArrVal[intArraySize - 1] + funcKnapSackRecursive(intCapacity - intArrWeight[intArraySize - 1], intArrVal, intArrWeight, intArraySize - 1);
        
        // Checks and goes over to the path that is not chosen if there are better options
        int intNotChosen = funcKnapSackRecursive(intCapacity, intArrVal, intArrWeight, intArraySize - 1);
        
        // Picks which between the chosen and not chosen in the current call is greater 
        return Math.max(intChosen, intNotChosen);
    }

    public static int funcKnapSack(int intCapacity, int[] intArrVal, int[] intArrWeight) {
        int intArraySize = intArrVal.length;
        return funcKnapSackRecursive(intCapacity, intArrVal, intArrWeight, intArraySize);
    }

    public static int funcIntOption(String strPrompt) {
        boolean boolValidOption = false; // Initialize the loop
        int intOption = 0; // Initialize current value
        System.out.print(strPrompt);
        do { // Do a loop to check if the user inputted an actual integer, return true if done correctly, reloops if invalid (character/float/etc)
            String strInput = objScanner.nextLine();
            try {
                intOption = Integer.parseInt(strInput); // Parse the input into an integer
                if (intOption <= 0) System.out.print("Option must be positive. Please enter an integer above 0: "); // Limitted every value to not be zero, we don't want zero value and weighted elements, zero capacity knapsack and zero selection elements now, do we? 
                else boolValidOption = true;
            } catch (NumberFormatException e) { // If what is inputted is NOT an integer, catch the exception, preventing the program to close and instead, show an error message
                System.out.print("Invalid input. Please enter an integer: ");
            }
        } while (!boolValidOption);

        return intOption;
    }

    public static int funcIntOptionRange(String strPrompt, int intMin, int intMax) { // Ditto from funcIntOption except with domain and range since I'm lazy :P
        boolean boolValidOption = false;
        int intOption = 0;
        System.out.print(strPrompt);
        do {
            String strInput = objScanner.nextLine();
            try {
                intOption = Integer.parseInt(strInput);
                if (intOption < intMin || intOption > intMax) System.out.printf("Option is out of bounds. Please enter an integer from %d-%d: ", intMin, intMax);
                else boolValidOption = true;
            } catch (NumberFormatException e) {
                System.out.printf("Invalid input. Please enter an integer from %d-%d: ", intMin, intMax);
            }
        } while (!boolValidOption);

        return intOption;
    }

    public static void funcShowKnapSack(int[] intArrVal, int[] intArrWeight, int intArraySize) { // Shows the selection elements, made it into a method for convenience
        System.out.println("Value:");
        for (int i = 0; i < intArraySize; i++) System.out.print(intArrVal[i] + "\t");
        System.out.println("\nWeight:");
        for (int j = 0; j < intArraySize; j++) System.out.print(intArrWeight[j] + "\t");
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
