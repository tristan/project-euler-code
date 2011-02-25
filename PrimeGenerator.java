public class PrimeGenerator implements java.util.Collection<Integer> {
    // TODO: make this an implementation off List

    private class PrimeIterator implements java.util.Iterator<Integer> {
	private final Integer[] wheel = {2, 4, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 4, 2, 10, 2, 10};
	private Integer[] mem = {2,3,5,7,11};
	private Integer wheelpos = 0;
	private Integer p = -1;
	private Integer n = 11;

	private boolean testPrime() {
	    for (int i = 0; mem[i] * mem[i] <= n; i++) {
		if (n % mem[i] == 0)
		    return false;
	    }
	    return true;
	}

	public boolean hasNext() {
	    return true;
	}
	public Integer next() {
	    System.out.println("called next()");
	    p++;
	    if (p < mem.length) {
		return mem[p];
	    } else {
		while (true) {
		    n += wheel[wheelpos];
		    wheelpos = (wheelpos + 1) % wheel.length;
		    if (testPrime()) {
			Integer[] newarray = new Integer[mem.length+1];
			System.arraycopy(mem, 0, newarray, 0, mem.length);
			newarray[mem.length] = n;
			mem = newarray;
			return n;
		    }
		}
	    }
	}
	public void remove() {
	}
    }

    public boolean add(Integer e) {
	throw new UnsupportedOperationException();
    }

    public boolean addAll(java.util.Collection<? extends Integer> c) {
	throw new UnsupportedOperationException();
    }

    public void clear() {
	throw new UnsupportedOperationException();
    }

    public boolean contains(Object o) {
	if (!(o instanceof Integer)) {
	    throw new ClassCastException();
	}
	return false;
    }

    public boolean containsAll(java.util.Collection<?> c) {
	//	if (!(c instanceof java.util.Collection<Integer>)) {
	//throw new ClassCastException();
	    //}
	return false;
    }

    public java.util.Iterator<Integer> iterator() {
	return new PrimeIterator();
    }

    public boolean isEmpty() {
	return false;
    }

    public boolean remove(Object e) {
	throw new UnsupportedOperationException();
    }

    public boolean removeAll(java.util.Collection<?> c) {
	throw new UnsupportedOperationException();
    }

    public boolean retainAll(java.util.Collection<?> c) {
	throw new UnsupportedOperationException();
    }

    public int size() {
	return Integer.MAX_VALUE;
    }

    public Integer[] toArray() {
	Integer[] a = new Integer[size()];
	toArray(a);
	return a;
    }
    public <Integer>Integer[] toArray(Integer[] a) {
	int size = size();
	Integer[] r = a.length >= size 
	    ? a 
	    : (Integer[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
	java.util.Iterator it = iterator();
	for (int i = 0; i < size(); i++) {
	    r[i] = (Integer)it.next();
	}
	return r;
    }
}