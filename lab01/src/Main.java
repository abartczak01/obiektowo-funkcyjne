import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            String input = showMenu(userInputScanner);
            switch (input) {
                case "0" -> running = false;
                case "1" -> handleFactorial(userInputScanner);
                case "2" -> handleSum(userInputScanner);
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        userInputScanner.close();
    }

    public static String showMenu(Scanner userInputScanner) {
        System.out.println("Choose an option:");
        System.out.println("0. Exit");
        System.out.println("1. Find factorial of a number");
        System.out.println("2. Find sum");
        System.out.print("Enter your choice: ");
        return userInputScanner.nextLine();
    }
    
    public static void handleFactorial(Scanner userInputScanner) {
        System.out.print("Enter a number to find factorial: ");
        int number = userInputScanner.nextInt();
        userInputScanner.nextLine();
        int factorialResult = Calculations.findFactorial(number);
        System.out.println(String.format("Factorial of %1$s is %2$s\n", number, factorialResult));
    }
    
    public static void handleSum(Scanner userInputScanner) {
        System.out.println("Enter first number: ");
        int firstNumber = userInputScanner.nextInt();
        userInputScanner.nextLine();
        System.out.println("Enter second number: ");
        int secondNumber = userInputScanner.nextInt();
        userInputScanner.nextLine();

        if (firstNumber < secondNumber) {
            int sumResult = Calculations.findSum(firstNumber, secondNumber);
            System.out.println(String.format("Sum of ints from [%1$s, %2$s] is %3$s\n", firstNumber, secondNumber, sumResult));
        } else {
            System.out.println(String.format("Wrong numbers. Second number (%2$s) should be bigger than first number (%1$s)", firstNumber, secondNumber));
        }
    }
}
