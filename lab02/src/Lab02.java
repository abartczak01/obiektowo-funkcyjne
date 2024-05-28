import java.util.Scanner;

public class Lab02 {
    private static Scanner userInputScanner = new Scanner(System.in);
    private static Walec walec = new Walec();
    public static void main(String[] args) throws Exception {
        
        boolean running = true;
        System.out.println("Program for calculating the area and volume of a cylinder");
        while (running) {
            String input = showMenu();
            System.out.println("");
            switch (input) {
                case "0" -> running = false;
                case "1" -> handleSettingHeigth();
                case "2" -> handleSettingRadius();
                case "3" -> System.out.println("Height: " + walec.getHeight());
                case "4" -> System.out.println("Radius: " + walec.getRadius()); 
                case "5" -> handleCalculatingAreas();
                case "6" -> System.out.println("Volume: " + walec.volume());
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static String showMenu() {
        System.out.println("Choose an option:");
        System.out.println("0. Exit");
        System.out.println("1. Set height");
        System.out.println("2. Set radius");
        System.out.println("3. Get height");
        System.out.println("4. Get radius");
        System.out.println("5. Calculate area");
        System.out.println("6. Calculate volume");
        System.out.print("Enter your choice: ");
        return userInputScanner.nextLine();
    }

    public static void handleSettingHeigth() {
        System.out.print("Enter the new height: ");
        double newHeight = Double.parseDouble(userInputScanner.nextLine());
        walec.setHeight(newHeight);
    }

    public static void handleSettingRadius() {
        System.out.print("Enter the new radius: ");
        double newRadius = Double.parseDouble(userInputScanner.nextLine());
        walec.setRadius(newRadius);
    }

    public static void handleCalculatingAreas() {
        System.out.println("Base area: " + walec.areaOfBase());
        System.out.println("Lateral area: " + walec.lateralArea()); 
        System.out.println("Total area: " + walec.totalArea());
    }
}
