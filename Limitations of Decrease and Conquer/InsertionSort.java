import java.util.Scanner;
import java.util.Arrays;

public class InsertionSort {
    private int operationCount = 0; // Track operations for complexity analysis

    /* Function to sort array using insertion sort */
    void sort(int storage[]) {
        int n = storage.length;
        for (int i = 1; i < n; ++i) {
            int key = storage[i];
            int j = i - 1;

            while (j >= 0 && storage[j] > key) {
                storage[j + 1] = storage[j];
                j = j - 1;
                operationCount++; // Counting shifts
            }

            storage[j + 1] = key;
            operationCount++; // Counting final placement

            // Display step-by-step sorting process
            System.out.println("Iteration " + i + ": " + Arrays.toString(storage) + " | Operations: " + operationCount);
        }
    }

    /* Utility function to print array */
    void output(int storage[]) {
        for (int num : storage) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        Scanner user = new Scanner(System.in);

        System.out.print("Input number of elements you want: ");
        int n = user.nextInt();
        int[] storage = new int[n];

        System.out.println("Input " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            storage[i] = user.nextInt();
        }

        user.close();

        System.out.println("-----------------**Insertion Sort**-----------------");

        // Sorting array with step-by-step output and complexity tracking
        InsertionSort ob = new InsertionSort();
        System.out.println("\nInitial array: " + Arrays.toString(storage));
        ob.sort(storage);

        System.out.println("\nSorted array:");
        ob.output(storage); // Ensure output() is called from object

        // Show final operation count
        System.out.println("\nTotal operations performed: " + ob.operationCount);
        System.out.println("Time Complexity Estimate: O(n²) for worst case.");
        System.out.println("-----------Thank you for using the program!-----------");
    }
}