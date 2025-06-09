import java.util.Scanner;
import java.util.Arrays;

class QuickSortOrig {
    void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int divide = partition(arr, low, high);

            // Show pivot and array state after each partition
            System.out.println("Pivot selected: " + arr[divide] + " | Array state: " + Arrays.toString(arr));

            quickSort(arr, low, divide - 1);
            quickSort(arr, divide + 1, high);
        }
    }

    int partition(int arr[], int low, int high) {
        int pivot = arr[high]; // Last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high); // Move pivot to correct position
        return i + 1;
    }

    void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input
        System.out.print("Input number of elements you want: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Input " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        scanner.close();

        System.out.println("--------------------**Quick Sort**--------------------");
        // Sorting with visualization
        QuickSortOriginal sorter = new QuickSortOriginal();
        System.out.println("Initial array: " + Arrays.toString(arr));
        sorter.quickSort(arr, 0, n - 1);

        System.out.println("\nSorted array: " + Arrays.toString(arr));
        System.out.println("-----------Thank you for using the program!-----------");
    }
}