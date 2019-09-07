package Trees;

import java.util.Vector;

public class BST <T extends Comparable<T>> {

    class Node<T extends Comparable<T>> {
        Node<T> left;
        Node<T> right;
        int length;
        T data;

        void addNodesBalanced(T value) {

            if (data == null) {
                data = value;
                length=0;
                return;
            }

            if (data.compareTo(value) <= 0) {

                if (right == null) {
                    right = new Node<T>();
                    right.data = value;
                    right.length= length+1;
                } else {
                    right.addNodesBalanced(value);
                }
            } else if (data.compareTo(value) >= 0) {
                if (left == null) {
                    left = new Node<T>();
                    left.data = value;
                    left.length= length+1;

                } else {
                    left.addNodesBalanced(value);
                }
            }
        }

        Node buildTreeFromVector(Vector<Node<T>> input, int left, int right) {
            if (left > right)
                return null;

            int mid = (left + right) / 2;
            Node node = input.get(mid);

            node.left = buildTreeFromVector(input, left, mid - 1);
            node.right = buildTreeFromVector(input, mid + 1, right);

            return node;
        }

        Node buildTreeFromArray(T[] input, int left, int right) {
            if (left > right)
                return null;

            int mid = (left + right) / 2;
            Node node = new Node();
            addNodesBalanced(input[mid]);

            node.left = buildTreeFromArray(input, left, mid - 1);
            node.right = buildTreeFromArray(input, mid + 1, right);

            return node;
        }

        boolean checkIntegerBST(Node<Integer> root) {
            return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        boolean checkBST(Node<Integer> root, int min, int max) {
            if (root == null) {
                return true;
            }
            if (root.data < min || root.data > max) {
                return false;
            }
            return checkBST(root.left, min, root.data - 1) && checkBST(root.right, root.data + 1, max);
        }

        boolean contains(T value) {
            if (data.compareTo(value) == 0) {
                return true;
            } else if (data.compareTo(value) > 0) {
                if (left == null) {
                    return false;
                } else {
                    return left.contains(value);
                }
            } else {
                if (right == null) {
                    return false;
                } else {
                    return right.contains(value);
                }
            }
        }

        public void printinorder() {
            if (left != null) {
                left.printinorder();
            }
            System.out.print(data);
            if (right != null) {
                right.printinorder();
            }
        }

        public void printpreorder() {
            System.out.print(data);
            if (left != null) {
                left.printpreorder();
            }
            if (right != null) {
                right.printpreorder();
            }
        }

        public void printpostorder() {
            if (left != null) {
                left.printpreorder();
            }
            if (right != null) {
                right.printpreorder();
            }
            System.out.print(data);
        }

    }

    Node<T> root;

    public void insert(T value) {
        if (root == null) {
            root = new Node<T>();
        }
        root.addNodesBalanced(value);
    }
}
