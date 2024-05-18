package ADTs;

public class ArrayList<T> implements ListInterface<T> {
    private T[] list;
    private int entryCount;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 100;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        this.list = (T[]) new Object[capacity];
        this.entryCount = 0;
        this.size = capacity;
    }

    @Override
    public void add(T newEntry) {
        ensureCapacity();
        list[entryCount] = newEntry;
        entryCount++;
    }

    @Override
    public void add(int position, T newEntry) { // position => 0 indexed
        if(position >= 0 && position < entryCount) {
            makeGap(position);
            list[position] = newEntry;
            entryCount++;
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public T remove(int givenIndex) {
        if(givenIndex >= 0 &&  givenIndex < entryCount) {
            assert !isEmpty();
            T removedEntry = list[givenIndex];
            removeGap(givenIndex);
            entryCount--;
            return removedEntry;
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public void clear() {
        list = (T[]) new Object[entryCount];
        entryCount = 0;
    }

    @Override
    public T replace(int givenIndex, T newEntry) {
        if(givenIndex >= 0 && givenIndex < entryCount) {
            T oldEntry = list[givenIndex];
            list[givenIndex] = newEntry;
            return oldEntry;
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public T getEntry(int givenIndex) {
        if(givenIndex >= 0 && givenIndex < entryCount) {
            return list[givenIndex];
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public T[] toArray() {
        T[] arr = (T[]) new Object[entryCount];
        for(int i = 0; i < entryCount; i++) {
            arr[i] = list[i];
        }
        return arr;
    }

    @Override
    public int contains(T entry) {
        // it is better to return index if contains.
        for(int i = 0; i < entryCount; i++) {
            if(list[i].equals(entry)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getEntryCount() {
        return entryCount;
    }

    @Override
    public boolean isEmpty() {
        return entryCount == 0;
    }

    private void ensureCapacity() {
        if(isFull()) {
            // find a solution for this!!!
            if(size * 2 > MAX_CAPACITY) {
                System.out.println("Max Capacity Exceed.");
                return;
            }
            T[] newList = (T[]) new Object[size * 2];
            for(int i = 0; i < entryCount; i++) {
               newList[i] = list[i];
            }
            list = newList;
        }
    }

    private boolean isFull() {
        return entryCount == this.size;
    }

    private void makeGap(int position) {
        ensureCapacity();
        for(int i = entryCount - 1; i > position; i--) {
            list[i + 1] = list[i];
        }
    }

    private void removeGap(int givenIndex) {
        for(int i = givenIndex + 1; i < entryCount; i++) {
            list[i - 1] = list[i];
        }
        list[entryCount - 1] = null;
    }
}
