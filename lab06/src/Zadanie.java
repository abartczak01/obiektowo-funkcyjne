import java.time.LocalTime;

final public class Zadanie extends Wydarzenie{
    private String status;

    public Zadanie(String description, LocalTime beginning, LocalTime end, String status){
        super(description, end, beginning);
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Beginning: " + beginning + ", End: " + end + ", Status: " + status;
    }

}
