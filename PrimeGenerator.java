public class PrimeGenerator implements java.util.List<Integer> {
    // TODO: make this an implementation off List

    private final Integer[] _wheel = {2, 4, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 4, 2, 10, 2, 10};
    private Integer[] _mem = {2,3,5,7,11};
    private Integer _wheelpos = 0;
    private Integer _p = -1;
    private Integer _n = 11;

    private boolean testPrime(Integer[] mem, Integer n) {
	for (int i = 0; mem[i] * mem[i] <= n; i++) {
	    if (n % mem[i] == 0)
		return false;
	}
	return true;
    }

    private void generatePrimesTillIndex(int tillIndex) {
	if (tillIndex < _mem.length) {
	    return;
	} else {
	    Integer cur = _mem.length;
	    Integer[] newarray = new Integer[tillIndex+1];
	    System.arraycopy(_mem, 0, newarray, 0, _mem.length);
	    while (cur < tillIndex+1) {
		_n += _wheel[_wheelpos];
		_wheelpos = (_wheelpos + 1) % _wheel.length;
		if (testPrime(newarray, _n)) {
		    newarray[cur] = _n;
		    cur++;
		}
	    }
	    _mem = newarray;
	}
    }

    private void generatePrimesTillValue(int tillValue) {
	if (tillValue <= _mem[_mem.length-1]) {
	    return;
	} else {
	    while (_mem[_mem.length-1] < tillValue) {
		_n += _wheel[_wheelpos];
		_wheelpos = (_wheelpos + 1) % _wheel.length;
		if (testPrime(_mem, _n)) {
		    Integer[] newarray = new Integer[_mem.length+1];
		    System.arraycopy(_mem, 0, newarray, 0, _mem.length);
		    newarray[_mem.length] = _n;
		    _mem = newarray;
		}
	    }
	}
    }

    private class PrimeIterator implements java.util.ListIterator<Integer> {
	private Integer lastPos;
	public PrimeIterator() {
	    lastPos = -1;
	}
	public PrimeIterator(int pos) {
	    lastPos = pos;
	}
	public void add(Integer e) {
	    throw new UnsupportedOperationException();
	}
	public boolean hasPrevious() {
	    return lastPos < -1;
	}
	public boolean hasNext() {
	    return true;
	}
	public Integer next() {
	    lastPos++;
	    return PrimeGenerator.this.get(lastPos);
	}
	public int nextIndex() {
	    return lastPos+1;
	}
	public Integer previous() {
	    Integer r = PrimeGenerator.this.get(lastPos);
	    lastPos--;
	    return r;
	}
	public int previousIndex() {
	    return lastPos;
	}
	public void remove() {
	    throw new UnsupportedOperationException();
	}
	public void set(Integer element) {
	    throw new UnsupportedOperationException();
	}
    }

    public boolean add(Integer e) {
	throw new UnsupportedOperationException();
    }
    public void add(int index, Integer e) {
	throw new UnsupportedOperationException();
    }

    public boolean addAll(java.util.Collection<? extends Integer> c) {
	throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, java.util.Collection<? extends Integer> c) {
	throw new UnsupportedOperationException();
    }

    public void clear() {
	throw new UnsupportedOperationException();
    }

    public boolean contains(Object o) {
	if (!(o instanceof Integer)) {
	    throw new ClassCastException();
	}
	generatePrimesTillValue((Integer)o);
	return testPrime(_mem, (Integer)o);
    }

    public boolean containsAll(java.util.Collection<?> c) {
	for (Object o: c) {
	    if (!contains(o)) {
		return false;
	    }
	}
	return true;
    }

    public Integer get(int index) {
	generatePrimesTillIndex(index);
	return _mem[index];
    }

    public int indexOf(Object o) {
	if (!(o instanceof Integer)) {
	    throw new ClassCastException();
	}
	generatePrimesTillValue((Integer)o);
	if (!testPrime(_mem, (Integer)o)) {
	    return -1;
	}
	for (int i = 0; i < _mem.length; i++) {
	    if (_mem[i] == (Integer)o) {
		return i;
	    }
	}
	return -1;
    }

    public java.util.Iterator<Integer> iterator() {
	return new PrimeIterator();
    }

    public boolean isEmpty() {
	return false;
    }

    public int lastIndexOf(Object o) {
	return indexOf(o);
    }

    public java.util.ListIterator<Integer> listIterator() {
	return new PrimeIterator();
    }

    public java.util.ListIterator<Integer> listIterator(int index) {
	return new PrimeIterator(index);
    }

    public Integer remove(int index) {
	throw new UnsupportedOperationException();
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

    public Integer set(int index, Integer element) {
	throw new UnsupportedOperationException();
    }	

    public int size() {
	return Integer.MAX_VALUE;
    }

    public java.util.List<Integer> subList(int fromIndex, int toIndex) {
	java.util.List<Integer> sl = new java.util.LinkedList<Integer>();
	for (int i = fromIndex; i < toIndex; i++) {
	    sl.add(_mem[i]);
	}
	return sl;
    }

    public Integer[] toArray() {
	Integer[] a = new Integer[size()];
	toArray(a);
	return a;
    }

    public <Integer>Integer[] toArray(Integer[] a) {
	int size = size()-1;
	Integer[] r = a.length >= size 
	    ? a 
	    : (Integer[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
	java.util.Iterator it = iterator();
	for (int i = 0; i < size; i++) {
	    r[i] = (Integer)it.next();
	}
	return r;
    }
}