package ADTs;

public class Stack<T> implements StackInterface<T> {
    private T[] data;
    private static final int DEFAULT_CAPACITY = 10;
    private int size, capacity;

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = capacity;
        T[] temp = (T[]) new Object[capacity];
        data = temp;
        size = 0;
    }

    public void push(T item) {
        if (size == capacity) {
            resize();
        }
        data[size++] = item;
    }

    public T pop() {
        if (size == 0) {
            return null;
        }
        T item = data[--size];
        data[size] = null;
        return item;
    }

    public T peek() {
        if (size == 0) {
            return null;
        }
        return data[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] temp = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        return temp;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }
}
