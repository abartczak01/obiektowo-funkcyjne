import java.time.LocalDate;

final public class Mieszkanie extends Lokal{
    private int nrMieszkania;
    private int nrPiętra;

    public Mieszkanie(String ulica, int nrDomu, String miejscowość, String kodPocztowy, float powierzchnia, float cena, LocalDate data, int nrMieszkania, int nrPiętra){
        super(ulica, nrDomu, miejscowość, kodPocztowy, powierzchnia, cena, data);
        this.nrMieszkania = nrMieszkania;
        this.nrPiętra = nrPiętra;
    }

    public int getPiętro(){
        return nrPiętra;
    }

    @Override
    public String toString(){
        return "Adres: ul." + ulica + " " + nrDomu+ "/" + nrMieszkania + ", nr piętra: " + nrPiętra + ", " + kodPocztowy + ", " + miejscowość
            + "\n powierzchnia: "+ powierzchnia+  ", cena: " + cena + ", data: " + data;  
    }
}
