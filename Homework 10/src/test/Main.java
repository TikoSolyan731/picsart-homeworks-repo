package test;

import lists.ArrayList;
import lists.SinglyLinkedList;
import stack.Stack;

public class Main {
    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>(2, 5, 48, 7, 6, 32, 0, 1, 5, 8, -5);
//
//        System.out.println(list);
//        System.out.println(list.size());
//        System.out.println();
//
//        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>(2, 4, 5, 87, 1, 0, 6);
//        System.out.println(linkedList);
//        System.out.println(linkedList.remove(Integer.valueOf(87)));
//        System.out.println(linkedList);
//        System.out.println();
//        Stack<Integer> stack = new Stack<>();
//
//        stack.push(5);
//        stack.push(2);
//        stack.push(1);
//        stack.push(3);
//        stack.push(4);
//        stack.push(12);
//        System.out.println(stack);
//        System.out.println(stack.peek());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack);

        String str = "{{{{[][][]()([}}}}";
        System.out.println(isBalanced(str));
    }

    static boolean isBalanced(String str) {
        Stack<Character> parentheses = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            switch (ch) {
                case '{':
                case '[':
                case '(':
                    parentheses.push(ch);
                    continue;
            }

            if (parentheses.isEmpty())
                return false;

            if (ch == '}') {
                if (parentheses.pop() != '{')
                    return false;
            } else if (ch == ']') {
                if (parentheses.pop() != '[')
                    return false;
            } else if (ch == ')') {
                if (parentheses.pop() != '(')
                    return false;
            }
        }
        return parentheses.isEmpty();
    }
}
