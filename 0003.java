public class _0003 {
    public Object fn0() {
        long number = 600851475143L;
        int divisor = 2;
        while (number > 1) {
            if (0 == (number % divisor)) {
                number /= divisor;
            } else {
                divisor++;
            }
        }
        return divisor;
    }

    public String[] solutions = new String[]{ "fn0" };
}
