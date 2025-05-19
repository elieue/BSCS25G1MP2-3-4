import java.util.Scanner;

public class TravelingSalesman {
    private static int[][] arrCityDistances;
    private static int[] arrBestTour, arrCurrentTour;
    private static boolean[] boolVisited;
    private static int intCityNumber, intStartingCity, intBestLength, intTourCount;

    public static void main(String[] args) {

        try (Scanner scnInput = new Scanner(System.in)) {

            System.out.println("-- Traveling Salesman Problem Using Brute Force --");

            intCityNumber = CityNumberPrompt(scnInput);
            intStartingCity = StartingCityPrompt(scnInput, intCityNumber);
            CityDistancesList(scnInput);

            arrBestTour = new int[intCityNumber + 1];
            arrCurrentTour = new int[intCityNumber + 1];
            boolVisited = new boolean[intCityNumber];
            intBestLength = Integer.MAX_VALUE;
            intTourCount = 0;
            arrCurrentTour[0] = intStartingCity;
            arrCurrentTour[intCityNumber] = intStartingCity;
            boolVisited[intStartingCity] = true;

            System.out.println("\nAll possible routes and their distances:");
            System.out.println("-------------------------------------------------");

            FindBestRoute(1);

            System.out.println("-------------------------------------------------");
            System.out.println("Total Routes: " + intTourCount);

            System.out.println("\nResults:");
            System.out.print("Best Tour: ");

            for (int i = 0; i <= intCityNumber; i++) {
                System.out.print(arrBestTour[i] + (i < intCityNumber ? " -> " : ""));
            }

            System.out.println("\nBest Tour Length: " + intBestLength);
        }
    }

    private static int CityNumberPrompt(Scanner scnInput) {
        int intCityNumber;

        while (true) {
            System.out.print("How many Cities do you want to visit? : ");

            if (!scnInput.hasNextInt()) {
                System.out.println("Error: Please enter an integer.");
                scnInput.next();
                continue;
            }

            intCityNumber = scnInput.nextInt();

            if (intCityNumber <= 0) {
                System.out.println("Error: Number of cities must be positive.");
            } else {
                break;
            }
        }

        return intCityNumber;
    }

    private static int StartingCityPrompt(Scanner scnInput, int intCityNumber) {
        int intStartingCity;

        while (true) {
            System.out.print("Enter the starting city (0 to " + (intCityNumber - 1) + "): ");

            if (!scnInput.hasNextInt()) {
                System.out.println("Error: Please enter an integer.");
                scnInput.next();
                continue;
            }

            intStartingCity = scnInput.nextInt();

            if (intStartingCity < 0 || intStartingCity >= intCityNumber) {
                System.out.println("Error: Starting city must be between 0 and " + (intCityNumber - 1) + ".");
            } else {
                break;
            }
        }

        return intStartingCity;
    }

    private static void CityDistancesList(Scanner scnInput){
        arrCityDistances = new int[intCityNumber][intCityNumber];

        for (int i = 0; i < intCityNumber; i++) {
            arrCityDistances[i][i] = 0;
        }

        System.out.println("\nEnter the distances between each city pair:");

        for (int i = 0; i < intCityNumber; i++) {

            for (int j = i + 1; j < intCityNumber; j++) {
                
                while (true) {
                    System.out.print("Distance from city " + i + " to city " + j + ": ");
                    if (!scnInput.hasNextInt()) {
                        System.out.println("Error: Please enter an integer.");
                        scnInput.next();
                        continue;
                    }

                    int intDistance = scnInput.nextInt();

                    if (intDistance < 0) {
                        System.out.println("Error: Distances cannot be negative.");

                    } else {
                        arrCityDistances[i][j] = intDistance;
                        arrCityDistances[j][i] = intDistance;
                        break;
                    }
                }
            }
        }
    }

    private static void FindBestRoute(int intPos) {

        if (intPos == intCityNumber) {
            intTourCount++; 
            int intCurLength = calculateTourLength();
            
            System.out.print("Route " + intTourCount + ": ");

            for (int i = 0; i <= intCityNumber; i++) {
                System.out.print(arrCurrentTour[i] + (i < intCityNumber ? " -> " : ""));
            }

            System.out.println(" | Distance: " + intCurLength);

            if (intCurLength < intBestLength) {
                intBestLength = intCurLength;
                System.arraycopy(arrCurrentTour, 0, arrBestTour, 0, intCityNumber + 1);
            }

            return;
        }

        for (int i = 0; i < intCityNumber; i++) {
            
            if (!boolVisited[i]) {
                boolVisited[i] = true;
                arrCurrentTour[intPos] = i;
                FindBestRoute(intPos + 1);
                boolVisited[i] = false;

            }
        }
    }

    private static int calculateTourLength() {
        int intLength = 0;

        for (int i = 0; i < intCityNumber; i++) {
            intLength += arrCityDistances[arrCurrentTour[i]][arrCurrentTour[i + 1]];
        }

        return intLength;
    }
}