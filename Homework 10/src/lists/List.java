package lists;

public interface List<T> {
    int size();

    boolean isEmpty();

    void add(T value);

    T remove(int i);

    boolean remove(T obj);

    T get(int i);
}
