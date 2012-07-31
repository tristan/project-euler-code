public class _0001 {

    public Object fn0() {
        int sum = 0;
        for (int i = 0; i < 1000; i++)
            if (i % 3 == 0 || i % 5 == 0)
                sum += i;
        return sum;
    }

    public String[] solutions = new String[]{"fn0"};
}
