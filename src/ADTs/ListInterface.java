package ADTs;

public interface ListInterface<T> {
    public void add(T newEntry);
    public void add(int index, T newEntry);
    public T remove(int givenIndex);
    public void clear();
    public T replace(int givenIndex, T newEntry);
    public T getEntry(int givenIndex);
    public T[] toArray();
    public int contains(T entry);
    public int getEntryCount();
    public boolean isEmpty();
}
