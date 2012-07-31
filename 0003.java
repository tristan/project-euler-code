public class _0003 {
    public static void main(String[] argv) {
	long number = 600851475143L;
	int divisor = 2;
	while (number > 1) {
	    if (0 == (number % divisor)) {
		number /= divisor;
	    } else {
		divisor++;
	    }
	}
	System.out.println(divisor);
    }
}