public class _0002 {
    public static void main(String[] argv) {
	int f1 = 1;
	int f2 = 2;
	int sum = 0;
	while (f2 < 4000000) {
	    if (f2 % 2 == 0)
		sum += f2;
	    f2 = f1^f2;
	    f1 = f1^f2;
	    f2 = f1 + (f1^f2);
	}
	System.out.println(sum);
    }
}