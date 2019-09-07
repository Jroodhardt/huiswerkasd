package Trees;

public class BinaryNode<T extends Comparable<T>> {
    T key;
    int height;
    BinaryNode<T> left;
    BinaryNode<T> right;

    public String toString() {
        return "treenode{" + "data=" + key + "}";
    }

    public T getKey() {
        return key;
    }

    public T findMax() {
        if (left != null) {
            if (left.key.compareTo(key) > 0) {
                return left.findMax();
            }
        }
        if (right != null) {
            if (right.key.compareTo(key) > 0) {
                return right.findMax();
            }
        }
            return key;

    }
}
