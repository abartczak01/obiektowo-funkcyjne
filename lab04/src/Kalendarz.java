import java.util.ArrayList;
import java.time.LocalTime;
// zaczynamy 10:10
// localtime, localdate - niezmienne, tak jak string
// przeciążanie metod, typy szersze
// operator rzutowanie na węższe z szerszych
// klasy abstrakcyjne, implementacja intefejsów
// wyrażenia lambda, konstrukcja, składnia, przykład
// interfejs predicate


// predicate przyjmuje obiekty klasy spotkanie
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

    public void cancelMeeting(int day, int index){
        meetings.get(day).remove(index);
    }

    public ArrayList<Spotkanie> getMeetingsByDay(int day){
        return meetings.get(day);
    }

    public ArrayList<Spotkanie> getMeetingsByPriority(int day, String priority){
        ArrayList<Spotkanie> meetingsByPriority = new ArrayList<>();
        for (Spotkanie meeting : meetings.get(day)) {
            if (meeting.getPriority().equals(priority)) {
                meetingsByPriority.add(meeting);
            }
        }

        return meetingsByPriority;
    }

    public ArrayList<Spotkanie> getMeetingsByTime(int day, LocalTime startTime){
        ArrayList<Spotkanie> meetingsByTime = new ArrayList<>();
        for (Spotkanie meeting : meetings.get(day)) {
            if (meeting.getBeginning().isAfter(startTime)) {
                meetingsByTime.add(meeting);
            }
        }
        return meetingsByTime;
    }
}
