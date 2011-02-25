public class _0008 {
    public static void main(String[] argv) {
	java.util.Collection<Integer> it = 
	    (java.util.Collection<Integer>)new PrimeGenerator();
	java.util.LinkedList<Integer> ll = new java.util.LinkedList<Integer>(it);
	for (int i = 0; i < 1000; i++) {
	    System.out.println(ll.get(i));
	}
    }
}