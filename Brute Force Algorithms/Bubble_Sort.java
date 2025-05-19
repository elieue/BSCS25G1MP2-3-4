import java.util.Arrays;
import java.util.Scanner;

public class Bubble_Sort {
    public static int[] bubbleSort(int[] intArr, int intElements){
        int[] intCopy = Arrays.copyOf(intArr, intElements);
        boolean flag;
        printArray("Unsorted Array", intArr, intElements);
        for(int i = 0; i < intElements; i++){
            flag = false;
            for(int j = 0; j < intElements - i - 1; j++){
                if(intCopy[j] > intCopy[j + 1]){
                    int Temp = intCopy[j];
                    intCopy[j] = intCopy[j + 1];
                    intCopy[j + 1] = Temp;
                    flag = true;
                }
            }
            if(flag == false){
                return intCopy;
            }
            printArray("Turn " + String.format("%d", i + 1), intCopy, intElements);
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
