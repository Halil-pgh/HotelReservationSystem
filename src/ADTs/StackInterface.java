package ADTs;

public interface StackInterface<T> {
	public void push(T item);
	public T pop();
	public T peek();
	
	public int size();
	public boolean isEmpty();
	
	public T[] toArray();
}
