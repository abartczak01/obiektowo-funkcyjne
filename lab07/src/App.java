import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class App {
    private static Scanner userInputScanner = new Scanner(System.in);
    private static ListaOfert listaOfert = new ListaOfert();

    public static void main(String[] args) throws Exception {
        listaOfert.dodajPrzykladoweOferty();
        boolean running = true;
        while (running) {
            String input = pokażMenu();
            System.out.println("");
            switch (input) {
                case "0" -> running = false;
                case "1" -> dodajDom();
                case "2" -> dodajMieszkanie();
                case "3" -> aktualneDomy();
                case "4" -> aktualneMieszkania();
                case "5" -> filtrowaneDomy();
                case "6" -> filtrowaneMieszkania();
                default -> System.out.println("Niewłaściwa opcja. Spróbuj ponownie.");
            }
        }
    }

    public static String pokażMenu() {
        System.out.println("Wybierz opcję:");
        System.out.println("0. Wyjście");
        System.out.println("1. Dodaj ofertę sprzedaży domu");
        System.out.println("2. Dodaj ofertę sprzedaży mieszkania");
        System.out.println("3. Wyświetl aktualne oferty domów");
        System.out.println("4. Wyświetl aktualne oferty mieszkań");
        System.out.println("5. Filtruj oferty domów po miejscowości, powierzchni i wartości");
        System.out.println("6. Filtruj oferty mieszkań po miejscowości, wartości i piętrze");      

        System.out.print("Wprowadź liczbę: ");
        return userInputScanner.nextLine();
    }

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return userInputScanner.nextLine();
    }

    public static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = userInputScanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Niewłaściwy format liczby. Spróbuj ponownie.");
            }
        }
    }

    public static float getFloatInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = userInputScanner.nextLine();
            try {
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.println("Niewłaściwy format liczby zmiennoprzecinkowej. Spróbuj ponownie.");
            }
        }
    }

    public static LocalDate getDateInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = userInputScanner.nextLine();
            try {
                LocalDate date = LocalDate.parse(input);
                if (!date.isBefore(LocalDate.now())) {
                    return date;
                } else {
                    System.out.println("Data musi być równa aktualnej dacie lub późniejsza. Spróbuj ponownie.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Niewłaściwy format daty. Spróbuj ponownie.");
            }
        }
    }

    public static String getPostalCodeInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = userInputScanner.nextLine();
            if (isValidPostalCode(input)) {
                return input;
            } else {
                System.out.println("Niewłaściwy format kodu pocztowego. Spróbuj ponownie.");
            }
        }
    }

    public static boolean isValidPostalCode(String postalCode) {
        String postalCodePattern = "\\d{2}-\\d{3}";
        return Pattern.matches(postalCodePattern, postalCode);
    }

    public static void dodajDom() {
        String ulica = getStringInput("Podaj ulicę: ");
        int nrDomu = getIntInput("Podaj numer domu: ");
        String miejscowość = getStringInput("Podaj miejscowość: ");
        String kodPocztowy = getPostalCodeInput("Podaj kod pocztowy (00-000): ");
        float powierzchnia = getFloatInput("Podaj powierzchnię (m2): ");
        float cena = getFloatInput("Podaj cenę: ");
        LocalDate data = getDateInput("Podaj datę (yyyy-mm-dd): ");
        float powierzchniaDziałki = getFloatInput("Podaj powierzchnię działki (m2): ");

        listaOfert.dodajOfertę(ulica, nrDomu, miejscowość, kodPocztowy, powierzchnia, cena, data, powierzchniaDziałki);
        System.out.println("Oferta domu została dodana.");
    }

    public static void dodajMieszkanie() {
        String ulica = getStringInput("Podaj ulicę: ");
        int nrDomu = getIntInput("Podaj numer domu: ");
        String miejscowość = getStringInput("Podaj miejscowość: ");
        String kodPocztowy = getPostalCodeInput("Podaj kod pocztowy (00-000): ");
        float powierzchnia = getFloatInput("Podaj powierzchnię (m2): ");
        float cena = getFloatInput("Podaj cenę: ");
        LocalDate data = getDateInput("Podaj datę (yyyy-mm-dd): ");
        int nrMieszkania = getIntInput("Podaj numer mieszkania: ");
        int nrPiętra = getIntInput("Podaj numer piętra: ");

        listaOfert.dodajOfertę(ulica, nrDomu, miejscowość, kodPocztowy, powierzchnia, cena, data, nrMieszkania, nrPiętra);
        System.out.println("Oferta mieszkania została dodana.");
    }

    public static void wyświetlOferty(ArrayList<Lokal> oferty){
        for (int i = 0; i < oferty.size(); i++) {
            System.out.println(i + ". " + oferty.get(i).toString());
        }
    }

    public static void aktualneDomy(){
        Predicate<Lokal> lokalPredykat = lokal -> lokal instanceof Dom && !lokal.getData().isBefore(LocalDate.now());
        var wybraneOferty = listaOfert.filtrujOferty(lokalPredykat);
        wyświetlOferty(wybraneOferty);
    }


    public static void aktualneMieszkania(){
        Predicate<Lokal> lokalPredykat = lokal -> lokal instanceof Mieszkanie && !lokal.getData().isBefore(LocalDate.now());
        var wybraneOferty = listaOfert.filtrujOferty(lokalPredykat);
        wyświetlOferty(wybraneOferty);
    }

    public static void filtrowaneDomy(){
        String miejscowość = getStringInput("Podaj miejscowość: ");
        float powierzchnia = getFloatInput("Podaj powierzchnię (m2): ");

        Predicate<Lokal> lokalPredykat = lokal -> 
            lokal instanceof Dom && 
            !lokal.getData().isBefore(LocalDate.now()) &&
            lokal.getMiejscowość().equals(miejscowość) &&
            lokal.getPowierzchnia() >= powierzchnia;
        var wybraneOferty = listaOfert.filtrujOferty(lokalPredykat);
        wyświetlOferty(wybraneOferty);
    }

    public static void filtrowaneMieszkania(){
        String miejscowość = getStringInput("Podaj miejscowość: ");
        float cena = getFloatInput("Podaj cenę: ");
        int nrPiętra = getIntInput("Podaj numer piętra: ");
        Predicate<Lokal> lokalPredykat = lokal -> 
            lokal instanceof Mieszkanie && 
            !lokal.getData().isBefore(LocalDate.now()) &&
            lokal.getMiejscowość().equals(miejscowość) &&
            lokal.getCena() <= cena &&
            ((Mieszkanie) lokal).getPiętro() >= nrPiętra;

        var wybraneOferty = listaOfert.filtrujOferty(lokalPredykat);
        wyświetlOferty(wybraneOferty);
    }
}
