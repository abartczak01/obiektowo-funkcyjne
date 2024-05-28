import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

public class ListaOfert {
    private ArrayList<Lokal> oferty = new ArrayList<>();

    // dla domu
    public void dodajOfertę(String ulica, int nrDomu, String miejscowość, String kodPocztowy, float powierzchnia, float cena, LocalDate data, float powierzchniaDzialki) {
        Dom dom = new Dom(ulica, nrDomu, miejscowość, kodPocztowy, powierzchnia, cena, data, powierzchniaDzialki);
        oferty.add(dom);
    }

    // dla mieszkania
    public void dodajOfertę(String ulica, int nrDomu, String miejscowość, String kodPocztowy, float powierzchnia, float cena, LocalDate data, int nrMieszkania, int nrPiętra) {
        Mieszkanie mieszkanie = new Mieszkanie(ulica, nrDomu, miejscowość, kodPocztowy, powierzchnia, cena, data, nrMieszkania, nrPiętra);
        oferty.add(mieszkanie);
    }

    public ArrayList<Lokal> filtrujOferty(Predicate<Lokal> predicate) {
        ArrayList<Lokal> wybraneOferty = new ArrayList<>();
        for (Lokal oferta : oferty) {
            if (predicate.test(oferta)) {
                wybraneOferty.add(oferta);
            }
        }
        return wybraneOferty;
    }

    public void dodajPrzykladoweOferty() {
        dodajOfertę("Mickiewicza", 10, "Warszawa", "00-001", 50.0f, 300000.0f, LocalDate.of(2024, 5, 28), 1, 3);
        dodajOfertę("Krucza", 15, "Kraków", "30-002", 70.0f, 450000.0f, LocalDate.of(2024, 8, 10), 2, 2);
        dodajOfertę("Krucza", 16, "Kraków", "30-002", 70.0f, 460000.0f, LocalDate.of(2024, 8, 10), 2, 2);
        dodajOfertę("Moniuszki", 27, "Kraków", "30-002", 70.0f, 450000.0f, LocalDate.of(2024, 9, 11), 1, 4);
        dodajOfertę("Grunwaldzka", 5, "Gdańsk", "80-003", 55.0f, 350000.0f, LocalDate.of(2024, 11, 30), 3, 4);
        dodajOfertę("Świętokrzyska", 20, "Poznań", "60-004", 65.0f, 400000.0f, LocalDate.of(2024, 7, 15), 4, 1);
        dodajOfertę("Mickiewicza", 11, "Warszawa", "00-001", 30.0f, 200000.0f, LocalDate.of(2024, 5, 30), 1, 2);
       
        dodajOfertę("Solikowskiego", 3, "Szczecin", "70-008", 110.0f, 790000.0f, LocalDate.of(2024, 5, 29), 550.0f);
        dodajOfertę("Długa", 12, "Szczecin", "50-005", 120.0f, 600000.0f, LocalDate.of(2024, 7, 25), 500.0f);
        dodajOfertę("Krótka", 8, "Szczecin", "90-006", 150.0f, 800000.0f, LocalDate.of(2024, 9, 5), 600.0f);
        dodajOfertę("Leśna", 3, "Katowice", "40-007", 110.0f, 550000.0f, LocalDate.of(2024, 12, 12), 450.0f);
        dodajOfertę("Słoneczna", 7, "Szczecin", "70-008", 130.0f, 700000.0f, LocalDate.of(2024, 7, 20), 550.0f);
        
    }
}
