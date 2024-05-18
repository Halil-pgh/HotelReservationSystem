package ADTs;

public interface QueueInterface<T> {
	public void enqueue(T item);
	public T dequeue();
	public T peek();
	
	public int size();
	public boolean isEmpty();
	public void clear();
	
	public T[] toArray();
}
