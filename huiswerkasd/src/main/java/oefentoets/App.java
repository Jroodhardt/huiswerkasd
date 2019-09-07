package oefentoets;

public class App {
    public static void main(String [ ] args) {
        Integer[] intArray = new Integer[] {1,3,8,10,19,22,32};
        BinarySearchTree.fromSortedArray(intArray,0,intArray.length);
        int i=0;
    }
}
