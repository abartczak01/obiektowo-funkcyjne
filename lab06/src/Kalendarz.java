import java.util.ArrayList;
import java.util.function.Predicate;
import java.time.LocalTime;

public class Kalendarz {
    private ArrayList<ArrayList<Wydarzenie>> events = new ArrayList<>();
    
    public Kalendarz() {
        for (int i = 0; i < 7; i++) {
            events.add(new ArrayList<>());
        }
    }

    public void addEvent(int day, Wydarzenie event){
        events.get(day).add(event);
    }

    public void addDefaultMondayEvents() {
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(9, 0);
        String priority = "normal";
        for (int i = 0; i < 7; i++) {
            String description = "Meeting " + (i + 1);
            if ( i > 2 ) {
                priority = "high";
            }
            if ( i > 5) {
                priority = "the highest";
            }
            Spotkanie event = new Spotkanie(description, startTime, endTime, priority);
            addEvent(0, event);
            startTime = startTime.plusHours(1);
            endTime = endTime.plusHours(1);
        }

        String[] statuses = {"planned", "confirmed", "ongoing", "completed"};
        for (int i = 0; i < 7; i++) {
            String description = "Task " + (i + 1);
            String status = statuses[i % statuses.length];
            Zadanie task = new Zadanie(description, startTime, endTime, status);
            addEvent(0, task);
            startTime = startTime.plusHours(1);
            endTime = endTime.plusHours(1);
        }
    }

    public ArrayList<Wydarzenie> filterEvents(int day, Predicate<Wydarzenie> priorityPredicate) {
        ArrayList<Wydarzenie> filteredEvents = new ArrayList<>();
        for (Wydarzenie event : events.get(day)) {
            if (priorityPredicate.test(event)) {
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }

    public void removeEvent(int day, int index){
        events.get(day).remove(index);
    }
    
}
