import java.time.LocalTime;

abstract public sealed class Wydarzenie permits Zadanie, Spotkanie {
    public static final LocalTime EARLIEST = LocalTime.of(7, 30);
    protected String description;
    protected LocalTime beginning;
    protected LocalTime end;

    public Wydarzenie(String description, LocalTime end, LocalTime beginning){
        this.description = description;
        this.beginning = beginning;
        this.end = end;
    }

    public LocalTime getBeginning() {
        return beginning;
    }

    public LocalTime getEnd(){
        return end;
    }

}
