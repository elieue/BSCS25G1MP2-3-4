import java.util.Scanner;

public class RussianMultiplication{

    static int russianPeasant(int num1, int num2){
        int sum = 0;
        boolean isNegative = false;
        
        if(num1 < 0){
            num1*=-1;
            isNegative = true;
        }
        if(num2 < 0){
            num2*=-1;
            isNegative = true;
        }
        if(num1 > 0 && num2 > 0){
            isNegative = false;
        }
        while(num2 > 0){
            if(num2 % 2 != 0){
                System.out.println(num1 + " " + num2);
                sum += num1;
            }else {
                System.out.println(num1 + "x " + num2 + "x");
            }

            num1 *= 2;
            num2 /= 2;

        }
        
        if(isNegative){
            sum*=-1;
        }
        

        return sum;

    }

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
       
        while(true){
        System.out.print("Enter your first number: ");
        int num1 = scan.nextInt();
        System.out.print("Enter your second number: ");
        int num2 = scan.nextInt();

        int sum = russianPeasant(num1, num2);

        System.out.println("The product is " + sum);
        System.out.print("Do you want to continue?(1-Yes, 0-No) ");
        int choice = scan.nextInt();
            if(choice == 0){
                scan.close();
                return;
            }

        
        }

    }
}