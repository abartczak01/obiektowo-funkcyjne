import java.time.LocalDate;

final public class Dom extends Lokal {
    private float powierzchniaDziałki;
    
    public Dom(String ulica, int nrDomu, String miejscowość, String kodPocztowy, float powierzchnia, float cena, LocalDate data, float powierzchniaDziałki){
        super(ulica, nrDomu, miejscowość, kodPocztowy, powierzchnia, cena, data);
        this.powierzchniaDziałki = powierzchniaDziałki;
    }
    @Override
    public String toString(){
        return "Adres: ul." + ulica + " " + nrDomu + ", " + kodPocztowy + ", " + miejscowość
            + "\n powierzchnia: "+ powierzchnia + ", powierzchnia działki: " + powierzchniaDziałki + ", cena: " + cena + ", data: " + data;  
    }
}
