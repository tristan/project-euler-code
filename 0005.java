public class _0005 {
    
    public static long gcd(long a, long b) {
	if (b == 0)
	    return a;
	return gcd(b, a % b);
    }
    public static long lcm(long a, long b) {
	return (a * b) / gcd(a, b);
    }

    public static void main(String[] argv) {
	long val = 1;
	for (int i = 2; i < 21; i++) {
	    val = lcm(val, i);
	}
	System.out.println(val);
    }
}