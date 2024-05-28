import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
import java.time.LocalTime;
public class App {
    private static Scanner userInputScanner = new Scanner(System.in);
    private static Kalendarz calendar = new Kalendarz();
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to your calendar app! :-)");
        calendar.addDefaultMondayEvents();
        boolean running = true;

        while (running) {
            String input = showMenu();
            System.out.println("");
            switch (input) {
                case "0" -> running = false;
                case "1" -> addMeeting();
                case "2" -> addTask();
                case "3" -> removeMeeting();
                case "4" -> removeTask();
                case "5" -> displayMeetings();
                case "6" -> displayTasks();
                case "7" -> getMeetingsByPriority();
                case "8" -> getTasksByStatus();
                case "9" -> getMeetingsByPriorityAndTime();
                case "10" -> getTasksByStatusAndTime();
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static String showMenu() {
        System.out.println("Choose an option:");
        System.out.println("0. Exit");
        System.out.println("1. Add meeting");
        System.out.println("2. Add task");
        System.out.println("3. Remove meeting");
        System.out.println("4. Remove task");
        System.out.println("5. Show meetings filtered by day");
        System.out.println("6. Show tasks filtered by day");
        System.out.println("7. Show meetings filtered by priority");
        System.out.println("8. Show tasks filtered by status");
        System.out.println("9.  Show meetings filtered by priority and time");
        System.out.println("10.  Show tasks filtered by status and time");        

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

    public static boolean isValidStatus(String priority) {
        return priority.equals("planned") || priority.equals("confirmed") || priority.equals("ongoing") || priority.equals("completed");
    }

    public static String readStatus() {
        String priority;
        do {
            System.out.print("Enter the status of the task: (planned, confirmed, ongoing, completed) ");
            priority = userInputScanner.nextLine();
            if (!isValidStatus(priority)) {
                System.out.println("Invalid status. Please enter either 'planned', 'confirmed', 'ongoing', 'completed'.");
            }
        } while (!isValidStatus(priority));
        return priority;
    }

    public static void displayMeetings(){
        int day = readDay();
        ArrayList<Wydarzenie> meetings = calendar.filterEvents(day, event -> event instanceof Spotkanie);
        displayEvents(meetings);
    }

    public static void displayTasks(){
        int day = readDay();
        ArrayList<Wydarzenie> tasks = calendar.filterEvents(day, event -> event instanceof Zadanie);
        displayEvents(tasks);
    }

    public static void displayEvents(ArrayList<Wydarzenie> events){
        for (int i = 0; i < events.size(); i++) {
            System.out.println(i + ". " + events.get(i).toString());
        }
    }

    public static void addMeeting() {
        int day = readDay();
    
        System.out.print("Enter the description of the meeting: ");
        String description = userInputScanner.nextLine();
    
        LocalTime beginning = readTime();
    
        System.out.print("Enter the end time of the meeting (HH:MM): ");
        LocalTime end = LocalTime.parse(userInputScanner.nextLine());
    
        String priority = readPriority();
    
        Spotkanie newMeeting = new Spotkanie(description, beginning, end, priority);
        calendar.addEvent(day, newMeeting);
    }


    public static void addTask() {
        int day = readDay();
    
        System.out.print("Enter the description of the task: ");
        String description = userInputScanner.nextLine();
    
        LocalTime beginning = readTime();
    
        System.out.print("Enter the end time of the task (HH:MM): ");
        LocalTime end = LocalTime.parse(userInputScanner.nextLine());
    
        String status = readStatus();
    
        Zadanie newMeeting = new Zadanie(description, beginning, end, status);
        calendar.addEvent(day, newMeeting);        
    }


    public static void displayToRemove(int day, boolean isTask){
        ArrayList<Wydarzenie> events = calendar.filterEvents(day, event -> true);
        
        for (int i = 0; i < events.size(); i++) {
            if (isTask){
                if (events.get(i) instanceof Zadanie){
                    System.out.println(i + ". " + events.get(i).toString());
                }
            } else {
                if (events.get(i) instanceof Spotkanie){
                    System.out.println(i + ". " + events.get(i).toString());
                }
            }
        }
    }

    public static void removeMeeting(){
        int day = readDay();
        var meetings = calendar.filterEvents(day, event -> event instanceof Spotkanie);
        displayToRemove(day, false);
        if (meetings.isEmpty()){
            System.out.println("no meetings on this day");
        } else {
            int meetingIndex;
            System.out.print("Enter the meeting index: ");
            meetingIndex = Integer.parseInt(userInputScanner.nextLine());
            calendar.removeEvent(day, meetingIndex);
        }
    }

    public static void removeTask(){
        int day = readDay();
        var meetings = calendar.filterEvents(day, meeting -> true);
        displayToRemove(day, true);
        if (meetings.isEmpty()){
            System.out.println("no tasks on this day");
        } else {
            int meetingIndex;
            System.out.print("Enter the task index: ");
            meetingIndex = Integer.parseInt(userInputScanner.nextLine());
            calendar.removeEvent(day, meetingIndex);
        }
    }

    public static void getMeetingsByPriority(){
        int day = readDay();
        String priority = readPriority();
        Predicate<Wydarzenie> priorityPredicate = meeting -> meeting instanceof Spotkanie && ((Spotkanie) meeting).getPriority().equals(priority);
        var filteredMeetings = calendar.filterEvents(day, priorityPredicate);
        displayEvents(filteredMeetings);
    }

    public static void getTasksByStatus() {
        int day = readDay();
        String status = readStatus();
        Predicate<Wydarzenie> statusPredicate = meeting -> meeting instanceof Zadanie && ((Zadanie) meeting).getStatus().equals(status);
        var filteredMeetings = calendar.filterEvents(day, statusPredicate);
        displayEvents(filteredMeetings);
    }

    public static void getMeetingsByPriorityAndTime() {
        int day = readDay();
        String priority = readPriority();
        System.out.print("Enter the beginning of meeting (in format HH:MM): ");
        LocalTime beginningTime = LocalTime.parse(userInputScanner.nextLine());
        Predicate<Wydarzenie> predicate = meeting -> 
            meeting instanceof Spotkanie && 
            ((Spotkanie) meeting).getPriority().equals(priority) &&
            (meeting.getBeginning().isAfter(beginningTime) || meeting.getBeginning().equals(beginningTime));
        var filterEvents = calendar.filterEvents(day, predicate);
        displayEvents(filterEvents);
    }

    public static void getTasksByStatusAndTime(){
        int day = readDay();
        String status = readStatus();
        System.out.print("Enter the end time of meeting (in format HH:MM): ");
        LocalTime endTime = LocalTime.parse(userInputScanner.nextLine());
        Predicate<Wydarzenie> predicate = task ->
            task instanceof Zadanie && 
            ((Zadanie) task).getStatus().equals(status) && 
            (task.getEnd().isBefore(endTime) || task.getEnd().equals(endTime));
        var filteredEvents = calendar.filterEvents(day, predicate);
        displayEvents(filteredEvents);
        }


}
