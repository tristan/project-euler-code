public class _0005 {
    
    public long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
    public long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    public Object fn0() {
        long val = 1;
        for (int i = 2; i < 21; i++) {
            val = lcm(val, i);
        }
        return val;
    }

    public String[] solutions = new String[]{ "fn0" };
}
