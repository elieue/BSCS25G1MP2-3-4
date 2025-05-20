import java.util.*;

public class Opt_Travelling_Salesman {
    public static void main(String[] args) {
        Scanner strScanner = new Scanner(System.in);
        
        int intCityCount = getCityCount(strScanner);

        int intStartCity = getStartCity(strScanner, intCityCount);
        
        int[][] intDistances = getCicyDistances(strScanner, intCityCount);
        
        displayCityDistances(intDistances, intCityCount);
        
        System.out.println("\n-----------------------------------------------------");
        System.out.println("--- Step 1: Creating a Nearest Neighbor Tour ---");
        System.out.println("-----------------------------------------------------");
        System.out.println("The Nearest Neighbor algorithm builds an initial tour by:");
        System.out.println("1. Starting at the specified city");
        System.out.println("2. Repeatedly visiting the closest unvisited city");
        System.out.println("3. Finally returning to the starting city\n");
        
        int[] intInitialTour = constructNearestNeighborTour(intDistances, intCityCount, intStartCity);
        int intInitialLength = calculateTourLength(intInitialTour, intDistances, intCityCount);
        
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("--- Step 2: Improving the Nearest Neighbor Tour using 2-Opt ---");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("The 2-Opt algorithm improves the tour by:");
        System.out.println("1. Looking for pairs of edges where removing them would create a shorter path");
        System.out.println("2. Reversing the segment between these edges to create a better tour");
        System.out.println("3. Repeating until no more improvements can be made\n");
        
        int[] intImprovedTour = apply2OptImprovement(intDistances, intCityCount, intInitialTour);
        int intFinalLength = calculateTourLength(intImprovedTour, intDistances, intCityCount);
        
        displayFinalResults(intInitialTour, intImprovedTour, intInitialLength, intFinalLength, intCityCount);
        
        strScanner.close();
    }
    
    private static int getCityCount(Scanner strScanner) {
        int intCityCount = 0;
        
        while (true) {
            System.out.print("Enter the number of cities: ");
            try {
                intCityCount = Integer.parseInt(strScanner.nextLine());
                if (intCityCount < 3) {
                    System.out.println("Error: At least 3 cities required.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number.");
            }
        }
        
        return intCityCount;
    }
    
    private static int[][] getCicyDistances(Scanner strScanner, int intCityCount) {
        int[][] intDistances = new int[intCityCount][intCityCount];
        
        System.out.println("\nEnter the distances between each city pair:");
        
        for (int i = 0; i < intCityCount; i++) {
            intDistances[i][i] = 0;
            
            for (int j = i + 1; j < intCityCount; j++) {
                while (true) {
                    System.out.print("Distance from city " + i + " to city " + j + ": ");
                    try {
                        int intDistance = Integer.parseInt(strScanner.nextLine());
                        if (intDistance < 0) {
                            System.out.println("Distance must be non-negative.");
                        } else {
                            intDistances[i][j] = intDistance;
                            intDistances[j][i] = intDistance;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                    }
                }
            }
        }
        
        return intDistances;
    }
    
    private static void displayCityDistances(int[][] intDistances, int intCityCount) {
        System.out.println("\n--- Complete List of Distances ---");
        System.out.println("(This shows distances between all city pairs)");
        
        System.out.print("      ");
        for (int j = 0; j < intCityCount; j++) {
            System.out.printf("City %-4d", j);
        }
        System.out.println();
        
        System.out.print("      ");
        for (int j = 0; j < intCityCount; j++) {
            System.out.print("--------");
        }
        System.out.println();
        
        for (int i = 0; i < intCityCount; i++) {
            System.out.printf("City %d| ", i);
            for (int j = 0; j < intCityCount; j++) {
                if (i == j) {
                    System.out.print(String.format(" %4d    ", 0));
                } else {
                    System.out.printf(" %4d    ", intDistances[i][j]);
                }
            }
            System.out.println();
        }
    }
    
    private static int getStartCity(Scanner strScanner, int intCityCount) {
        int intStartCity = -1;
        
        while (true) {
            System.out.print("\nEnter the starting city index (0 to " + (intCityCount - 1) + "): ");
            try {
                intStartCity = Integer.parseInt(strScanner.nextLine());
                if (intStartCity < 0 || intStartCity >= intCityCount) {
                    System.out.println("Invalid index.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
        
        return intStartCity;
    }

    
    private static int[] constructNearestNeighborTour(int[][] intDistances, int intCityCount, int intStartCity) {
        int[] intTour = new int[intCityCount + 1];
        boolean[] boolVisited = new boolean[intCityCount];
        
        int intCurrentCity = intStartCity;
        intTour[0] = intCurrentCity;
        boolVisited[intCurrentCity] = true;
        
        System.out.println("STEP 1: Starting at city " + intCurrentCity);
        System.out.print("Current partial tour: [" + intTour[0] + "]");
        
        int intTourPos = 1;
        int intStep = 2;
        
        while (intTourPos < intCityCount) {
            System.out.println("\n\nSTEP " + intStep + ": Finding closest city to city " + 
                            intCurrentCity);
            
            int intMinDist = Integer.MAX_VALUE;
            int intNextCity = -1;
            
            System.out.println("Evaluating distances from city " + intCurrentCity + " to all unvisited cities:");
            
            for (int i = 0; i < intCityCount; i++) {
                if (!boolVisited[i]) {
                    System.out.print("  Distance to city " + i + ": " + intDistances[intCurrentCity][i]);
                    if (intDistances[intCurrentCity][i] < intMinDist) {
                        intMinDist = intDistances[intCurrentCity][i];
                        intNextCity = i;
                        System.out.print(" (new closest)");
                    }
                    System.out.println();
                }
            }
            
            intTour[intTourPos] = intNextCity;
            boolVisited[intNextCity] = true;
            System.out.println("Closest city is " + intNextCity + 
                             " with distance " + intMinDist);
            
            System.out.print("Current partial tour: [" + intTour[0]);
            for (int i = 1; i <= intTourPos; i++) {
                System.out.print(" --> " + intTour[i]);
            }
            System.out.println("]");
            
            intCurrentCity = intNextCity;
            intTourPos++;
            intStep++;
        }
        
        System.out.println("\nSTEP " + intStep + 
                         ": Completing tour by returning to starting city " + intStartCity);
        System.out.println("Distance from city " + intCurrentCity + " back to city " + intStartCity + ": " + 
                         intDistances[intCurrentCity][intStartCity]);
        
        intTour[intTourPos] = intStartCity;
        
        System.out.print("Complete tour: [" + intTour[0]);
        for (int i = 1; i <= intTourPos; i++) {
            System.out.print(" --> " + intTour[i]);
        }
        System.out.println("]");
        
        int intTourLength = calculateTourLength(intTour, intDistances, intCityCount);
        System.out.println("\nInitial Tour Length: " + intTourLength);
        
        return intTour;
    }
    
    private static int[] apply2OptImprovement(int[][] intDistances, int intCityCount, int[] intInitialTour) {
        int[] intImprovedTour = Arrays.copyOf(intInitialTour, intInitialTour.length);
        int intInitialLength = calculateTourLength(intImprovedTour, intDistances, intCityCount);
        
        System.out.print("Starting 2-Opt with initial tour: [" + intImprovedTour[0]);
        for (int i = 1; i <= intCityCount; i++) {
            System.out.print(" --> " + intImprovedTour[i]);
        }
        System.out.println("]");
        System.out.println("Initial tour length: " + intInitialLength);
        
        boolean boolImproved = true;
        int intIteration = 0;
        
        while (boolImproved) {
            boolImproved = false;
            intIteration++;
            System.out.println("\n--- 2-OPT ITERATION " + intIteration + " ---");
            
            int intImprovementCount = 0;
            
            for (int i = 1; i < intCityCount - 1; i++) {
                for (int j = i + 1; j < intCityCount; j++) {
                    int intCity1 = intImprovedTour[i - 1];
                    int intCity2 = intImprovedTour[i];
                    int intCity3 = intImprovedTour[j];
                    int intCity4 = intImprovedTour[j + 1];
                    
                    int intCurrentSum = intDistances[intCity1][intCity2] + intDistances[intCity3][intCity4];
                    int intNewSum = intDistances[intCity1][intCity3] + intDistances[intCity2][intCity4];
                    int intImprovement = intCurrentSum - intNewSum;
                    
                    if (intImprovement > 0) {
                        intImprovementCount++;
                        
                        System.out.println("\nFound improving 2-opt swap #" + 
                                      intImprovementCount + " in this iteration:");
                        
                        System.out.println("Current edges: (" + intCity1 + "-" + intCity2 + ") + (" + 
                                        intCity3 + "-" + intCity4 + ") = " + 
                                        intDistances[intCity1][intCity2] + " + " + 
                                        intDistances[intCity3][intCity4] + " = " + 
                                        intCurrentSum);
                        
                        System.out.println("New edges: (" + intCity1 + "-" + intCity3 + ") + (" + 
                                        intCity2 + "-" + intCity4 + ") = " + 
                                        intDistances[intCity1][intCity3] + " + " + 
                                        intDistances[intCity2][intCity4] + " = " + 
                                        intNewSum);
                        
                        System.out.println("Improvement: " + intImprovement);
                        
                        int intBeforeLength = calculateTourLength(intImprovedTour, intDistances, intCityCount);
                        
                        System.out.print("Before swap: [" + intImprovedTour[0]);
                        for (int k = 1; k <= intCityCount; k++) {
                            System.out.print(" --> " + intImprovedTour[k]);
                        }
                        System.out.println("] (length: " + intBeforeLength + ")");
                        
                        System.out.println("Reversing segment between positions " + i + " and " + j);
                        System.out.print("  Swapping: ");
                        
                        int start = i;
                        int end = j;
                        while (start < end) {
                            System.out.print("(" + intImprovedTour[start] + "<-->" + intImprovedTour[end] + ") ");
                            int temp = intImprovedTour[start];
                            intImprovedTour[start] = intImprovedTour[end];
                            intImprovedTour[end] = temp;
                            start++;
                            end--;
                        }
                        System.out.println();
                        
                        int intAfterLength = calculateTourLength(intImprovedTour, intDistances, intCityCount);
                        
                        System.out.print("After swap:  [" + intImprovedTour[0]);
                        for (int k = 1; k <= intCityCount; k++) {
                            System.out.print(" --> " + intImprovedTour[k]);
                        }
                        System.out.println("] (length: " + intAfterLength + ")");
                        
                        boolImproved = true;
                    }
                }
            }
            
            if (!boolImproved) {
                System.out.println("No improving swaps found in this iteration.");
                System.out.println("2-Opt optimization complete.");
            } else {
                int intCurrentLength = calculateTourLength(intImprovedTour, intDistances, intCityCount);
                
                System.out.println("\nSummary of iteration " + intIteration + ":");
                System.out.println("Number of improvements: " + intImprovementCount);
                System.out.println("Current tour length: " + intCurrentLength);
            }
        }
        
        return intImprovedTour;
    }
    
    private static int calculateTourLength(int[] intTour, int[][] intDistances, int intCityCount) {
        int intTourLength = 0;
        for (int i = 0; i < intCityCount; i++) {
            intTourLength += intDistances[intTour[i]][intTour[i + 1]];
        }
        return intTourLength;
    }
    
    private static void displayFinalResults(int[] intInitialTour, int[] intImprovedTour, 
                                       int intInitialLength, int intFinalLength, int intCityCount) {
        int intImprovement = intInitialLength - intFinalLength;
        int intPercentImprovement = intImprovement * 100 / intInitialLength;
        
        System.out.println("\n=== FINAL RESULTS ===");
        
        System.out.print("Initial Tour (Nearest Neighbor): [" + intInitialTour[0]);
        for (int i = 1; i <= intCityCount; i++) {
            System.out.print(" --> " + intInitialTour[i]);
        }
        System.out.println("]");
        System.out.println("Initial Tour Length: " + intInitialLength);
        
        System.out.print("Final Tour (After 2-Opt): [" + intImprovedTour[0]);
        for (int i = 1; i <= intCityCount; i++) {
            System.out.print(" --> " + intImprovedTour[i]);
        }
        System.out.println("]");
        System.out.println("Final Tour Length: " + intFinalLength);
        
        System.out.println("Improvement: " + intImprovement + 
                         " (" + intPercentImprovement + "%)");
    }
}