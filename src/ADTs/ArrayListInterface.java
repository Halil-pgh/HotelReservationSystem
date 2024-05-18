package ADTs;

public interface ArrayListInterface<T> {
	public void add(T item);
	public T remove(int index);
	
	public T get(int index);
	public int size();
	public boolean isEmpty();
	public void clear();
	
	public T[] toArray();
	public void fromArray(T[] array);
}
