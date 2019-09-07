package oefentoets;

import java.util.Arrays;

public class BinarySearchTree<T extends Comparable<T>> {
    static class BinarySearchTreeNode<E>{
        private BinarySearchTreeNode<E> left;
        private BinarySearchTreeNode<E> right;
        private int length;
        private E data;

        public BinarySearchTreeNode(E data) {
            this.data=data;
        }
        boolean leftIsEmpty(){
            return left == null;
        }
        boolean rightIsEmpty(){
            return right == null;
        }
        public boolean dataIsEmpty() {
            return data == null;
        }

        public int getLength() {
            return length;
        }
    }
         BinarySearchTreeNode<T> root;
    public boolean isEmpty() {
        return root == null;
    }
     void insert(BinarySearchTreeNode<T> node, T value){
            if(isEmpty()){
                root= new BinarySearchTreeNode<T>(value);
            }
            else if(value.compareTo(node.data) < 0){
                if(node.leftIsEmpty()){
                    node.left=new BinarySearchTreeNode<T>(value);
                }else{
                    insert(node.left,value);
                }
            }
                else if(value.compareTo(node.data) > 0) {
                if (node.rightIsEmpty()) {
                    node.right = new BinarySearchTreeNode<T>(value);
                }else{
                    insert(node.right,value);
                }
            }
    }

    private int heightNode(BinarySearchTreeNode<T> node) {
        return 0;
    }

    String toString(BinarySearchTreeNode<T> node, String placement){
        System.out.println(placement + node.data +" length: " + node.getLength());
        if(!node.leftIsEmpty())
        return toString(node.left, "left leaf: ");
        if(!node.rightIsEmpty())
        return toString(node.right,"right leaf: ");
        return null;
    }

    public static <T> BinarySearchTree fromSortedArray(T[] input,int start ,int end){
        if (start>=end) {
            return null;
        }
        int mid= (start+end)/2;
        BinarySearchTree bst = new BinarySearchTree();
        T value = input[mid];
        T left = input[mid-1];
        T right = input[mid+1];

        if(bst.isEmpty()){
            bst.root= new BinarySearchTreeNode<T>(value);
        }
        bst.root.data=value;
        if(bst.root.leftIsEmpty()) {
            bst.root.left = new BinarySearchTreeNode<T>(left);
            fromSortedArray(input, start, mid-1);
        }
        if(bst.root.rightIsEmpty()){
            bst.root.right= new BinarySearchTreeNode<T>(right);
            fromSortedArray(input, mid + 1, end);
        }
        return bst;
    }

}
