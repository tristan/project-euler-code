public class _0005 {
    public static void main(String[] argv) {
	int n = 20;
	int val = n*n-n;
	int i;
	boolean t = false;
	while(!t) {
	    t = true;
	    val+=n;
	    for (i = n; i > 1; --i) {
		if (val % i != 0) {
		    t = false;
		    break;
		}
	    }
	}
	System.out.println(val);
    }
}