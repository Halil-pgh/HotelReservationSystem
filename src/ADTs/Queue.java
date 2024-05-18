package ADTs;

public class Queue<T> implements QueueInterface<T> {
    private T[] data;
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity, front, back;

    public Queue() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public Queue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = capacity;
        T[] temp = (T[]) new Object[capacity];
        data = temp;
        front = 0;
        back = 0;
    }

    public void enqueue(T item) {
        if ((back + 1) % capacity == front) {
            resize();
        }
        data[back++] = item;
        back %= capacity;
    }

    public T dequeue() {
        if (front == back) {
            throw new IllegalStateException("Queue is empty");
        }
        T item = data[front];
        data[front++] = null;
        front %= capacity;
        return item;
    }

    public T peek() {
        if (front == back) {
            throw new IllegalStateException("Queue is empty");
        }
        return data[front];
    }

    public int size() {
        return (back - front + capacity) % capacity;
    }

    public boolean isEmpty() {
        return front == back;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] temp = (T[]) new Object[size()];
        for (int i = 0; i < size(); i++) {
            temp[i] = data[(front + i) % capacity];
        }
        return temp;
    }

    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        T[] temp = (T[]) new Object[capacity];
        final int size = size();
        for (int i = 0; i < size; i++) {
            temp[i] = data[(front + i) % capacity];
        }
        data = temp;
        front = 0;
        back = size;
    }
}
