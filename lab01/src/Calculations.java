public class Calculations {
    public static int findFactorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static int findSum(int a, int b) {
        int result = a;
        for (int i = a + 1; i <= b; i++) {
            result += i;
        }
        return result;
    }

}
