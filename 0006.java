public class _0006 {

    public Object fn0() {
        int i;
        long sumofsq = 0;
        long sum = 0;
        for (i = 1; i < 101; i++) {
            sumofsq += (i * i);
            sum += i;
        }
        return sum * sum - sumofsq;
    }

    public String[] solutions = new String[]{"fn0"};
}
