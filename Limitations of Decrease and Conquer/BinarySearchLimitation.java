import java.util.Scanner;

public class BinarySearchLimitation {

    // Binary search function - returns the index of target if found, else -1
    public static int binarySearch(int[] arr, int target) {
        int intlow = 0;
        int inthigh = arr.length - 1;

        while (intlow <= inthigh) {
            int intmid = intlow + (inthigh - intlow) / 2;

            if (arr[intmid] == target) {
                return intmid; // Target found
            } else if (arr[intmid] < target) {
                intlow = intmid + 1; // Search right half
            } else {
                inthigh = intmid - 1; // Search left half
            }
        }

        return -1; // Target not found
    }

    // Function to check if array is sorted in ascending order
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false; // Found an unsorted pair
            }
        }
        return true; // All elements are in order
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            boolean tryAgain;

            // Main loop: continues while user wants to try again
            do {
                System.out.print("Enter the number of elements in the array: ");
                int n = scanner.nextInt();

                // Validate array size
                if (n <= 0) {
                    System.out.println("Array size must be greater than 0.");
                    return;
                }

                int[] arr = new int[n];
                System.out.println("Enter " + n + " elements:");

                // Input array elements
                for (int i = 0; i < n; i++) {
                    arr[i] = scanner.nextInt();
                }

                // Input target value to search
                System.out.print("Enter the target value to search: ");
                int target = scanner.nextInt();

                // Check if the array is sorted before applying binary search
                if (!isSorted(arr)) {
                    System.out.println("Warning: The array is not sorted. Binary search may not return correct results. Please try again.");
                } else {
                    // Perform binary search
                    int result = binarySearch(arr, target);

                    // Show search result
                    if (result == -1) {
                        System.out.println("Element not found.");
                    } else {
                        System.out.println("Element found at index: " + result);
                    }
                }

                // Ask if the user wants to try again
                System.out.print("Do you want to try again? (yes/no): ");
                String response = scanner.next();
                tryAgain = response.equalsIgnoreCase("yes");

            } while (tryAgain); // Repeat if user answers "yes"

            System.out.println("Thank you for using binary search limitation program!\n\n");

        } catch (Exception e) {  // Catch any invalid input
            System.out.println("Invalid input. Please enter integers only.");
        } finally { // Always close the scanner to avoid memory leaks
            scanner.close();
        }
    }
}
