import java.util.*;

public class SelectionSort_Optimize{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean tryAgain = true;

        while (tryAgain) {
            System.out.println("\n=== Double Selection Sort Sample Program ===");
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

            doubleSelectionSort(arr);

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

    public static void doubleSelectionSort(int[] arr) {
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        int swapCount = 0;
        int passCount = 0;
        

        while (left < right) {
            passCount++;
            System.out.println("\n--- Pass " + passCount + " ---");
            int minIndex = left;
            int maxIndex = left;

            for (int i = left + 1; i <= right; i++) {
                if (arr[i] < arr[minIndex]) {
                    minIndex = i;
                }
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }

            if (left != minIndex) {
                int temp = arr[left];
                arr[left] = arr[minIndex];
                arr[minIndex] = temp;
                swapCount++;
                System.out.println("Swap " + swapCount + ": " + Arrays.toString(arr));
            } else {
                System.out.println("No minimum swap needed in this pass.");
            }

            if (right != maxIndex) {
                if (maxIndex == left) {
                    maxIndex = minIndex;
                }
                int temp = arr[right];
                arr[right] = arr[maxIndex];
                arr[maxIndex] = temp;
                swapCount++;
                System.out.println("Swap " + swapCount + ": " + Arrays.toString(arr));
            } else {
                System.out.println("No maximum swap needed in this pass.");
            }

            left++;
            right--;
        }
    }
}
