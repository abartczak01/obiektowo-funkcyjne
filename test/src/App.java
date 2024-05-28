import java.time.*;
public class App {
    public static void fly(int numMiles) {
        System.out.println("int");
    }
    
    public static void fly(short numFeet) {
        System.out.println("short");
    }
    public static void main(String[] args) throws Exception {
        fly((short) 1);
        int a = 1;
        Integer b = a;

        System.out.println();
        // LocalDate ld = LocalDate.now();
        // LocalTime lt = LocalTime.now();
        // LocalDateTime ltd = LocalDateTime.now();
        // // System.out.println(ld);
        // // System.out.println(lt);
        // // System.out.println(ltd);
        // // System.out.println("==================================");

        // LocalDate ld1 = LocalDate.of(2001, 03, 9);
        // LocalDate ld2 = LocalDate.of(2005, 03, 8);
        // LocalTime lt1 = LocalTime.of(10, 15, 56, 890);
        // LocalDateTime ltd1 = LocalDateTime.of(ld1, lt1);
        // System.out.println(ld1);
        // System.out.println(ld1.withDayOfYear(239));
        // System.out.println(ld1.get());
        // System.out.println(lt1);
        // System.out.println(ltd1);
        // System.out.println(ltd1.plusHours(15));
    
        

    }

}
