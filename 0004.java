public class _0004 {
    public Object fn0() {
        int max = 0;
        for (int i = 100; i < 1000; i++) {
            for (int j = 100; j < 1000; j++) {
                String s = Integer.toString(i * j);
                for (int a = 0, b = s.length()-1; a < b; a++, b--) {
                    if (s.charAt(a) != s.charAt(b)) break;
                    if (a == b || a+1 >= b-1) {
                        if (i * j > max) {
                            max = i * j;
                        }
                    }
                }
            }
        }
        return max;
    }

    public String[] solutions = new String[]{ "fn0" };
}
