import java.time.LocalDate;

abstract public sealed class Lokal permits Dom, Mieszkanie {
    protected String ulica;
    protected int nrDomu;
    protected String miejscowość;
    protected String kodPocztowy;
    protected float powierzchnia;
    protected float cena;
    protected LocalDate data;

    public Lokal(String ulica, int nrDomu, String miejscowość, String kodPocztowy, float powierzchnia, float cena, LocalDate data){
        this.ulica = ulica;
        this.nrDomu = nrDomu;
        this.miejscowość = miejscowość;
        this.kodPocztowy = kodPocztowy;
        this.powierzchnia = powierzchnia;
        this.cena = cena;
        this.data = data;
    }

    public LocalDate getData(){
        return data;
    }

    public String getMiejscowość(){
        return miejscowość;
    }
    
    public float getPowierzchnia(){
        return powierzchnia;
    }

    public float getCena(){
        return cena;
    }

}
