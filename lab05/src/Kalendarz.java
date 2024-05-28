import java.util.ArrayList;
import java.util.function.Predicate;
import java.time.LocalTime;

public class Kalendarz {
    private ArrayList<ArrayList<Spotkanie>> meetings = new ArrayList<>();
    
    public Kalendarz() {
        for (int i = 0; i < 7; i++) {
            meetings.add(new ArrayList<>());
        }
    }

    public void addMeeting(int day, Spotkanie meeting){
        meetings.get(day).add(meeting);
    }

    public void addDefaultMondayMeetings() {
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
            Spotkanie meeting = new Spotkanie(description, startTime, endTime, priority);
            addMeeting(0, meeting);
            startTime = startTime.plusHours(1);
            endTime = endTime.plusHours(1);
        }
    }

    public ArrayList<Spotkanie> filterMeetings(int day, Predicate<Spotkanie> predicate) {
        ArrayList<Spotkanie> filteredMeetings = new ArrayList<>();
        for (Spotkanie meeting : meetings.get(day)) {
            if (predicate.test(meeting)) {
                filteredMeetings.add(meeting);
            }
        }
        return filteredMeetings;
    }

    public void cancelMeeting(int day, int index){
        meetings.get(day).remove(index);
    }

}
