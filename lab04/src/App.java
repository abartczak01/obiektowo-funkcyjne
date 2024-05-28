import java.util.Scanner;
import java.time.LocalTime;

public class App {
    private static Scanner userInputScanner = new Scanner(System.in);
    private static Kalendarz calendar = new Kalendarz();

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to your calendar app! :-)");
        boolean running = true;

        while (running) {
            String input = showMenu();
            System.out.println("");
            switch (input) {
                case "0" -> running = false;
                case "1" -> handleAdding();
                case "2" -> handleCancel();
                case "3" -> handleGetByPriority();
                case "4" -> handleGetByTime();
                case "5" -> handleDisplayMeetings();
                case "6" -> calendar.addDefaultMondayMeetings();
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static String showMenu() {
        System.out.println("Choose an option:");
        System.out.println("0. Exit");
        System.out.println("1. Add meeting");
        System.out.println("2. Cancel meeting");
        System.out.println("3. Show meetings filtered by priority");
        System.out.println("4. Show meetings filtered by time");
        System.out.println("5. Show meetings by day");
        System.out.println("6. Add example meetings");
        System.out.print("Enter your choice: ");
        return userInputScanner.nextLine();
    }

    public static int readDay() {
        int dayNumber;
        do {
            System.out.println("Choose a day:");
            System.out.println("0. Monday");
            System.out.println("1. Tuesday");
            System.out.println("2. Wednesday");
            System.out.println("3. Thursday");
            System.out.println("4. Friday");
            System.out.println("5. Saturday");
            System.out.println("6. Sunday");
            System.out.print("Enter the number of the day: ");

            dayNumber = Integer.parseInt(userInputScanner.nextLine());

            if (dayNumber < 0 || dayNumber > 6) {
                System.out.println("invalid day number");
            }
        } while (dayNumber < 0 || dayNumber > 6);

        return dayNumber;
    }

    public static LocalTime readTime() {
        LocalTime time;
        do {
            System.out.print("Enter the time (HH:MM): ");
            String timeString = userInputScanner.nextLine();
            time = LocalTime.parse(timeString);

            if (time.isBefore(Spotkanie.EARLIEST)) {
                System.out.println("Meeting cannot start earlier than " + Spotkanie.EARLIEST);
            }
        } while (time.isBefore(Spotkanie.EARLIEST));

        return time;
    }

    public static boolean isValidPriority(String priority) {
        return priority.equals("normal") || priority.equals("high") || priority.equals("the highest");
    }

    public static String readPriority() {
        String priority;
        do {
            System.out.print("Enter the priority of the meeting: (normal, high, the highest) ");
            priority = userInputScanner.nextLine();
            if (!isValidPriority(priority)) {
                System.out.println("Invalid priority. Please enter either 'normal', 'high', or 'the highest'.");
            }
        } while (!isValidPriority(priority));
        return priority;
    }

    public static void handleAdding() {
        int day = readDay();
    
        System.out.print("Enter the description of the meeting: ");
        String description = userInputScanner.nextLine();
    
        LocalTime beginning = readTime();
    
        System.out.print("Enter the end time of the meeting (HH:MM): ");
        LocalTime end = LocalTime.parse(userInputScanner.nextLine());
    
        String priority = readPriority();
    
        Spotkanie newMeeting = new Spotkanie(description, beginning, end, priority);
        calendar.addMeeting(day, newMeeting);
    }

    public static void handleCancel(){
        int day = readDay();
        var meetings = calendar.getMeetingsByDay(day);
        for (int i = 0; i < meetings.size(); i++) {
            System.out.println(i + "." + meetings.get(i).getAllProperties());
        }
        if (meetings.isEmpty()){
            System.out.println("no meetings on this day");
        } else {
            int meetingIndex;
            do {
                System.out.print("Enter the meeting index (0 - " + (meetings.size() - 1) + "): ");
                meetingIndex = Integer.parseInt(userInputScanner.nextLine());
                if (meetingIndex < 0 || meetingIndex > meetings.size() - 1) {
                    System.out.println("invalid meeting index");
                }
            } while (meetingIndex < 0 || meetingIndex > meetings.size() - 1);
            calendar.cancelMeeting(day, meetingIndex);
        }
        
    }

    public static void handleGetByPriority(){
        int day = readDay();
        var meetings = calendar.getMeetingsByDay(day);
        if (meetings.isEmpty()){
            System.out.println("no meeting on this day");
        } else {
            String priority = readPriority();
            var filteredMeetings = calendar.getMeetingsByPriority(day, priority);
            for (int i = 0; i < filteredMeetings.size(); i++) {
                System.out.println(i + "." + filteredMeetings.get(i).getAllProperties());
            }
        }
    }

    public static void handleGetByTime(){
        int day = readDay();
        System.out.print("Enter the beginning of meeting (in format HH:MM): ");
        LocalTime beginningTime = LocalTime.parse(userInputScanner.nextLine());
        var meetingsByTime = calendar.getMeetingsByTime(day, beginningTime);
        if (meetingsByTime.isEmpty()){
            System.out.println("No meetings on this day fulfill the condition");
        }
        for (int i = 0; i < meetingsByTime.size(); i++) {
            System.out.println(i + "." + meetingsByTime.get(i).getAllProperties());
        }
    }

    public static void handleDisplayMeetings(){
        int day = readDay();
        var meetings = calendar.getMeetingsByDay(day);
        for (int i = 0; i < meetings.size(); i++) {
            System.out.println(i + "." + meetings.get(i).getAllProperties());
        }
    }


}
