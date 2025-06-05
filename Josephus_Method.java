import java.util.*;

public class Josephus_Method {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // Scanner to get the user input

        System.out.print("Enter the cycle count: "); // Asks for user input for the cycle count
        int cycleCount = scan.nextInt(); // Stores the number for the cycle count

        System.out.print("Enter the 1st number to test: "); // Asks for user input for the first number to test
        int num1 = scan.nextInt(); // Stores the first number

        System.out.print("Enter the 2nd number to test: "); // Asks for user input for the second number to test
        int num2 = scan.nextInt(); // Stores the second number

        System.out.print("Enter the 3rd number to test: "); // Asks for user input for the third number to test
        int num3 = scan.nextInt(); // Stores the third number

        scan.close(); //Closes the scanner to avoid errors

        System.out.println("\n--- Josephus Method Results ---"); // Prints the Header
        System.out.println("Cycle Count = " + cycleCount); // Displays the cycle count from user input
        System.out.println("---------------------------------------------------------------"); // To separate and make it organized

        // Test for the first number
        System.out.println("First Number: " + num1); // Display which number is being tested
        int startTime1 = (int) System.currentTimeMillis(); // gets the starting time or current time where we start
        int lastEliminated1 = runJosephusSimulation(num1, cycleCount, true); // gets the number that was the last eliminated
        int endTime1 = (int) System.currentTimeMillis(); // gets the end time
        int duration1 = endTime1 - startTime1; // gets the total duration
        //Displays the data from above
        System.out.println("Last Eliminated: " + lastEliminated1); 
        System.out.println("Time: " + duration1 + " ms");
        System.out.println("---------------------------------------------------------------");


        // Test for the second number
        System.out.println("Second Number: " + num2); // Display which number is being tested
        int startTime2 = (int) System.currentTimeMillis(); // gets the starting time or current time where we start
        int lastEliminated2 = runJosephusSimulation(num2, cycleCount, true); // gets the number that was the last eliminated
        int endTime2 = (int) System.currentTimeMillis(); // gets the end time
        int duration2 = endTime2 - startTime2; // gets the total duration
        //Displays the data from above
        System.out.println("Last Eliminated: " + lastEliminated2); 
        System.out.println("Time: " + duration2 + " ms");
        System.out.println("---------------------------------------------------------------");

        // Test for the third number
        System.out.println("Third Number: " + num3); // Display which number is being tested
        int startTime3 = (int) System.currentTimeMillis(); // gets the starting time or current time where we start
        int lastEliminated3 = runJosephusSimulation(num3, cycleCount, true); // gets the number that was the last eliminated
        int endTime3 = (int) System.currentTimeMillis(); // gets the end time
        int duration3 = endTime3 - startTime3; // gets the total duration
        //Displays the data from above
        System.out.println("Last Eliminated: " + lastEliminated3);
        System.out.println("Time: " + duration3 + " ms");
        System.out.println("---------------------------------------------------------------");
    }

    public static int runJosephusSimulation(int n, int k, boolean printSequence) {
        List<Integer> numbersInCircle = new ArrayList<>(); // List for the numbers
        for (int i = 1; i <= n; i++) { // this loop puts the numbers to the list
            numbersInCircle.add(i);
        }

        int currentSpot = 0; // starts the cycle at spot 0 or first spot
        int lastNumberEliminated = -1; // variable for the last number eliminated

        if (printSequence) { // This prints the elimination sequence
            System.out.print("Elimination Sequence: ");
        }

        while (numbersInCircle.size() > 1) {
            currentSpot = (currentSpot + k - 1) % numbersInCircle.size();
            
            lastNumberEliminated = numbersInCircle.get(currentSpot);
            
            if (printSequence) {
                System.out.print(lastNumberEliminated + " ");
            }
            numbersInCircle.remove(currentSpot);
        }
        if (printSequence) {
            System.out.println(); 
        }
        
        return numbersInCircle.get(0); 
    }
}