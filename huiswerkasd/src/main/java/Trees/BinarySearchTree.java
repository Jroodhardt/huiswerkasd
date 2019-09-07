package Trees;

public class BinarySearchTree {
    private BinaryNode<Integer> root;

    private BinaryNode<Integer> addNode(BinaryNode<Integer> node, Integer integer) {
        if (node == null) {
            node = new BinaryNode<Integer>();
            node.key = integer;
            return node;
        }
        if (integer.compareTo(node.key) < 0) {
            node.left = addNode(node.left, integer);
        } else if (integer.compareTo(root.key) > 0) {
            node.right = addNode(node.right, integer);
        }
        node.height = 1 + max(getHeight(node.left),
                getHeight(node.right));
        int balance = getBalance(node);
        if (balance > 1 && integer < node.left.key)
            return rightRotate(node);

        if (balance < -1 && integer > node.right.key)
            return leftRotate(node);

        if (balance > 1 && integer > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && integer < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public void insert(int key) {
        root = addNode(root, key);
    }

    private BinaryNode<Integer> rightRotate(BinaryNode<Integer> y) {
        BinaryNode<Integer> x = y.left;
        BinaryNode<Integer> T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        updateHeights(y, x);

        // Return new root
        return x;
    }

    private BinaryNode<Integer> leftRotate(BinaryNode<Integer> x) {
        BinaryNode<Integer> y = x.right;
        BinaryNode<Integer> T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeights(x, y);

        return y;
    }

    private void updateHeights(BinaryNode<Integer> a, BinaryNode<Integer> b) {
        a.height = max(getHeight(a.left), getHeight(a.right)) + 1;
        b.height = max(getHeight(b.left), getHeight(b.right)) + 1;
    }

    int getBalance(BinaryNode<Integer> n) {
        if (n == null)
            return 0;

        return getHeight(n.left) - getHeight(n.right);
    }

    int getHeight(BinaryNode<Integer> n) {
        if (n == null)
            return 0;
        return n.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public Integer findMax(){
        return root.findMax();
    }
}
