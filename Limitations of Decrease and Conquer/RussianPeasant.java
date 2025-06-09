import java.util.Scanner;

public class RussianPeasant {
    
    static int countDecimalPlaces(double num) {
        double absNum;
        if (num < 0) {
            absNum = -num;
        } else {
            absNum = num;
        }
        String str = String.valueOf(absNum);
        // Handle scientific notation
        if (str.contains("E") || str.contains("e")) {
            return 0; // Treat as whole number for very large/small numbers
        }
        if (str.contains(".")) {
            return str.length() - str.indexOf('.') - 1;
        }
        return 0;
    }
    
    static boolean isInteger(double num) {
        return num == (long)num;
    }
    
    static long russianMultiplicationInteger(long num1, long num2) {
        long sum = 0;
        boolean isNegative = false;
        
        long int1, int2;
        if (num1 < 0) {
            int1 = -num1;
            isNegative = !isNegative;
        } else {
            int1 = num1;
        }
        
        if (num2 < 0) {
            int2 = -num2;
            isNegative = !isNegative;
        } else {
            int2 = num2;
        }
        
        System.out.println("Working with integers: " + int1 + " x " + int2);
        System.out.println("Steps:");
        
        while (int2 > 0) {
            if (int2 % 2 != 0){
                System.out.println(int1 + " | " + int2 + " (odd - add " + int1 + ")");
                sum += int1;
            } else {
                System.out.println(int1 + " | " + int2 + " (even - skip)");
            }
            
            int1 *= 2;
            int2 /= 2;
        }
        
        if (isNegative) {
            sum = -sum;
        }
        
        return sum;
    }
    
    static long decimalToInt(double decimal) {
        int decimalPlaces = countDecimalPlaces(decimal);
        // Scale the number by multiplying by 10^decimalPlaces manually
        double absDecimal;
        if (decimal < 0) {
            absDecimal = -decimal;
        } else {
            absDecimal = decimal;
        }
        double scaled = absDecimal;
        for (int i = 0; i < decimalPlaces; i++) {
            scaled *= 10;
        }
        return (long)(scaled + 0.5);
    }
    
    static double intToDecimal(long integer, int decimalPlaces) {
        if (decimalPlaces == 0) {
            return (double) integer;
        }
        // Scale back down by dividing by 10^decimalPlaces manually
        double result = (double) integer;
        for (int i = 0; i < decimalPlaces; i++) {
            result /= 10;
        }
        return result;
    }

    static double russianMultiplication(double num1, double num2) {
        
        // Check if both numbers are integers
        if (isInteger(num1) && isInteger(num2)) {
            long result = russianMultiplicationInteger((long)num1, (long)num2);
            return (double)result;
        }
        
        int decimalPlaces1 = countDecimalPlaces(num1);
        int decimalPlaces2 = countDecimalPlaces(num2);
        int totalDecimalPlaces = decimalPlaces1 + decimalPlaces2;
        
       
        long int1 = decimalToInt(num1);
        long int2 = decimalToInt(num2);
        
        long sum = 0;
        boolean isNegative = false;
        
        
        if (num1 < 0) {
            isNegative = !isNegative;
        }
        if (num2 < 0) {
            isNegative = !isNegative;
        }
        
        System.out.println("Convert into integers: " + int1 + " x " + int2);
        System.out.println("Steps:");
        
        while (int2 > 0) {
            if (int2 % 2 != 0){
                System.out.println(int1 + " | " + int2 + " (odd - add " + int1 + ")");
                sum += int1;
            } else {
              
                System.out.println(int1 + " | " + int2 + " (even - skip)");
            }
            
            int1 *= 2;
            int2 /= 2;
        }
        
        
        double result = intToDecimal(sum, totalDecimalPlaces);
        if (isNegative) {
            result = -result;
        }
        
        return result;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        while (true) {
            System.out.print("Enter your first number: ");
            double num1 = scan.nextDouble();
            System.out.print("Enter your second number: ");
            double num2 = scan.nextDouble();
            
            System.out.println("\nCalculating " + num1 + " x " + num2);
            double product = russianMultiplication(num1, num2);
            
            System.out.println("The product is " + product);
            
            
            double verification = num1 * num2;
            System.out.println("\nVerification: " + num1 + " x " + num2 + " = " + verification);
            
            System.out.print("\nDo you want to continue? (1-Yes, 0-No): ");
            int choice = scan.nextInt();
            if (choice == 0) {
                scan.close();
                return;
            }
            System.out.println();
        }
    }
}