import Trees.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.add(6);
        bst.add(2);
        bst.add(7);
        bst.add(3);
        bst.add(8);

        bst.inOrder();
        bst.preOrder();
        bst.postOrder();

        bst.breadthFirst();

        System.out.println(bst.getSize());
        System.out.println(bst.root());
        System.out.println(bst.contains(2));
        bst.delete(6);

        bst.inOrder();
    }
}
