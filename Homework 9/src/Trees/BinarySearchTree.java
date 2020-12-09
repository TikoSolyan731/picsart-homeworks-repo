package Trees;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this(value, null, null);
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    private int size;

    public BinarySearchTree() { }

    public int root() {
        return root.value;
    }

    public int getSize() {
        return size;
    }

    // Depth-First Traversals
    public void inOrder() {
        inOrderTraversalRecursive(root);
        System.out.println();
    }

    private void inOrderTraversalRecursive(Node node) {
        if (node != null) {
            inOrderTraversalRecursive(node.left);
            System.out.print(node.value + " ");
            inOrderTraversalRecursive(node.right);
        }
    }

    public void preOrder() {
        preOrderTraversalRecursive(root);
        System.out.println();
    }

    private void preOrderTraversalRecursive(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrderTraversalRecursive(node.left);
            preOrderTraversalRecursive(node.right);
        }
    }

    public void postOrder() {
        postOrderTraversalRecursive(root);
        System.out.println();
    }

    private void postOrderTraversalRecursive(Node node) {
        if (node != null) {
            postOrderTraversalRecursive(node.left);
            postOrderTraversalRecursive(node.right);
            System.out.print(node.value + " ");
        }
    }

    // Breadth-First Traversal
    public void breadthFirst() {
        if (root == null)
            return;

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node n = nodes.remove();

            System.out.print(n.value + " ");

            if (n.left != null)
                nodes.add(n.left);

            if (n.right != null)
                nodes.add(n.right);
        }

        System.out.println();
    }


    public void add(int value) {
        root = addRecursive(root, value);
        size++;
    }

    private Node addRecursive(Node current, int value) {
        if (current == null)
            return new Node(value);

        if (value < current.value)
            current.left = addRecursive(current.left, value);
        else if (value > current.value)
            current.right = addRecursive(current.right, value);
        else
            return current;

        return current;
    }

    public boolean contains(int value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node current, int value) {
        if (current == null)
            return false;
        if (current.value == value)
            return true;

        return value < current.value ? containsRecursive(current.left, value)
                : containsRecursive(current.right, value);
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null)
            return null;
        if (current.value == value) {
            // If current is a leaf node
            if (current.left == null && current.right == null) {
                return null;
            }

            // If current has one child
            if (current.left == null)
                return current.right;
            if (current.right == null)
                return current.left;

            // If current has two children
            int successor = findSuccessor(current.right);
            current.value = successor;
            current.right = deleteRecursive(current.right, successor);
            return current;
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSuccessor(Node current) {
        return current.left == null ? current.value : findSuccessor(current.left);
    }
}
