package Trees;

import graphs.Node;

public class BinaryTree<T extends Comparable<T>> {
    private BinaryNode<T> root;


    public void addNode(T newnode) {
        BinaryNode<T> newBinaryNode = new BinaryNode<T>();
        newBinaryNode.key = newnode;
        if (root == null) {
            root = newBinaryNode;
        } else {
            BinaryNode<T> focusnode = root;
            BinaryNode<T> parent;
            while (true) {
                parent = focusnode;
                if (focusnode.key.equals(newnode)) {
                    focusnode = focusnode.left;
                    if (focusnode == null) {
                        parent.left = newBinaryNode;
                        return;
                    }
                } else {
                    focusnode = focusnode.right;
                    if (focusnode == null) {
                        parent.right = newBinaryNode;
                        return;
                    }
                }
            }
        }
    }

    public void inOrderTraverseTree(BinaryNode focusNode) {

        if (focusNode != null) {

            inOrderTraverseTree(focusNode.left);
            System.out.println(focusNode);
            inOrderTraverseTree(focusNode.right);
        }
    }

    public void preorderTraverseTree(BinaryNode focusNode) {

        if (focusNode != null) {
            System.out.println(focusNode);
            preorderTraverseTree(focusNode.left);
            preorderTraverseTree(focusNode.right);
        }
    }

    public void postOrderTraverseTree(BinaryNode focusNode) {

        if (focusNode != null) {
            postOrderTraverseTree(focusNode.left);
            postOrderTraverseTree(focusNode.right);
            System.out.println(focusNode);
        }
    }

    public BinaryNode findNode(T key) {

        BinaryNode focusNode = root;

        while (focusNode.key != key) {
            if (key.equals(focusNode.key)) {
                focusNode = focusNode.left;
            } else {
                focusNode = focusNode.right;
            }
            if (focusNode == null)
                return null;
        }
        return focusNode;
    }
//    public static <T extends Comparable<T>> BinaryTree<T> fromSortedArray(T[] input){
//        BinaryTree<T> btree=new BinaryTree<T>();
//
//        sortedArrayToBST(input, 0, input.length-1, btree);
//        return btree;
//
//    }
//
//
//    private void sortedArrayToBST(T arr, int start, int end, BinaryTree<T> bst) {
//
//        if( start == end){
//            bst.addNode(arr[start]);
//            return;
//        }
//        else if(start > end) {
//            return;
//        }
//        int middle = (start+end)/2;
//        bst.addNode(arr[middle]);
//        sortedArrayToBST(arr, start, middle - 1, bst);
//        sortedArrayToBST(arr, middle+1, end, bst);
//    }


    public int compareTo(T o) {
        return 0;
    }
}
