package stack;

import lists.ArrayList;

public class Stack<T> {
    private ArrayList<T> data;

    public Stack() {
        data = new ArrayList<>();
    }

    public Stack(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public void push(T value) {
        data.add(value);
    }

    public T peek() {
        return data.get(data.size() - 1);
    }

    public T pop() {
        return data.remove(data.size() - 1);
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
