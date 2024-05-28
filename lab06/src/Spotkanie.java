import java.time.LocalTime;

final public class Spotkanie extends Wydarzenie {
    private String priority;

    public Spotkanie(String description, LocalTime beginning, LocalTime end, String priority) {
        super(description, end, beginning);
        this.priority = priority;
    }

    public String getPriority(){
        return priority;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Beginning: " + beginning + ", End: " + end + ", Priority: " + priority;
    }
}
