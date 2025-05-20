package AlgoforDaSeat.Original;
import java.util.*;

public class Selection_Sort{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean tryAgain = true;

        while (tryAgain) {
            System.out.println("\n=== Selection Sort Sample Program===");
            int numElements = 0;
            int[] arr = null;

            
            while (true) {
                try {
                    System.out.print("How many elements do you want to sort? ");
                    numElements = scanner.nextInt();
                    if (numElements <= 0) {
                        System.out.println("ERROR! Please enter a positive number of elements.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("ERROR! Please enter a valid integer.");
                    scanner.next(); 
                }
            }

            
            arr = new int[numElements];
            System.out.println("Elements to be sorted (enter " + numElements + " numbers, one by one):");
            for (int i = 0; i < numElements; i++) {
                while (true) {
                    try {
                        System.out.print("Enter element " + (i + 1) + ": ");
                        arr[i] = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR! Please enter a valid integer.");
                        scanner.next(); 
                    }
                }
            }
            scanner.nextLine();

            System.out.println("\n--- Unsorted Array ---");
            System.out.println(Arrays.toString(arr));

            selectionSort(arr);

            System.out.println("\n--- Sorted Array ---");
            System.out.println(Arrays.toString(arr));

        
            while (true) {
                System.out.print("\nWant to try again, y or n? ");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (choice.equals("y")) {
                    tryAgain = true;
                    break;
                } else if (choice.equals("n")) {
                    tryAgain = false;
                    System.out.println("Thank you for using the program!");
                    break;
                } else {
                    System.out.println("ERROR! Please enter 'y' or 'n'.");
                }
            }
        }
        scanner.close();
    }


    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int swapCount = 0; 

        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }

            if (min_idx != i) {
                int temp = arr[min_idx];
                arr[min_idx] = arr[i];
                arr[i] = temp;
                swapCount++; 
                System.out.println("Swap " + swapCount + ": " + Arrays.toString(arr)); 
            }
        }
    }
}