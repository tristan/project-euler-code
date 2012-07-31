import java.lang.reflect.Method;

public class Benchmark {
    public static void main(String[] args) throws Exception{
        Object o = Benchmark.class.forName("_" + args[0]).newInstance();
        for (String soln: (String[])o.getClass().getField("solutions").get(o)) {
            Method m = o.getClass().getMethod(soln);
            long st = System.currentTimeMillis();
            Object res = m.invoke(o);
            long et = System.currentTimeMillis();
            if (res == null)
                return;
            System.out.println(res);
            System.out.println("Runtime: " + (et - st) + "ms");
        }
    }
}
