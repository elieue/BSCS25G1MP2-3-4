import java.util.Scanner;

public class Opt_Sequential_Search {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n=== Sentinel Linear Search Program ===\n");

        boolean tryAgain;

        do {
            int n = 0;

            while (true) {
                System.out.print("Enter the number of elements you want to add (max 15): ");
                if (scanner.hasNextInt()) {
                    n = scanner.nextInt();
                    if (n > 0 && n <= 15) {
                        break;
                    } else {
                        System.out.println("Oops! Please enter a number between 1 and 15.\n");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a numeric value.\n");
                    scanner.next(); 
                }
            }

            int[] arr = new int[n];

            System.out.println("\nPlease enter the elements:");
            for (int i = 0; i < n; i++) {
                while (true) {
                    System.out.print("Element " + (i + 1) + ": ");
                    if (scanner.hasNextInt()) {
                        arr[i] = scanner.nextInt();
                        break;
                    } else {
                        System.out.println("Invalid input! Please enter a valid number.");
                        scanner.next();
                    }
                }
            }

            int searchValue = 0;
            while (true) {
                System.out.print("\nEnter the number you want to search for: ");
                if (scanner.hasNextInt()) {
                    searchValue = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.next();
                }
            }

            int result = sentinelLinearSearch(arr, searchValue);

            System.out.println();
            if (result != -1) {
                System.out.println("Congrats! The element " + searchValue + " is found at index " + result + ".");
            } else {
                System.out.println("Sorry, the element " + searchValue + " was not found in the list.");
            }

            System.out.print("\nDo you want to try again? (yes/no): ");
            scanner.nextLine(); 
            String response = scanner.nextLine().trim().toLowerCase();

            tryAgain = response.equals("yes") || response.equals("y");
            System.out.println();

        } while (tryAgain);

        System.out.println("Thank you for using the Sentinel Linear Search Program!\n\n");
        scanner.close();
    }

    // Optimized Sentinel Linear Search method
    public static int sentinelLinearSearch(int[] arr, int searchValue) {
        int n = arr.length;

        // If array has only one element, handle directly
        if (n == 1) {
            return (arr[0] == searchValue) ? 0 : -1;
        }

        int last = arr[n - 1]; // Save the last element
        arr[n - 1] = searchValue; // Place sentinel

        int i = 0;
        while (arr[i] != searchValue) {
            i++;
        }

        arr[n - 1] = last; // Restore last element

        // Check if match was real
        if (i < n - 1 || arr[n - 1] == searchValue) {
            return i;
        }

        return -1;
    }
}
