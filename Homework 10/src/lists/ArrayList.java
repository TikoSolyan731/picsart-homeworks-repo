package lists;


public class ArrayList<T> implements List<T> {
    private Object[] data;
    private static final int CAPACITY = 10;
    private int size;

    public ArrayList() {
        this(CAPACITY);
    }

    public ArrayList(int capacity) {
        data = new Object[capacity];
    }

    public ArrayList(T... values) {
        this();
        for (int i = 0; i < values.length; i++) {
            add(values[i]);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(T value) {
        if (size == data.length)
            growArray();

        data[size] = value;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int i) {
        checkIndex(i);

        T temp = (T) data[i];
        for (int j = i; j < size - 1; j++)
            data[j] = data[j + 1];
        data[size - 1] = null;

        size--;
        return temp;
    }

    @Override
    public boolean remove(T obj) {
        for (int i = 0; i < size; i++) {
            if (areEqual(data[i], obj)) {
                for (int j = i; j < size - 1; j++)
                    data[j] = data[j + 1];

                data[size - 1] = null;
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int i) {
        checkIndex(i);
        return (T) data[i];
    }

    private void checkIndex(int i) {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException("Index is out of bounds.");
    }

    private void growArray() {
        Object[] newArray = new Object[size * 2];
        System.arraycopy(data, 0, newArray, 0, size);
        data = newArray;
    }

    private boolean areEqual(Object o1, Object o2) {
        if (o1 != null)
            return o1.equals(o2);
        else
            return o2 == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]).append(", ");
        }
        if (!isEmpty())
            sb.append(data[size - 1]);
        sb.append("]");

        return sb.toString();
    }
}
