import java.util.Scanner;
public class App {
    private static Scanner userInputScanner = new Scanner(System.in);
    private static GradeList grades = new GradeList();
    public static void main(String[] args) throws Exception {
        System.out.println("Program to manage grades :-)");

        boolean running = true;
        while (running) {
            String input = showMenu();
            System.out.println("");
            switch (input) {
                case "0" -> running = false;
                case "1"-> handleAddingGrade();
                case "2" -> handleAverage();
                case "3" -> handleBestGrade();
                case "4" -> handleWorstGrade();
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static String showMenu() {
        System.out.println("Choose an option:");
        System.out.println("0. Exit");
        System.out.println("1. Add new grade");
        System.out.println("2. Calculate average");
        System.out.println("3. Show the best grade");
        System.out.println("4. Show the worst grade");
        System.out.print("Enter your choice: ");
        return userInputScanner.nextLine();
    }

    public static void handleAddingGrade() {
        System.out.println("Enter a grade: ");
        double newGrade = Double.parseDouble(userInputScanner.nextLine());
        if (newGrade < 0) {
            System.out.println("Grade cannot be smaller then 0");
        } else {
            grades.addGrade(newGrade);
            System.out.println("Grade "+ newGrade + " added");
        }
    }

    public static void handleBestGrade() {
        double grade = grades.getBestGrade();
        if (grade == -1) {
            System.out.println("Grades list is empty");
        } else {
            System.out.println("The best grade: " + grades.getBestGrade());
        }
    }

    public static void handleWorstGrade() {
        double grade = grades.getBestGrade();
        if (grade == -1) {
            System.out.println("Grades list is empty");
        } else {
            System.out.println("The worst grade: " + grades.getWorstGrade());
        }
    }

    public static void handleAverage() {
        double grade = grades.getBestGrade();
        if (grade == -1) {
            System.out.println("Grades list is empty");
        } else {
            System.out.println("Average: " + grades.getAverage());
        }
    }

}
