package lists;

public class SinglyLinkedList<T> implements List<T> {
    private static class Node<T> {
        public T value;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private int size;

    public SinglyLinkedList(T... values) {
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
        if (head == null) {
            head = new Node<>(value);
            size++;
            return;
        }
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node<>(value);
        size++;
    }

    @Override
    public T remove(int i) {
        checkIndex(i);
        if (i == 0) {
            T temp = head.value;
            head = head.next;
            return temp;
        }
        Node<T> current = head;
        for (int j = 0; j < i - 1; j++) {
            current = current.next;
        }
        T temp = current.next.value;

        current.next = current.next.next;
        size--;
        return temp;
    }

    @Override
    public boolean remove(T obj) {
        if (head == null)
            return false;

        if (head.value.equals(obj)) {
            head = head.next;
            size--;
            return true;
        }

        Node<T> current = head;
        Node<T> prev;
        while (current.next != null) {
            prev = current;
            current = current.next;
            if (current.value.equals(obj)) {
                prev.next = current.next;
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public T get(int i) {
        checkIndex(i);
        Node<T> current = head;
        for (int j = 0; j < i; j++) {
            current = current.next;
        }
        return current.value;
    }

    private void checkIndex(int i) {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException("Index is out of bounds.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value).append(" -> ");
            current = current.next;
        }
        sb.append("end");

        return sb.toString();
    }
}
