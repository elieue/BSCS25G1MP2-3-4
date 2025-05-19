import java.util.Arrays;
import java.util.Scanner;

public class Optimized_Bubble_Sort {
    public static int[] bubbleSort(int[] intArr, int intElements){
        int[] intCopy = Arrays.copyOf(intArr, intElements);
        int intMag = intElements;
        int intTurn = 1;
        boolean flag;
        printArray("Unsorted Array", intArr, intElements);

        while (intMag > 1){
            int intTracker = 0;
            int intRef = 0;
            flag = false;
            for(int j = 0; j < intMag - 1; j++){
                if(intCopy[intRef] > intCopy[j + 1]){
                    int Temp = intCopy[intTracker];
                    intCopy[intTracker] = intCopy[j + 1];
                    intCopy[j + 1] = Temp;

                    intRef++;
                    intTracker++;
                } else if(intCopy[intRef] == intCopy[j + 1]){
                    intRef = j + 1;
                } else {
                    intRef = j + 1;
                    intTracker = j + 1;
                }
                if(flag == false){
                    return intCopy;
                }
                printArray("", intCopy, intElements);
            }

            int intNSize = (intRef - intTracker) + 1;
            if(intNSize <= 0) break;
            intMag = intMag - intNSize;
            printArray("Turn " + String.format("%d", intTurn), intCopy, intElements);
            intTurn++;
        }
        return intCopy;
    }

    public static void printArray(String strTurn, int[] intArr, int intElements){
        if(!strTurn.equals("")){
            System.out.print(strTurn + ": ");
        }
        for(int intIndex = 0; intIndex < intElements; intIndex++){
            if(intIndex == intElements - 1){
                System.out.print(intArr[intIndex] + "\n");
            } else {
                System.out.print(intArr[intIndex] + ", ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanInput = new Scanner(System.in);
        int intElements;
        int[] intArr;
        String strChoice;

        do{
            System.out.println("How many elements do you want your array to have? Maximum of 10 elements.");
            intElements = scanInput.nextInt();
            if(intElements >= 1 && intElements <= 10){
                intArr = new int[intElements];
                System.out.println("Array of size " + intElements + " succesfully created. Please input your elements.");
                for(int intIndex = 0; intIndex < intElements; intIndex++){
                    intArr[intIndex] = scanInput.nextInt();
                }
                intArr = bubbleSort(intArr, intElements);
            } else { System.out.println("Invalid input. Please try again."); }

            System.out.println("Do you want to try again? [Y/N]");
            strChoice = scanInput.next();
            if(!strChoice.equalsIgnoreCase("N") && !strChoice.equalsIgnoreCase("Y")){
                System.out.println("Invalid input. Terminating program...");
                return;
            }
        } while (!strChoice.equalsIgnoreCase("N"));

        scanInput.close();
        return;
    }
}
