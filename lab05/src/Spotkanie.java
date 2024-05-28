import java.time.LocalTime;

public class Spotkanie {
    public static final LocalTime EARLIEST = LocalTime.of(7, 30);

    private String description;
    private LocalTime beginning;
    private LocalTime end;
    private String priority;

    public Spotkanie(String description, LocalTime beginning, LocalTime end, String priority) {
        this.beginning = beginning;
        this.description = description;
        this.beginning = beginning;
        this.end = end;
        this.priority = priority;
    }

    public String getPriority(){
        return priority;
    }

    public LocalTime getBeginning() {
        return beginning;
    }

    public LocalTime getEnd(){
        return end;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Beginning: " + beginning + ", End: " + end + ", Priority: " + priority;
    }
}
