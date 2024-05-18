package ADTs;

public class ArrayList<T> implements ArrayListInterface<T> {

        private T[] data;
        private static final int DEFAULT_CAPACITY = 10;
        private int size, capacity;

        public ArrayList() {
            this(DEFAULT_CAPACITY);
        }

        @SuppressWarnings("unchecked")
        public ArrayList(int capacity) {
            if (capacity < 0) {
                throw new IllegalArgumentException("Capacity must be greater than 0");
            }
            this.capacity = capacity;
            T[] temp = (T[]) new Object[capacity];
            data = temp;
            size = 0;
        }

        public void add(T item) {
            if (size == capacity) {
                resize();
            }
            data[size++] = item;
        }

        public T remove(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            T item = data[index];
            for (int i = index; i < size - 1; i++) {
                data[i] = data[i + 1];
            }
            data[--size] = null;
            return item;
        }

        public T get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            return data[index];
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

        @SuppressWarnings("unchecked")
        public void fromArray(T[] array) {
            if (array.length > capacity) {
                capacity = array.length;
                T[] temp = (T[]) new Object[capacity];
                data = temp;
            }
            for (int i = 0; i < array.length; i++) {
                data[i] = array[i];
            }
            size = array.length;
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
